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
package org.lorislab.tower.web.common.model;

import java.io.Serializable;
import org.lorislab.guardian.app.model.Role;

/**
 * The role item.
 *
 * @author Andrej_Petras
 */
public class RoleItem implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 2603014614581275490L;

    /**
     * The role.
     */
    private Role role;

    /**
     * The selected flag.
     */
    private boolean selected;

    /**
     * The default constructor.
     *
     * @param role the role.
     * @param selected the selected flag.
     */
    public RoleItem(Role role, boolean selected) {
        this.role = role;
        this.selected = selected;
    }

    /**
     * Gets the role.
     *
     * @return the role.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the role.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the selected flag.
     *
     * @return the selected flag.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Sets the selected flag.
     *
     * @param selected the selected flag.
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
