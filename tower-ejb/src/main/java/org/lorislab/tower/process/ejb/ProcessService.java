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

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.jel.base.resources.ResourceManager;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.tower.agent.ejb.AgentClientService;
import org.lorislab.tower.agent.util.VersionMapper;
import org.lorislab.tower.base.dto.model.Request;
import org.lorislab.tower.notification.ejb.NotificationService;
import org.lorislab.tower.process.model.DeployResult;
import org.lorislab.tower.process.model.InstallResult;
import org.lorislab.tower.process.resources.ErrorKeys;
import org.lorislab.tower.store.criteria.ApplicationCriteria;
import org.lorislab.tower.store.criteria.TargetSystemCriteria;
import org.lorislab.tower.store.ejb.ApplicationService;
import org.lorislab.tower.store.ejb.TargetSystemService;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.Build;
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
     * The store system service.
     */
    @EJB
    private TargetSystemService systemService;

    /**
     * The agent client service.
     */
    @EJB
    private AgentClientService agentClientService;

    /**
     * The install service.
     */
    @EJB
    private ExecutionProcessService installService;

    /**
     * The notification service.
     */
    @EJB
    private NotificationService notificationService;

    /**
     * The store application service.
     */
    @EJB
    private ApplicationService appService;

    /**
     * The timer process method.
     */
    public void timerService() {
        TargetSystemCriteria criteria = new TargetSystemCriteria();
        criteria.setFetchAgent(true);
        criteria.setTimer(Boolean.TRUE);
        criteria.setEnabled(Boolean.TRUE);
        List<TargetSystem> systems = systemService.getSystems(criteria);
        if (systems != null) {
            for (TargetSystem system : systems) {
                try {
                    Build build = agentClientService.getBuild(system.getAgent(), system.getService());
                    if (build != null) {
                        // check the application for to key
                        ApplicationCriteria appCriteria = new ApplicationCriteria();
                        appCriteria.setSystem(system.getGuid());
                        Application app = appService.getApplication(appCriteria);
                        if (app != null) {

                            // install the request
                            InstallResult result = installService.install(build, app);
                            if (result.isInstall()) {
                                // send the notification about the new build
                                notificationService.sendInstallNotification(result);
                            }
                            // deploy the build
                            DeployResult deployResult = installService.deploy(app, system, result.getBuild(), SystemBuildType.TIMER);
                            // send notification
                            if (deployResult.isDeployed()) {
                                notificationService.sendDeployNotification(deployResult);
                            }
                        } else {
                            LOGGER.log(Level.WARNING, "Could not get find the application for the system {0}", system.getGuid());
                        }
                    } else {
                        LOGGER.log(Level.WARNING, "Could not get the build for the system {0}", system.getGuid());
                    }
                } catch (ServiceException e) {
                    LOGGER.log(Level.SEVERE, "Error: {0}", ResourceManager.getMessage(e.getResourceMessage(), null));
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, "Error reading the build information from system: " + system.getName(), ex);
                }
            }
        } else {
            LOGGER.log(Level.FINEST, "No systems to check");
        }
    }

    /**
     * Install the store build.
     *
     * @param request the request.
     * @throws ServiceException if the method fails.
     */
    public void install(final Request request) throws ServiceException {
        // check the application for to key
        ApplicationCriteria criteria = new ApplicationCriteria();
        criteria.setKey(request.getKey());
        Application app = appService.getApplication(criteria);
        if (app == null) {
            throw new ServiceException(ErrorKeys.NO_APPLICATION_FOR_KEY_FOUND, request.getKey(), request.getKey());
        }
        // get the build from the version
        Build build = VersionMapper.map(request.getVersion());
        // install the request
        InstallResult result = installService.install(build, app);
        if (result.isInstall()) {
            // send the notification about the new build
            notificationService.sendInstallNotification(result);
        } else {
            throw new ServiceException(ErrorKeys.BUILD_ALREADY_INSTALLED, build.getGuid(), app.getName(), build.getMavenVersion(), build.getBuild());
        }
    }

    /**
     * Deploy the store build.
     *
     * @param request the request.
     * @throws ServiceException if the method fails.
     */
    public void deploy(final Request request) throws ServiceException {
        // check the system by GUID
        TargetSystemCriteria criteria = new TargetSystemCriteria();
        criteria.setKey(request.getKey());
        criteria.setFetchApplication(true);
        TargetSystem system = systemService.getSystem(criteria);
        if (system == null) {
            throw new ServiceException(ErrorKeys.NO_SYSTEM_FOR_KEY_FOUND, request.getKey(), request.getKey());
        }
        // get the build from the version
        Build build = VersionMapper.map(request.getVersion());
        // install the request
        InstallResult result = installService.install(build, system.getApplication());
        if (result.isInstall()) {
            // send the notification about the new build
            notificationService.sendInstallNotification(result);
        }
        // deploy the request
        DeployResult deployResult = installService.deploy(system.getApplication(), system, result.getBuild(), SystemBuildType.MANUAL);
        // send notification
        if (deployResult.isDeployed()) {
            notificationService.sendDeployNotification(deployResult);
        }
    }

}
