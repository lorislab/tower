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
import org.lorislab.jel.jsf.converter.EntityPersistentConverter;
import org.lorislab.tower.store.criteria.ApplicationCriteria;
import org.lorislab.tower.store.ejb.ApplicationService;
import org.lorislab.tower.store.ejb.ProjectService;
import org.lorislab.tower.store.ejb.SCMSystemService;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.Project;
import org.lorislab.tower.store.model.SCMSystem;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.action.Navigation;
import org.lorislab.tower.web.common.converter.EntityLabelCallbackInstances;
import org.lorislab.tower.web.common.view.EntityViewController;

/**
 * The application view controller.
 *
 * @author Andrej Petras
 */
@Named("applicationVC")
@SessionScoped
public class ApplicationViewController extends EntityViewController<Application> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1766808728513642285L;

    /**
     * The project service.
     */
    @EJB
    private ApplicationService service;

    /**
     * The SCMSystem service.
     */
    @EJB
    private SCMSystemService scmService;

    /**
     * The project service.
     */
    @EJB
    private ProjectService projectService;
    
    /**
     * The BTSystem converter.
     */
    private EntityPersistentConverter<SCMSystem> scmSystemConverter;

    /**
     * The project converter.
     */
    private EntityPersistentConverter<Project> projectConverter;
    
    /**
     * The default constructor.
     */
    public ApplicationViewController() {
        super(Context.APPLICATION);
        scmSystemConverter = new EntityPersistentConverter(EntityLabelCallbackInstances.SCMSYSTEM);
        projectConverter = new EntityPersistentConverter(EntityLabelCallbackInstances.PROJECT);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object open(String guid) {
        Object result = Navigation.TO_APPLICATION_EDIT;
        load(guid);

        if (isEmpty()) {
            result = null;
        } else {
            loadSystems();
        }
        return result;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object delete() throws Exception {
        service.deleteApplication(getModel().getGuid());
        setModel(null);
        scmSystemConverter.clear();
        projectConverter.clear();
        return Navigation.TO_APPLICATION;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object save() throws Exception {
        Application tmp = service.saveApplication(getModel());
        load(tmp.getGuid());
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object close() throws Exception {
        setModel(null);
        scmSystemConverter.clear();
        projectConverter.clear();
        return Navigation.TO_APPLICATION;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object create() throws Exception {
        setModel(new Application());
        loadSystems();
        return Navigation.TO_APPLICATION_EDIT;
    }

    /**
     * Loads the project by GUID.
     *
     * @param guid the project GUID.
     */
    private void load(String guid) {
        ApplicationCriteria criteria = new ApplicationCriteria();
        criteria.setGuid(guid);
        criteria.setFetchSCM(true);
        criteria.setFetchProject(true);
        Application tmp = service.getApplication(criteria);
        setModel(tmp);
    }

    /**
     * Loads the systems.
     */
    private void loadSystems() {
        // load the SCM
        List<SCMSystem> tmp = scmService.getSCMSystems();
        scmSystemConverter.setData(tmp);
        // load projects
        List<Project> projects = projectService.getProjects();
        projectConverter.setData(projects);        
    }

    /**
     * Gets the SCMSystem converter.
     *
     * @return the SCMSystem converter.
     */
    public EntityPersistentConverter<SCMSystem> getScmSystemConverter() {
        return scmSystemConverter;
    }

    /**
     * Gets the project converter.
     *
     * @return the project converter.
     */
    public EntityPersistentConverter<Project> getProjectConverter() {
        return projectConverter;
    }    
}
