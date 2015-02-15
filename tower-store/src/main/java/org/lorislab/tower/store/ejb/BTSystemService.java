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
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.lorislab.tower.store.criteria.BTSystemCriteria;
import org.lorislab.tower.store.model.BTSystem;
import org.lorislab.tower.store.model.BTSystem_;
import org.lorislab.tower.store.model.Project_;

/**
 * The bug tracking system service.
 * 
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class BTSystemService extends AbstractStoreEntityServiceBean<BTSystem> {
   
    /**
     * Saves the BTS system.
     * @param system the BTS system to be saved.
     * @return the saved BTS system.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public BTSystem saveBTSystem(BTSystem system) {
        return this.save(system);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteBTSystem(String guid) {
        return this.delete(guid);
    }
    
    public BTSystem getBTSystem(String guid) {
        return getById(guid);
    }
    
    public BTSystem getBTSystem(BTSystemCriteria criteria) {
        List<BTSystem> tmp = getBTSystems(criteria);
        if (tmp != null && !tmp.isEmpty()) {
            return tmp.get(0);
        }
        return null;
    }
    
    public List<BTSystem> getBTSystems() {
        return getBTSystems(new BTSystemCriteria());
    }
    
    /**
     * Gets the list of bug tracking systems by criteria.
     *
     * @param criteria the criteria.
     * @return the corresponding list of bug tracking systems.
     */
    public List<BTSystem> getBTSystems(BTSystemCriteria criteria) {
        List<BTSystem> result = new ArrayList<>();

        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<BTSystem> cq = getBaseEAO().createCriteriaQuery();
        Root<BTSystem> root = cq.from(BTSystem.class);

        if (criteria.isFetchProject()) {
            root.fetch(BTSystem_.projects, JoinType.LEFT);
        }

        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getGuid() != null) {
            predicates.add(cb.equal(root.get(BTSystem_.guid), criteria.getGuid()));
        }

        if (criteria.getProject() != null) {
            predicates.add(cb.in(root.join(BTSystem_.projects).get(Project_.guid)).value(criteria.getProject()));
        }

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        try {
            TypedQuery<BTSystem> typeQuery = getBaseEAO().createTypedQuery(cq);
            result = typeQuery.getResultList();
        } catch (NoResultException ex) {
            // do nothing
        }
        return result;
    }    
}
