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
package org.lorislab.tower.service.dashboard.model;

import java.io.Serializable;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.Project;

/**
 *
 * @author Andrej_Petras
 */
public class DashboardTableItem implements Serializable {
    
    private static final long serialVersionUID = -6302829333715422716L;
    
    private final Project project;
    
    private final Application application;
    
    private final DashboardTargetSystem system;

    public DashboardTableItem(Project project, Application application, DashboardTargetSystem system) {
        this.project = project;
        this.application = application;
        this.system = system;
    }

    public Application getApplication() {
        return application;
    }

    public Project getProject() {
        return project;
    }

    public DashboardTargetSystem getSystem() {
        return system;
    }
    
}
