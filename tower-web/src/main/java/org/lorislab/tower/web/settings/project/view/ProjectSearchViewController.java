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
package org.lorislab.tower.web.settings.project.view;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.guardian.web.view.AbstractContextSearchViewController;
import org.lorislab.guardian.web.view.actions.ContextResetAction;
import org.lorislab.guardian.web.view.actions.ContextSearchAction;
import org.lorislab.tower.store.criteria.ProjectCriteria;
import org.lorislab.tower.store.ejb.ProjectService;
import org.lorislab.tower.store.model.Project;
import org.lorislab.tower.web.common.action.Action;
import org.lorislab.tower.web.common.action.Context;

/**
 *
 * @author Andrej Petras
 */
@Named(value = "projectSVC")
@SessionScoped
public class ProjectSearchViewController extends AbstractContextSearchViewController<Project, ProjectCriteria> {

    private static final long serialVersionUID = -773118609199094980L;

    @EJB
    private ProjectService service;

    private ContextResetAction resetAction;

    private ContextSearchAction searchAction;

    public ProjectSearchViewController() {
        resetAction = new ContextResetAction(this, Context.PROJECT, Action.RESET);
        searchAction = new ContextSearchAction(this, Context.PROJECT, Action.SEARCH);
    }

    @Override
    protected List<Project> doSearch() throws Exception {
        List<Project> result = service.getProjects();
        if (result == null) {
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public List<Project> getResult() {
        List<Project> tmp = super.getResult();
        if (tmp == null) {
            try {
                search();
            } catch (Exception ex) {

            }
        }
        return super.getResult();
    }

    public ContextResetAction getResetAction() {
        return resetAction;
    }

    public ContextSearchAction getSearchAction() {
        return searchAction;
    }

}
