/*
 * Copyright 2013 lorislab.org.
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
package org.lorislab.tower.jira.client;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.lorislab.jira.jaxrs.services.MyPermissionsClient;
import org.lorislab.jira.jaxrs.services.MySelfClient;
import org.lorislab.jira.jaxrs.services.ProjectClient;
import org.lorislab.jira.jaxrs.services.SearchClient;

/**
 * The JIRA client.
 *
 * @author Andrej Petras
 */
public class JIRAClient {

    /**
     * The server.
     */
    private final ResteasyWebTarget target;

    /**
     * Creates the JIRA client.
     *
     * @param server the server URL.
     * @param username the username.
     * @param password the password.
     * @param auth the authentication flag.
     * @throws Exception if the method fails.
     */
    public JIRAClient(String server, String username, char[] password, boolean auth) throws Exception {
        
        //FIXME: security!!
        ResteasyClient client = new ResteasyClientBuilder().disableTrustManager().register(JSONJiraClientProvider.class).build();    
        if (auth) {
            client.register(new BasicAuthenticator(username, password));
        }
        target = client.target(server);               
    }

    /**
     * Gets the search client.
     *
     * @return the search client.
     */
    public SearchClient createSearchClient() {
        return target.proxy(SearchClient.class);
    }

    /**
     * Gets the project client.
     *
     * @return the project client.
     */
    public ProjectClient createProjectClient() {
        return target.proxy(ProjectClient.class);
    }

    /**
     * Gets the my self client.
     *
     * @return the my self client.
     */
    public MySelfClient createMySelfClient() {
        return target.proxy(MySelfClient.class);
    }
    
    /**
     * Gets the my permissions client.
     *
     * @return the my permissions client.
     */
    public MyPermissionsClient createMyPermissionsClient() {
        return target.proxy(MyPermissionsClient.class);
    }    
}
