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
package org.lorislab.tower.web.common.converter;

import org.lorislab.jel.jsf.api.converter.EntityLabelCallback;
import org.lorislab.tower.store.model.Agent;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.BTSystem;
import org.lorislab.tower.store.model.Project;
import org.lorislab.tower.store.model.SCMSystem;

/**
 * The default entity label callback instances.
 *
 * @author Andrej Petras
 */
public interface EntityLabelCallbackInstances {

    /**
     * The BTSsystem label callback instance.
     */
    public static final EntityLabelCallback<BTSystem> BTSYSTEM = new EntityLabelCallback<BTSystem>() {

        /**
         * {@inheritDoc }
         */
        @Override
        public String getEntityLabel(BTSystem entity) {
            return entity.getName();
        }
    };
    
    /**
     * The BTSsystem label callback instance.
     */
    public static final EntityLabelCallback<SCMSystem> SCMSYSTEM = new EntityLabelCallback<SCMSystem>() {

        /**
         * {@inheritDoc }
         */
        @Override
        public String getEntityLabel(SCMSystem entity) {
            return entity.getName();
        }
    };
       
    /**
     * The project label callback instance.
     */
    public static final EntityLabelCallback<Project> PROJECT = new EntityLabelCallback<Project>() {

        /**
         * {@inheritDoc }
         */
        @Override
        public String getEntityLabel(Project entity) {
            return entity.getName();
        }
    };    
    
    /**
     * The application label callback instance.
     */
    public static final EntityLabelCallback<Application> APPLICATION = new EntityLabelCallback<Application>() {

        /**
         * {@inheritDoc }
         */
        @Override
        public String getEntityLabel(Application entity) {
            return entity.getName();
        }
    }; 
    
    
    /**
     * The agent label callback instance.
     */
    public static final EntityLabelCallback<Agent> AGENT = new EntityLabelCallback<Agent>() {

        /**
         * {@inheritDoc }
         */
        @Override
        public String getEntityLabel(Agent entity) {
            return entity.getName();
        }
    };     
}
