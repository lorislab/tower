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
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.lorislab.tower.store.criteria.BuildCriteria;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.Build;
import org.lorislab.tower.store.model.Build_;
import org.lorislab.tower.store.model.Application_;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The build service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class BuildService extends AbstractEntityServiceBean<Build> {

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
     * Saves the store build.
     *
     * @param data the build.
     * @return the saved build.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Build saveBuild(Build data) {
        return this.save(data);
    }

    /**
     * Gets the build by GUID.
     *
     * @param guid the GUID.
     * @return the corresponding build.
     */
    public Build getBuild(String guid) {
        BuildCriteria criteria = new BuildCriteria();
        criteria.setGuid(guid);
        return getBuild(criteria);
    }

    /**
     * Gets the build by criteria.
     *
     * @param criteria the criteria.
     * @return the corresponding build.
     */
    public Build getBuild(BuildCriteria criteria) {
        Build result = null;
        List<Build> tmp = getBuilds(criteria);
        if (tmp != null && !tmp.isEmpty()) {
            result = tmp.get(0);
        }
        return result;
    }

    /**
     * Gets all builds.
     *
     * @return the list of all builds.
     */
    public List<Build> getBuilds() {
        return getBuilds(new BuildCriteria());
    }

    /**
     * Gets the builds by criteria.
     *
     * @param criteria the criteria.
     * @return the corresponding list of criteria.
     */
    public List<Build> getBuilds(BuildCriteria criteria) {
        List<Build> result = new ArrayList<>();

        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<Build> cq = getBaseEAO().createCriteriaQuery();
        Root<Build> root = cq.from(Build.class);

        List<Predicate> predicates = new ArrayList<>();

        if (criteria.isFetchParameters()) {
            root.fetch(Build_.parameters, JoinType.LEFT);
        }

        if (criteria.isFetchApplication()) {
            Fetch<Build, Application> af = root.fetch(Build_.application, JoinType.LEFT);            
            
            if (criteria.isFetchApplicationProject()) {
                af.fetch(Application_.project, JoinType.LEFT);
            }
        }

        if (criteria.getMavenVersion() != null) {
            predicates.add(cb.equal(root.get(Build_.mavenVersion), criteria.getMavenVersion()));
        }

        if (criteria.getApplication() != null) {
            predicates.add(cb.equal(root.get(Build_.application).get(Application_.guid), criteria.getApplication()));
        }

        if (criteria.getGuid() != null) {
            predicates.add(cb.equal(root.get(Build_.guid), criteria.getGuid()));
        }

        if (criteria.getAgent() != null) {
            predicates.add(cb.equal(root.get(Build_.agent), criteria.getAgent()));
        }

        if (criteria.getDate() != null) {
            predicates.add(cb.equal(root.get(Build_.date), criteria.getDate()));
        }

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        List<Order> orders = new ArrayList<>();
        
        if (criteria.getOrderByDate() != null) {
            if (criteria.getOrderByDate()) {
                orders.add( cb.asc(root.get(Build_.date)));
            } else {
                orders.add( cb.desc(root.get(Build_.date)));
            }
        }
        
        if (!orders.isEmpty()) {
            cq.orderBy(orders);
        }
        
        try {
            TypedQuery<Build> typeQuery = getBaseEAO().createTypedQuery(cq);
            result = typeQuery.getResultList();
        } catch (NoResultException ex) {
            // do nothing
        }
        return result;
    }
}
