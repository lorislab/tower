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

package org.lorislab.tower.treasure.service;

import org.kohsuke.MetaInfServices;
import org.lorislab.treasure.api.service.SecretKeyService;

/**
 * The treasure secret key service.
 * 
 * @author Andrej Petras
 */
@MetaInfServices
public class SecretKeyServiceImpl implements SecretKeyService {

    /**
     * The key system property.
     */
    private final static String PROP_KEY = "org.lorislab.tower.treasure.secret.key";
    
    /**
     * The key.
     */
    private static final char[] DEFAULT_KEY = {116, 111, 119, 101, 114};
    
    /**
     * {@inheritDoc }
     */
    @Override
    public char[] getSecretKey() {
        char[] result = DEFAULT_KEY;
        String tmp = System.getProperty(PROP_KEY);
        if (tmp != null) {
            result = tmp.toCharArray();
        }
        return result;
    }
    
}
