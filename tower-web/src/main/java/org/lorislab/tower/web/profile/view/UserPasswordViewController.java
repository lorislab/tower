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

import java.security.Principal;
import java.util.Arrays;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.guardian.user.ejb.UserPasswordService;
import org.lorislab.guardian.user.model.UserPassword;
import org.lorislab.jel.jsf.api.util.FacesResourceUtil;
import org.lorislab.jel.jsf.entity.controller.AbstractEntityViewController;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.model.ChangePassword;
import org.lorislab.tower.web.settings.resources.ValidationErrorKey;
import org.lorislab.treasure.api.factory.PasswordServiceFactory;
import org.lorislab.treasure.api.service.PasswordService;

/**
 * The user password view controller.
 *
 * @author Andrej_Petras
 */
@Named("userPasswordVC")
@ViewScoped
public class UserPasswordViewController extends AbstractEntityViewController<ChangePassword> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1334014331693331407L;

    /**
     * The user password service.
     */
    @EJB
    private UserPasswordService service;

    /**
     * The principal.
     */
    @Inject
    private Principal principal;

    /**
     * The default constructor.
     */
    public UserPasswordViewController() {
        super(Context.PASSWORD);
        setModel(new ChangePassword());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object edit(String guid) throws Exception {
        getModel().clear();
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object save() throws Exception {
        ChangePassword password = getModel();
        if (password.isValid()) {
            try {
                PasswordService passwordService = PasswordServiceFactory.getService();
                UserPassword up = service.getUserPasswordByPrincipal(principal.getName());
                if (passwordService.verifySecretPassword(password.getOld(), up.getPassword())) {
                    String tmp = passwordService.updateSecretPassword(password.getOld(), password.getNew1(), up.getPassword());
                    up.setPassword(tmp);
                    service.saveUserPassword(up);
                    getModel().clear();
                } else {
                    FacesResourceUtil.addFacesErrorMessage(ValidationErrorKey.PASSWORD_DOES_NOT_MACH_OLD);
                    FacesContext.getCurrentInstance().validationFailed();
                }
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

}
