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

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.lorislab.jel.jsf.entity.controller.AbstractEntityViewController;
import org.lorislab.tower.store.ejb.NotificationGroupService;
import org.lorislab.tower.store.model.NotificationGroup;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.action.Navigation;

/**
 * The notification group view controller.
 * 
 * @author Andrej_Petras
 */
@Named("notifyGroupVC")
@SessionScoped
public class NotificationGroupViewController extends AbstractEntityViewController<NotificationGroup> {
    
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -3649525143322587876L;

    /**
     * The notification group service.
     */
    @EJB
    private NotificationGroupService service;
    
    /**
     * The default constructor.
     */
    public NotificationGroupViewController() {
        super(Context.NOTIFY_GROUP);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object edit(String guid) throws Exception {
        NotificationGroup tmp = service.getNotificationGroup(guid);
        setModel(tmp);
        return Navigation.TO_NOTIFY_GROUP_EDIT;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public Object save() throws Exception {
        NotificationGroup tmp = service.saveNotificationGroup(getModel());
        setModel(tmp);
        return null;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public Object create() throws Exception {
        setModel(new NotificationGroup());
        return Navigation.TO_NOTIFY_GROUP_EDIT;
    }    
    
    /**
     * {@inheritDoc }
     */
    @Override
    public Object close() throws Exception {
        setModel(null);
        return Navigation.TO_NOTIFY_GROUP;
    }
    
    
}
