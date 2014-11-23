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

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The build.
 *
 * @author Andrej Petras
 */
@Entity
@Table(name = "TW_ST_BUILD", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"C_DATE", "C_KEY", "C_APP"})})
public class Build extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1095643007199796298L;

    /**
     * The application.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_APP")
    private Application application;

    /**
     * The agent.
     */
    @Column(name = "C_AGENT")
    private String agent;

    /**
     * The UID.
     */
    @Column(name = "C_UID")
    private String uid;

    /**
     * The key.
     */
    @Column(name = "C_KEY")
    private String key;

    /**
     * The version.
     */
    @Column(name = "C_VERSION")
    private Integer ver;

    /**
     * The date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "C_DATE")
    private Date date;

    /**
     * The install date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "C_INSTALL")
    private Date install;

    /**
     * The service.
     */
    @Column(name = "C_SERVICE")
    private String service;

    /**
     * The group ID.
     */
    @Column(name = "C_MVN_GROUP_ID")
    private String groupId;

    /**
     * The artifact ID.
     */
    @Column(name = "C_MVN_ARTIFACT_ID")
    private String artifactId;

    /**
     * The MAVEN version.
     */
    @Column(name = "C_MVN_VERSION")
    private String mavenVersion;

    /**
     * The source code management.
     */
    @Column(name = "C_SCM")
    private String scm;

    /**
     * The build.
     */
    @Column(name = "C_BUILD")
    private String build;

    /**
     * The project version.
     */
    @Column(name = "C_PROJECT_VERSION")
    private String projectVersion;

    /**
     * The build parameters.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "C_BUILD")
    private Set<BuildParameter> parameters;

    /**
     * Gets the project version.
     *
     * @return the project version.
     */
    public String getProjectVersion() {
        return projectVersion;
    }

    /**
     * Sets the project version.
     *
     * @param projectVersion the project version.
     */
    public void setProjectVersion(String projectVersion) {
        this.projectVersion = projectVersion;
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
     * Gets the date of the install.
     *
     * @return the date of the install.
     */
    public Date getInstall() {
        return install;
    }

    /**
     * Sets the install date.
     *
     * @param install the install date.
     */
    public void setInstall(Date install) {
        this.install = install;
    }

    /**
     * Gets the agent.
     *
     * @return the agent.
     */
    public String getAgent() {
        return agent;
    }

    /**
     * Sets the agent.
     *
     * @param agent the agent.
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }

    /**
     * Gets the list of build parameter.
     *
     * @return the list of build parameter.
     */
    public Set<BuildParameter> getParameters() {
        return parameters;
    }

    /**
     * Sets the list of build parameters.
     *
     * @param parameters the list of build parameters.
     */
    public void setParameters(Set<BuildParameter> parameters) {
        this.parameters = parameters;
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
     * Gets the application.
     *
     * @return the application.
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Sets the application.
     *
     * @param application the application.
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    /**
     * Gets the UID.
     *
     * @return the UID.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets the UID.
     *
     * @param uid the UID to set.
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Gets the version of the request.
     *
     * @return the version of the request.
     */
    public int getVer() {
        return ver;
    }

    /**
     * Sets the version of the request.
     *
     * @param ver the version of the request to set
     */
    public void setVer(int ver) {
        this.ver = ver;
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
     * @param date the date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets the service.
     *
     * @return the service.
     */
    public String getService() {
        return service;
    }

    /**
     * Sets the service.
     *
     * @param service the service to set.
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * Gets the group ID.
     *
     * @return the group ID.
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Sets the group ID.
     *
     * @param groupId the group ID to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets the artifact ID.
     *
     * @return the artifact ID.
     */
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * Sets the artifact ID .
     *
     * @param artifactId the artifact ID to set.
     */
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    /**
     * Gets the MAVEN version.
     *
     * @return the MAVEN version.
     */
    public String getMavenVersion() {
        return mavenVersion;
    }

    /**
     * Sets the MAVEN version.
     *
     * @param mavenVersion the MAVEN version to set.
     */
    public void setMavenVersion(String mavenVersion) {
        this.mavenVersion = mavenVersion;
    }

    /**
     * Gets the SCM.
     *
     * @return the SCM.
     */
    public String getScm() {
        return scm;
    }

    /**
     * Sets the SCM.
     *
     * @param scm the SCM to set.
     */
    public void setScm(String scm) {
        this.scm = scm;
    }

}
