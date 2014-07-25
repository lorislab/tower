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
package org.lorislab.tower.activity.ejb;

import java.util.Collections;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.tower.activity.comparator.ActivityChangeLogWrapperComparator;
import org.lorislab.tower.activity.comparator.ActivityChangeWrapperComparator;
import org.lorislab.tower.activity.criteria.ActivityWrapperCriteria;
import org.lorislab.tower.activity.wrapper.ActivityType;
import org.lorislab.tower.activity.wrapper.ActivityChangeLogWrapper;
import org.lorislab.tower.activity.wrapper.ActivityChangeWrapper;
import org.lorislab.tower.activity.wrapper.ActivityWrapper;
import org.lorislab.tower.store.criteria.ActivityCriteria;
import org.lorislab.tower.store.criteria.ApplicationCriteria;
import org.lorislab.tower.store.ejb.ActivityService;
import org.lorislab.tower.store.ejb.ApplicationService;
import org.lorislab.tower.store.model.Activity;
import org.lorislab.tower.store.model.ActivityChange;
import org.lorislab.tower.store.model.ActivityLog;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.BTSystem;
import org.lorislab.tower.store.model.Build;
import org.lorislab.tower.store.model.Project;
import org.lorislab.tower.store.model.SCMSystem;
import org.lorislab.tower.process.util.LinkUtil;

/**
 * The activity wrapper service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ActivityWrapperService {

    @EJB
    private ActivityService activityService;

    @EJB
    private ApplicationService appService;

    public ActivityWrapper create(ActivityWrapperCriteria criteria) {

        // create wrapper and load the activity                        
        ActivityCriteria sac = new ActivityCriteria();
        sac.setBuild(criteria.getBuild());
        sac.setGuid(criteria.getGuid());
        sac.setFetchBuild(true);
        sac.setFetchChange(true);
        sac.setFetchChangeLog(true);
        sac.setFetchChangeLogBuild(true);
        Activity activity = activityService.getActivity(sac);
        
        return create(activity, criteria);
    }
    
    public ActivityWrapper create(ActivityWrapperCriteria criteria, Activity activity) {        
        if (activity == null) {
            return create(criteria);
        }
        return create(activity, criteria);
    }
    
    private ActivityWrapper create(Activity activity, ActivityWrapperCriteria criteria) {        
        ActivityWrapper result = new ActivityWrapper(activity);
                
        Build build = activity.getBuild();        
        criteria.setBuild(build.getGuid());
        
        // load application and project
        BTSystem bts = null;
        SCMSystem scm = null;
        Project project = null;
        Application app;

        // fetch the application and project
        ApplicationCriteria sapc = new ApplicationCriteria();
        sapc.setBuild(criteria.getBuild());
        sapc.setFetchSCM(true);
        sapc.setFetchProject(true);
        sapc.setFetchProjectBts(true);

        app = appService.getApplication(sapc);
        result.setApplication(app);

        if (app != null) {
            scm = app.getScm();
            project = app.getProject();
            result.setProject(project);
            bts = project.getBts();
        }

        // temporary 
        boolean btsLink = bts != null && project != null;
        boolean scmLink = scm != null && app != null;

        // create the wrapper list
        if (activity.getChanges()
                != null) {
            for (ActivityChange change : activity.getChanges()) {
                // add to the types
                String type = change.getType();
                if (type == null) {
                    type = ActivityType.ERROR;
                }
                result.getTypes().add(type);

                // the build change
                ActivityChangeWrapper bacw = null;
                // create the change                
                ActivityChangeWrapper acw = new ActivityChangeWrapper(change);
                if (btsLink) {
                    String link = LinkUtil.createLink(bts.getLink(), change, project);
                    acw.setLink(link);
                }
                result.getChanges().add(acw);

                // check the change logs
                if (change.getLogs() != null) {
                    for (ActivityLog log : change.getLogs()) {

                        ActivityChangeLogWrapper aclw = new ActivityChangeLogWrapper(log);
                        if (scmLink) {
                            String link = LinkUtil.createLink(scm.getLink(), log, app);
                            aclw.setLink(link);
                        }
                        acw.getLogs().add(aclw);

                        // check the build change.
                        if (build.equals(log.getBuild())) {
                            if (bacw == null) {
                                bacw = new ActivityChangeWrapper(change);
                                bacw.setLink(acw.getLink());
                            }
                            bacw.getLogs().add(aclw);
                        }
                    }
                }

                if (criteria.isSortList()) {
                    Collections.sort(acw.getLogs(), ActivityChangeLogWrapperComparator.INSTANCE);
                }

                // add the build change to the wrapper
                if (bacw != null) {
                    result.getBuildChanges().add(bacw);
                    if (criteria.isSortList()) {
                        Collections.sort(bacw.getLogs(), ActivityChangeLogWrapperComparator.INSTANCE);
                    }
                }
            }

            //FIXME: sort order the list
            if (criteria.isSortList()) {
                Collections.sort(result.getChanges(), ActivityChangeWrapperComparator.INSTANCE);
                Collections.sort(result.getBuildChanges(), ActivityChangeWrapperComparator.INSTANCE);
            }
        }
        return result;
    }

}
