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

import org.lorislab.jel.jsf.view.controller.action.AbstractAction;
import org.lorislab.tower.web.common.action.Permission;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.settings.view.TimerViewController;

/**
 * The stop timer action.
 *
 * @author Andrej Petras
 */
public class StopTimerAction extends AbstractAction<TimerViewController> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -2567197259843467475L;

    /**
     * The default constructor.
     *
     * @param parent the parent view controller.
     */
    public StopTimerAction(TimerViewController parent) {
        super(parent, Context.TIMER, Permission.STOP);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected Object doExecute() throws Exception {
        return getParent().stop();
    }

    /**
     * {@inheritDoc }
     */    
    @Override
    public boolean isEnabled() {
        boolean result = super.isEnabled();
        if (result) {
            result = getParent().getDate() != null;
        }
        return result;
    }    
}
