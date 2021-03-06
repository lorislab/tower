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

import org.lorislab.jel.jsf.view.controller.AbstractChildViewController;
import java.util.UUID;
import org.lorislab.tower.web.common.action.ClearKeyAction;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.action.CreateKeyAction;
import org.lorislab.tower.web.common.action.Permission;

/**
 * The key view controller.
 *
 * @author Andrej Petras
 */
public class KeyViewController extends AbstractChildViewController<KeyListener> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 1956712426617362245L;

    /**
     * The create key action.
     */
    private final CreateKeyAction createKeyAction;

    /**
     * The clear key action.
     */
    private final ClearKeyAction clearKeyAction;

    /**
     * The default constructor.
     *
     * @param parent the parent view controller.
     * @param context the context.
     */
    public KeyViewController(KeyListener parent, Context context) {
        super(parent);
        clearKeyAction = new ClearKeyAction(this, context, Permission.KEY);
        createKeyAction = new CreateKeyAction(this, context, Permission.KEY);
    }

    /**
     * Gets the clear key action.
     *
     * @return the clear key action.
     */    
    public ClearKeyAction getClearKeyAction() {
        return clearKeyAction;
    }

    /**
     * Gets the create key action.
     *
     * @return the create key action.
     */
    public CreateKeyAction getCreateKeyAction() {
        return createKeyAction;
    }

    /**
     * Clears the key in the parent view controller.
     *
     * @return the navigation path.
     * @throws java.lang.Exception if the method fails.
     */
    public Object clearKey() throws Exception {
        getParent().setKey(null);
        return null;
    }

    /**
     * The create key action.
     *
     * @return the navigation path.
     * @throws java.lang.Exception if the method fails.
     */
    public Object createKey() throws Exception {
        String tmp = UUID.randomUUID().toString();
        getParent().setKey(tmp);
        return null;
    }
  
}
