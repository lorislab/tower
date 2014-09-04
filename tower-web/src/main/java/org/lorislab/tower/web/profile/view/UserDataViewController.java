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
package org.lorislab.tower.web.profile.view;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.guardian.api.model.UserData;
import org.lorislab.guardian.web.user.controller.UserDataController;
import org.lorislab.jel.jsf.entity.controller.AbstractEntityViewController;
import org.lorislab.jel.jsf.entity.controller.CloseViewController;
import org.lorislab.jel.jsf.entity.controller.OpenViewController;
import org.lorislab.jel.jsf.entity.controller.SaveViewController;
import org.lorislab.jel.jsf.entity.controller.action.CloseAction;
import org.lorislab.jel.jsf.entity.controller.action.OpenAction;
import org.lorislab.jel.jsf.entity.controller.action.SaveAction;
import org.lorislab.tower.guardian.config.model.UserConfig;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.view.KeyListener;

/**
 * The user profile view controller.
 *
 * @author Andrej Petras
 */
@Named("userDataVC")
@SessionScoped
public class UserDataViewController extends AbstractEntityViewController<UserData> implements KeyListener, OpenViewController, SaveViewController, CloseViewController {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 7565895047085028278L;

    /**
     * The open action.
     */
    private final OpenAction openAction;

    /**
     * The save action.
     */
    private final SaveAction saveAction;

    /**
     * The close action.
     */
    private final CloseAction closeAction;

    /**
     * The user service.
     */
    @Inject
    private UserDataController controller;

    /**
     * The default constructor.
     */
    public UserDataViewController() {
        super();
        closeAction = new CloseAction(this, Context.PROFILE);
        saveAction = new SaveAction(this, Context.PROFILE);
        openAction = new OpenAction(this, Context.PROFILE);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object open(String guid) throws Exception {
        controller.load();
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object save() throws Exception {
        controller.save();
        return null;
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
    public CloseAction getCloseAction() {
        return closeAction;
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
     * Gets the save action.
     *
     * @return the save action.
     */
    public SaveAction getSaveAction() {
        return saveAction;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public UserData getModel() {
        return controller.getUserData();
    }
    
    /**
     * {@inheritDoc }
     */    
    @Override
    public void setKey(String data) {
        UserData tmp = controller.getUserData();
        UserConfig config = (UserConfig) tmp.getConfig();
        config.setKey(data);
    }
}
