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

import java.util.HashMap;
import java.util.Map;
import org.lorislab.jel.jpa.model.Persistent;

/**
 *
 * @author Andrej_Petras
 */
public class Dashboard extends AbstractDashboardWrapper<Persistent, DashboardProject> {
    
    private static final long serialVersionUID = -3770372562560387951L;
    
    private final Map<String, DashboardTargetSystem> systems = new HashMap<>();

    public Map<String, DashboardTargetSystem> getSystems() {
        return systems;
    }
    
    
}
