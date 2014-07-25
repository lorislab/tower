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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;
import org.lorislab.tower.store.criteria.TargetSystemCriteria;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.Application_;
import org.lorislab.tower.store.model.TargetSystem;
import org.lorislab.tower.store.model.TargetSystem_;

/**
 * The system service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TargetSystemService extends AbstractEntityServiceBean<TargetSystem> {

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
     * Deletes the system.
     *
     * @param guid the GUID.
     * @return <code>true</code> if the system was deleted.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteSystem(String guid) {
        return this.delete(guid);
    }
    
    /**
     * Saves the system.
     *
     * @param system the system.
     * @return the saved system.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public TargetSystem saveSystem(TargetSystem system) {
        return this.save(system);
    }

    /**
     * Gets the system by GUID.
     *
     * @param guid the GUID.
     * @return the corresponding system.
     */
    public TargetSystem getSystem(String guid) {
        TargetSystemCriteria criteria = new TargetSystemCriteria();
        criteria.setGuid(guid);
        return getSystem(criteria);
    }

    /**
     * Gets the system by criteria.
     *
     * @param criteria the criteria.
     * @return the corresponding system.
     */
    public TargetSystem getSystem(TargetSystemCriteria criteria) {
        TargetSystem result = null;
        List<TargetSystem> tmp = getSystems(criteria);
        if (tmp != null && !tmp.isEmpty()) {
            result = tmp.get(0);
        }
        return result;
    }

    /**
     * Gets the list of all systems.
     *
     * @return the list of all systems.
     */
    public List<TargetSystem> getSystems() {
        return getSystems(new TargetSystemCriteria());
    }

    /**
     * Gets the list of systems by criteria.
     *
     * @param criteria the criteria.
     * @return the list of systems corresponding to the criteria.
     */
    public List<TargetSystem> getSystems(TargetSystemCriteria criteria) {
        List<TargetSystem> result = new ArrayList<>();

        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<TargetSystem> cq = getBaseEAO().createCriteriaQuery();
        Root<TargetSystem> root = cq.from(TargetSystem.class);

        if (criteria.isFetchAgent()) {
            root.fetch(TargetSystem_.agent, JoinType.LEFT);
        }

        if (criteria.isFetchApplication()) {
            Fetch<TargetSystem, Application> pf = root.fetch(TargetSystem_.application, JoinType.LEFT);
            
            if (criteria.isFetchApplicationProject()) {
                pf.fetch(Application_.project, JoinType.LEFT);
            }            
        }

        List<Predicate> predicates = new ArrayList<>();
        if (criteria.isEnabled() != null) {
            predicates.add(cb.equal(root.get(TargetSystem_.enabled), criteria.isEnabled()));
        }

        if (criteria.getGuid() != null) {
            predicates.add(cb.equal(root.get(TargetSystem_.guid), criteria.getGuid()));
        }

        if (criteria.getKey() != null) {
            predicates.add(cb.equal(root.get(TargetSystem_.key), criteria.getKey()));
        }
        
        if (criteria.isTimer() != null) {
            predicates.add(cb.equal(root.get(TargetSystem_.timer), criteria.isTimer()));
        }

        if (criteria.getApplications() != null && !criteria.getApplications().isEmpty()) {
            predicates.add(root.get(TargetSystem_.application).get(Application_.guid).in(criteria.getApplications()));
        }

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        try {
            TypedQuery<TargetSystem> typeQuery = getBaseEAO().createTypedQuery(cq);
            result = typeQuery.getResultList();
        } catch (NoResultException ex) {
            // do nothing
        }
        return result;
    }
}
