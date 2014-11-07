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
package org.lorislab.tower.notification.util;

import java.util.ArrayList;
import java.util.List;
import org.lorislab.guardian.user.model.User;
import org.lorislab.guardian.user.model.UserConfig;
import org.lorislab.postman.api.model.Email;

/**
 *
 * @author Andrej_Petras
 */
public final class NotificationUtil {

    private NotificationUtil() {
    }

    
    /**
     * Creates the build deployed mails.
     *
     * @param users the set of users.
     * @param template the template.
     * @param values the object for the email renderer.
     * @return the list of mails.
     */
    public static List<Email> createBuildDeployedMails(List<User> users, String template, Object[] values) {
        List<Email> result = null;
        if (users != null) {
            result = new ArrayList<>();
            for (User user : users) {
                UserConfig config = user.getConfig();

                if (config.isNotification()) {
                    Email mail = new Email();
                    mail.getTo().add(user.getProfile().getEmail());
                    mail.setTemplate(template);
                    // add the user to the parameters
                    mail.getParameters().put(user.getClass().getSimpleName(), user);
                    // add the list of values to the parameters
                    if (values != null) {
                        for (Object value : values) {
                            if (value != null) {
                                mail.getParameters().put(value.getClass().getSimpleName(), value);
                            }
                        }
                    }
                    result.add(mail);
                }
            }
        }
        return result;
    }
}
