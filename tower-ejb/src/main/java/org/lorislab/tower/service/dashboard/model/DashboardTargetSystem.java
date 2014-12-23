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

import org.lorislab.tower.store.model.TargetSystem;

/**
 * The dashboard application system.
 *
 * @author Andrej Petras
 */
public class DashboardTargetSystem extends AbstractDashboardWrapper<TargetSystem, Object> {

    private static final long serialVersionUID = 2932042159555247986L;

    private boolean loaded = false;
    
    public DashboardTargetSystem(TargetSystem system) {
        setModel(system);
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isLoaded() {
        return loaded;
    }

    
}
