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
package org.lorislab.tower.web.dashboard.action;

import org.lorislab.jel.jsf.view.controller.action.AbstractAction;
import org.lorislab.tower.web.common.action.Permission;
import org.lorislab.tower.web.dashboard.view.DashboardViewController;

/**
 * The change view action.
 * 
 * @author Andrej_Petras
 */
public class DashboardViewAction extends AbstractAction<DashboardViewController> {
    
    private static final long serialVersionUID = 455067126589275560L;

    public DashboardViewAction(DashboardViewController parent, Enum context) {
        super(parent, context, Permission.CHANGE_VIEW);
    }

    @Override
    protected Object doExecute() throws Exception {
        getParent().setTable(false);
        return null;
    }
    
    
}
