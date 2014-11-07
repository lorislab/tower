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
package org.lorislab.tower.notification.ejb;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.barn.api.service.ConfigurationService;
import org.lorislab.guardian.user.criteria.UserSearchCriteria;
import org.lorislab.guardian.user.ejb.UserService;
import org.lorislab.guardian.user.model.User;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.postman.api.model.Email;
import org.lorislab.postman.api.model.EmailConfig;
import org.lorislab.postman.api.service.EmailService;
import org.lorislab.tower.notification.util.NotificationUtil;
import org.lorislab.tower.process.model.DeployResult;
import org.lorislab.tower.process.model.InstallResult;
import org.lorislab.tower.store.criteria.NotificationGroupCriteria;
import org.lorislab.tower.store.ejb.NotificationGroupService;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.NotificationGroup;
import org.lorislab.tower.store.model.TargetSystem;

/**
 *
 * @author Andrej_Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class NotificationService {
    
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(NotificationService.class.getName());
    
    /**
     * The build deploy on the system template.
     */
    private static final String MAIL_BUILD_DEPLOY = "deploy";
    /**
     * The build install in the system template.
     */
    private static final String MAIL_BUILD_INSTALL = "install";
    
    /**
     * The mail service.
     */
    @EJB
    private EmailService mailService;

    /**
     * The configuration service.
     */
    @EJB
    private ConfigurationService configService;

    /**
     * The user service.
     */
    @EJB
    private UserService userService;
    /**
     * The notification group service.
     */
    @EJB
    private NotificationGroupService notificationGroupService;
    
    @Asynchronous
    public void sendDeployNotification(final DeployResult result) throws ServiceException {
        
         TargetSystem system = result.getSystem();
         
        // check the notification
        if (!system.isNotification()) {
            LOGGER.log(Level.WARNING, "The notification for the system {0} is disabled!", system.getName());
            return;
        }
        // load the user guids for notification
        NotificationGroupCriteria ngc = new NotificationGroupCriteria();
        ngc.setFetchUsers(true);
        ngc.setSystem(system.getGuid());
        List<NotificationGroup> groups = notificationGroupService.getNotificationGroups(ngc);      
        sendNotification(groups, MAIL_BUILD_DEPLOY, result.getValues());        
    }
    
    @Asynchronous
    public void sendInstallNotification(final InstallResult result) throws ServiceException {

        Application application = result.getApplication();
        
        // check the notification
        if (!application.isNotification()) {
            LOGGER.log(Level.WARNING, "The notification for the application {0} is disabled!", application.getName());
            return;
        }

        // load the user guids for notification
        NotificationGroupCriteria ngc = new NotificationGroupCriteria();
        ngc.setFetchUsers(true);
        ngc.setApplication(application.getGuid());
        List<NotificationGroup> groups = notificationGroupService.getNotificationGroups(ngc);      
        sendNotification(groups, MAIL_BUILD_INSTALL, result.getValues());
    }
    
    
    private void sendNotification(final List<NotificationGroup> groups, final String template, final Object[] values) { 
        
        // create list of users guids.
        Set<String> userGuids = new HashSet<>();
        if (groups != null) {
            for (NotificationGroup group : groups) {
                userGuids.addAll(group.getUsers());
            }
        }  
        
        // load the users
        List<User> users = null;
        try {            
            UserSearchCriteria uc = new UserSearchCriteria();
            uc.setFetchConfig(true);
            uc.setFetchProfile(true);
            uc.setGuids(userGuids);            
            users = userService.getUsers(uc);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error reading the user data by criteria", ex);
        }

        // check the list of users.
        if (users == null || users.isEmpty()) {
            LOGGER.log(Level.WARNING, "No user found for the notification.[{0}]", template);
            return;
        }
        
        // load the email configuration
        EmailConfig eConfig = configService.getConfiguration(EmailConfig.class);
        // create the emails
        List<Email> mails = NotificationUtil.createBuildDeployedMails(users, template, values);
        if (mails != null) {
            for (Email mail : mails) {
                try {
                    mailService.sendEmail(mail, eConfig);
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, "Error sending the build notification mail to users: " + mail.getTo().toString(), ex);
                }
            }
        }                
    }
}
