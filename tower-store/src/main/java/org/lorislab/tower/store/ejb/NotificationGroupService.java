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

import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.lorislab.jel.ejb.services.AbstractEntityServiceBean;
import org.lorislab.tower.store.criteria.NotificationGroupCriteria;
import org.lorislab.tower.store.model.NotificationGroup;

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
    
    public NotificationGroup getNotificationGroup(String guid) {
        return getById(guid);
    }
    
    public List<String> getNotificationUsers(String system) {
        List<String> result = null;
        
        return result;
    }
    
    public List<NotificationGroup> getNotificationGroups(NotificationGroupCriteria criteria) {
        List<NotificationGroup> result = null;
        
        return result;
    }
}
