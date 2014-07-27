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

package org.lorislab.tower.web.menu.action;

import javax.faces.context.FacesContext;
import org.lorislab.guardian.web.view.actions.AbstractContextControllerAction;
import org.lorislab.tower.web.common.action.Action;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.action.Navigation;
import org.lorislab.tower.web.menu.view.MenuViewController;

/**
 *
 * @author Andrej Petras
 */
public class LogoutMenuAction extends AbstractContextControllerAction<MenuViewController> {
    
    private static final long serialVersionUID = -9154512589848412309L;

    public LogoutMenuAction(MenuViewController parent) {
        super(parent, Context.MENU_LOGOUT, Action.EXECUTION);
    }
    
    @Override
    public Object execute() throws Exception {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return Navigation.TO_HOME;
    }    
}
