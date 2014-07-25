/*
 * Copyright 2014 Andrej Petras.
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
package org.lorislab.tower.guardian.config.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.lorislab.guardian.api.model.UserDataConfig;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The user profile.
 *
 * @author Andrej Petras
 */
@Entity(name = "UserConfig")
@Table(name = "TW_GU_CONFIG")
public class UserConfig extends Persistent implements UserDataConfig {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 7706500724718802209L;

    /**
     * The user.
     */
    @Column(name = "C_USER_GUID")
    private String user;

    /*
     * The first name.
     */
    @Column(name = "C_NOTIFY")
    private boolean notification;

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }
          
    /**
     * Gets the user.
     *
     * @return the user.
     */
    @Override
    public String getUser() {
        return user;
    }

    /**
     * Sets the user.
     *
     * @param user the user to set.
     */
    public void setUser(String user) {
        this.user = user;
    }

}
