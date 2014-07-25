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
package org.lorislab.tower.store.criteria;

import java.util.Set;
import org.lorislab.jel.base.criteria.AbstractSearchCriteria;

/**
 * The application criteria.
 *
 * @author Andrej Petras
 */
public class ApplicationCriteria extends AbstractSearchCriteria {

    /**
     * The timeout configuration: second.
     */
    private static final long serialVersionUID = -506625437892985522L;

    /**
     * The GUID.
     */
    private String guid;

    /**
     * The enable flag.
     */
    private Boolean enabled;
    /**
     * The set of projects.
     */
    private Set<String> projects;
    /**
     * The fetch SCM flag.
     */
    private boolean fetchSCM;
    /**
     * The fetch project flag.
     */
    private boolean fetchProject;
    /**
     * The fetch project BTS flag.
     */
    private boolean fetchProjectBts;
    /**
     * The fetch system flag.
     */
    private boolean fetchSystem;
    /**
     * The fetch builds flag.
     */
    private boolean fetchBuilds;
    /**
     * The fetch builds version.
     */
    private String fetchBuildsVersion;
    /**
     * The system.
     */
    private String system;
    /**
     * The build.
     */
    private String build;
    /**
     * The key.
     */
    private String key;

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
     * Gets the fetch project BTS flag.
     *
     * @return the fetch project BTS flag.
     */
    public boolean isFetchProjectBts() {
        return fetchProjectBts;
    }

    /**
     * Sets the fetch project BTS flag.
     *
     * @param fetchProjectBts the fetch project BTS flag.
     */
    public void setFetchProjectBts(boolean fetchProjectBts) {
        this.fetchProjectBts = fetchProjectBts;
    }

    /**
     * Gets the fetch builds version.
     *
     * @return the fetch builds version.
     */
    public String getFetchBuildsVersion() {
        return fetchBuildsVersion;
    }

    /**
     * Sets the fetch builds version.
     *
     * @param buildVersion the fetch builds version.
     */
    public void setFetchBuildsVersion(String fetchBuildsVersion) {
        this.fetchBuildsVersion = fetchBuildsVersion;
    }

    /**
     * Gets the build.
     *
     * @return the build.
     */
    public String getBuild() {
        return build;
    }

    /**
     * Sets the build.
     *
     * @param build the build.
     */
    public void setBuild(String build) {
        this.build = build;
    }

    /**
     * Gets the fetch builds flag.
     *
     * @return the fetch builds flag.
     */
    public boolean isFetchBuilds() {
        return fetchBuilds;
    }

    /**
     * Sets the fetch builds flag.
     *
     * @param fetchBuilds the fetch builds flag.
     */
    public void setFetchBuilds(boolean fetchBuilds) {
        this.fetchBuilds = fetchBuilds;
    }

    /**
     * Gets the fetch system flag.
     *
     * @return the fetch system flag.
     */
    public boolean isFetchSystem() {
        return fetchSystem;
    }

    /**
     * Sets the fetch system flag.
     *
     * @param fetchSystem the fetch system flag.
     */
    public void setFetchSystem(boolean fetchSystem) {
        this.fetchSystem = fetchSystem;
    }

    /**
     * Gets the system.
     *
     * @return the system.
     */
    public String getSystem() {
        return system;
    }

    /**
     * Sets the system.
     *
     * @param system the system.
     */
    public void setSystem(String system) {
        this.system = system;
    }

    /**
     * Gets the fetch project flag.
     *
     * @return the fetch project flag.
     */
    public boolean isFetchProject() {
        return fetchProject;
    }

    /**
     * Sets the fetch project flag.
     *
     * @param fetchProject the fetch project flag.
     */
    public void setFetchProject(boolean fetchProject) {
        this.fetchProject = fetchProject;
    }

    /**
     * Gets the fetch SCM flag.
     *
     * @return the fetch SCM flag.
     */
    public boolean isFetchSCM() {
        return fetchSCM;
    }

    /**
     * Sets the fetch SCM flag.
     *
     * @param fetchSCM the fetch SCM flag.
     */
    public void setFetchSCM(boolean fetchSCM) {
        this.fetchSCM = fetchSCM;
    }

    /**
     * Gets the GUID.
     *
     * @return the GUID.
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Sets the GUID.
     *
     * @param guid the GUID.
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * Gets the enable flag.
     *
     * @return the enable flag.
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the enable flag.
     *
     * @param enabled the enable flag.
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Gets the set of projects.
     *
     * @return the set of projects.
     */
    public Set<String> getProjects() {
        return projects;
    }

    /**
     * Sets the set of projects.
     *
     * @param projects the set of projects.
     */
    public void setProjects(Set<String> projects) {
        this.projects = projects;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        projects = null;
        enabled = null;
        guid = null;
        fetchSCM = false;
        fetchProject = false;
        system = null;
        fetchSystem = false;
        build = null;
        fetchBuildsVersion = null;
        fetchProjectBts = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return isEmpty(enabled, projects, guid, system, build, fetchBuildsVersion);
    }

}
