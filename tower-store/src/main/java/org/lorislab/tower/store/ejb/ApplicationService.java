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
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.lorislab.tower.store.criteria.ApplicationCriteria;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.Application_;
import org.lorislab.tower.store.model.Build;
import org.lorislab.tower.store.model.Build_;
import org.lorislab.tower.store.model.Project;
import org.lorislab.tower.store.model.Project_;
import org.lorislab.tower.store.model.TargetSystem;
import org.lorislab.tower.store.model.TargetSystem_;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The application service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ApplicationService extends AbstractEntityServiceBean<Application> {

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
     * Deletes the application.
     *
     * @param guid the GUID.
     * @return <code>true</code> if the application was deleted.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteApplication(String guid) {
        return this.delete(guid);
    }

    /**
     * Saves the application.
     *
     * @param application the application.
     * @return the saved application.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Application saveApplication(Application application) {
        Application tmp = this.save(application);
        return getApplication(tmp.getGuid());
    }

    /**
     * Gets the application by GUID.
     *
     * @param guid the application GUID.
     * @return the corresponding application.
     */
    public Application getApplication(String guid) {
        ApplicationCriteria criteria = new ApplicationCriteria();
        criteria.setGuid(guid);
        return this.getById(guid);
    }

    /**
     * Gets the application by application criteria.
     *
     * @param criteria the application criteria.
     * @return the corresponding application.
     */
    public Application getApplication(ApplicationCriteria criteria) {
        Application result = null;
        List<Application> tmp = getApplications(criteria);
        if (tmp != null && !tmp.isEmpty()) {
            result = tmp.get(0);
        }
        return result;
    }

    /**
     * Gets the list of all applications.
     *
     * @return the list of all applications.
     */
    public List<Application> getApplications() {
        ApplicationCriteria criteria = new ApplicationCriteria();
        return getApplications(criteria);
    }

    /**
     * Gets the application object for the deployment the build on the system.
     *
     * @param system the system GUID.
     * @param build the build GUID.
     * 
     * @return the corresponding application.
     */
    public Application getApplicationForDeployment(String system, String build) {
        Application result = null;

        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<Application> cq = getBaseEAO().createCriteriaQuery();
        Root<Application> root = cq.from(Application.class);

        cq.distinct(true);

        List<Predicate> predicates = new ArrayList<>();
        root.fetch(Application_.project, JoinType.LEFT);
        
        Join<Application, Build> fb = (Join<Application, Build>)  root.fetch(Application_.builds, JoinType.LEFT);
        predicates.add(cb.equal(fb.get(Build_.guid), build));                

        Join<Application, TargetSystem> fs = (Join<Application, TargetSystem>) root.fetch(Application_.systems, JoinType.LEFT);
        predicates.add(cb.equal(fs.get(TargetSystem_.guid), system));

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        try {
            TypedQuery<Application> typeQuery = getBaseEAO().createTypedQuery(cq);
            List<Application> tmp = typeQuery.getResultList();
            if (tmp != null && !tmp.isEmpty()) {
                result = tmp.get(0);
            }
        } catch (NoResultException ex) {
            // do nothing
        }
        return result;
    }
    
    /**
     * Gets the application object for the deployment list.
     *
     * @param system the system GUID.
     * @return the corresponding application.
     */
    public Application getApplicationForDeployment(String system) {
        Application result = null;

        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<Application> cq = getBaseEAO().createCriteriaQuery();
        Root<Application> root = cq.from(Application.class);

        cq.distinct(true);

        List<Predicate> predicates = new ArrayList<>();

        root.fetch(Application_.builds, JoinType.LEFT);
        root.fetch(Application_.project, JoinType.LEFT);

        Join<Application, TargetSystem> fs = (Join<Application, TargetSystem>) root.fetch(Application_.systems, JoinType.LEFT);
        predicates.add(cb.equal(fs.get(TargetSystem_.guid), system));

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        try {
            TypedQuery<Application> typeQuery = getBaseEAO().createTypedQuery(cq);
            List<Application> tmp = typeQuery.getResultList();
            if (tmp != null && !tmp.isEmpty()) {
                result = tmp.get(0);
            }
        } catch (NoResultException ex) {
            // do nothing
        }
        return result;
    }

    /**
     * Gets the applications by application criteria.
     *
     * @param criteria the application criteria.
     * @return the corresponding list of application.
     */
    public List<Application> getApplications(ApplicationCriteria criteria) {
        List<Application> result = new ArrayList<>();

        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<Application> cq = getBaseEAO().createCriteriaQuery();
        Root<Application> root = cq.from(Application.class);

        List<Predicate> predicates = new ArrayList<>();

        if (criteria.isFetchSCM()) {
            root.fetch(Application_.scm, JoinType.LEFT);
        }

        if (criteria.isFetchProject()) {
            Fetch<Application, Project> pf = root.fetch(Application_.project, JoinType.LEFT);

            if (criteria.isFetchProjectBts()) {
                pf.fetch(Project_.bts, JoinType.LEFT);
            }
        }

        if (criteria.isFetchSystem()) {
            root.fetch(Application_.systems, JoinType.LEFT);
        }

        if (criteria.isFetchBuilds()) {
            Fetch<Application, Build> fb = root.fetch(Application_.builds, JoinType.LEFT);

            if (criteria.getFetchBuildsVersion() != null) {
                Join<Application, Build> jb = (Join<Application, Build>) fb;
                predicates.add(cb.equal(jb.get(Build_.mavenVersion), criteria.getFetchBuildsVersion()));
            }
        }

        if (criteria.getGuid() != null) {
            predicates.add(cb.equal(root.get(Application_.guid), criteria.getGuid()));
        }

        if (criteria.getKey() != null) {
            predicates.add(cb.equal(root.get(Application_.key), criteria.getKey()));
        }

        if (criteria.getBuild() != null) {
            predicates.add(cb.in(root.join(Application_.builds).get(Build_.guid)).value(criteria.getBuild()));
        }

        if (criteria.isEnabled() != null) {
            predicates.add(cb.equal(root.get(Application_.enabled), criteria.isEnabled()));
        }

        if (criteria.getProjects() != null && !criteria.getProjects().isEmpty()) {
            predicates.add(root.get(Application_.project).in(criteria.getProjects()));
        }

        if (criteria.getSystem() != null) {
            predicates.add(cb.equal(root.join(Application_.systems).get(TargetSystem_.guid), criteria.getSystem()));
        }

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        try {
            TypedQuery<Application> typeQuery = getBaseEAO().createTypedQuery(cq);
            result = typeQuery.getResultList();
        } catch (NoResultException ex) {
            // do nothing
        }
        return result;
    }

}
