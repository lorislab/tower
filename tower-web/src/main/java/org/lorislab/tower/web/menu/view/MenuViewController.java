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
import org.lorislab.guardian.web.view.actions.ContextLogoutAction;
import org.lorislab.guardian.web.view.actions.ContextMenuAction;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.action.Menu;
import org.lorislab.tower.web.common.action.Navigation;

/**
 * The menu view controller.
 *
 * @author Andrej Petras
 */
@Named("menuVC")
@SessionScoped
public class MenuViewController extends AbstractActionContextViewController {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -146734439197629212L;
    /**
     * The agent menu.
     */
    private final ContextMenuAction agent;
    /**
     * The application menu.
     */
    private final ContextMenuAction application;
    /**
     * The bug tracking system menu.
     */
    private final ContextMenuAction bts;
    /**
     * The deployment menu.
     */
    private final ContextMenuAction deployment;
    /**
     * The dashboard menu.
     */
    private final ContextMenuAction dashboard;
    /**
     * The deploy menu.
     */
    private final ContextMenuAction deploy;
    /**
     * The settings menu.
     */
    private final ContextMenuAction settings;
    /**
     * The logout menu.
     */
    private final ContextMenuAction logout;
    /**
     * The project menu.
     */
    private final ContextMenuAction project;
    /**
     * The mail menu.
     */
    private final ContextMenuAction mail;
    /**
     * The profile menu.
     */
    private final ContextMenuAction profile;
    /**
     * The role menu.
     */
    private final ContextMenuAction role;
    /**
     * The source control management menu.
     */
    private final ContextMenuAction scm;
    /**
     * The system menu.
     */
    private final ContextMenuAction system;
    /**
     * The timer menu.
     */
    private final ContextMenuAction timer;
    /**
     * The user management menu.
     */
    private final ContextMenuAction userman;
    /**
     * The user menu.
     */
    private final ContextMenuAction user;

    /**
     * The default constructor.
     */
    public MenuViewController() {
        agent = new ContextMenuAction(this, Context.MENU, Menu.AGENT, Navigation.TO_AGENT);
        application = new ContextMenuAction(this, Context.MENU, Menu.APPLICATION, Navigation.TO_APPLICATION);
        bts = new ContextMenuAction(this, Context.MENU, Menu.BTS, Navigation.TO_BTS);
        deployment = new ContextMenuAction(this, Context.MENU, Menu.DEPLOYMENT);
        mail = new ContextMenuAction(this, Context.MENU, Menu.MAIL, Navigation.TO_MAIL);
        profile = new ContextMenuAction(this, Context.MENU, Menu.PROFILE, Navigation.TO_PROFILE);
        role = new ContextMenuAction(this, Context.MENU, Menu.ROLE, Navigation.TO_ROLE);
        scm = new ContextMenuAction(this, Context.MENU, Menu.SCM, Navigation.TO_SCM);
        system = new ContextMenuAction(this, Context.MENU, Menu.SYSTEM, Navigation.TO_SYSTEM);
        timer = new ContextMenuAction(this, Context.MENU, Menu.TIMER, Navigation.TO_TIMER);
        userman = new ContextMenuAction(this, Context.MENU, Menu.USERMAN);
        user = new ContextMenuAction(this, Context.MENU, Menu.USER, Navigation.TO_USER);
        dashboard = new ContextMenuAction(this, Context.MENU, Menu.DASHBOARD, Navigation.TO_DASHBOARD);
        deploy = new ContextMenuAction(this, Context.MENU, Menu.DEPLOY, Navigation.TO_DEPLOY);
        settings = new ContextMenuAction(this, Context.MENU, Menu.SETTINGS);
        logout = new ContextLogoutAction(this, Context.MENU, Menu.LOGOUT, Navigation.TO_HOME);
        project = new ContextMenuAction(this, Context.MENU, Menu.PROJECT, Navigation.TO_PROJECT);
    }

    /**
     * Gets the agent menu.
     *
     * @return the agent menu.
     */
    public ContextMenuAction getAgent() {
        return agent;
    }

    /**
     * Gets the application menu.
     *
     * @return the application menu.
     */
    public ContextMenuAction getApplication() {
        return application;
    }

    /**
     * Gets the bug tracking system menu.
     *
     * @return the bug tracking system menu.
     */
    public ContextMenuAction getBts() {
        return bts;
    }

    /**
     * Gets the deployment menu.
     *
     * @return the deployment menu.
     */
    public ContextMenuAction getDeployment() {
        return deployment;
    }

    /**
     * Gets the profile menu.
     *
     * @return the profile menu.
     */
    public ContextMenuAction getProfile() {
        return profile;
    }

    /**
     * Gets the mail menu.
     *
     * @return the mail menu.
     */
    public ContextMenuAction getMail() {
        return mail;
    }

    /**
     * Gets the role menu.
     *
     * @return the role menu.
     */
    public ContextMenuAction getRole() {
        return role;
    }

    /**
     * Gets the user menu.
     *
     * @return the user menu.
     */
    public ContextMenuAction getUser() {
        return user;
    }

    /**
     * Gets the user management menu.
     *
     * @return the user management menu.
     */
    public ContextMenuAction getUserman() {
        return userman;
    }

    /**
     * Gets the timer menu.
     *
     * @return the timer menu.
     */
    public ContextMenuAction getTimer() {
        return timer;
    }

    /**
     * Gets the system menu.
     *
     * @return the system menu.
     */
    public ContextMenuAction getSystem() {
        return system;
    }

    /**
     * Gets the source control management.
     *
     * @return the source control management.
     */
    public ContextMenuAction getScm() {
        return scm;
    }

    /**
     * Gets the project menu.
     *
     * @return the project menu.
     */
    public ContextMenuAction getProject() {
        return project;
    }

    /**
     * Gets the logout menu.
     *
     * @return the logout menu.
     */
    public ContextMenuAction getLogout() {
        return logout;
    }

    /**
     * Gets the dashboard menu.
     *
     * @return the dashboard menu.
     */
    public ContextMenuAction getDashboard() {
        return dashboard;
    }

    /**
     * Gets the deploy menu.
     *
     * @return the deploy menu.
     */
    public ContextMenuAction getDeploy() {
        return deploy;
    }

    /**
     * Gets the settings menu.
     *
     * @return the settings menu.
     */
    public ContextMenuAction getSettings() {
        return settings;
    }
}
