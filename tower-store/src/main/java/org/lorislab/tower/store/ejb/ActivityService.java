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
import org.lorislab.tower.store.criteria.ActivityCriteria;
import org.lorislab.tower.store.model.Activity;
import org.lorislab.tower.store.model.ActivityChange;
import org.lorislab.tower.store.model.ActivityChange_;
import org.lorislab.tower.store.model.ActivityLog;
import org.lorislab.tower.store.model.ActivityLog_;
import org.lorislab.tower.store.model.Activity_;
import org.lorislab.tower.store.model.Build_;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The store activity service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ActivityService extends AbstractEntityServiceBean<Activity> {

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
     * Saves the activity.
     *
     * @param activity the activity.
     * @return the saved activity.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Activity saveActivity(Activity activity) {
        return this.save(activity);
    }

    /**
     * Gets the activity.
     *
     * @param guid the activity GUID.
     * @return the corresponding activity.
     */
    public Activity getActivity(String guid) {
        ActivityCriteria criteria = new ActivityCriteria();
        criteria.setGuid(guid);
        return getActivity(criteria);
    }

    /**
     * Gets the activity.
     *
     * @param criteria the activity criteria.
     * @return the corresponding activity.
     */
    public Activity getActivity(ActivityCriteria criteria) {
        Activity result = null;
        List<Activity> tmp = getActivities(criteria);
        if (tmp != null && !tmp.isEmpty()) {
            result = tmp.get(0);
        }
        return result;
    }

    /**
     * Gets the list of all activities.
     *
     * @return the list of all activities.
     */
    public List<Activity> getActivities() {
        return getActivities(new ActivityCriteria());
    }

    /**
     * Get activities by activity criteria.
     *
     * @param criteria the activity criteria.
     * @return the corresponding activities.
     */
    public List<Activity> getActivities(ActivityCriteria criteria) {
        List<Activity> result = new ArrayList<>();

        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<Activity> cq = getBaseEAO().createCriteriaQuery();
        Root<Activity> root = cq.from(Activity.class);

        if (criteria.isFetchBuild()) {
            root.fetch(Activity_.build, JoinType.LEFT);
        }
        
        if (criteria.isFetchChange()) {
            Fetch<Activity, ActivityChange> chf = root.fetch(Activity_.changes, JoinType.LEFT);

            if (criteria.isFetchChangeLog()) {
                Fetch<ActivityChange, ActivityLog> lf = chf.fetch(ActivityChange_.logs, JoinType.LEFT);
                
                if (criteria.isFetchChangeLogBuild()) {
                    lf.fetch(ActivityLog_.build, JoinType.LEFT);
                }                
            }
        }

        List<Predicate> predicates = new ArrayList<>();
        if (criteria.getBuild()!= null) {
            predicates.add(cb.equal(root.join(Activity_.build).get(Build_.guid), criteria.getBuild()));
        }

        if (criteria.getGuid() != null) {
            predicates.add(cb.equal(root.get(Activity_.guid), criteria.getGuid()));
        }

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }
        try {
            TypedQuery<Activity> typeQuery = getBaseEAO().createTypedQuery(cq);
            result = typeQuery.getResultList();
        } catch (NoResultException ex) {
            // do nothing
        }
        return result;
    }
}
