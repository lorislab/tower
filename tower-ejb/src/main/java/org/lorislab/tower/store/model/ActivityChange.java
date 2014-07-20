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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.Persistent;
import org.lorislab.tower.store.model.enums.ActivityChangeError;

/**
 * The store activity change.
 *
 * @author Andrej Petras
 */
@Entity
@Table(name = "TW_ST_ACTIVITY_CHANGE")
public class ActivityChange extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -9133817705107708066L;

    /**
     * The activity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_ACTIVITY")
    private Activity activity;

    /**
     * The set of activity change logs.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, mappedBy = "change")
    private Set<ActivityLog> logs;

    /**
     * The type.
     */
    @Column(name = "C_TYPE")
    private String type;
    /**
     * The key.
     */
    @Column(name = "C_KEY")
    private String key;
    /**
     * The parent.
     */
    @Column(name = "C_PARENT")
    private String parent;
    /**
     * The status.
     */
    @Column(name = "C_STATUS")
    private String status;
    /**
     * The user.
     */
    @Column(name = "C_USER")
    private String user;
    /**
     * The description.
     */
    @Column(name = "C_DESCRIPTION")
    private String description;

    /**
     * The error.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "C_ERROR")
    private ActivityChangeError error;

    /**
     * Gets the error.
     *
     * @return the error.
     */
    public ActivityChangeError getError() {
        return error;
    }

    /**
     * Sets the error.
     *
     * @param error the error.
     */
    public void setError(ActivityChangeError error) {
        this.error = error;
    }

    /**
     * Gets the parent.
     *
     * @return the parent.
     */
    public String getParent() {
        return parent;
    }

    /**
     * Sets the parent.
     *
     * @param parent the parent.
     */
    public void setParent(String parent) {
        this.parent = parent;
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
     * Gets the type.
     *
     * @return the type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the status.
     *
     * @return the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the key.
     *
     * @return the key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key the key.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the description.
     *
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the set of activity change logs.
     *
     * @return the set of activity change logs.
     */
    public Set<ActivityLog> getLogs() {
        return logs;
    }

    /**
     * Sets the set of activity change logs.
     *
     * @param logs the set of activity change logs.
     */
    public void setLogs(Set<ActivityLog> logs) {
        this.logs = logs;
    }

    /**
     * Gets the activity.
     *
     * @return the activity.
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Sets the activity.
     *
     * @param activity the activity.
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

}
