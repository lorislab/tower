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

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.guardian.web.view.AbstractContextEntityViewController;
import org.lorislab.guardian.web.view.ContextEntityViewController;
import org.lorislab.guardian.web.view.actions.ContextCloseAction;
import org.lorislab.guardian.web.view.actions.ContextCreateAction;
import org.lorislab.guardian.web.view.actions.ContextDeleteAction;
import org.lorislab.guardian.web.view.actions.ContextOpenAction;
import org.lorislab.guardian.web.view.actions.ContextSaveAction;
import org.lorislab.tower.store.ejb.ProjectService;
import org.lorislab.tower.store.model.Project;
import org.lorislab.tower.web.common.action.Action;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.action.Navigation;
import org.lorislab.tower.web.settings.project.action.ProjectCreateAction;
import org.lorislab.tower.web.settings.project.action.ProjectDeleteAction;
import org.lorislab.tower.web.settings.project.action.ProjectSaveAction;

/**
 * The project view controller.
 *
 * @author Andrej Petras
 */
@Named("projectVC")
@SessionScoped
public class ProjectViewController extends AbstractContextEntityViewController<Project> implements ContextEntityViewController {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1766808728513642285L;

    /**
     * The project service.
     */
    @EJB
    private ProjectService service;

    /**
     * The open action.
     */
    private ContextOpenAction openAction;

    /**
     * The create action.
     */
    private ContextCreateAction createAction;

    /**
     * The close action.
     */
    private ContextCloseAction closeAction;

    /**
     * The save action.
     */
    private ContextSaveAction saveAction;

    /**
     * The delete action.
     */
    private ContextDeleteAction deleteAction;

    /**
     * The default constructor.
     */
    public ProjectViewController() {
        createAction = new ProjectCreateAction(this, Context.PROJECT, Action.CREATE);
        closeAction = new ContextCloseAction(this, Context.PROJECT, Action.CLOSE);
        saveAction = new ProjectSaveAction(this, Context.PROJECT, Action.SAVE);
        deleteAction = new ProjectDeleteAction(this, Context.PROJECT, Action.DELETE);
        openAction = new ContextOpenAction(this, Context.PROJECT, Action.EDIT);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object open(String guid) {
        Project tmp = service.getProject(guid);
        setModel(tmp);
        return Navigation.TO_PROJECT_EDIT;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object delete() throws Exception {
        service.deleteProject(getModel().getGuid());
        setModel(null);
        return Navigation.TO_PROJECT;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object save() throws Exception {
        Project tmp = service.saveProject(getModel());
        setModel(tmp);
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object close() throws Exception {
        setModel(null);
        return Navigation.TO_PROJECT;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object create() throws Exception {
        setModel(new Project());
        return Navigation.TO_PROJECT_EDIT;
    }

    /**
     * Gets the open action.
     *
     * @return the open action.
     */
    public ContextOpenAction getOpenAction() {
        return openAction;
    }

    /**
     * Gets the close action.
     *
     * @return the close action.
     */
    public ContextCloseAction getCloseAction() {
        return closeAction;
    }

    /**
     * Gets the create action.
     *
     * @return the create action.
     */
    public ContextCreateAction getCreateAction() {
        return createAction;
    }

    /**
     * Gets the delete action.
     *
     * @return the delete action.
     */
    public ContextDeleteAction getDeleteAction() {
        return deleteAction;
    }

    /**
     * Gets the save action.
     *
     * @return the save action.
     */
    public ContextSaveAction getSaveAction() {
        return saveAction;
    }

}
