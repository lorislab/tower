/*
 * Copyright 2015 lorislab.org.
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
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.tower.service.activity.wrapper.ActivityChangeWrapper;
import org.lorislab.tower.service.activity.wrapper.ActivityLogWrapper;
import org.lorislab.tower.service.activity.wrapper.ActivityWrapper;
import org.lorislab.tower.store.criteria.ActivityCriteria;
import org.lorislab.tower.store.criteria.ApplicationCriteria;
import org.lorislab.tower.store.ejb.ActivityService;
import org.lorislab.tower.store.ejb.ApplicationService;
import org.lorislab.tower.store.model.Activity;
import org.lorislab.tower.store.model.ActivityChange;
import org.lorislab.tower.store.model.ActivityLog;
import org.lorislab.tower.store.model.Application;

/**
 * The activity wrapper service.
 *
 * @author Andrej_Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ActivityWrapperService {

    /**
     * The activity service.
     */
    @EJB
    private ActivityService activityService;

    /**
     * The application
     */
    @EJB
    private ApplicationService applicationService;

    /**
     * Gets the activity wrapper by build GUID.
     *
     * @param buildGuid the build GUID.
     * @return the corresponding activity wrapper.
     */
    public ActivityWrapper getActivityWrapper(String buildGuid) {
        ActivityWrapper result = null;

        ActivityCriteria ac = new ActivityCriteria();
        ac.setBuild(buildGuid);
        ac.setFetchChange(true);
        ac.setFetchChangeLog(true);
        ac.setFetchChangeLogBuild(true);
        Activity activity = activityService.getActivity(ac);

        if (activity != null) {

            result = new ActivityWrapper();
            result.setModel(activity);
            
            // fetch the application, scm, project and bts
            ApplicationCriteria sapc = new ApplicationCriteria();
            sapc.setBuild(buildGuid);
            sapc.setFetchSCM(true);
            sapc.setFetchProject(true);
            sapc.setFetchProjectBts(true);

            Application application = applicationService.getApplication(sapc);
            if (application != null) {
                result.setApplication(application);
                
                if (application.getProject() != null) {
                    result.setProject(application.getProject());
                }
            }
            
            Set<ActivityChange> changes = activity.getChanges();
            if (changes != null) {

                List<ActivityChangeWrapper> changeWrappers = new ArrayList<>(changes.size());
                result.setChanges(changeWrappers);

                for (ActivityChange change : changes) {

                    ActivityChangeWrapper acw = new ActivityChangeWrapper();
                    acw.setModel(change);
                    changeWrappers.add(acw);

                    if (change.getStatus() != null) {
                        result.getStatuses().add(change.getStatus());
                    }

                    if (change.getType() != null) {
                        result.getTypes().add(change.getType());
                    }

                    Set<ActivityLog> logs = change.getLogs();
                    if (logs != null) {

                        List<ActivityLogWrapper> logWrappers = new ArrayList<>(logs.size());
                        acw.setLogs(logWrappers);

                        for (ActivityLog log : logs) {
                            ActivityLogWrapper alw = new ActivityLogWrapper();
                            alw.setModel(log);
                            logWrappers.add(alw);
                        }
                    }
                }
            }
        }

        return result;
    }
}
