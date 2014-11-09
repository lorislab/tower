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
package org.lorislab.tower.service.activity.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import org.lorislab.tower.bts.model.BtsCriteria;
import org.lorislab.tower.bts.model.BtsIssue;
import org.lorislab.tower.bts.model.BtsResult;
import org.lorislab.tower.scm.model.ScmCriteria;
import org.lorislab.tower.scm.model.ScmLog;
import org.lorislab.tower.scm.model.ScmResult;
import org.lorislab.tower.store.criteria.ApplicationCriteria;
import org.lorislab.tower.store.criteria.BuildCriteria;
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
import org.lorislab.tower.service.system.ejb.BtsClientService;
import org.lorislab.tower.service.system.ejb.ScmClientService;
import org.lorislab.tower.service.activity.comparator.BuildInfoComparator;
import org.lorislab.tower.service.activity.model.BuildInfo;
import org.lorislab.tower.service.activity.util.BuildInfoUtil;
import org.lorislab.tower.store.ejb.ApplicationService;
import org.lorislab.tower.store.ejb.BuildService;
import org.lorislab.treasure.api.factory.PasswordServiceFactory;

/**
 * The activity build service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ActivityBuildService {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(ActivityBuildService.class.getName());
    /**
     * The store application service.
     */
    @EJB
    private ApplicationService appService;

    /**
     * The store build service.
     */
    @EJB
    private BuildService buildService;

    /**
     * The SCM system service.
     */
    @EJB
    private ScmClientService scmService;

    /**
     * The BTS system service.
     */
    @EJB
    private BtsClientService btsService;

    public Activity createActivityForApplication(final String guid, final Build build) throws Exception {
        ApplicationCriteria criteria = new ApplicationCriteria();
        criteria.setGuid(guid);
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
        BTSystem bts = project.getBts();
        char[] pswd = PasswordServiceFactory.getService().getPassword(bts.getPassword());
        BtsCriteria bc = new BtsCriteria();
        bc.setServer(bts.getServer());
        bc.setUser(bts.getUser());
        bc.setPassword(pswd);
        bc.setAuth(bts.isAuth());
        bc.setType(bts.getType());

        bc.setVersion(build.getProjectVersion());
        bc.setProject(project.getBtsId());
        BtsResult btsResult = btsService.getIssues(bc);
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
        BuildCriteria sbc = new BuildCriteria();
        sbc.setApplication(application.getGuid());
        sbc.setMavenVersion(build.getMavenVersion());
        sbc.setOrderByDate(Boolean.TRUE);
        List<Build> builds = buildService.getBuilds(sbc);

        // the issue in the commits but not in the bts
        Set<String> errors = new HashSet<>();

        // create key pattern
        String kkey = btsService.getIdPattern(project.getBts().getType(), project.getBtsId());
        Pattern pattern = Pattern.compile(kkey);
        // create logs        
        SCMSystem scm = application.getScm();
        String ttype = application.getScmBranches();
        if (application.getScmType() == ApplicationScmRepository.TRUNK) {
            ttype = application.getScmTrunk();
        } else if (application.getScmType() == ApplicationScmRepository.TAG) {
            ttype = application.getScmTags();
        }
        String server = LinkUtil.createLink(ttype, scm, build);

        char[] pswds = PasswordServiceFactory.getService().getPassword(scm.getPassword());
        ScmCriteria criteria = new ScmCriteria();
        criteria.setType(scm.getType());
        criteria.setServer(server);
        criteria.setAuth(scm.isAuth());
        criteria.setUser(scm.getUser());
        criteria.setPassword(pswds);
        criteria.setReadTimeout(scm.getReadTimeout());
        criteria.setConnectionTimeout(scm.getConnectionTimeout());
        ScmResult scmResult = scmService.getLog(criteria);

        if (scmResult != null && !scmResult.isEmpty()) {

            // create build information
            List<BuildInfo> buildInfos = new ArrayList<>();

            if (builds != null && !builds.isEmpty()) {
                builds.add(build);
                for (Build b : builds) {
                    ScmLog log = scmResult.getScmLog(b.getScm());
                    if (log != null) {
                        buildInfos.add(new BuildInfo(log.getDate(), b));
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
                    Build logBuild = BuildInfoUtil.findBuild(buildInfos, commit.getDate());
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

            BtsCriteria btsc = new BtsCriteria();
            btsc.setServer(bts.getServer());
            btsc.setUser(bts.getUser());
            btsc.setPassword(pswd);
            btsc.setAuth(bts.isAuth());
            btsc.setType(bts.getType());

            for (String key : errors) {
                ActivityChange change = changes.get(key);
                // check if the issue exists
                btsc.setId(key);

                BtsResult tmp = null;
                try {
                    tmp = btsService.getIssues(btsc);
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
                    change.setType("ERROR");
                    change.setError(ActivityChangeError.WRONG_KEY);
                }
            }
        }
        return result;
    }

}
