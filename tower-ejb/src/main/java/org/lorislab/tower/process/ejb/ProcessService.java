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

import java.util.ArrayList;
import java.util.Date;
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
import org.lorislab.guardian.user.model.UserConfig;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.postman.api.model.Email;
import org.lorislab.postman.api.model.EmailConfig;
import org.lorislab.postman.api.service.EmailService;
import org.lorislab.tower.activity.criteria.ActivityWrapperCriteria;
import org.lorislab.tower.activity.ejb.ActivityWrapperService;
import org.lorislab.tower.activity.wrapper.ActivityWrapper;
import org.lorislab.tower.process.resources.ErrorKeys;
import org.lorislab.tower.store.criteria.ApplicationCriteria;
import org.lorislab.tower.store.criteria.BuildCriteria;
import org.lorislab.tower.store.criteria.NotificationGroupCriteria;
import org.lorislab.tower.store.criteria.SystemBuildCriteria;
import org.lorislab.tower.store.criteria.TargetSystemCriteria;
import org.lorislab.tower.store.ejb.ActivityService;
import org.lorislab.tower.store.ejb.ApplicationService;
import org.lorislab.tower.store.ejb.BuildService;
import org.lorislab.tower.store.ejb.NotificationGroupService;
import org.lorislab.tower.store.ejb.SystemBuildService;
import org.lorislab.tower.store.ejb.TargetSystemService;
import org.lorislab.tower.store.model.Activity;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.Build;
import org.lorislab.tower.store.model.NotificationGroup;
import org.lorislab.tower.store.model.SystemBuild;
import org.lorislab.tower.store.model.TargetSystem;
import org.lorislab.tower.store.model.enums.SystemBuildType;

/**
 * The process service.
 *
 * @author Andrej Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ProcessService {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(ProcessService.class.getName());

    /**
     * The new build deploy template.
     */
    private static final String MAIL_BUILD_DEPLOYED_TEMPLATE = "deploy";

    /**
     * The store system service.
     */
    @EJB
    private TargetSystemService systemService;

    /**
     * The store application service.
     */
    @EJB
    private ApplicationService appService;

    /**
     * The store build service.
     */
    @EJB
    private BuildService buildService;

    /**
     * The activity process service.
     */
    @EJB
    private ActivityProcessService activityProcessService;

    /**
     * The store activity service.
     */
    @EJB
    private ActivityService activityService;

    /**
     * The store system build service.
     */
    @EJB
    private SystemBuildService systemBuildService;

    @EJB
    private UserService userService;

    /**
     * The mail service.
     */
    @EJB
    private EmailService mailService;

    /**
     * The activity wrapper service.
     */
    @EJB
    private ActivityWrapperService activityWrapperService;

    /**
     * The notification group service.
     */
    @EJB
    private NotificationGroupService notificationGroupService;

    /**
     * The configuration service.
     */
    @EJB
    private ConfigurationService configService;

    /**
     * Install the store build.
     *
     * @param key the key.
     * @param build the store build.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void install(final String key, final Build build) throws ServiceException {
        // check the application for to key
        ApplicationCriteria criteria = new ApplicationCriteria();
        criteria.setKey(key);
        Application app = appService.getApplication(criteria);
        if (app == null) {
            throw new ServiceException(ErrorKeys.NO_APPLICATION_FOR_KEY_FOUND, key, key);
        }
        installProcess(app, build);
    }

    /**
     * Installs the process.
     *
     * @param app the application.
     * @param build the build.
     * @return the installed build.
     * @throws ServiceException if the method fails.
     */
    private Build installProcess(final Application app, final Build build) throws ServiceException {
        // create new build and save it
        Build buildNew = null;
        try {
            build.setApplication(app);
            build.setInstall(new Date());
            buildNew = buildService.saveBuild(build);
        } catch (Exception ex) {
            throw new ServiceException(ErrorKeys.BUILD_ALREADY_INSTALLED, ex, app.getName(), build.getMavenVersion(), build.getBuild());
        }

        // create activity for the build.
        try {
            Activity activity = activityProcessService.createActivity(app, buildNew);
            activityService.saveActivity(activity);
        } catch (Exception ex) {
            throw new ServiceException(ErrorKeys.ERROR_CREATE_ACTIVITY_FOR_BUILD, ex, app.getName(), build.getMavenVersion(), build.getBuild());
        }

        return buildNew;
    }

    /**
     * Deploys the build on the system.
     *
     * @param systemGuid the system GUID.
     * @param buildGuid the build GUID.
     * @param type the deploy type.
     * @return the system build.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SystemBuild deploy(final String systemGuid, final String buildGuid, final SystemBuildType type) throws ServiceException {

        SystemBuild result = null;

        // check the system by GUID
        TargetSystemCriteria criteria = new TargetSystemCriteria();
        criteria.setGuid(systemGuid);
        criteria.setFetchApplication(true);
        TargetSystem system = systemService.getSystem(criteria);
        if (system == null) {
            throw new ServiceException(ErrorKeys.NO_SYSTEM_FOR_KEY_FOUND, systemGuid, systemGuid);
        }

        Application application = system.getApplication();

        // check the build
        BuildCriteria buildCriteria = new BuildCriteria();
        buildCriteria.setGuid(buildGuid);
        buildCriteria.setApplication(application.getGuid());
        Build build = buildService.getBuild(buildCriteria);
        if (build == null) {
            throw new ServiceException(ErrorKeys.NO_BUILD_FOR_GUID_AND_APPLICATION_FOUND, buildGuid, buildGuid, systemGuid);
        }

        // load the system build, build and system
        SystemBuildCriteria ssbc = new SystemBuildCriteria();
        ssbc.setMaxDate(Boolean.TRUE);
        ssbc.setSystem(system.getGuid());
        ssbc.setFetchBuild(true);
        SystemBuild sb = systemBuildService.getSystemBuild(ssbc);
        if (sb != null && sb.getBuild() != null) {
            if (build.equals(sb.getBuild())) {
                result = sb;
                LOGGER.log(Level.FINEST, "The build {0} is already deploy on the system {1}", new Object[]{build.getGuid(), system.getGuid()});
            }
        }

        if (result == null) {
            // deploy the build on the system
            SystemBuild sysBuild = new SystemBuild();
            sysBuild.setBuild(build);
            sysBuild.setSystem(system);
            sysBuild.setType(type);
            sysBuild.setDate(new Date());
            result = systemBuildService.saveSystemBuild(sysBuild);
        } else {
            result = null;
        }

        return result;

    }

    /**
     * Deploy the store build.
     *
     * @param key the key.
     * @param build the store build.
     * @param type the store system build type.
     * @return the store system build.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SystemBuild deploy(final String key, final Build build, final SystemBuildType type) throws ServiceException {

        SystemBuild result = null;

        // check the system by key
        TargetSystemCriteria criteria = new TargetSystemCriteria();
        criteria.setKey(key);
        criteria.setFetchApplication(true);
        TargetSystem system = systemService.getSystem(criteria);
        if (system == null) {
            throw new ServiceException(ErrorKeys.NO_SYSTEM_FOR_KEY_FOUND, key, key);
        }

        Application application = system.getApplication();

        // check the build
        BuildCriteria buildCriteria = new BuildCriteria();
        buildCriteria.setDate(build.getDate());
        buildCriteria.setKey(build.getKey());
        buildCriteria.setApplication(application.getGuid());
        Build buildOld = buildService.getBuild(buildCriteria);

        // if the build does not exist install it first
        if (buildOld == null) {
            buildOld = installProcess(application, build);
        } else {
            // load the system build, build and system
            SystemBuildCriteria ssbc = new SystemBuildCriteria();
            ssbc.setMaxDate(Boolean.TRUE);
            ssbc.setSystem(system.getGuid());
            ssbc.setFetchBuild(true);
            SystemBuild sb = systemBuildService.getSystemBuild(ssbc);
            if (sb != null && sb.getBuild() != null) {
                if (buildOld.equals(sb.getBuild())) {
                    result = sb;
                    LOGGER.log(Level.FINEST, "The build {0} is already deploy on the system {1}", new Object[]{buildOld.getGuid(), system.getGuid()});
                }
            }
        }

        if (result == null) {
            // deploy the build on the system
            SystemBuild sysBuild = new SystemBuild();
            sysBuild.setBuild(buildOld);
            sysBuild.setSystem(system);
            sysBuild.setType(type);
            sysBuild.setDate(new Date());
            result = systemBuildService.saveSystemBuild(sysBuild);
        } else {
            result = null;
        }

        return result;
    }

    @Asynchronous
    public void sendNotificationForSystem(String guid) throws ServiceException {
        // load the system build, build and system
        SystemBuildCriteria criteria = new SystemBuildCriteria();
        criteria.setMaxDate(Boolean.TRUE);
        criteria.setSystem(guid);
        criteria.setFetchSystem(true);
        criteria.setFetchBuild(true);
        SystemBuild systemBuild = systemBuildService.getSystemBuild(criteria);
        if (systemBuild == null) {
            throw new ServiceException(ErrorKeys.NO_SYSTEM_BUILD_FOR_SYSTEM_FOUND, guid);
        }
        // send notification
        notification(systemBuild.getBuild().getGuid(), systemBuild.getSystem(), systemBuild, null);
    }

    @Asynchronous
    public void sendNotificationForSystemBuild(String guid) throws ServiceException {
        // load the system build, build and system
        SystemBuildCriteria criteria = new SystemBuildCriteria();
        criteria.setGuid(guid);
        criteria.setFetchSystem(true);
        criteria.setFetchBuild(true);
        SystemBuild systemBuild = systemBuildService.getSystemBuild(criteria);
        if (systemBuild == null) {
            throw new ServiceException(ErrorKeys.NO_SYSTEM_BUILD_FOUND, guid);
        }
        // send notification
        notification(systemBuild.getBuild().getGuid(), systemBuild.getSystem(), systemBuild, null);
    }

    /**
     * Creates the notification and send mails.
     *
     * @param build the build.
     * @param system the system.
     * @param activity the activity.
     */
    private void notification(String build, TargetSystem system, SystemBuild sysBuild, Activity activity) throws ServiceException {

        // check the notification
        if (!system.isNotification()) {
            LOGGER.log(Level.WARNING, "The notification for the system {0} is disabled!", system.getName());
            return;
        }

        ActivityWrapperCriteria wc = new ActivityWrapperCriteria();
        wc.setSortList(true);
        wc.setBuild(build);

        ActivityWrapper wrapper = activityWrapperService.create(wc, activity);
        if (wrapper == null) {
            LOGGER.log(Level.SEVERE, "No activity for the build found!");
            return;
//            throw new ServiceException(ErrorKeys.NO_ACTIVITY_FOUND_FOR_BUILD, build);
        }

        // load the user guids for notification
        NotificationGroupCriteria ngc = new NotificationGroupCriteria();
        ngc.setFetchUsers(true);
        ngc.setSystem(system.getGuid());        
        List<NotificationGroup> groups = notificationGroupService.getNotificationGroups(ngc);

        // create list of users guids.
        Set<String> ug = new HashSet<>();
        if (groups != null) {
            for (NotificationGroup group : groups) {
                ug.addAll(group.getUsers());
            }
        }
        
        // load the users
        List<User> users = null;
        try {            
            UserSearchCriteria uc = new UserSearchCriteria();
            uc.setFetchConfig(true);
            uc.setFetchProfile(true);
            uc.setGuids(ug);            
            users = userService.getUsers(uc);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error reading the user data by criteria", ex);
        }

        if (users == null || users.isEmpty()) {
            LOGGER.log(Level.WARNING, "No user found for the notification system {0}.", system.getName());
            return;
        }

        // create mails
        EmailConfig eConfig = configService.getConfiguration(EmailConfig.class);

        List<Email> mails = createBuildDeployedMails(users, wrapper.getBuild(), system, wrapper, wrapper.getApplication(), wrapper.getProject(), sysBuild);
        if (mails != null) {
            for (Email mail : mails) {
                try {
                    mailService.sendEmail(mail, eConfig);
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, "Error sending the build notification mail to users: {0}", mail.getTo().toString());
                }
            }
        }
    }

    /**
     * Creates the build deployed mails.
     *
     * @param users the set of users.
     * @param system the system.
     * @param build the build.
     * @return the list of mails.
     */
    private List<Email> createBuildDeployedMails(List<User> users, Object... values) {
        List<Email> result = null;
        if (users != null) {
            result = new ArrayList<>();
            for (User user : users) {
                UserConfig config = user.getConfig();

                if (config.isNotification()) {
                    Email mail = new Email();
                    mail.getTo().add(user.getProfile().getEmail());
                    mail.setTemplate(MAIL_BUILD_DEPLOYED_TEMPLATE);
                    // add the user to the parameters
                    mail.getParameters().put(user.getClass().getSimpleName(), user);
                    // add the list of values to the parameters
                    if (values != null) {
                        for (Object value : values) {
                            mail.getParameters().put(value.getClass().getSimpleName(), value);
                        }
                    }
                    result.add(mail);
                }
            }
        }
        return result;
    }
}
