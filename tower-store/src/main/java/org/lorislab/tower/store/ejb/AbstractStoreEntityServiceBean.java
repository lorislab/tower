/*
 * Copyright 2015 lorislab.org.
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;
import org.lorislab.jel.jpa.model.Persistent;

/**
 * The abstract store entity service bean.
 *
 * @param <E> the persistent entity.
 *
 * @author Andrej_Petras
 *
 */
public abstract class AbstractStoreEntityServiceBean<E extends Persistent> extends AbstractEntityServiceBean<E> {

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

}
