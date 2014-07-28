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
package org.lorislab.tower.guardian.config.ejb;

import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.lorislab.guardian.api.service.UserConfigService;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;
import org.lorislab.tower.guardian.config.model.UserConfig;
import org.lorislab.tower.guardian.config.model.UserConfig_;

/**
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserConfigServiceBean extends AbstractEntityServiceBean<UserConfig> implements UserConfigService<UserConfig> {

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

    @Override
    public UserConfig getUserConfig(String user) throws Exception {
        UserConfig result = null;
        CriteriaBuilder cb = getBaseEAO().getCriteriaBuilder();
        CriteriaQuery<UserConfig> cq = getBaseEAO().createCriteriaQuery();
        Root<UserConfig> root = cq.from(UserConfig.class);

        cq.where(cb.equal(root.get(UserConfig_.user), user));

        TypedQuery<UserConfig> query = getBaseEAO().createTypedQuery(cq);
        List<UserConfig> tmp = query.getResultList();
        if (tmp != null && !tmp.isEmpty()) {
            result = tmp.get(0);
        }
        return result;

    }

    @Override
    public UserConfig saveUserConfig(UserConfig data) throws Exception {
        return this.save(data);
    }

    @Override
    public List<UserConfig> getUserConfigs(Set<String> users) throws Exception {
        return this.getAll();
    }
}