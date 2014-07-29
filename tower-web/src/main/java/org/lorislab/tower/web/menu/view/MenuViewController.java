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
import org.lorislab.guardian.web.view.actions.ContextMenuAction;
import org.lorislab.tower.web.common.action.Action;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.action.Menu;
import org.lorislab.tower.web.common.action.Navigation;
import org.lorislab.tower.web.menu.action.LogoutMenuAction;

/**
 *
 * @author Andrej Petras
 */
@Named("menuVC")
@SessionScoped
public class MenuViewController extends AbstractActionContextViewController {
    
    private static final long serialVersionUID = -146734439197629212L;

    private ContextMenuAction agent;    
    private ContextMenuAction application;    
    private ContextMenuAction bts;    
    private ContextMenuAction deployment;        
    private ContextMenuAction dashboard;    
    private ContextMenuAction deploy;       
    private ContextMenuAction settings;    
    private ContextMenuAction logout;
    private ContextMenuAction project;    
    private ContextMenuAction mail;    
    private ContextMenuAction profile;    
    private ContextMenuAction role;    
    private ContextMenuAction scm;    
    private ContextMenuAction system;    
    private ContextMenuAction timer;   
    private ContextMenuAction userman;    
    private ContextMenuAction user;
    
    public MenuViewController() {
        agent = new ContextMenuAction(this, Context.MENU, Menu.AGENT, Navigation.TO_AGENT);
        application = new ContextMenuAction(this, Context.MENU, Menu.APPLICATION, Navigation.TO_APPLICATION);
        bts = new ContextMenuAction(this, Context.MENU, Menu.BTS, Navigation.TO_BTS);
        deployment = new ContextMenuAction(this, Context.MENU, Menu.DEPLOYMENT);
        mail = new ContextMenuAction(this, Context.MENU, Menu.MAIL, Navigation.TO_MAIL);
        profile = new ContextMenuAction(this, Context.MENU, Menu.PROFILE, Navigation.TO_PROFILE);
        role = new ContextMenuAction(this, Context.MENU, Menu.ROLE,Navigation.TO_ROLE);
        scm = new ContextMenuAction(this, Context.MENU, Menu.SCM, Navigation.TO_SCM);
        system = new ContextMenuAction(this, Context.MENU, Menu.SYSTEM, Navigation.TO_SYSTEM);
        timer = new ContextMenuAction(this, Context.MENU, Menu.TIMER, Navigation.TO_TIMER);
        userman = new ContextMenuAction(this, Context.MENU, Menu.USERMAN);
        user = new ContextMenuAction(this, Context.MENU, Menu.USER, Navigation.TO_USER);
        dashboard = new ContextMenuAction(this, Context.MENU, Menu.DASHBOARD, Navigation.TO_DASHBOARD);
        deploy = new ContextMenuAction(this, Context.MENU, Menu.DEPLOY, Navigation.TO_DEPLOY);
        settings = new ContextMenuAction(this, Context.MENU, Menu.SETTINGS);
        logout = new LogoutMenuAction(this, Context.MENU, Menu.LOGOUT, Navigation.TO_HOME);
        project = new ContextMenuAction(this, Context.MENU, Menu.PROJECT, Navigation.TO_PROJECT);        
    }
    
    public ContextMenuAction getAgent() {
        return agent;
    }

    public ContextMenuAction getApplication() {
        return application;
    }
    
    public ContextMenuAction getBts() {
        return bts;
    }
    
    public ContextMenuAction getDeployment() {
        return deployment;
    }
    
    public ContextMenuAction getProfile() {
        return profile;
    }
    
    public ContextMenuAction getMail() {
        return mail;
    }
    
    public ContextMenuAction getRole() {
        return role;
    }
    
    public ContextMenuAction getUser() {
        return user;
    }
    
    public ContextMenuAction getUserman() {
        return userman;
    }

    
    public ContextMenuAction getTimer() {
        return timer;
    }

    
    public ContextMenuAction getSystem() {
        return system;
    }

    
    public ContextMenuAction getScm() {
        return scm;
    }

    
    public ContextMenuAction getProject() {
        return project;
    }
    
    
    public ContextMenuAction getLogout() {
        return logout;
    }
        
    public ContextMenuAction getDashboard() {
        return dashboard;
    }

    public ContextMenuAction getDeploy() {
        return deploy;
    }

    public ContextMenuAction getSettings() {
        return settings;
    }    
}
