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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.Persistent;
import org.lorislab.tower.store.model.enums.AgentType;

/**
 * The agent.
 *
 * @author Andrej Petras
 */
@Entity
@Table(name = "TW_ST_AGENT")
public class Agent extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1986215389250537156L;

    /**
     * The name.
     */
    @Column(name = "C_NAME")
    private String name;

    /**
     * The systems.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "agent")
    private Set<TargetSystem> systems;

    /**
     * The URL.
     */
    @Column(name = "C_URL")
    private String url;

    /**
     * The agent type.
     */
    @Column(name = "C_TYPE")
    @Enumerated(EnumType.STRING)
    private AgentType type;

    /**
     * The authentication flag.
     */
    @Column(name = "C_AUTH")
    private boolean authentication;

    /**
     * The user.
     */
    @Column(name = "C_USER")
    private String user;

    /**
     * The password.
     */
    @Column(name = "C_PASSWORD")
    private String password;

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
     * Gets the list of systems.
     *
     * @return the list of systems.
     */
    public Set<TargetSystem> getSystems() {
        return systems;
    }

    /**
     * The the list of systems.
     *
     * @param systems the list of systems.
     */
    public void setSystems(Set<TargetSystem> systems) {
        this.systems = systems;
    }

    /**
     * Gets the authentication flag.
     *
     * @return the authentication flag.
     */
    public boolean isAuthentication() {
        return authentication;
    }

    /**
     * Sets the authentication flag
     *
     * @param authentication the authentication flag
     */
    public void setAuthentication(boolean authentication) {
        this.authentication = authentication;
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
     * @param password the password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the URL.
     *
     * @return the URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL.
     *
     * @param url the URL.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the agent type.
     *
     * @return the agent type.
     */
    public AgentType getType() {
        return type;
    }

    /**
     * Sets the agent type.
     *
     * @param type the agent type.
     */
    public void setType(AgentType type) {
        this.type = type;
    }

}
