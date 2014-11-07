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
package org.lorislab.tower.rs.service;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import org.lorislab.jel.base.resources.ResourceManager;
import org.lorislab.jel.ejb.exception.ServiceException;
import org.lorislab.tower.base.dto.model.Request;
import org.lorislab.tower.base.dto.model.Result;
import org.lorislab.tower.base.dto.model.enums.Status;
import org.lorislab.tower.base.rs.service.ServerService;
import org.lorislab.tower.process.ejb.ProcessService;

/**
 * The monitor rest-service implementation.
 *
 * @author Andrej Petras
 */
public class ServerServiceImpl implements ServerService {

    /**
     * The process service.
     */
    @EJB
    private ProcessService service;

    /**
     * The HTTP request.
     */
    @Context
    private HttpServletRequest httpRequest;

    /**
     * {@inheritDoc }
     */
    @Override
    public Result deploy(Request request) {
        Result result = new Result();
        result.setStatus(Status.ERROR);
        if (request != null) {
            try {
                service.deploy(request);
                result.setStatus(Status.OK);
            } catch (ServiceException ex) {
                result.setMessage(ResourceManager.getMessage(ex.getResourceMessage(), httpRequest.getLocale()));
            }
        } else {
            result.setMessage("Missing request object!");
        }
        return result;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public Result install(Request request) {
        Result result = new Result();
        result.setStatus(Status.ERROR);
        if (request != null) {
            try {
                service.install(request);
                result.setStatus(Status.OK);
            } catch (ServiceException ex) {
                result.setMessage(ResourceManager.getMessage(ex.getResourceMessage(), httpRequest.getLocale()));
            }
        } else {
            result.setMessage("Missing request object!");
        }
        return result;
    }

}
