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
package org.lorislab.tower.web.common.view;

import javax.ejb.EJB;
import org.lorislab.barn.api.service.ConfigurationService;
import org.lorislab.jel.jsf.api.interceptor.annotations.FacesServiceMethod;
import org.lorislab.jel.jsf.entity.controller.AbstractEntityViewController;
import org.lorislab.tower.web.common.action.Context;

/**
 * The project view controller.
 *
 * @param <T> the type of the configuration model.
 * @author Andrej Petras
 */
public class ConfigurationViewController<T> extends AbstractEntityViewController<T> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1766808728513642285L;

    /**
     * The project service.
     */
    @EJB
    private ConfigurationService service;

    /**
     * The configuration class.
     */
    private Class<T> clazz;

    /**
     * The default constructor.
     */
    public ConfigurationViewController() {
        this(null, null);
    }

    /**
     * The default constructor.
     *
     * @param context the context.
     * @param clazz the configuration class.
     */
    public ConfigurationViewController(Context context, Class<T> clazz) {
        super(context);
        this.clazz = clazz;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    @FacesServiceMethod
    public Object edit(String guid) throws Exception {
        if (isEmpty()) {
            super.edit(guid);
            T tmp = service.getConfiguration(clazz);
            setModel(tmp);
        }
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    @FacesServiceMethod
    public Object save() throws Exception {
        T tmp = service.setConfiguration(getModel());
        setModel(tmp);
        return null;
    }

}
