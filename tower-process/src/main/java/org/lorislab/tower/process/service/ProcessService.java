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
package org.lorislab.tower.process.service;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.tower.base.dto.model.Request;
import org.lorislab.tower.process.resources.ErrorKeys;
import org.lorislab.tower.store.criteria.ApplicationCriteria;
import org.lorislab.tower.store.ejb.ApplicationService;
import org.lorislab.tower.store.ejb.BuildService;
import org.lorislab.tower.store.model.Activity;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.Build;

/**
 *
 * @author Andrej_Petras
 */
public class ProcessService {

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
     * Install the store build.
     *
     * @param request the request.
     * @throws ServiceException if the method fails.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void install(final Request request) throws ServiceException {
        Build build = VersionMapper.map(request.getVersion());
        // check the application for to key
        ApplicationCriteria criteria = new ApplicationCriteria();
        criteria.setKey(request.getKey());
        Application app = appService.getApplication(criteria);
        if (app == null) {
            throw new ServiceException(ErrorKeys.NO_APPLICATION_FOR_KEY_FOUND, request.getKey(), request.getKey());
        }
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
    }
}
