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
package org.lorislab.tower.web.settings.view;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.guardian.api.user.model.UserSourceData;
import org.lorislab.guardian.app.model.Role;
import org.lorislab.guardian.user.ejb.UserService;
import org.lorislab.guardian.user.model.User;
import org.lorislab.jel.jsf.api.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.entity.controller.AbstractEntityViewController;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.action.Navigation;
import org.lorislab.tower.web.common.view.ApplicationController;
import org.lorislab.tower.web.common.view.KeyListener;
import org.lorislab.tower.web.common.view.KeyViewController;
import org.lorislab.tower.web.settings.action.ImportUserAction;
import org.lorislab.tower.web.common.model.RoleItem;
import org.lorislab.tower.web.common.view.ChangePasswordListener;
import org.lorislab.tower.web.common.view.ChangePasswordViewController;

/**
 * The user view controller.
 *
 * @author Andrej_Petras
 */
@Named("userVC")
@SessionScoped
public class UserViewController extends AbstractEntityViewController<User> implements KeyListener, ChangePasswordListener {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -9056528681264454508L;

    /**
     * The user import action.
     */
    private final ImportUserAction importAction;

    /**
     * The change password view controller.
     */
    private final ChangePasswordViewController passwordVC;

    /**
     * The user service.
     */
    @EJB
    private UserService service;

    /**
     * The application controller.
     */
    @Inject
    private ApplicationController applicationController;

    /**
     * The key view controller.
     */
    private final KeyViewController keyViewController;

    /**
     * The list of role items.
     */
    private List<RoleItem> roleItems;

    /**
     * The default constructor.
     */
    public UserViewController() {
        super(Context.USER);
        importAction = new ImportUserAction(this, Context.USER);
        keyViewController = new KeyViewController(this, Context.USER);
        passwordVC = new ChangePasswordViewController(this, Context.USER);
    }

    /**
     * Loads the user by user GUID.
     *
     * @param guid the user GUID.
     * @throws java.lang.Exception if the method fails.
     */
    private void loadUser(String guid) throws Exception {
        roleItems = new ArrayList<>();
        User tmp = service.getFullUser(guid);
        for (Role role : applicationController.getRoles()) {
            roleItems.add(new RoleItem(role, tmp.getRoles().contains(role.getName())));
        }
        setModel(tmp);
    }

    /**
     * Gets the password view controller.
     *
     * @return the password view controller.
     */
    public ChangePasswordViewController getPasswordVC() {
        return passwordVC;
    }

    /**
     * Gets the role items.
     *
     * @return the role items.
     */
    public List<RoleItem> getRoleItems() {
        return roleItems;
    }

    /**
     * Gets the import user action.
     *
     * @return the import user action.
     */
    public ImportUserAction getImportAction() {
        return importAction;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object save() throws Exception {
        User tmp = getModel();
        tmp.getRoles().clear();
        for (RoleItem item : roleItems) {
            if (item.isSelected()) {
                tmp.getRoles().add(item.getRole().getName());
            }
        }
        tmp = service.saveUser(getModel());
        loadUser(tmp.getGuid());
        return super.save();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object edit(String guid) throws Exception {
        loadUser(guid);
        return Navigation.TO_USER_EDIT;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    @FacesServiceMethod
    public Object close() throws Exception {
        setModel(null);
        return Navigation.TO_USER;
    }

    /**
     * Imports the user from the user source data.
     *
     * @param user the user source data.
     * @return the navigation path.
     */
    public Object importUser(UserSourceData user) {
        Object result = null;
        if (user != null) {

            result = Navigation.TO_USER_EDIT;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setKey(String data) {
        getModel().getConfig().setKey(data);
    }

    /**
     * Gets the key view controller.
     *
     * @return the key view controller.
     */
    public KeyViewController getKeyViewController() {
        return keyViewController;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public void changePassword(String data) {
        getModel().getPassword().setPassword(data);
    }    
}
