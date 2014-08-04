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
import org.lorislab.tower.store.criteria.ProjectCriteria;
import org.lorislab.tower.store.ejb.BTSystemService;
import org.lorislab.tower.store.ejb.ProjectService;
import org.lorislab.tower.store.model.BTSystem;
import org.lorislab.tower.store.model.Project;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.action.Navigation;
import org.lorislab.tower.web.common.converter.EntityLabelCallbackInstances;
import org.lorislab.tower.web.common.converter.EntityPersistentConverter;
import org.lorislab.tower.web.common.view.EntityViewController;

/**
 * The project view controller.
 *
 * @author Andrej Petras
 */
@Named("projectVC")
@SessionScoped
public class ProjectViewController extends EntityViewController<Project> {

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
     * The BTSystem service.
     */
    @EJB
    private BTSystemService btsService;

    /**
     * The BTSystem converter.
     */
    private EntityPersistentConverter<BTSystem> btSystemConverter;

    /**
     * The default constructor.
     */
    public ProjectViewController() {
        super(Context.PROJECT);
        btSystemConverter = new EntityPersistentConverter(EntityLabelCallbackInstances.BTSYSTEM);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object open(String guid) {
        Object result = Navigation.TO_PROJECT_EDIT;
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
        service.deleteProject(getModel().getGuid());
        setModel(null);
        btSystemConverter.clear();
        return Navigation.TO_PROJECT;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object save() throws Exception {
        Project tmp = service.saveProject(getModel());
        load(tmp.getGuid());
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object close() throws Exception {
        setModel(null);
        btSystemConverter.clear();
        return Navigation.TO_PROJECT;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object create() throws Exception {
        setModel(new Project());
        loadSystems();
        return Navigation.TO_PROJECT_EDIT;
    }

    /**
     * Loads the project by GUID.
     *
     * @param guid the project GUID.
     */
    private void load(String guid) {
        ProjectCriteria criteria = new ProjectCriteria();
        criteria.setGuid(guid);
        criteria.setFetchBTS(true);
        Project tmp = service.getProject(criteria);
        setModel(tmp);
    }

    /**
     * Loads the systems.
     */
    private void loadSystems() {
        List<BTSystem> tmp = btsService.getBTSystems();
        btSystemConverter.setData(tmp);
    }

    /**
     * Gets the BTSystem converter.
     *
     * @return the BTSystem converter.
     */
    public EntityPersistentConverter<BTSystem> getBtSystemConverter() {
        return btSystemConverter;
    }

}
