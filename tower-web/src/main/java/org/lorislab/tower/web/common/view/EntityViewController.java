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
package org.lorislab.tower.web.common.view;

import org.lorislab.guardian.web.view.AbstractContextEntityViewController;
import org.lorislab.guardian.web.view.ContextEntityViewController;
import org.lorislab.guardian.web.view.actions.ContextCloseAction;
import org.lorislab.guardian.web.view.actions.ContextCreateAction;
import org.lorislab.guardian.web.view.actions.ContextDeleteAction;
import org.lorislab.guardian.web.view.actions.ContextOpenAction;
import org.lorislab.guardian.web.view.actions.ContextSaveAction;
import org.lorislab.jel.jpa.model.Persistent;
import org.lorislab.tower.web.common.action.Action;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.action.EntityDeleteAction;

/**
 * The project view controller.
 *
 * @author Andrej Petras
 * @param <T>
 */
public abstract class EntityViewController<T extends Persistent> extends AbstractContextEntityViewController<T> implements ContextEntityViewController {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1766808728513642285L;

    /**
     * The open action.
     */
    private ContextOpenAction openAction;

    /**
     * The create action.
     */
    private ContextCreateAction createAction;

    /**
     * The close action.
     */
    private ContextCloseAction closeAction;

    /**
     * The save action.
     */
    private ContextSaveAction saveAction;

    /**
     * The delete action.
     */
    private ContextDeleteAction deleteAction;

    
    public EntityViewController() {
        
    }
    
    /**
     * The default constructor.
     * @param context
     */
    public EntityViewController(Context context) {
        createAction = new ContextCreateAction(this, context, Action.CREATE);
        closeAction = new ContextCloseAction(this, context, Action.CLOSE);
        saveAction = new ContextSaveAction(this, context, Action.SAVE);
        deleteAction = new EntityDeleteAction(this, context, Action.DELETE);
        openAction = new ContextOpenAction(this, context, Action.EDIT);
    }
   
    /**
     * Gets the open action.
     *
     * @return the open action.
     */
    public ContextOpenAction getOpenAction() {
        return openAction;
    }

    /**
     * Gets the close action.
     *
     * @return the close action.
     */
    public ContextCloseAction getCloseAction() {
        return closeAction;
    }

    /**
     * Gets the create action.
     *
     * @return the create action.
     */
    public ContextCreateAction getCreateAction() {
        return createAction;
    }

    /**
     * Gets the delete action.
     *
     * @return the delete action.
     */
    public ContextDeleteAction getDeleteAction() {
        return deleteAction;
    }

    /**
     * Gets the save action.
     *
     * @return the save action.
     */
    public ContextSaveAction getSaveAction() {
        return saveAction;
    }

}
