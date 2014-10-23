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
package org.lorislab.tower.store.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The notification group.
 *
 * @author Andrej Petras
 */
@Entity
@Table(name = "TW_ST_NOTIFY_GROUP", uniqueConstraints = {
    @UniqueConstraint(columnNames = "C_NAME")})
public class NotificationGroup extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 8971299788310715067L;

    /**
     * The name.
     */
    @Column(name = "C_NAME")
    private String name;

    /**
     * The enabled flag.
     */
    @Column(name = "C_ENABLED")
    private boolean enabled;

    /**
     * The set of users.
     */
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "TW_ST_NOTIFY_USERS",
            joinColumns = @JoinColumn(name = "C_USER_GUID")
    )
    private Set<String> users = new HashSet<>();

    /**
     * The set of systems.
     */
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "TW_ST_NOTIFY_SYS",
            joinColumns = @JoinColumn(name = "C_SYSTEM_GUID")
    )
    @Column(name = "C_SYSTEMS")
    private Set<String> systems = new HashSet<>();

    /**
     * The set of application.
     */
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "TW_ST_NOTIFY_APP",
            joinColumns = @JoinColumn(name = "C_APP_GUID")
    )
    private Set<String> applications = new HashSet<>();

    /**
     * Sets the enabled flag.
     *
     * @param enabled the enabled flag.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Gets the enabled flag.
     *
     * @return the enabled flag.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Gets the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the applications.
     *
     * @return the applications.
     */
    public Set<String> getApplications() {
        return applications;
    }

    /**
     * Sets the applications.
     *
     * @param applications the applications.
     */
    public void setApplications(Set<String> applications) {
        this.applications = applications;
    }

    /**
     * Gets the systems.
     *
     * @return the systems.
     */
    public Set<String> getSystems() {
        return systems;
    }

    /**
     * Sets the systems.
     *
     * @param systems the systems.
     */
    public void setSystems(Set<String> systems) {
        this.systems = systems;
    }

    /**
     * Gets the users.
     *
     * @return the users.
     */
    public Set<String> getUsers() {
        return users;
    }

    /**
     * Sets the users.
     *
     * @param users the users.
     */
    public void setUsers(Set<String> users) {
        this.users = users;
    }

}
