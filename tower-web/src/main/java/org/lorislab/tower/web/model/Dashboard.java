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
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * The dashboard.
 *
 * @author Andrej Petras
 */
public class Dashboard implements Serializable {
    
    private static final long serialVersionUID = 4805569559391488649L;

    private boolean msg;
    /**
     * The size of the projects.
     */
    private int size;
    /**
     * The creation date.
     */
    private Date date;
    /**
     * The map of the dashboard projects.
     */
    private List<DashboardProject> projects;

    /**
     * @return the msg
     */
    public boolean isMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(boolean msg) {
        this.msg = msg;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the projects
     */
    public List<DashboardProject> getProjects() {
        return projects;
    }

    /**
     * @param projects the projects to set
     */
    public void setProjects(List<DashboardProject> projects) {
        this.projects = projects;
    }

    
}
