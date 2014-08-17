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

import org.lorislab.guardian.web.view.ActionContextViewController;

/**
 * The change password view controller.
 *
 * @author Andrej Petras
 */
public interface ChangePasswordViewController extends ActionContextViewController {

    /**
     * Before opens the change password dialog.
     *
     * @return the navigation path.
     * @throws java.lang.Exception if the method fails.
     */
    public Object openPasswordChange() throws Exception;

    /**
     * The change password action.
     *
     * @return the navigation path.
     * @throws java.lang.Exception if the method fails.
     */
    public Object changePassword() throws Exception;
}
