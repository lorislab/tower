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

import javax.ejb.EJB;
import org.lorislab.barn.api.service.ConfigurationService;
import org.lorislab.guardian.web.view.AbstractContextEntityViewController;
import org.lorislab.guardian.web.view.ContextCloseViewController;
import org.lorislab.guardian.web.view.ContextOpenViewController;
import org.lorislab.guardian.web.view.ContextSaveViewController;
import org.lorislab.guardian.web.view.actions.ContextCloseAction;
import org.lorislab.guardian.web.view.actions.ContextOpenAction;
import org.lorislab.guardian.web.view.actions.ContextSaveAction;
import org.lorislab.tower.web.common.action.Action;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.action.Navigation;

/**
 * The project view controller.
 *
 * @param <T> the type of the configuration model.
 * @author Andrej Petras
 */
public class ConfigurationViewController<T> extends AbstractContextEntityViewController<T> implements ContextOpenViewController, ContextSaveViewController, ContextCloseViewController {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1766808728513642285L;

    /**
     * The project service.
     */
    @EJB
    private ConfigurationService service;

    /**
     * The open action.
     */
    private ContextOpenAction openAction;

    /**
     * The save action.
     */
    private ContextSaveAction saveAction;

    /**
     * The close action.
     */
    private ContextCloseAction closeAction;

    /**
     * The configuration class.
     */
    private Class<T> clazz;

    /**
     * The default constructor.
     */
    public ConfigurationViewController() {
        // empty consturctor
    }

    /**
     * The default constructor.
     *
     * @param context the context.
     * @param clazz the configuration class.
     */
    public ConfigurationViewController(Context context, Class<T> clazz) {
        saveAction = new ContextSaveAction(this, context, Action.SAVE);
        openAction = new ContextOpenAction(this, context, Action.EDIT);
        closeAction = new ContextCloseAction(this, context, Action.CLOSE);
        this.clazz = clazz;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object open(String guid) {
        T tmp = service.getConfiguration(clazz);
        setModel(tmp);
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object save() throws Exception {
        T tmp = service.setConfiguration(getModel());
        setModel(tmp);
        return null;
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
     * Gets the save action.
     *
     * @return the save action.
     */
    public ContextSaveAction getSaveAction() {
        return saveAction;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object close() throws Exception {
        return null;
    }

    /**
     * Gets the close action.
     *
     * @return the close action.
     */
    public ContextCloseAction getCloseAction() {
        return closeAction;
    }

}
