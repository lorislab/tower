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


import org.lorislab.jel.jpa.model.Persistent;
import org.lorislab.jel.jsf.entity.controller.AbstractEntityViewController;
import org.lorislab.jel.jsf.entity.controller.CloseViewController;
import org.lorislab.jel.jsf.entity.controller.CreateViewController;
import org.lorislab.jel.jsf.entity.controller.DeleteViewController;
import org.lorislab.jel.jsf.entity.controller.OpenViewController;
import org.lorislab.jel.jsf.entity.controller.SaveViewController;
import org.lorislab.jel.jsf.entity.controller.action.CloseAction;
import org.lorislab.jel.jsf.entity.controller.action.CreateAction;
import org.lorislab.jel.jsf.entity.controller.action.DeleteAction;
import org.lorislab.jel.jsf.entity.controller.action.OpenAction;
import org.lorislab.jel.jsf.entity.controller.action.SaveAction;
import org.lorislab.tower.web.common.action.EntityDeleteAction;

/**
 * The project view controller.
 *
 * @author Andrej Petras
 * @param <T>
 */
public abstract class AbstractDefaultViewController<T extends Persistent> extends AbstractEntityViewController<T>
            implements DeleteViewController, CreateViewController, OpenViewController, SaveViewController, CloseViewController {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1766808728513642285L;

    /**
     * The open action.
     */
    private OpenAction openAction;

    /**
     * The create action.
     */
    private CreateAction createAction;

    /**
     * The close action.
     */
    private CloseAction closeAction;

    /**
     * The save action.
     */
    private SaveAction saveAction;

    /**
     * The delete action.
     */
    private DeleteAction deleteAction;

    /**
     * The default constructor.
     */
    public AbstractDefaultViewController() {
        // empty constructor
    }
    
    /**
     * The default constructor.
     * @param context
     */
    public AbstractDefaultViewController(Enum context) {
        super(context);
        createAction = new CreateAction(this, context);
        closeAction = new CloseAction(this, context);
        saveAction = new SaveAction(this, context);
        deleteAction = new EntityDeleteAction(this, context);
        openAction = new OpenAction(this, context);
    }
   
    /**
     * Gets the open action.
     *
     * @return the open action.
     */
    public OpenAction getOpenAction() {
        return openAction;
    }

    /**
     * Gets the close action.
     *
     * @return the close action.
     */
    public CloseAction getCloseAction() {
        return closeAction;
    }

    /**
     * Gets the create action.
     *
     * @return the create action.
     */
    public CreateAction getCreateAction() {
        return createAction;
    }

    /**
     * Gets the delete action.
     *
     * @return the delete action.
     */
    public DeleteAction getDeleteAction() {
        return deleteAction;
    }

    /**
     * Gets the save action.
     *
     * @return the save action.
     */
    public SaveAction getSaveAction() {
        return saveAction;
    }

}
