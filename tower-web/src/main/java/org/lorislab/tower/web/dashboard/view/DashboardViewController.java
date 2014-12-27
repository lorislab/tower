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
import org.lorislab.tower.service.dashboard.model.DashboardTableItem;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.view.AbstractDefaultSearchViewController;
import org.lorislab.tower.web.dashboard.action.DashboardViewAction;
import org.lorislab.tower.web.dashboard.action.TableViewAction;

/**
 * The overview dashboard view controller.
 * 
 * @author Andrej Petras
 */
@Named("dashboardVC")
@SessionScoped
public class DashboardViewController extends AbstractDefaultSearchViewController<DashboardTableItem, AbstractSearchCriteria> {
    
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 5835025357031129345L;
    
    @EJB
    private DashboardService service;

    private Dashboard dashboard;
    
    private boolean table;
    
    private final TableViewAction tableViewAction;
    
    private final DashboardViewAction dashboardViewAction;
    
    public DashboardViewController() {
        super(Context.DB_OVERVIEW);
        table = false;
        tableViewAction = new TableViewAction(this, Context.DB_OVERVIEW);
        dashboardViewAction = new DashboardViewAction(this, Context.DB_OVERVIEW);
    }

    public DashboardViewAction getDashboardViewAction() {
        return dashboardViewAction;
    }

    public TableViewAction getTableViewAction() {
        return tableViewAction;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    
    @Override
    protected List<DashboardTableItem> doSearch() throws Exception {
        dashboard = service.getDashboardProjects();
        return dashboard.getTableItems();
    }
    
    public boolean isTable() {
        return table;
    }

    public void setTable(boolean table) {
        this.table = table;
    }
  
}
