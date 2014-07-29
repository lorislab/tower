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

/**
 *
 * @author Andrej Petras
 */
@Named("projectVC")
public class ProjectViewController extends AbstractContextEntityViewController<Project> implements ContextEntityViewController {

    private static final long serialVersionUID = -1766808728513642285L;

    @EJB
    private ProjectService service;

    private ContextOpenAction openAction;
    private ContextCreateAction createAction;
    private ContextCloseAction closeAction;
    private ContextSaveAction saveAction;
    private ContextDeleteAction deleteAction;
        
    public ProjectViewController() {
        createAction = new ContextCreateAction(this, Context.PROJECT, Action.CREATE);
        closeAction = new ContextCloseAction(this, Context.PROJECT, Action.CLOSE);
        saveAction = new ContextSaveAction(this, Context.PROJECT, Action.SAVE);
        deleteAction = new ContextDeleteAction(this, Context.PROJECT, Action.DELETE);
        openAction = new ContextOpenAction(this, Context.PROJECT, Action.EDIT);
    }

    @Override
    public Object open(String guid) {
        Project tmp = service.getProject(guid);
        setModel(tmp);
        return Navigation.TO_PROJECT_EDIT;
    }

    @Override
    public Object delete() throws Exception {
        service.deleteProject(getModel().getGuid());
        setModel(null);
        return Navigation.TO_PROJECT;
    }

    @Override
    public Object save() throws Exception {
        Project tmp = service.saveProject(getModel());
        setModel(tmp);
        return null;
    }

    @Override
    public Object close() throws Exception {
        setModel(null);
        return Navigation.TO_PROJECT;
    }

    @Override
    public Object create() throws Exception {
        setModel(new Project());        
        return Navigation.TO_PROJECT_EDIT;
    }
    
    public ContextOpenAction getOpenAction() {
        return openAction;
    }

    public ContextCloseAction getCloseAction() {
        return closeAction;
    }

    public ContextCreateAction getCreateAction() {
        return createAction;
    }

    public ContextDeleteAction getDeleteAction() {
        return deleteAction;
    }

    public ContextSaveAction getSaveAction() {
        return saveAction;
    }
    
}
