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
package org.lorislab.tower.guardian.config.ejb;

import java.util.List;
import java.util.Set;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.guardian.api.model.UserMetaData;
import org.lorislab.guardian.api.service.UserMetaDataService;

/**
 * The default user meta data service.
 *
 * @author Andrej Petras
 */
@Stateless
@Local(UserMetaDataService.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserMetaDataServiceBean implements UserMetaDataService {

    /**
     * {@inheritDoc }
     */
    @Override
    public UserMetaData loadUserMetaData(String userGuid) throws Exception {
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public UserMetaData saveUserMetaData(UserMetaData userMetaData) throws Exception {
        return userMetaData;
    }

    @Override
    public List<? extends UserMetaData> getUserMetaDatas(Set<String> users) throws Exception {
        return null;
    }

}
