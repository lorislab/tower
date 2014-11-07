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

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.tower.process.model.DeployResult;
import org.lorislab.tower.process.model.InstallResult;
import org.lorislab.tower.process.resources.ErrorKeys;
import org.lorislab.tower.service.activity.ejb.ActivityBuildService;
import org.lorislab.tower.store.criteria.BuildCriteria;
import org.lorislab.tower.store.criteria.SystemBuildCriteria;
import org.lorislab.tower.store.ejb.ActivityService;
import org.lorislab.tower.store.ejb.BuildService;
import org.lorislab.tower.store.ejb.SystemBuildService;
import org.lorislab.tower.store.ejb.TargetSystemService;
import org.lorislab.tower.store.model.Activity;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.Build;
import org.lorislab.tower.store.model.SystemBuild;
import org.lorislab.tower.store.model.TargetSystem;
import org.lorislab.tower.store.model.enums.SystemBuildType;

/**
 * The process execution service.
 *
 * @author Andrej_Petras
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ExecutionProcessService {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(ExecutionProcessService.class.getName());
    /**
     * The store build service.
     */
    @EJB
    private BuildService buildService;

    /**
     * The activity process service.
     */
    @EJB
    private ActivityBuildService activityProcessService;

    /**
     * The store activity service.
     */
    @EJB
    private ActivityService activityService;

    /**
     * The system build service.
     */
    @EJB
    private SystemBuildService systemBuildService;

    /**
     * Deploy the build.
     *
     * @param application the application.
     * @param system the system.
     * @param build the build.
     * @param type the type of the deployment.
     * @return the deployment result.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public DeployResult deploy(final Application application, final TargetSystem system, final Build build, final SystemBuildType type) throws ServiceException {

        DeployResult result = new DeployResult();
        result.setApplication(application);
        result.setSystem(system);
        result.setBuild(build);
        result.setDeployed(false);

        SystemBuild sysBuild = null;
        
        // load the system build, build and system
        SystemBuildCriteria ssbc = new SystemBuildCriteria();
        ssbc.setMaxDate(Boolean.TRUE);
        ssbc.setSystem(system.getGuid());
        ssbc.setFetchBuild(true);
        SystemBuild sb = systemBuildService.getSystemBuild(ssbc);
        if (sb != null && sb.getBuild() != null) {
            if (build.equals(sb.getBuild())) {
                sysBuild = sb;                
            }
        }
        
        if (sysBuild == null) {            
            
            // deploy the build on the system
            sysBuild = new SystemBuild();
            sysBuild.setBuild(build);
            sysBuild.setSystem(system);
            sysBuild.setType(type);
            sysBuild.setDate(new Date());
            sysBuild = systemBuildService.saveSystemBuild(sysBuild);  
            result.setDeployed(true);
            
            // load the activity
            
        } else {            
            LOGGER.log(Level.FINEST, "The build {0} is already deploy on the system {1}", new Object[]{build.getGuid(), system.getGuid()});            
        }
        result.setSystemBuild(sysBuild);
        
        
        
        return result;
    }

    /**
     * Install the store build.
     *
     * @param build the build.
     * @param app the application.
     * @return the result.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public InstallResult install(final Build build, final Application app) throws ServiceException {

        InstallResult result = new InstallResult();
        result.setApplication(app);
        result.setInstall(false);

        // check the build
        BuildCriteria buildCriteria = new BuildCriteria();
        buildCriteria.setDate(build.getDate());
        buildCriteria.setKey(build.getKey());
        buildCriteria.setApplication(app.getGuid());
        Build buildOld = buildService.getBuild(buildCriteria);
        result.setBuild(build);

        if (buildOld == null) {

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
            Activity activity;
            try {
                activity = activityProcessService.createActivityForApplication(app.getGuid(), buildNew);
                activityService.saveActivity(activity);
            } catch (Exception ex) {
                throw new ServiceException(ErrorKeys.ERROR_CREATE_ACTIVITY_FOR_BUILD, ex, app.getName(), build.getMavenVersion(), build.getBuild());
            }

            // create the result   
            result.setInstall(true);
            result.setBuild(buildNew);
            result.setActivity(activity);
        }

        return result;
    }

}
