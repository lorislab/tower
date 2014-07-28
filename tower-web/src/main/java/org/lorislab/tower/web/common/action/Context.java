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

package org.lorislab.tower.web.common.action;

/**
 *
 * @author Andrej Petras
 */
public enum Context {
    
    PROJECT,
    
    // dashboard
    MENU_DASHBOARD,
    
    // deployment
    MENU_DEPLOYMENT,
    MENU_DEPLOY,
    
    // settings
    MENU_SETTINGS,
    MENU_PROJECT,    
    MENU_APPLICATION,    
    MENU_SYSTEM,
    MENU_SCM,
    MENU_BTS,
    MENU_AGENT,
    MENU_MAIL,
    MENU_TIMER,
    
    // user management
    MENU_USERMAN,
    MENU_ROLE,
    MENU_USER,
    
    // other
    MENU_PROFILE,
    MENU_LOGOUT;
}
