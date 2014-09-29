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
import org.lorislab.tower.store.model.enums.ApplicationScmRepository;

/**
 * The application.
 *
 * @author Andrej Petras
 */
@Entity
@Table(name = "TW_ST_APP")
public class Application extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -6964092704804204045L;

    /**
     * The project.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_PROJECT")
    private Project project;

    /**
     * The repository link.
     */
    @Column(name = "C_REPO_LINK")
    private String repoLink;

    /**
     * The SCM.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "C_SCM")
    private SCMSystem scm;

    /**
     * The notification flag.
     */
    @Column(name = "C_NOTIFY")
    private boolean notification;
    
    /**
     * The name.
     */
    @Column(name = "C_NAME")
    private String name;

    /**
     * The key for manual deployment.
     */
    @Column(name = "C_KEY")
    private String key;

    /**
     * The SCM trunk.
     */
    @Column(name = "C_SCM_TRUNK")
    private String scmTrunk;

    /**
     * The SCM tags.
     */
    @Column(name = "C_SCM_TAG")
    private String scmTags;

    /**
     * The SCM branches.
     */
    @Column(name = "C_SCM_BRANCHES")
    private String scmBranches;

    /**
     * The SCM repository.
     */
    @Column(name = "C_SCM_REPO")
    private String scmRepo;

    /**
     * The SCM repository type.
     */
    @Column(name = "C_SCM_TYPE")
    @Enumerated(EnumType.STRING)
    private ApplicationScmRepository scmType;

    /**
     * The enabled flag.
     */
    @Column(name = "C_ENABLED")
    private boolean enabled;

    /**
     * The type.
     */
    @Column(name = "C_TYPE")
    private String type;

    /**
     * The application builds.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "application")
    private Set<Build> builds;

    /**
     * Set of application systems.
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "application")
    private Set<TargetSystem> systems;

    /**
     * The index.
     */
    @Column(name = "C_INDEX")
    private Integer index;

    /**
     * Gets the notification flag.
     *
     * @return the notification flag.
     */
    public boolean isNotification() {
        return notification;
    }

    /**
     * Sets the notification flag.
     *
     * @param notification the notification flag.
     */
    public void setNotification(boolean notification) {
        this.notification = notification;
    }
    
    /**
     * Gets the SCM repository type.
     *
     * @return the SCM repository type.
     */
    public ApplicationScmRepository getScmType() {
        return scmType;
    }

    /**
     * Sets the SCM repository type.
     *
     * @param scmType the SCM repository type.
     */
    public void setScmType(ApplicationScmRepository scmType) {
        this.scmType = scmType;
    }

    /**
     * Gets the index.
     *
     * @return the index.
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Sets the index.
     *
     * @param index the index.
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * Gets the key for manual deployment.
     *
     * @return the key for manual deployment.
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key for manual deployment.
     *
     * @param key the key for manual deployment.
     */
    public void setKey(String key) {
        this.key = key;
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
     * Gets the SCM repository.
     *
     * @return the SCM repository.
     */
    public String getScmRepo() {
        return scmRepo;
    }

    /**
     * Sets the SCM repository.
     *
     * @param scmRepo the SCM repository.
     */
    public void setScmRepo(String scmRepo) {
        this.scmRepo = scmRepo;
    }

    /**
     * Gets the repository link.
     *
     * @return the repository link.
     */
    public String getRepoLink() {
        return repoLink;
    }

    /**
     * Sets the repository link.
     *
     * @param repoLink the repository link.
     */
    public void setRepoLink(String repoLink) {
        this.repoLink = repoLink;
    }

    public String getScmBranches() {
        return scmBranches;
    }

    public void setScmBranches(String scmBranches) {
        this.scmBranches = scmBranches;
    }

    public String getScmTags() {
        return scmTags;
    }

    public void setScmTags(String scmTags) {
        this.scmTags = scmTags;
    }

    public String getScmTrunk() {
        return scmTrunk;
    }

    public void setScmTrunk(String scmTrunk) {
        this.scmTrunk = scmTrunk;
    }

    public Set<TargetSystem> getSystems() {
        return systems;
    }

    public void setSystems(Set<TargetSystem> systems) {
        this.systems = systems;
    }

    public Set<Build> getBuilds() {
        return builds;
    }

    public void setBuilds(Set<Build> builds) {
        this.builds = builds;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
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
     * @return the SCM
     */
    public SCMSystem getScm() {
        return scm;
    }

    /**
     * @param scm the SCM to set
     */
    public void setScm(SCMSystem scm) {
        this.scm = scm;
    }

}
