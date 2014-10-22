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
package org.lorislab.tower.web.settings.view;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.lorislab.tower.store.criteria.NotificationGroupCriteria;
import org.lorislab.tower.store.ejb.NotificationGroupService;
import org.lorislab.tower.store.model.NotificationGroup;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.view.AbstractDefaultSearchViewController;

/**
 * The notification group search view controller.
 * 
 * @author Andrej_Petras
 */
@Named("notifyGroupSVC")
@ViewScoped
public class NotificationGroupSearchViewController extends AbstractDefaultSearchViewController<NotificationGroup, NotificationGroupCriteria> {
    
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 591140972743283724L;

    /**
     * The notification group service.
     */
    @EJB
    private NotificationGroupService service;

    /**
     * The default constructor.
     */
    public NotificationGroupSearchViewController() {
        super(Context.NOTIFY_GROUP);
        setCriteria(new NotificationGroupCriteria());
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    protected List<NotificationGroup> doSearch() throws Exception {
        return service.getNotificationGroups(getCriteria());
    }
    
}
