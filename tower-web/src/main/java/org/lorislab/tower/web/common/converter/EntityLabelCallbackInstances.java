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

import org.lorislab.tower.store.model.BTSystem;

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
}
