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

import org.lorislab.guardian.web.view.actions.AbstractContextControllerAction;
import org.lorislab.tower.web.settings.view.BtsViewController;

/**
 *
 * @author Andrej Petras
 */
public class BtsChangePasswordAction extends AbstractContextControllerAction<BtsViewController> {
    
    private static final long serialVersionUID = 2144032715047939391L;

    public BtsChangePasswordAction(BtsViewController parent, Enum context, Enum action) {
        super(parent, context, action);
    }

    @Override
    protected Object doExecute() throws Exception {
        return getParent().changePassword();
    }
    
    public Object open() throws Exception {
        return getParent().openPasswordChange();
    }
    
}
