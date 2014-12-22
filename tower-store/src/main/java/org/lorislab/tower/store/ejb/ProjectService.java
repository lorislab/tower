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
package org.lorislab.tower.store.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.lorislab.tower.store.criteria.ProjectCriteria;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.Application_;
import org.lorislab.tower.store.model.Project;
import org.lorislab.tower.store.model.Project_;
import org.lorislab.tower.store.model.TargetSystem;
import org.lorislab.tower.store.model.TargetSystem_;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The project service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ProjectService extends AbstractEntityServiceBean<Project> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -4937927663216469945L;

    /**
     * The entity manager.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Saves the project.
     *
     * @param project the project.
     * @return the saved project.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Project saveProject(Project project) {
        return this.save(project);
    }

    /**
     * Deletes the project.
     *
     * @param guid the GUID.
     * @return <code>true</code> if the project was deleted.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteProject(String guid) {
        return this.delete(guid);
    }

    /**
     * Gets the project by GUID.
     *
     * @param guid the GUID.
     * @return the project corresponding to the GUID.
     */
    public Project getProject(String guid) {
        ProjectCriteria criteria = new ProjectCriteria();
        criteria.setGuid(guid);
        return getProject(criteria);
    }

    /**
     * Gets the project by criteria.
     *
     * @param criteria the criteria.
     * @return the project corresponding to the criteria.
     */
    public Project getProject(ProjectCriteria criteria) {
        List<Project> tmp = getProjects(criteria);
        if (tmp != null && !tmp.isEmpty()) {
            return tmp.get(0);
        }
        return null;
    }

    /**
     * Gets the list of all projects.
     *
     * @return the list of all projects.
     */
    public List<Project> getProjects() {
        return getProjects(new ProjectCriteria());
    }

    /**
     * Gets the projects list for the dashboard.
     *
     * @return the projects list for the dashboard.
     */
    public List<Project> getDashboardProjects() {
        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<Project> cq = getBaseEAO().createCriteriaQuery();
        Root<Project> root = cq.from(Project.class);

        cq.distinct(true);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get(Project_.enabled), true));

        Join<Project, Application> applications = (Join<Project, Application>) root.fetch(Project_.applications, JoinType.LEFT);
        predicates.add(cb.equal(applications.get(Application_.enabled), true));

        Join<Application, TargetSystem> systems = (Join<Application, TargetSystem>) applications.fetch(Application_.systems, JoinType.LEFT);
        predicates.add(cb.equal(systems.get(TargetSystem_.enabled), true));

        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        TypedQuery<Project> typeQuery = getBaseEAO().createTypedQuery(cq);
        List<Project> result = typeQuery.getResultList();

        return result;
    }

    /**
     * Gets the list of projects by the criteria.
     *
     * @param criteria the criteria.
     * @return the list of projects corresponding to the criteria.
     */
    public List<Project> getProjects(ProjectCriteria criteria) {
        List<Project> result = new ArrayList<>();

        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<Project> cq = getBaseEAO().createCriteriaQuery();
        Root<Project> root = cq.from(Project.class);

        if (criteria.isFetchApplication()) {
            root.fetch(Project_.applications, JoinType.LEFT);
        }

        if (criteria.isFetchBTS()) {
            root.fetch(Project_.bts, JoinType.LEFT);
        }

        List<Predicate> predicates = new ArrayList<>();
        if (criteria.isEnabled() != null) {
            predicates.add(cb.equal(root.get(Project_.enabled), criteria.isEnabled()));
        }

        if (criteria.getGuid() != null) {
            predicates.add(cb.equal(root.get(Project_.guid), criteria.getGuid()));
        }

        if (criteria.getApplication() != null) {
            predicates.add(cb.in(root.join(Project_.applications).get(Application_.guid)).value(criteria.getApplication()));
        }

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        try {
            TypedQuery<Project> typeQuery = getBaseEAO().createTypedQuery(cq);
            result = typeQuery.getResultList();
        } catch (NoResultException ex) {
            // do nothing
        }
        return result;
    }
}
