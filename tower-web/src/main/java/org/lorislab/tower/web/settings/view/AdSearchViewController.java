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
import org.lorislab.guardian.api.user.criteria.UserSourceSearchCriteria;
import org.lorislab.guardian.api.user.model.UserSourceData;
import org.lorislab.guardian.api.user.service.UserSourceService;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.view.AbstractDefaultSearchViewController;

/**
 * The active directory search view controller.
 * 
 * @author Andrej_Petras
 */
@Named("adSVC")
@SessionScoped
public class AdSearchViewController extends AbstractDefaultSearchViewController<UserSourceData, UserSourceSearchCriteria> {
    
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -5733824813107737224L;

    /**
     * The user source service.
     */
    @EJB
    private UserSourceService service;

    /**
     * The default constructor.
     */    
    public AdSearchViewController() {
        super(Context.AD_SEARCH);
        setCriteria(new UserSourceSearchCriteria());
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    protected List<UserSourceData> doSearch() throws Exception {
        return service.findUsers(getCriteria());
    }
    
}
