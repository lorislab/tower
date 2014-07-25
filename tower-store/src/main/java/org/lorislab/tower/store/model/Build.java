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
@Table(name = "TW_ST_BUILD", uniqueConstraints={@UniqueConstraint(columnNames={"C_DATE", "C_KEY", "C_APP"})})
public class Build extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1095643007199796298L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_APP")
    private Application application;

    @Column(name = "C_AGENT")
    private String agent;

    @Column(name = "C_UID")
    private String uid;

    @Column(name = "C_KEY")
    private String key;
    
    @Column(name = "C_VERSION")
    private Integer ver;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "C_DATE")
    private Date date;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "C_INSTALL")
    private Date install;
    
    @Column(name = "C_SERVICE")
    private String service;

    @Column(name = "C_MVN_GROUP_ID")
    private String groupId;

    @Column(name = "C_MVN_ARTIFACT_ID")
    private String artifactId;

    @Column(name = "C_MVN_VERSION")
    private String mavenVersion;

    @Column(name = "C_SCM")
    private String scm;

    @Column(name = "C_BUILD")
    private String build;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "C_BUILD")
    private Set<BuildParameter> parameters;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    
    public Date getInstall() {
        return install;
    }

    public void setInstall(Date install) {
        this.install = install;
    }
    
    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Set<BuildParameter> getParameters() {
        return parameters;
    }

    public void setParameters(Set<BuildParameter> parameters) {
        this.parameters = parameters;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return the ver
     */
    public int getVer() {
        return ver;
    }

    /**
     * @param ver the ver to set
     */
    public void setVer(int ver) {
        this.ver = ver;
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
     * @return the service
     */
    public String getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * @return the groupdId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupdId to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * @return the artifactId
     */
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * @param artifactId the artifactId to set
     */
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    /**
     * @return the mavenVersion
     */
    public String getMavenVersion() {
        return mavenVersion;
    }

    /**
     * @param mavenVersion the mavenVersion to set
     */
    public void setMavenVersion(String mavenVersion) {
        this.mavenVersion = mavenVersion;
    }

    /**
     * @return the scm
     */
    public String getScm() {
        return scm;
    }

    /**
     * @param scm the scm to set
     */
    public void setScm(String scm) {
        this.scm = scm;
    }

}
