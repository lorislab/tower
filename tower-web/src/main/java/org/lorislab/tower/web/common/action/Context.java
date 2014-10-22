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
 * The context object.
 * 
 * @author Andrej Petras
 */
public enum Context {
   
    /**
     * The password.
     */
    PASSWORD,
    /**
     * The user.
     */
    USER,
    /**
     * The active directory.
     */
    AD,
    /**
     * The active directory.
     */
    AD_SEARCH,    
    /**
     * The use profile context.
     */
    PROFILE,
    /**
     * The timer.
     */
    TIMER,
    /**
     * The mail.
     */
    MAIL,
    /**
     * The agent.
     */
    AGENT,
    /**
     * The bug tracking system.
     */
    BTS,
    /**
     * The source control system.
     */
    SCM,
    /**
     * The system.
     */
    SYSTEM,
    /**
     * The application.
     */
    APPLICATION,
    /**
     * The project.
     */
    PROJECT;
}
