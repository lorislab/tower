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
import org.lorislab.guardian.user.model.User;
import org.lorislab.guardian.web.user.controller.UserController;
import org.lorislab.jel.jsf.api.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.entity.controller.AbstractEntityViewController;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.view.KeyListener;
import org.lorislab.tower.web.common.view.KeyViewController;

/**
 * The user profile view controller.
 *
 * @author Andrej Petras
 */
@Named("userProfileVC")
@SessionScoped
public class UserProfileViewController extends AbstractEntityViewController<User> implements KeyListener {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 7565895047085028278L;

    /**
     * The user data service.
     */
    @Inject
    private UserController service;

    /**
     * The key view controller.
     */
    private final KeyViewController keyViewController;
    
    /**
     * The default constructor.
     */
    public UserProfileViewController() {
        super(Context.PROFILE);
        keyViewController = new KeyViewController(this, Context.PROFILE);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    @FacesServiceMethod
    public Object edit(String guid) throws Exception {
        super.edit(guid);
        service.load();
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    @FacesServiceMethod
    public Object save() throws Exception {
        service.save();
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object close() throws Exception {
        setModel(null);
        return null;
    }

    /**
     * {@inheritDoc }
     */       
    @Override
    public User getModel() {
        return service.getUser();
    }
     
    /**
     * {@inheritDoc }
     */    
    @Override
    public void setKey(String data) {
        getModel().getConfig().setKey(data);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public KeyViewController getKeyViewController() {
        return keyViewController;
    }
    
   
}
