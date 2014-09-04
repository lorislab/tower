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

import java.io.Serializable;
import javax.faces.context.FacesContext;
import org.lorislab.jel.jsf.api.util.FacesResourceUtil;
import org.lorislab.jel.jsf.view.controller.ViewController;
import org.lorislab.tower.web.common.action.Permission;
import org.lorislab.tower.web.common.action.ChangePasswordAction;
import org.lorislab.tower.web.common.action.ClearPasswordAction;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.model.ChangePassword;
import org.lorislab.tower.web.settings.resources.ValidationErrorKey;
import org.lorislab.treasure.api.factory.PasswordServiceFactory;
import org.lorislab.treasure.api.service.PasswordService;

/**
 * The change password view controller.
 *
 * @author Andrej Petras
 */
public class ChangePasswordViewController implements Serializable, ViewController {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 1956712426617362245L;

    /**
     * The change password.
     */
    private final ChangePassword password;

    /**
     * The change password action.
     */
    private final ChangePasswordAction changePasswordAction;

    /**
     * The clear password action.
     */
    private final ClearPasswordAction clearPasswordAction;
    
    /**
     * The parent.
     */
    private final ChangePasswordListener parent;

    /**
     * The default constructor.
     *
     * @param parent the parent view controller.
     * @param context the context.
     */
    public ChangePasswordViewController(ChangePasswordListener parent, Context context) {
        this.parent = parent;
        password = new ChangePassword();
        clearPasswordAction = new ClearPasswordAction(this, context, Permission.PASSWORD);
        changePasswordAction = new ChangePasswordAction(this, context, Permission.PASSWORD);
    }

    /**
     * Gets the clear password action.
     *
     * @return the clear password action.
     */    
    public ClearPasswordAction getClearPasswordAction() {
        return clearPasswordAction;
    }
    
    /**
     * Gets the change password action.
     *
     * @return the change password action.
     */
    public ChangePasswordAction getChangePasswordAction() {
        return changePasswordAction;
    }

    /**
     * Gets the change password.
     *
     * @return the change password.
     */
    public ChangePassword getPassword() {
        return password;
    }

    /**
     * Before opens the change password dialog.
     *
     * @return the navigation path.
     * @throws java.lang.Exception if the method fails.
     */
    public Object openPasswordChange() throws Exception {
        password.clear();
        return null;
    }

    /**
     * Clears the password in the parent view controller.
     *
     * @return the navigation path.
     * @throws java.lang.Exception if the method fails.
     */
    public Object clearPassword() throws Exception {
        parent.changePassword(null);
        return null;
    }

    /**
     * The change password action.
     *
     * @return the navigation path.
     * @throws java.lang.Exception if the method fails.
     */
    public Object changePassword() throws Exception {
        if (password.isValid()) {
            try {
                PasswordService service = PasswordServiceFactory.getService();
                String tmp = service.createPassword(password.getNew1());
                parent.changePassword(tmp);
            } catch (Exception ex) {
                FacesResourceUtil.addFacesErrorMessage(ValidationErrorKey.PASSWORD_ERROR);
                FacesContext.getCurrentInstance().validationFailed();
            }
        } else {
            FacesResourceUtil.addFacesErrorMessage(ValidationErrorKey.PASSWORD_DOES_NOT_MACH);
            FacesContext.getCurrentInstance().validationFailed();
        }
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean hasUserAction(Enum action, Enum context) {
        return parent.hasUserAction(action, context);
    }
}
