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
 *
 * @author Andrej Petras
 */
@Entity
@Table(name = "TW_ST_NOTIFY_GROUP", uniqueConstraints = { @UniqueConstraint(columnNames = "C_NAME") })
public class NotificationGroup extends Persistent {
    
    private static final long serialVersionUID = 8971299788310715067L;
    
    @Column(name = "C_NAME")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "TW_ST_NOTIFY_USERS",
            joinColumns = @JoinColumn(name = "C_USER_GUID")            
    )
    @Column(name = "C_USERS")
    private Set<String> users;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "TW_ST_NOTIFY_SYS",
            joinColumns = @JoinColumn(name = "C_SYSTEM_GUID")            
    )
    @Column(name = "C_USERS")
    private Set<String> systems;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getSystems() {
        return systems;
    }

    public void setSystems(Set<String> systems) {
        this.systems = systems;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }
                
}
