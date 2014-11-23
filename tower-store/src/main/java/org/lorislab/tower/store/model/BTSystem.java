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

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The bug tracking system.
 *
 * @author Andrej Petras
 */
@Entity
@Table(name = "TW_ST_BTS")
public class BTSystem extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -4923603154378426272L;

    /**
     * The list of projects.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "bts")
    private Set<Project> projects;

    /**
     * The name.
     */
    @Column(name = "C_NAME")
    private String name;

    /**
     * The user.
     */
    @Column(name = "C_USER")
    private String user;

    /**
     * The password.
     */
    @Column(name = "C_PASWORD")
    private String password;

    /**
     * The authentication flag.
     */
    @Column(name = "C_AUTH")
    private boolean auth;

    /**
     * The server.
     */
    @Column(name = "C_SERVER")
    private String server;

    /**
     * The link.
     */
    @Column(name = "C_LINK")
    private String link;

    /**
     * The type.
     */
    @Column(name = "C_TYPE")
    private String type;

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
     * Gets the list of projects.
     *
     * @return the list of projects.
     */
    public Set<Project> getProjects() {
        return projects;
    }

    /**
     * Sets the list of projects.
     *
     * @param projects the projects to set.
     */
    public void setProjects(Set<Project> projects) {
        this.projects = projects;
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
     * @param user the user to set.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets the password.
     *
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the authentication flag.
     *
     * @return the authentication flag.
     */
    public boolean isAuth() {
        return auth;
    }

    /**
     * Sets the authentication flag
     *
     * @param auth the authentication flag.
     */
    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    /**
     * Gets the server.
     *
     * @return the server.
     */
    public String getServer() {
        return server;
    }

    /**
     * Sets the server.
     *
     * @param server the server to set.
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * Gets the link.
     *
     * @return the link.
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the link.
     *
     * @param link the link to set.
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Gets the BTS type.
     *
     * @return the BTS type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

}
