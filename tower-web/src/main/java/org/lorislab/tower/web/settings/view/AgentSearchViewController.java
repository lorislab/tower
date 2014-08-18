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
import org.lorislab.tower.store.criteria.AgentCriteria;
import org.lorislab.tower.store.ejb.AgentService;
import org.lorislab.tower.store.model.Agent;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.view.AbstractSearchViewController;

/**
 * The SCM search view controller.
 * 
 * @author Andrej Petras
 */
@Named("agentSVC")
@SessionScoped
public class AgentSearchViewController extends AbstractSearchViewController<Agent, AgentCriteria> {
    
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 3380686998733851443L;

    /**
     * The service.
     */
    @EJB
    private AgentService service;
   
    /**
     * The default constructor.
     */
    public AgentSearchViewController() {
        super(Context.AGENT);
    }
        
    /**
     * {@inheritDoc }
     */
    @Override
    protected List<Agent> doSearch() throws Exception {
        return service.getAgents();
    }
      
}
