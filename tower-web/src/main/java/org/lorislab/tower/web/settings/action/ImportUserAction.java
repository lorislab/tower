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
package org.lorislab.tower.web.settings.action;

import org.lorislab.guardian.user.api.model.UserSourceData;
import org.lorislab.jel.jsf.view.controller.action.AbstractAction;
import org.lorislab.tower.web.common.action.Permission;
import org.lorislab.tower.web.settings.view.UserViewController;

/**
 * The import user action.
 *
 * @author Andrej_Petras
 */
public class ImportUserAction extends AbstractAction<UserViewController> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 6247687898528170823L;

    /**
     * The user source data.
     */
    private UserSourceData user;
    
    /**
     * The default constructor.
     *
     * @param parent the parent view controller.
     * @param context the context.
     */
    public ImportUserAction(UserViewController parent, Enum context) {
        super(parent, context, Permission.IMPORT);
    }

    public void setUser(UserSourceData user) {
        this.user = user;
    }

    @Override
    protected Object doExecute() throws Exception {
        return getParent().importUser(user);
    }


    
}
