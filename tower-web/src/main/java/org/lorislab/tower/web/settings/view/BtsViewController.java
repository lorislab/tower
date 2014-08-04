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
package org.lorislab.tower.web.settings.view;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.tower.store.ejb.BTSystemService;
import org.lorislab.tower.store.model.BTSystem;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.action.Navigation;
import org.lorislab.tower.web.common.view.EntityViewController;

/**
 * The project view controller.
 *
 * @author Andrej Petras
 */
@Named("btsVC")
@SessionScoped
public class BtsViewController extends EntityViewController<BTSystem> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1766808728513642285L;

    /**
     * The project service.
     */
    @EJB
    private BTSystemService service;

    /**
     * The default constructor.
     */
    public BtsViewController() {
        super(Context.BTS);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object open(String guid) {
        Object result = Navigation.TO_BTS_EDIT;
        BTSystem tmp = service.getBTSystem(guid);
        setModel(tmp);
        
        if (isEmpty()) {
            result = null;
        }
        return result;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object delete() throws Exception {
        service.deleteBTSystem(getModel().getGuid());
        setModel(null);
        return Navigation.TO_BTS;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object save() throws Exception {
        BTSystem tmp = service.saveBTSystem(getModel());
        setModel(tmp);
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object close() throws Exception {
        setModel(null);
        return Navigation.TO_BTS;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object create() throws Exception {
        setModel(new BTSystem());
        return Navigation.TO_BTS_EDIT;
    }

}
