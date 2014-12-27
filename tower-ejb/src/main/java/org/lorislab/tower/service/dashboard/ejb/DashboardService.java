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
package org.lorislab.tower.service.dashboard.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.tower.service.dashboard.model.Dashboard;
import org.lorislab.tower.service.dashboard.model.DashboardApplication;
import org.lorislab.tower.service.dashboard.model.DashboardTargetSystem;
import org.lorislab.tower.service.dashboard.model.DashboardProject;
import org.lorislab.tower.service.dashboard.model.DashboardTableItem;
import org.lorislab.tower.store.criteria.SystemBuildCriteria;
import org.lorislab.tower.store.ejb.ProjectService;
import org.lorislab.tower.store.ejb.SystemBuildService;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.Project;
import org.lorislab.tower.store.model.SystemBuild;
import org.lorislab.tower.store.model.TargetSystem;

/**
 *
 * @author Andrej_Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class DashboardService {

    @EJB
    private ProjectService projectService;

    @EJB
    private SystemBuildService systemBuildService;

    public Dashboard getDashboardProjects() {
        Dashboard result = null;

        List<Project> projects = projectService.getDashboardProjects();
        if (projects != null) {
            result = new Dashboard();
            result.setItems(new ArrayList<DashboardProject>(projects.size()));

            for (Project project : projects) {

                DashboardProject dp = new DashboardProject(project);
                result.getItems().add(dp);

                Set<Application> applications = project.getApplications();
                if (applications != null) {
                    dp.setItems(new ArrayList<DashboardApplication>(applications.size()));

                    for (Application application : applications) {
                        DashboardApplication da = new DashboardApplication(application);
                        dp.getItems().add(da);

                        Set<TargetSystem> systems = application.getSystems();
                        if (systems != null) {
                            da.setItems(new ArrayList<DashboardTargetSystem>(systems.size()));

                            for (TargetSystem system : systems) {

                                DashboardTargetSystem das = new DashboardTargetSystem(system);
                                da.getItems().add(das);
                                result.getSystems().put(das.getGuid(), das);
                                
                                result.getTableItems().add(new DashboardTableItem(project, application, das));
                            }
                        }
                    }
                }
            }

            for (int p = 0; p < 10; p++) {
                Project pr = new Project();
                pr.setName("Project " + p);

                DashboardProject dp2 = new DashboardProject(pr);
                result.getItems().add(dp2);

                dp2.setItems(new ArrayList<DashboardApplication>(5));
                for (int a = 0; a < 5; a++) {

                    Application ap = new Application();
                    ap.setName("Application " + a);

                    DashboardApplication da2 = new DashboardApplication(ap);
                    dp2.getItems().add(da2);
                    da2.setItems(new ArrayList<DashboardTargetSystem>(5));

                    for (int s = 0; s < 5; s++) {

                        TargetSystem ts = new TargetSystem();
                        ts.setName("System " + s);

                        DashboardTargetSystem das2 = new DashboardTargetSystem(ts);
                        da2.getItems().add(das2);
                        result.getSystems().put(das2.getGuid(), das2);
                        
                        result.getTableItems().add(new DashboardTableItem(pr, ap, das2));
                    }
                }
            }

            Map<String, DashboardTargetSystem> systems = result.getSystems();
            
            SystemBuildCriteria criteria = new SystemBuildCriteria();
            criteria.setSystems(systems.keySet());
            criteria.setFetchBuild(true);
            criteria.setFetchSystem(true);
            criteria.setMaxDate(Boolean.TRUE);
            List<SystemBuild> ssb = systemBuildService.getSystemBuilds(criteria);
            if (ssb != null) {
                for (SystemBuild sb : ssb) {
                    DashboardTargetSystem dts = systems.get(sb.getSystem().getGuid());
                    if (dts != null) {
                        dts.setBuild(sb);
                    }
                }
            }
        }
        return result;
    }
}
