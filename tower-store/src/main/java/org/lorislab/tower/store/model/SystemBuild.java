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

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.lorislab.jel.jpa.model.Persistent;
import org.lorislab.tower.store.model.enums.SystemBuildType;

/**
 * The system build.
 *
 * @author Andrej Petras
 */
@Entity
@Table(name = "TW_ST_SYSTEM_BUILD")
public class SystemBuild extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -5357640264532302086L;

    /**
     * The target system.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "C_SYSTEM")
    private TargetSystem system;

    /**
     * The build.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "C_BUILD")
    private Build build;

    /**
     * The date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "C_DATE")
    private Date date;

    /**
     * The system build type.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "C_TYPE")
    private SystemBuildType type;

    /**
     * Gets the system build type.
     *
     * @return the system build type.
     */
    public SystemBuildType getType() {
        return type;
    }

    /**
     * Sets the system build type.
     *
     * @param type the system build type.
     */
    public void setType(SystemBuildType type) {
        this.type = type;
    }

    /**
     * Gets the date.
     *
     * @return the date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date.
     *
     * @param date the date.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the target system.
     *
     * @return the target system.
     */
    public TargetSystem getSystem() {
        return system;
    }

    /**
     * Sets the target system.
     *
     * @param system the target system.
     */
    public void setSystem(TargetSystem system) {
        this.system = system;
    }

    /**
     * Gets the build.
     *
     * @return the build.
     */
    public Build getBuild() {
        return build;
    }

    /**
     * Sets the build.
     *
     * @param build the build.
     */
    public void setBuild(Build build) {
        this.build = build;
    }

}
