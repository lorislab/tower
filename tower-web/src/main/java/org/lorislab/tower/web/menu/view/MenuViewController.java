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
import org.lorislab.jel.jsf.menu.controller.action.LogoutAction;
import org.lorislab.jel.jsf.menu.controller.action.MenuAction;
import org.lorislab.jel.jsf.view.controller.AbstractViewController;
import org.lorislab.tower.web.common.action.Menu;
import org.lorislab.tower.web.common.action.Navigation;

/**
 * The menu view controller.
 *
 * @author Andrej Petras
 */
@Named("menuVC")
@SessionScoped
public class MenuViewController extends AbstractViewController {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -146734439197629212L;
    /**
     * The agent menu.
     */
    private final MenuAction agent;
    /**
     * The application menu.
     */
    private final MenuAction application;
    /**
     * The bug tracking system menu.
     */
    private final MenuAction bts;
    /**
     * The deployment menu.
     */
    private final MenuAction deployment;
    /**
     * The dashboard menu.
     */
    private final MenuAction dashboard;
    /**
     * The deploy menu.
     */
    private final MenuAction deploy;
    /**
     * The settings menu.
     */
    private final MenuAction settings;
    /**
     * The logout menu.
     */
    private final MenuAction logout;
    /**
     * The project menu.
     */
    private final MenuAction project;
    /**
     * The mail menu.
     */
    private final MenuAction mail;
    /**
     * The profile menu.
     */
    private final MenuAction profile;
    /**
     * The source control management menu.
     */
    private final MenuAction scm;
    /**
     * The system menu.
     */
    private final MenuAction system;
    /**
     * The timer menu.
     */
    private final MenuAction timer;
    /**
     * The user management menu.
     */
    private final MenuAction userman;
    /**
     * The user menu.
     */
    private final MenuAction user;

    /**
     * The active directory configuration menu.
     */
    private final MenuAction ad;
    
    /**
     * The default constructor.
     */
    public MenuViewController() {
        agent = new MenuAction(this, Menu.AGENT, Navigation.TO_AGENT);
        application = new MenuAction(this, Menu.APPLICATION, Navigation.TO_APPLICATION);
        bts = new MenuAction(this, Menu.BTS, Navigation.TO_BTS);
        deployment = new MenuAction(this, Menu.DEPLOYMENT);
        mail = new MenuAction(this, Menu.MAIL, Navigation.TO_MAIL);
        profile = new MenuAction(this, Menu.PROFILE, Navigation.TO_PROFILE);
        scm = new MenuAction(this, Menu.SCM, Navigation.TO_SCM);
        system = new MenuAction(this, Menu.SYSTEM, Navigation.TO_SYSTEM);
        timer = new MenuAction(this, Menu.TIMER, Navigation.TO_TIMER);
        userman = new MenuAction(this, Menu.USERMAN);
        user = new MenuAction(this, Menu.USER, Navigation.TO_USER);
        dashboard = new MenuAction(this, Menu.DASHBOARD, Navigation.TO_DASHBOARD);
        deploy = new MenuAction(this, Menu.DEPLOY, Navigation.TO_DEPLOY);
        settings = new MenuAction(this, Menu.SETTINGS);
        logout = new LogoutAction(this, Navigation.TO_HOME);
        project = new MenuAction(this, Menu.PROJECT, Navigation.TO_PROJECT);
        ad = new MenuAction(this, Menu.AD, Navigation.TO_AD);
    }

    /**
     * Gets the active directory menu.
     * @return the active directory menu.
     */
    public MenuAction getAd() {
        return ad;
    }
    
    /**
     * Gets the agent menu.
     *
     * @return the agent menu.
     */
    public MenuAction getAgent() {
        return agent;
    }

    /**
     * Gets the application menu.
     *
     * @return the application menu.
     */
    public MenuAction getApplication() {
        return application;
    }

    /**
     * Gets the bug tracking system menu.
     *
     * @return the bug tracking system menu.
     */
    public MenuAction getBts() {
        return bts;
    }

    /**
     * Gets the deployment menu.
     *
     * @return the deployment menu.
     */
    public MenuAction getDeployment() {
        return deployment;
    }

    /**
     * Gets the profile menu.
     *
     * @return the profile menu.
     */
    public MenuAction getProfile() {
        return profile;
    }

    /**
     * Gets the mail menu.
     *
     * @return the mail menu.
     */
    public MenuAction getMail() {
        return mail;
    }

    /**
     * Gets the user menu.
     *
     * @return the user menu.
     */
    public MenuAction getUser() {
        return user;
    }

    /**
     * Gets the user management menu.
     *
     * @return the user management menu.
     */
    public MenuAction getUserman() {
        return userman;
    }

    /**
     * Gets the timer menu.
     *
     * @return the timer menu.
     */
    public MenuAction getTimer() {
        return timer;
    }

    /**
     * Gets the system menu.
     *
     * @return the system menu.
     */
    public MenuAction getSystem() {
        return system;
    }

    /**
     * Gets the source control management.
     *
     * @return the source control management.
     */
    public MenuAction getScm() {
        return scm;
    }

    /**
     * Gets the project menu.
     *
     * @return the project menu.
     */
    public MenuAction getProject() {
        return project;
    }

    /**
     * Gets the logout menu.
     *
     * @return the logout menu.
     */
    public MenuAction getLogout() {
        return logout;
    }

    /**
     * Gets the dashboard menu.
     *
     * @return the dashboard menu.
     */
    public MenuAction getDashboard() {
        return dashboard;
    }

    /**
     * Gets the deploy menu.
     *
     * @return the deploy menu.
     */
    public MenuAction getDeploy() {
        return deploy;
    }

    /**
     * Gets the settings menu.
     *
     * @return the settings menu.
     */
    public MenuAction getSettings() {
        return settings;
    }
}
