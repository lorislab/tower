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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;
import org.lorislab.tower.store.criteria.NotificationGroupCriteria;
import org.lorislab.tower.store.model.Application_;
import org.lorislab.tower.store.model.NotificationGroup;
import org.lorislab.tower.store.model.NotificationGroup_;
import org.lorislab.tower.store.model.Project;

/**
 * The notification group service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class NotificationGroupService extends AbstractEntityServiceBean<NotificationGroup> {

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
     * Saves the notification group.
     *
     * @param group the notification group.
     * @return the saved notification group.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public NotificationGroup saveNotificationGroup(NotificationGroup group) {
        return this.save(group);
    }

    /**
     * Gets the notification group by GUID.
     *
     * @param guid the notification group GUID.
     * @return the corresponding notification group.
     */
    public NotificationGroup getNotificationGroup(String guid) {
        return getById(guid);
    }

    /**
     * Gets the notification groups by criteria.
     *
     * @param criteria the notification criteria.
     * @return the corresponding list of notification groups.
     */
    public List<NotificationGroup> getNotificationGroups(NotificationGroupCriteria criteria) {
        List<NotificationGroup> result = null;

        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<NotificationGroup> cq = getBaseEAO().createCriteriaQuery();
        Root<NotificationGroup> root = cq.from(NotificationGroup.class);

        List<Predicate> predicates = new ArrayList<>();
        if (criteria != null) {
            if (criteria.getSystem() != null) {
                predicates.add(cb.isMember(criteria.getSystem(), root.get(NotificationGroup_.systems)));
            }

            if (criteria.getUser() != null) {
                predicates.add(cb.isMember(criteria.getUser(), root.get(NotificationGroup_.users)));
            }
        }
        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        try {
            TypedQuery<NotificationGroup> typeQuery = getBaseEAO().createTypedQuery(cq);
            result = typeQuery.getResultList();
        } catch (NoResultException ex) {
            // do nothing
        }

        return result;
    }
}
