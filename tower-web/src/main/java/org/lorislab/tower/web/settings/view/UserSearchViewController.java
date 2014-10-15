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
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.guardian.user.criteria.UserSearchCriteria;
import org.lorislab.guardian.user.ejb.UserService;
import org.lorislab.guardian.user.model.User;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.view.AbstractDefaultSearchViewController;

/**
 *
 * @author Andrej_Petras
 */
@Named("userSVC")
@SessionScoped
public class UserSearchViewController extends AbstractDefaultSearchViewController<User, UserSearchCriteria> {
    
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -6156249847954640059L;
    
    /**
     * The user data service.
     */
    @EJB
    private UserService service;

    /**
     * The default constructor.
     */    
    public UserSearchViewController() {
        super(Context.USER);
        UserSearchCriteria criteria = new UserSearchCriteria();
        criteria.setFetchProfile(true);
        setCriteria(criteria);
    }
    
    /**
     * {@inheritDoc }
     */    
    @Override
    protected List<User> doSearch() throws Exception {        
        return service.getUsers(getCriteria());
    }
}
