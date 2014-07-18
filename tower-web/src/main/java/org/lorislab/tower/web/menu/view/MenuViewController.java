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

package org.lorislab.tower.web.menu.view;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.guardian.jsf.view.ActionContextViewController;
import org.lorislab.tower.web.menu.action.DashboardMenuAction;
import org.lorislab.tower.web.menu.action.DeployMenuAction;
import org.lorislab.tower.web.menu.action.SettingsMenuAction;

/**
 *
 * @author Andrej Petras
 */
@Named("menuVC")
@SessionScoped
public class MenuViewController implements ActionContextViewController, Serializable {
    
    private static final long serialVersionUID = -146734439197629212L;

    private DashboardMenuAction dashboard;
    
    private DeployMenuAction deploy;
    
    private SettingsMenuAction settings;
    
    public MenuViewController() {
        dashboard = new DashboardMenuAction(this);
        deploy = new DeployMenuAction(this);
        settings = new SettingsMenuAction(this);
    }
     
    @Override
    public boolean hasUserAction(Enum action, Enum context) {
        return true;
    }

    public DashboardMenuAction getDashboard() {
        return dashboard;
    }

    public DeployMenuAction getDeploy() {
        return deploy;
    }

    public SettingsMenuAction getSettings() {
        return settings;
    }    
}