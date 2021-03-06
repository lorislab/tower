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

import org.lorislab.jel.jpa.model.Persistent;
import org.lorislab.jel.jsf.entity.controller.AbstractEntityViewController;
import org.lorislab.jel.jsf.entity.controller.action.DeleteAction;

/**
 * The entity delete action.
 *
 * @author Andrej Petras
 */
public class EntityDeleteAction extends DeleteAction<AbstractEntityViewController> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -5450629946700529458L;

    /**
     * The default constructor.
     *
     * @param parent the parent view controller.
     * @param context the context object.
     */
    public EntityDeleteAction(AbstractEntityViewController parent, Enum context) {
        super(parent, context);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isAvailable() {
        Persistent tmp = (Persistent) getParent().getModel();
        if (tmp != null && !tmp.isNew()) {
            return super.isAvailable();
        }
        return false;
    }
}
