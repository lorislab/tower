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

import org.lorislab.jel.jsf.view.controller.ViewController;

/**
 * The abstract change password view controller.
 *
 * @author Andrej Petras
 */
public interface ChangePasswordListener extends ViewController {

    /**
     * The change password.
     *
     * @param data the new password.
     */
    public void changePassword(String data);
    
    /**
     * Gets the password view controller.
     *
     * @return the password view controller.
     */    
    public ChangePasswordViewController getPasswordVC();
}
