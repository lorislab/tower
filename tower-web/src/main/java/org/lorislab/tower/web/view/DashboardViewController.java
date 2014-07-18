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

package org.lorislab.tower.web.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.inject.Named;
import org.lorislab.tower.web.model.Dashboard;
import org.lorislab.tower.web.model.DashboardApplication;
import org.lorislab.tower.web.model.DashboardApplicationSystem;
import org.lorislab.tower.web.model.DashboardProject;

/**
 *
 * @author Andrej Petras
 */
@Named("dashboardVC")
public class DashboardViewController implements Serializable {
    private static final long serialVersionUID = 5835025357031129345L;
    
    
    private Dashboard dashboard;

    public Dashboard getDashboard() {
        if (dashboard == null) {
            reload();
        }
        return dashboard;
    }
    
    public Dashboard reload() {
        dashboard = new Dashboard();
        dashboard.setDate(new Date());
        
        List<DashboardProject> projects = new ArrayList<>();        
        dashboard.setProjects(projects);
        
        for (int i=0; i<5; i++) {
            DashboardProject project = new DashboardProject();
            projects.add(project);
                    
            
            project.setGuid(UUID.randomUUID().toString());
            project.setIndex(i);
            project.setName("Project " + i);
            
            List<DashboardApplication> applications = new ArrayList<>();
            project.setApplications(applications);
            for (int j=0; j<5; j++) {
                DashboardApplication app = new DashboardApplication();
                applications.add(app);
                
                app.setGuid(UUID.randomUUID().toString());
                app.setIndex(j);
                app.setName("Application " + i + j);
                app.setProject(project.getGuid());
                
                List<DashboardApplicationSystem> systems = new ArrayList<>();
                app.setSystems(systems);
                for (int k=0; k<3; k++)  {
                    DashboardApplicationSystem sys = new DashboardApplicationSystem();
                    systems.add(sys);
                    
                    sys.setGuid(UUID.randomUUID().toString());
                    sys.setApplication(app.getGuid());
                    sys.setProject(project.getGuid());
                    sys.setClazz("Classfication " + k);
                    sys.setName("System " + i + j + k);                    
                }
            }
        }
        return dashboard;
    }
}
