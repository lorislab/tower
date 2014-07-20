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
package org.lorislab.tower.scm.service;

import org.lorislab.tower.scm.model.ScmCriteria;
import org.lorislab.tower.scm.model.ScmResult;

/**
 * The SCM service client.
 *
 * @author Andrej Petras
 */
public interface ScmServiceClient {

    /**
     * Gets the type.
     *
     * @return the type.
     */
    public String getType();

    /**
     * Gets the name.
     *
     * @return the name.
     */
    public String getName();

    /**
     * Gets the log.
     *
     * @param criteria the criteria.
     * @return the SCM result.
     * @throws Exception if the method fails.
     */
    public ScmResult getLog(ScmCriteria criteria) throws Exception;
    
    /**
     * Tests the connection and configuration.
     *
     * @param criteria the criteria.
     * @throws Exception if the method fails.
     */
    public void testConnection(ScmCriteria criteria) throws Exception;    

    /**
     * Tests the connection to the repository.
     *
     * @param criteria the criteria.
     * @throws Exception if the method fails.
     */
    public void testRepository(ScmCriteria criteria) throws Exception;     
}
