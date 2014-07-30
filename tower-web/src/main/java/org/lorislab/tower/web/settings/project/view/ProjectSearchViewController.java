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

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.guardian.web.view.AbstractContextSearchViewController;
import org.lorislab.guardian.web.view.actions.ContextSearchAction;
import org.lorislab.tower.store.criteria.ProjectCriteria;
import org.lorislab.tower.store.ejb.ProjectService;
import org.lorislab.tower.store.model.Project;
import org.lorislab.tower.web.common.action.Action;
import org.lorislab.tower.web.common.action.Context;

/**
 * The project search view controller.
 *
 * @author Andrej Petras
 */
@Named(value = "projectSVC")
@SessionScoped
public class ProjectSearchViewController extends AbstractContextSearchViewController<Project, ProjectCriteria> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -773118609199094980L;

    /**
     * The project service.
     */
    @EJB
    private ProjectService service;

    /**
     * The search action.
     */
    private ContextSearchAction searchAction;

    /**
     * The default constructor.
     */
    public ProjectSearchViewController() {
        searchAction = new ContextSearchAction(this, Context.PROJECT, Action.SEARCH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Project> doSearch() throws Exception {
        return service.getProjects();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> getResult() {
        List<Project> tmp = super.getResult();
        if (tmp == null) {
            searchAction.execute();
        }
        return super.getResult();
    }

    /**
     * Gets the context search action.
     *
     * @return the context search action.
     */
    public ContextSearchAction getSearchAction() {
        return searchAction;
    }

}
