/*
 * Copyright 2014 lorislab.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lorislab.tower.process.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.tower.activity.wrapper.ActivityType;
import org.lorislab.tower.bts.model.BtsCriteria;
import org.lorislab.tower.bts.model.BtsIssue;
import org.lorislab.tower.bts.model.BtsResult;
import org.lorislab.tower.scm.model.ScmCriteria;
import org.lorislab.tower.scm.model.ScmLog;
import org.lorislab.tower.scm.model.ScmResult;
import org.lorislab.tower.store.criteria.ApplicationCriteria;
import org.lorislab.tower.store.criteria.BuildCriteria;
import org.lorislab.tower.store.criteria.ProjectCriteria;
import org.lorislab.tower.store.model.Activity;
import org.lorislab.tower.store.model.ActivityChange;
import org.lorislab.tower.store.model.ActivityLog;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.BTSystem;
import org.lorislab.tower.store.model.Build;
import org.lorislab.tower.store.model.Project;
import org.lorislab.tower.store.model.SCMSystem;
import org.lorislab.tower.store.model.enums.ActivityChangeError;
import org.lorislab.tower.store.model.enums.ApplicationScmRepository;
import org.lorislab.tower.process.util.LinkUtil;
import org.lorislab.tower.store.ejb.ApplicationService;
import org.lorislab.tower.store.ejb.BuildService;
import org.lorislab.tower.store.ejb.ProjectService;

/**
 * The activity process service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ActivityProcessService {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(ActivityProcessService.class.getName());
    /**
     * The store application service.
     */
    @EJB
    private ApplicationService appService;
    /**
     * The store project service.
     */
    @EJB
    private ProjectService projectService;
    /**
     * The store build service.
     */
    @EJB
    private BuildService buildService;

    /**
     * The external system service.
     */
    @EJB
    private ExternalSystemService externalService;
    
    /**
     * Creates the store activity.
     *
     * @param build the build GUID.
     * @return the create activity.
     * @throws Exception if the method fails.
     */
    public Activity createActivity(String build) throws Exception {
        Build b = getBuildWithAppAndProject(build);
        Project pr = getProject(b.getApplication().getProject().getGuid());
        Application app = getApplication(b.getApplication().getGuid());
        return createActivity(b, pr, app);
    }

    public Activity createActivity(final Application app, final Build build) throws Exception {
        ApplicationCriteria criteria = new ApplicationCriteria();
        criteria.setGuid(app.getGuid());
        criteria.setFetchSCM(true);
        criteria.setFetchProject(true);
        criteria.setFetchProjectBts(true);
        Application application = appService.getApplication(criteria);
        return createActivity(build, application.getProject(), application);
    }

    /**
     * Creates the activity for the application build in the project.
     *
     * @param build the build.
     * @param project the project.
     * @param application the application.
     * @return the activity.
     * @throws Exception if the method fails.
     */
    private Activity createActivity(Build build, Project project, Application application) throws Exception {

        // create activity
        Activity result = new Activity();
        result.setChanges(new HashSet<ActivityChange>());
        result.setBuild(build);
        result.setDate(new Date());

        // create changes
        BtsResult btsResult = getIssues(project, build);
        Map<String, ActivityChange> changes = new HashMap<>();
        if (btsResult != null && !btsResult.isEmpty()) {
            for (BtsIssue issue : btsResult.getIssues()) {
                // create change
                ActivityChange change = new ActivityChange();
                // add the change to the activity
                change.setActivity(result);
                result.getChanges().add(change);
                // create change
                change.setDescription(issue.getSummary());
                change.setKey(issue.getId());
                change.setStatus(issue.getResolution());
                change.setType(issue.getType());
                change.setUser(issue.getAssignee());
                change.setParent(issue.getParent());
                change.setLogs(new HashSet<ActivityLog>());
                // add the change to the help map
                changes.put(change.getKey(), change);
            }
        }

        // load builds for the version
        List<Build> builds = getBuilds(application, build);

        // the issue in the commits but not in the bts
        Set<String> errors = new HashSet<>();

        // create logs        
        Pattern pattern = getKeyPattern(project);
        ScmResult scmResult = getCommits(application, build);
        if (scmResult != null && !scmResult.isEmpty()) {

            // create build information
            List<BuildInfo> buildInfos = new ArrayList<>();

            if (builds != null && !builds.isEmpty()) {
                builds.add(build);
                for (Build b : builds) {
                    ScmLog log = scmResult.getScmLog(b.getScm());
                    if (log != null) {
                        BuildInfo bi = new BuildInfo();
                        bi.build = b;
                        bi.date = log.getDate();
                        buildInfos.add(bi);
                    } else {
                        LOGGER.log(Level.WARNING, "The store build {0} has wrong revision number {1}.", new Object[]{b.getGuid(), b.getScm()});
                    }
                }
                Collections.sort(buildInfos, BuildInfoComparator.INSTANCE);
            }

            // load commits
            for (ScmLog commit : scmResult.getScmLogs()) {
                Matcher matcher = pattern.matcher(commit.getMessage());
                while (matcher.find()) {
                    ActivityLog log = new ActivityLog();
                    log.setDate(commit.getDate());
                    log.setRevision(commit.getId());
                    log.setUser(commit.getUser());
                    log.setMessage(commit.getMessage());

                    // set the build to the log.
                    Build logBuild = findBuild(buildInfos, commit.getDate());
                    log.setBuild(logBuild);

                    String key = matcher.group();
                    ActivityChange change = changes.get(key);
                    if (change == null) {
                        // create change (check it later)
                        change = new ActivityChange();
                        change.setKey(key);
                        change.setActivity(result);
                        change.setLogs(new HashSet<ActivityLog>());
                        result.getChanges().add(change);
                        errors.add(change.getKey());
                        changes.put(change.getKey(), change);
                    }

                    log.setChange(change);
                    change.getLogs().add(log);
                }
            }
        }

        // check the errors
        if (!errors.isEmpty()) {

            BtsCriteria btsc = createBtsCriteria(project);
            for (String key : errors) {
                ActivityChange change = changes.get(key);
                // check if the issue exists
                btsc.setId(key);

                BtsResult tmp = null;
                try {                    
                    tmp = externalService.getIssues(btsc);
                } catch (Exception ex) {                    
                    LOGGER.log(Level.FINEST, "Error get the BTS issue", ex);
                }

                if (tmp != null && !tmp.isEmpty()) {
                    BtsIssue issue = tmp.getIssues().get(0);
                    // issue has wrong release version
                    change.setError(ActivityChangeError.WRONG_VERSION);
                    change.setDescription(issue.getSummary());
                    change.setKey(issue.getId());
                    change.setStatus(issue.getResolution());
                    change.setType(issue.getType());
                    change.setUser(issue.getAssignee());
                    change.setParent(issue.getParent());
                } else {
                    // the issue is not valid issue in the BTS
                    LOGGER.log(Level.WARNING, "{0} is not valid issue for this project", key);
                    change.setType(ActivityType.ERROR);
                    change.setError(ActivityChangeError.WRONG_KEY);
                }
            }
        }
        return result;
    }

    /**
     * Gets the build by GUID.
     *
     * @param guid the build GUID.
     * @return the build.
     */
    private Build getBuild(String guid) {
        BuildCriteria criteria = new BuildCriteria();
        criteria.setGuid(guid);
        return buildService.getBuild(criteria);
    }

    /**
     * Gets the build by GUID.
     *
     * @param guid the build GUID.
     * @return the build.
     */
    private Build getBuildWithAppAndProject(String guid) {
        BuildCriteria criteria = new BuildCriteria();
        criteria.setGuid(guid);
        criteria.setFetchApplication(true);
        criteria.setFetchApplicationProject(true);
        return buildService.getBuild(criteria);
    }

    /**
     * Gets the builds for the application and version.
     *
     * @param application the application.
     * @param build the build version.
     * @return the corresponding list of builds.
     */
    private List<Build> getBuilds(Application application, Build build) {
        BuildCriteria sbc = new BuildCriteria();
        sbc.setApplication(application.getGuid());
        sbc.setMavenVersion(build.getMavenVersion());
        sbc.setOrderByDate(Boolean.TRUE);
        return buildService.getBuilds(sbc);
    }

    /**
     * Gets the project.
     *
     * @param project the project GUID.
     * @return the corresponding project.
     */
    private Project getProject(String project) {
        ProjectCriteria criteria = new ProjectCriteria();
        criteria.setGuid(project);
        criteria.setFetchBTS(true);
        return projectService.getProject(criteria);
    }

    /**
     * Gets the application.
     *
     * @param application the application GUID.
     * @return the corresponding application.
     */
    private Application getApplication(String application) {
        ApplicationCriteria criteria = new ApplicationCriteria();
        criteria.setGuid(application);
        criteria.setFetchSCM(true);
        return appService.getApplication(criteria);
    }

    /**
     * Gets the SCM result for the application and build.
     *
     * @param application the application.
     * @param build the build.
     * @return the SCM result.
     * @throws Exception if the method fails.
     */
    private ScmResult getCommits(Application application, Build build) throws Exception {
        SCMSystem scm = application.getScm();
        String tmp = application.getScmBranches();
        if (application.getScmType() == ApplicationScmRepository.TRUNK) {
            tmp = application.getScmTrunk();
        } else {
            if (application.getScmType() == ApplicationScmRepository.TAG) {
                tmp = application.getScmTags();
            }
        }
        //LOGGER.log(Level.FINEST, "The repository link {0} and type {1}", new Object[]{ tmp, application.getScmType()});
        
        String server = LinkUtil.createLink(tmp, scm, build);

        ScmCriteria criteria = new ScmCriteria();
        criteria.setType(scm.getType());
        criteria.setServer(server);
        criteria.setAuth(scm.isAuth());
        criteria.setUser(scm.getUser());
        criteria.setPassword(scm.getPassword());
        criteria.setReadTimeout(scm.getReadTimeout());
        criteria.setConnectionTimeout(scm.getConnectionTimeout());
        return externalService.getLog(criteria);
    }

    /**
     * Gets the BTS result for the project and build.
     *
     * @param project the project.
     * @param build the build.
     * @return the BTS result.
     * @throws Exception if the method fails.
     */
    private BtsResult getIssues(Project project, Build build) throws Exception {
        BtsCriteria bc = createBtsCriteria(project);
        bc.setVersion(build.getMavenVersion());
        bc.setProject(project.getBtsId());
        return externalService.getIssues(bc);
    }

    /**
     * Creates the BTS criteria for the project.
     *
     * @param project the project.
     * @return the BTS criteria.
     */
    private BtsCriteria createBtsCriteria(Project project) {
        BTSystem bts = project.getBts();
        BtsCriteria bc = new BtsCriteria();
        bc.setServer(bts.getServer());
        bc.setUser(bts.getUser());
        bc.setPassword(bts.getPassword());
        bc.setAuth(bts.isAuth());
        bc.setType(bts.getType());
        return bc;
    }

    /**
     * Creates the key pattern.
     *
     * @param project the project.
     * @return the key pattern.
     * @throws Exception if the method fails.
     */
    private Pattern getKeyPattern(Project project) throws Exception {
        String key = externalService.getIdPattern(project.getBts().getType(), project.getBtsId());
        return Pattern.compile(key);
    }

    /**
     * Finds the build for the date in the list.
     *
     * @param infos the list of build info order ASC.
     * @param date the date.
     * @return the store build or <code>null</code> if no build find.
     */
    private Build findBuild(List<BuildInfo> infos, Date date) {
        Build result = null;
        if (infos != null && date != null) {
            Iterator<BuildInfo> iter = infos.iterator();
            while (result == null && iter.hasNext()) {
                BuildInfo item = iter.next();
                if (!date.after(item.date)) {
                    result = item.build;
                }
            }
        }
        return result;
    }

    private class BuildInfo {

        public Date date;

        public Build build;
    }

    private static class BuildInfoComparator implements Comparator<BuildInfo> {

        private static final BuildInfoComparator INSTANCE = new BuildInfoComparator();

        @Override
        public int compare(BuildInfo o1, BuildInfo o2) {
            return o1.date.compareTo(o2.date);
        }

    }
}
