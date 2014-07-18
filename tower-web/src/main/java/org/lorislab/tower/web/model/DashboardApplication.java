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
package org.lorislab.tower.web.model;

import java.io.Serializable;
import java.util.List;

/**
 * The dashboard application.
 *
 * @author Andrej Petras
 */
public class DashboardApplication implements Serializable {
    private static final long serialVersionUID = 1375715613493802021L;

    /**
     * The GUID.
     */
    private String guid;
    /**
     * The project GUID.
     */
    private String project;
    /**
     * The name.
     */
    private String name;
    /**
     * The index.
     */
    private Integer index;    
    /**
     * The set of dashboard application systems.
     */
    private List<DashboardApplicationSystem> systems;

    /**
     * @return the guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid the guid to set
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * @return the project
     */
    public String getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(String project) {
        this.project = project;
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

    /**
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * @return the systems
     */
    public List<DashboardApplicationSystem> getSystems() {
        return systems;
    }

    /**
     * @param systems the systems to set
     */
    public void setSystems(List<DashboardApplicationSystem> systems) {
        this.systems = systems;
    }
    
    
}
