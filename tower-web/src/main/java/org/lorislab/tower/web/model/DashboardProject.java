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
import java.util.Set;

/**
 * The dashboard project.
 *
 * @author Andrej Petras
 */
public class DashboardProject implements Serializable {
    private static final long serialVersionUID = -7702438629479435490L;

    /**
     * The GUID.
     */
    private String guid;
    /**
     * The name.
     */
    private String name;
    /**
     * The index.
     */
    private Integer index;
    /**
     * The map of dashboard applications.
     */
    private List<DashboardApplication> applications;

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
     * @return the applications
     */
    public List<DashboardApplication> getApplications() {
        return applications;
    }

    /**
     * @param applications the applications to set
     */
    public void setApplications(List<DashboardApplication> applications) {
        this.applications = applications;
    }
    
    
}
