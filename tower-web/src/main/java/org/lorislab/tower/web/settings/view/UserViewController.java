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

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.guardian.api.model.UserData;
import org.lorislab.guardian.api.user.model.UserSourceData;
import org.lorislab.jel.jsf.api.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.entity.controller.AbstractEntityViewController;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.action.Navigation;
import org.lorislab.tower.web.settings.action.ImportUserAction;

/**
 * The user view controller.
 *
 * @author Andrej_Petras
 */
@Named("userVC")
@SessionScoped
public class UserViewController extends AbstractEntityViewController<UserData> implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -9056528681264454508L;

    /**
     * The user import action.
     */
    private final ImportUserAction importAction;

    /**
     * The default constructor.
     */
    public UserViewController() {
        super(Context.USER);
        importAction = new ImportUserAction(this, Context.USER);
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
            UserData tmp = new UserData();
            setModel(tmp);
            result = Navigation.TO_USER_EDIT;
        }
        return result;
    }

}
