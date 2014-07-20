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
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The store activity model.
 *
 * @author Andrej Petras
 */
@Entity
@Table(name = "TW_ST_ACTIVITY")
public class Activity extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -5982578664161285029L;

    /**
     * The date.
     */
    @Column(name = "C_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    /**
     * The build.
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "C_BUILD")
    private Build build;

    /**
     * The set of activity changes.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, mappedBy = "activity")
    private Set<ActivityChange> changes;

    /**
     * Gets the set of activity changes.
     *
     * @return the set of activity changes.
     */
    public Set<ActivityChange> getChanges() {
        return changes;
    }

    /**
     * Sets the set of activity changes.
     *
     * @param changes the set of activity changes.
     */
    public void setChanges(Set<ActivityChange> changes) {
        this.changes = changes;
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
