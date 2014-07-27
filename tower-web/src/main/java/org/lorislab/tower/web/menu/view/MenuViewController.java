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

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.guardian.web.view.AbstractActionContextViewController;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.menu.action.AgentMenuAction;
import org.lorislab.tower.web.menu.action.ApplicationMenuAction;
import org.lorislab.tower.web.menu.action.BTSMenuAction;
import org.lorislab.tower.web.menu.action.DashboardMenuAction;
import org.lorislab.tower.web.menu.action.DeployMenuAction;
import org.lorislab.tower.web.menu.action.DeploymentMenuAction;
import org.lorislab.tower.web.menu.action.LogoutMenuAction;
import org.lorislab.tower.web.menu.action.MailMenuAction;
import org.lorislab.tower.web.menu.action.ProfileMenuAction;
import org.lorislab.tower.web.menu.action.ProjectMenuAction;
import org.lorislab.tower.web.menu.action.RoleMenuAction;
import org.lorislab.tower.web.menu.action.SCMMenuAction;
import org.lorislab.tower.web.menu.action.SettingsMenuAction;
import org.lorislab.tower.web.menu.action.SystemMenuAction;
import org.lorislab.tower.web.menu.action.TimerMenuAction;
import org.lorislab.tower.web.menu.action.UserManagmentMenuAction;
import org.lorislab.tower.web.menu.action.UserMenuAction;

/**
 *
 * @author Andrej Petras
 */
@Named("menuVC")
@SessionScoped
public class MenuViewController extends AbstractActionContextViewController {
    
    private static final long serialVersionUID = -146734439197629212L;

    private AgentMenuAction agent;
    
    private ApplicationMenuAction application;
    
    private BTSMenuAction bts;
    
    private DeploymentMenuAction deployment;
        
    private DashboardMenuAction dashboard;
    
    private DeployMenuAction deploy;
       
    private SettingsMenuAction settings;
    
    private LogoutMenuAction logout;

    private ProjectMenuAction project;
    
    private MailMenuAction mail;
    
    private ProfileMenuAction profile;
    
    private RoleMenuAction role;
    
    private SCMMenuAction scm;
    
    private SystemMenuAction system;
    
    private TimerMenuAction timer;
    
    private UserManagmentMenuAction userman;
    
    private UserMenuAction user;
    
    public MenuViewController() {
        agent = new AgentMenuAction(this);
        application = new ApplicationMenuAction(this);
        bts = new BTSMenuAction(this);
        deployment = new DeploymentMenuAction(this);
        mail = new MailMenuAction(this);
        profile = new ProfileMenuAction(this);
        role = new RoleMenuAction(this);
        scm = new SCMMenuAction(this);
        system = new SystemMenuAction(this);
        timer = new TimerMenuAction(this);
        userman = new UserManagmentMenuAction(this);
        user = new UserMenuAction(this);
        dashboard = new DashboardMenuAction(this);
        deploy = new DeployMenuAction(this);
        settings = new SettingsMenuAction(this);
        logout = new LogoutMenuAction(this);
        project = new ProjectMenuAction(this);        
    }
    
    public AgentMenuAction getAgent() {
        return agent;
    }

    public ApplicationMenuAction getApplication() {
        return application;
    }
    
    public BTSMenuAction getBts() {
        return bts;
    }
    
    public DeploymentMenuAction getDeployment() {
        return deployment;
    }
    
    public ProfileMenuAction getProfile() {
        return profile;
    }
    
    public MailMenuAction getMail() {
        return mail;
    }
    
    public RoleMenuAction getRole() {
        return role;
    }
    
    public UserMenuAction getUser() {
        return user;
    }
    
    public UserManagmentMenuAction getUserman() {
        return userman;
    }

    
    public TimerMenuAction getTimer() {
        return timer;
    }

    
    public SystemMenuAction getSystem() {
        return system;
    }

    
    public SCMMenuAction getScm() {
        return scm;
    }

    
    public ProjectMenuAction getProject() {
        return project;
    }
    
    
    public LogoutMenuAction getLogout() {
        return logout;
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
