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
package org.lorislab.tower.process.model;

import java.io.Serializable;

/**
 * The change password model.
 *
 * @author Andrej Petras
 */
public class ChangePassword implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -680608891056515070L;
    /**
     * The new password.
     */
    private char[] new1;
    /**
     * The confirm password.
     */
    private char[] new2;

    /**
     * Gets the new password.
     *
     * @return the new password.
     */
    public char[] getNew1() {
        return new1;
    }

    /**
     * Sets the new password.
     *
     * @param new1 the new password.
     */
    public void setNew1(char[] new1) {
        this.new1 = new1;
    }

    /**
     * Gets the confirm password.
     *
     * @return the confirm password.
     */
    public char[] getNew2() {
        return new2;
    }

    /**
     * Sets the confirm password.
     *
     * @param new2 the confirm password.
     */
    public void setNew2(char[] new2) {
        this.new2 = new2;
    }

    /**
     * Clears the password.
     */
    public void clear() {
        new1 = null;
        new2 = null;
    }
}
