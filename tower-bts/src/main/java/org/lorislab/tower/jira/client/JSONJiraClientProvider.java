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
package org.lorislab.tower.jira.client;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import javax.ws.rs.Consumes;

/**
 * The JSON provider configuration.
 *
 * @author Andrej_Petras
 */
@Consumes(MediaType.APPLICATION_JSON)
public class JSONJiraClientProvider extends JacksonJaxbJsonProvider {

    /**
     * The default constructor.
     */
    public JSONJiraClientProvider() {
        ObjectMapper mapper = new ObjectMapper();
//          mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//          mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
//          mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        super.setMapper(mapper);
    }
}
