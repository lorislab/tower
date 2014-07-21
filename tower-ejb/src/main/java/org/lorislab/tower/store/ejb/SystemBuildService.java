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
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.lorislab.tower.store.criteria.SystemBuildCriteria;
import org.lorislab.tower.store.model.Build;
import org.lorislab.tower.store.model.Build_;
import org.lorislab.tower.store.model.SystemBuild;
import org.lorislab.tower.store.model.SystemBuild_;
import org.lorislab.tower.store.model.TargetSystem_;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;

/**
 * The system build service.
 * 
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class SystemBuildService extends AbstractEntityServiceBean<SystemBuild> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 3658813042535292506L;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public SystemBuild saveSystemBuild(SystemBuild data) {
        return this.save(data);
    }

    public SystemBuild getSystemBuild(String guid) {
        SystemBuildCriteria criteria = new SystemBuildCriteria();
        criteria.setGuid(guid);
        return this.getById(guid);
    }

    public SystemBuild getSystemBuild(SystemBuildCriteria criteria) {
        SystemBuild result = null;
        List<SystemBuild> tmp = getSystemBuilds(criteria);
        if (tmp != null && !tmp.isEmpty()) {
            result = tmp.get(0);
        }
        return result;
    }

    public List<SystemBuild> getSystemBuilds() {
        return getSystemBuilds(new SystemBuildCriteria());
    }

    public List<SystemBuild> getSystemBuilds(SystemBuildCriteria criteria) {
        List<SystemBuild> result = new ArrayList<>();

        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<SystemBuild> cq = getBaseEAO().createCriteriaQuery();
        Root<SystemBuild> root = cq.from(SystemBuild.class);

        if (criteria.isFetchBuild()) {
            Fetch<SystemBuild, Build> bf = root.fetch(SystemBuild_.build, JoinType.LEFT);
            
            if (criteria.isFetchBuildParam()) {
                bf.fetch(Build_.parameters, JoinType.LEFT);
            }
        }

        if (criteria.isFetchSystem()) {
            Fetch<SystemBuild, System> bf =  root.fetch(SystemBuild_.system, JoinType.LEFT);
            
            if (criteria.isFetchSystemApplication()) {
                bf.fetch(TargetSystem_.application, JoinType.LEFT);
            }
        }

        List<Predicate> predicates = new ArrayList<>();
        if (criteria.getGuid() != null) {            
            predicates.add(cb.equal(root.get(SystemBuild_.guid), criteria.getGuid()));
        }

        if (criteria.isMaxDate()) {
            Subquery<Date> sq = cq.subquery(Date.class);
            Root<SystemBuild> ssb = sq.from(SystemBuild.class);
            sq.select(cb.greatest(ssb.get(SystemBuild_.date)))
                    .where(
                            cb.equal(
                                    ssb.get(SystemBuild_.system).get(TargetSystem_.guid),
                                    root.get(SystemBuild_.system).get(TargetSystem_.guid)
                            )
                    );
            predicates.add(cb.equal(root.get(SystemBuild_.date), sq));
        }
        
        if (criteria.getSystems() != null && !criteria.getSystems().isEmpty()) {
            predicates.add(root.get(SystemBuild_.system).get(TargetSystem_.guid).in(criteria.getSystems()));
        }
        
        if (criteria.getSystem() != null) {
            predicates.add(cb.equal(root.get(SystemBuild_.system).get(TargetSystem_.guid), criteria.getSystem()));
        }

        if (criteria.getBuild() != null) {
            predicates.add(cb.equal(root.get(SystemBuild_.build).get(Build_.guid), criteria.getBuild()));
        }

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        }

        try {
            TypedQuery<SystemBuild> typeQuery = getBaseEAO().createTypedQuery(cq);
            result = typeQuery.getResultList();
        } catch (NoResultException ex) {
            // do nothing
        }
        return result;
    }
}
