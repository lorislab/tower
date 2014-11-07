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
package org.lorislab.tower.service.system.ejb;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.tower.bts.model.BtsCriteria;
import org.lorislab.tower.bts.model.BtsResult;
import org.lorislab.tower.bts.service.BtsService;

/**
 * The BTS system service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class BtsClientService {

    /**
     * Test the access to the project.
     *
     * @param criteria the criteria.
     * @throws Exception if the method fails.
     */
    public void testProjectAccess(BtsCriteria criteria) throws Exception {
        BtsService.testProjectAccess(criteria);
    }
  
    /**
     * Test the connection.
     *
     * @param criteria the criteria.
     * @throws Exception if the method fails.
     */
    public void testConnection(BtsCriteria criteria) throws Exception {
        BtsService.testConnection(criteria);
    }

    /**
     * Gets the list of issues.
     *
     * @param criteria the criteria.
     * @return the list of issues.
     * @throws Exception if the method fails.
     */
    public BtsResult getIssues(BtsCriteria criteria) throws Exception {
        return BtsService.getIssues(criteria);
    }

    /**
     * Gets the pattern for ID.
     *
     * @param type the BTS type.
     * @param id the id.
     * @return the search pattern.
     * @throws Exception if the method fails.
     */
    public String getIdPattern(String type, String id) throws Exception {
        return BtsService.getIdPattern(type, id);
    }
}
