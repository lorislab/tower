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
package org.lorislab.tower.process.ejb;

import java.util.Arrays;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.tower.process.model.ChangePassword;
import org.lorislab.treasure.service.PasswordService;

/**
 * The change password service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ChangePasswordService {

    /**
     * The key system property.
     */
    private final static String PROP_KEY = "org.lorislab.tower.treasure.key";

    /**
     * The default key.
     */
    private final static char[] DEFAULT_KEY = {'t', 'o', 'w', 'e', 'r'};

    /**
     * Creates the new password.
     *
     * @param password the new password information.
     * @return the new create corresponding password.
     * @throws Exception if the method fails.
     */
    public String createPassword(ChangePassword password) throws Exception {
        String result = null;
        if (Arrays.equals(password.getNew1(), password.getNew2())) {
            char[] key = DEFAULT_KEY;

            // check the system property configuration
            String tmp = System.getProperty(PROP_KEY);
            if (tmp != null) {
                key = tmp.toCharArray();
            }

            result = PasswordService.encrypt(password.getNew1(), key);
            password.clear();
        } else {
            throw new Exception("The password are not same!");
        }
        return result;
    }
}
