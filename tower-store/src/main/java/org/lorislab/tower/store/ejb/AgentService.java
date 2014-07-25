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
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.lorislab.tower.store.criteria.AgentCriteria;
import org.lorislab.tower.store.model.Agent;
import org.lorislab.tower.store.model.Agent_;
import org.lorislab.tower.store.model.TargetSystem_;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The agent service.
 * 
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AgentService extends AbstractEntityServiceBean<Agent> {

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
     * Deletes the agent.
     *
     * @param guid the GUID.
     * @return <code>true</code> if the agent was deleted.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteAgent(String guid) {
        return this.delete(guid);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Agent saveAgent(Agent agent) {
        return this.save(agent);
    }

    public Agent getAgent(String guid) {
        AgentCriteria criteria = new AgentCriteria();
        criteria.setGuid(guid);
        return loadAgent(criteria);
    }

    public List<Agent> getAgents() {
        return getAgents(new AgentCriteria());
    }

    public Agent loadAgent(AgentCriteria criteria) {
        Agent result = null;
        List<Agent> tmp = getAgents(criteria);
        if (tmp != null && !tmp.isEmpty()) {
            result = tmp.get(0);
        }
        return result;
    }

    public List<Agent> getAgents(AgentCriteria criteria) {
        List<Agent> result = new ArrayList<>();

        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<Agent> cq = getBaseEAO().createCriteriaQuery();
        Root<Agent> root = cq.from(Agent.class);

        if (criteria.isFetchSystem()) {
            root.fetch(Agent_.systems, JoinType.LEFT);
        }

        List<Predicate> predicates = new ArrayList<>();
        if (criteria.getSystem() != null) {
            predicates.add(cb.in(root.join(Agent_.systems).get(TargetSystem_.guid)).value(criteria.getSystem()));
        }

        if (criteria.getGuid() != null) {
            predicates.add(cb.equal(root.get(Agent_.guid), criteria.getGuid()));
        }

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        try {
            TypedQuery<Agent> typeQuery = getBaseEAO().createTypedQuery(cq);
            result = typeQuery.getResultList();
        } catch (NoResultException ex) {
            // do nothing
        }
        return result;
    }
}
