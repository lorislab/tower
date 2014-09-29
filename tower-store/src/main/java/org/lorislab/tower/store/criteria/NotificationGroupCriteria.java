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
package org.lorislab.tower.store.criteria;

import org.lorislab.jel.base.criteria.AbstractSearchCriteria;

/**
 * The notification group criteria.
 *
 * @author Andrej Petras
 */
public class NotificationGroupCriteria extends AbstractSearchCriteria {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -4685095438105046328L;

    /**
     * The system.
     */
    private String system;

    /**
     * The user.
     */
    private String user;

    /**
     * The application.
     */
    private String application;

    /**
     * The fetch users flag.
     */
    private boolean fetchUsers;

    /**
     * The fetch systems flag.
     */
    private boolean fetchSystems;

    /**
     * The fetch applications flag.
     */
    private boolean fetchApplications;

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        system = null;
        user = null;
        application = null;
        fetchSystems = false;
        fetchUsers = false;
        fetchApplications = false;
    }

    /**
     * Gets the application.
     *
     * @return the application.
     */
    public String getApplication() {
        return application;
    }

    /**
     * Sets the application.
     *
     * @param application the application.
     */
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * Sets the fetch applications flag.
     *
     * @param fetchApplications the fetch applications flag.
     */
    public void setFetchApplications(boolean fetchApplications) {
        this.fetchApplications = fetchApplications;
    }

    /**
     * Gets the fetch applications flag.
     *
     * @return the fetch applications flag.
     */
    public boolean isFetchApplications() {
        return fetchApplications;
    }

    /**
     * Sets the fetch systems flag.
     *
     * @param fetchSystems the fetch systems flag.
     */
    public void setFetchSystems(boolean fetchSystems) {
        this.fetchSystems = fetchSystems;
    }

    /**
     * Gets the fetch systems flag.
     *
     * @return the fetch systems flag.
     */
    public boolean isFetchSystems() {
        return fetchSystems;
    }

    /**
     * Sets the fetch users flag.
     *
     * @param fetchUsers the fetch users flag.
     */
    public void setFetchUsers(boolean fetchUsers) {
        this.fetchUsers = fetchUsers;
    }

    /**
     * Gets the fetch users flag.
     *
     * @return the fetch users flag.
     */
    public boolean isFetchUsers() {
        return fetchUsers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return isEmpty(system, user);
    }

    /**
     * Gets the user.
     *
     * @return the user.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the user.
     *
     * @param user the user.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets the system.
     *
     * @return the system.
     */
    public String getSystem() {
        return system;
    }

    /**
     * Sets the system.
     *
     * @param system the system.
     */
    public void setSystem(String system) {
        this.system = system;
    }

}
