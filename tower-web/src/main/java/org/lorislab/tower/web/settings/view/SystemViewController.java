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
import org.lorislab.tower.store.criteria.TargetSystemCriteria;
import org.lorislab.tower.store.ejb.AgentService;
import org.lorislab.tower.store.ejb.ApplicationService;
import org.lorislab.tower.store.ejb.TargetSystemService;
import org.lorislab.tower.store.model.Agent;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.TargetSystem;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.action.Navigation;
import org.lorislab.tower.web.common.converter.EntityLabelCallbackInstances;
import org.lorislab.tower.web.common.view.EntityViewController;

/**
 * The application view controller.
 *
 * @author Andrej Petras
 */
@Named("systemVC")
@SessionScoped
public class SystemViewController extends EntityViewController<TargetSystem> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1766808728513642285L;

    /**
     * The target system service.
     */
    @EJB
    private TargetSystemService service;

    /**
     * The application service.
     */
    @EJB
    private ApplicationService applicationService;

    /**
     * The agent service.
     */
    @EJB
    private AgentService agentService;
    
    /**
     * The application converter.
     */
    private final EntityPersistentConverter<Application> applicationConverter;
    
    /**
     * The agent converter.
     */
    private final EntityPersistentConverter<Agent> agentConverter;
    
    /**
     * The default constructor.
     */
    public SystemViewController() {
        super(Context.SYSTEM);
        applicationConverter = new EntityPersistentConverter(EntityLabelCallbackInstances.APPLICATION);
        agentConverter = new EntityPersistentConverter(EntityLabelCallbackInstances.AGENT);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object open(String guid) {
        Object result = Navigation.TO_SYSTEM_EDIT;
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
        service.deleteSystem(getModel().getGuid());
        setModel(null);
        agentConverter.clear();
        applicationConverter.clear();
        return Navigation.TO_SYSTEM;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object save() throws Exception {
        TargetSystem tmp = service.saveSystem(getModel());
        load(tmp.getGuid());
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object close() throws Exception {
        setModel(null);
        agentConverter.clear();
        applicationConverter.clear();
        return Navigation.TO_SYSTEM;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object create() throws Exception {
        setModel(new TargetSystem());
        loadSystems();
        return Navigation.TO_SYSTEM_EDIT;
    }

    /**
     * Loads the system by GUID.
     *
     * @param guid the system GUID.
     */
    private void load(String guid) {
        TargetSystemCriteria criteria = new TargetSystemCriteria();
        criteria.setGuid(guid);
        criteria.setFetchApplication(true);
        criteria.setFetchAgent(true);
        TargetSystem tmp = service.getSystem(criteria);
        setModel(tmp);
    }

    /**
     * Loads the systems.
     */
    private void loadSystems() {
        // load applications
        List<Agent> agents = agentService.getAgents();
        agentConverter.setData(agents);          
        // load applications
        List<Application> applications = applicationService.getApplications();
        applicationConverter.setData(applications);        
    }

    /**
     * Gets the application converter.
     *
     * @return the application converter.
     */
    public EntityPersistentConverter<Application> getApplicationConverter() {
        return applicationConverter;
    }
    
    /**
     * Gets the agent converter.
     *
     * @return the agent converter.
     */
    public EntityPersistentConverter<Agent> getAgentConverter() {
        return agentConverter;
    }    
}
