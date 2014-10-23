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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.guardian.user.criteria.UserSearchCriteria;
import org.lorislab.guardian.user.ejb.UserService;
import org.lorislab.guardian.user.model.User;
import org.lorislab.jel.jpa.model.Persistent;
import org.lorislab.jel.jsf.entity.controller.AbstractEntityViewController;
import org.lorislab.jel.jsf.entity.controller.EntitySelectItemListController;
import org.lorislab.tower.store.ejb.ApplicationService;
import org.lorislab.tower.store.ejb.NotificationGroupService;
import org.lorislab.tower.store.ejb.TargetSystemService;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.NotificationGroup;
import org.lorislab.tower.store.model.TargetSystem;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.action.Navigation;
import org.lorislab.jel.jsf.entity.model.EntitySelectItem;

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

    @EJB
    private UserService userService;

    @EJB
    private ApplicationService appService;

    @EJB
    private TargetSystemService sysService;

    private final EntitySelectItemListController<User> user;

    private final EntitySelectItemListController<TargetSystem> system;

    private final EntitySelectItemListController<Application> application;

    /**
     * The default constructor.
     */
    public NotificationGroupViewController() {
        super(Context.NOTIFY_GROUP);
        user = new EntitySelectItemListController<>();
        system = new EntitySelectItemListController<>();
        application = new EntitySelectItemListController<>();
    }
    
    private void loadData() throws Exception {
        // load the users
        UserSearchCriteria uc = new UserSearchCriteria();
        uc.setFetchProfile(true);
        List<User> tmpUsers = userService.getUsers(uc);
        user.loadData(tmpUsers, getModel().getUsers());        
        // load the applications
        List<Application> apps = appService.getApplications();
        application.loadData(apps, getModel().getApplications());
        // load the systems
        List<TargetSystem> syss = sysService.getSystems();
        system.loadData(syss, getModel().getSystems());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object edit(String guid) throws Exception {
        NotificationGroup tmp = service.getFullNotificationGroup(guid);
        setModel(tmp);
        loadData();
        return Navigation.TO_NOTIFY_GROUP_EDIT;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object save() throws Exception {
        NotificationGroup group = getModel();
        group.setApplications(application.getKeys());        
        group.setSystems(system.getKeys());                
        group.setUsers(user.getKeys());        
        group = service.saveNotificationGroup(group);
        group = service.getFullNotificationGroup(group.getGuid());
        setModel(group);
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object create() throws Exception {
        setModel(new NotificationGroup());
        loadData();
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

    @Override
    public Object delete() throws Exception {
        service.deleteNotificationGroup(getModel().getGuid());
        setModel(null);
        return Navigation.TO_NOTIFY_GROUP;
    }

    public EntitySelectItemListController<Application> getApplication() {
        return application;
    }

    public EntitySelectItemListController<User> getUser() {
        return user;
    }

    public EntitySelectItemListController<TargetSystem> getSystem() {
        return system;
    }

}
