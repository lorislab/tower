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
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.tower.service.dashboard.model.Dashboard;
import org.lorislab.tower.service.dashboard.model.DashboardApplication;
import org.lorislab.tower.service.dashboard.model.DashboardTargetSystem;
import org.lorislab.tower.service.dashboard.model.DashboardProject;
import org.lorislab.tower.store.ejb.ProjectService;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.Project;
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
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
