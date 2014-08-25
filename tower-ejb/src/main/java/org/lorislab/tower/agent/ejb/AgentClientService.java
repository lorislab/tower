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
package org.lorislab.tower.agent.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.tower.base.dto.model.Version;
import org.lorislab.tower.agent.dto.mapper.ObjectMapper;
import org.lorislab.tower.agent.dto.model.AgentRequest;
import org.lorislab.tower.agent.rs.service.VersionService;
import org.lorislab.tower.agent.util.RestClient;
import org.lorislab.tower.agent.util.VersionMapper;
import org.lorislab.tower.store.model.Agent;
import org.lorislab.tower.store.model.Build;
import org.lorislab.tower.store.model.enums.AgentType;
import org.lorislab.treasure.api.factory.PasswordServiceFactory;
import org.lorislab.treasure.api.service.PasswordService;

/**
 * The agent client service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AgentClientService {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(AgentClientService.class.getName());

    /**
     * The agent REST link
     */
    private static final String AGENT_SERVICE = "/rs";

    /**
     * Gets all applications build.
     *
     * @param agent the agent.
     * @return the list of applications build.
     */
    public List<Build> getBuilds(Agent agent) {
        List<Build> result = new ArrayList<>();

        try {
            VersionService client = createClientService(agent);
            AgentRequest request = ObjectMapper.create();

            List<Version> versions = client.getVersion(request);
            if (versions != null) {

                for (Version version : versions) {
                    Build tmp = VersionMapper.map(version);
                    if (tmp != null) {
                        result.add(tmp);
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error reading all application versions for the agent " + agent.getGuid(), ex);
        }
        return result;
    }

    /**
     * Gets the agent service build.
     *
     * @param agent the agent.
     * @return the agent service build.
     * 
     * @throws Exception if the method fails.
     */
    public Build getAgentBuild(Agent agent) throws Exception {
        Build result = null;

        try {
            VersionService client = createClientService(agent);
            Version version = client.getAgentVersion(true);
            result = VersionMapper.map(version);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error reading the version of the agent {0}", agent.getGuid());
            throw ex;
        }
        return result;
    }

    /**
     * Gets the application build.
     *
     * @param agent the agent.
     * @param service the service name.
     * @return the build of the application.
     */
    public Build getBuild(Agent agent, String service) {
        Build result = null;

        try {
            VersionService client = createClientService(agent);
            AgentRequest request = ObjectMapper.create();
            request.service = service;

            List<Version> versions = client.getVersion(request);
            Version version = null;
            if (versions != null && !versions.isEmpty()) {
                version = versions.get(0);
            }
            result = VersionMapper.map(version);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error reading the application version for the the agent " + agent.getGuid(), ex);
        }
        return result;
    }

    /**
     * Creates the version service.
     *
     * @param agent the agent.
     * @return the version service for the agent.
     */
    private static VersionService createClientService(Agent agent) {
        VersionService result = null;
        if (agent != null) {

            StringBuilder sb = new StringBuilder();
            sb.append(agent.getUrl());
            
            if (AgentType.SERVICE.equals(agent.getType())) {
                sb.append(AGENT_SERVICE);
            }
            try {
                PasswordService pswd = PasswordServiceFactory.getService();
                char[] tmp = pswd.getPassword(agent.getPassword());
                result = RestClient.getClient(VersionService.class, sb.toString(), agent.isAuthentication(), agent.getUser(), tmp);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Error creating the version service for the agent " + agent.getGuid(), ex);
            }
        }
        return result;
    }

}
