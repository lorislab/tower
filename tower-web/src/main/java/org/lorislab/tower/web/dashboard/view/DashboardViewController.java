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

package org.lorislab.tower.web.dashboard.view;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.jel.base.criteria.AbstractSearchCriteria;
import org.lorislab.tower.service.dashboard.ejb.DashboardService;
import org.lorislab.tower.service.dashboard.model.Dashboard;
import org.lorislab.tower.service.dashboard.model.DashboardProject;
import org.lorislab.tower.service.dashboard.model.DashboardTargetSystem;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.view.AbstractDefaultSearchViewController;

/**
 * The overview dashboard view controller.
 * 
 * @author Andrej Petras
 */
@Named("dashboardVC")
@SessionScoped
public class DashboardViewController extends AbstractDefaultSearchViewController<DashboardProject, AbstractSearchCriteria> {
    
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 5835025357031129345L;
    
    @EJB
    private DashboardService service;

    private Dashboard dashboard;
    
    public DashboardViewController() {
        super(Context.DB_OVERVIEW);
    }
    
    @Override
    protected List<DashboardProject> doSearch() throws Exception {
        dashboard = service.getDashboardProjects();
        return dashboard.getItems();
    }
    
    public void test(String guid) {
        System.out.println("GUID " + guid);
        DashboardTargetSystem system = dashboard.getSystems().get(guid);
        if (system != null) {
            system.setLoaded(true);
        }
    }
    
}
