/*
 * Copyright 2013 lorislab.org.
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The project.
 *
 * @author Andrej Petras
 */
@Entity
@Table(name = "TW_ST_PROJECT")
public class Project extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -4437511008574023358L;

    @Column(name = "C_NAME")
    private String name;

    @Column(name = "C_BTS_ID")
    private String btsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_BTS")
    private BTSystem bts;

    /**
     * The enabled flag.
     */    
    @Column(name = "C_ENABLED")
    private boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "project")
    private Set<Application> applications;

    /**
     * The index.
     */
    @Column(name = "C_INDEX")
    private Integer index;

    /**
     * Gets the index.
     *
     * @return the index.
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Sets the index.
     *
     * @param index the index.
     */
    public void setIndex(Integer index) {
        this.index = index;
    }
    
    public BTSystem getBts() {
        return bts;
    }

    public void setBts(BTSystem bts) {
        this.bts = bts;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getBtsId() {
        return btsId;
    }

    public void setBtsId(String btsId) {
        this.btsId = btsId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
