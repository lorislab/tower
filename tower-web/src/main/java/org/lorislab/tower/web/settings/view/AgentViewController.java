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
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.lorislab.jel.jsf.util.FacesResourceUtil;
import org.lorislab.tower.process.ejb.ChangePasswordService;
import org.lorislab.tower.process.model.ChangePassword;
import org.lorislab.tower.store.ejb.AgentService;
import org.lorislab.tower.store.model.Agent;
import org.lorislab.tower.web.common.action.Action;
import org.lorislab.tower.web.common.action.ChangePasswordAction;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.action.Navigation;
import org.lorislab.tower.web.common.view.ChangePasswordViewController;
import org.lorislab.tower.web.common.view.EntityViewController;
import org.lorislab.tower.web.settings.resources.ValidationErrorKey;

/**
 * The agent view controller.
 *
 * @author Andrej Petras
 */
@Named("agentVC")
@SessionScoped
public class AgentViewController extends EntityViewController<Agent> implements ChangePasswordViewController {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1766808728513642285L;

    /**
     * The agent service.
     */
    @EJB
    private AgentService service;

    /**
     * The change password service.
     */
    @EJB
    private ChangePasswordService passwordService;
    
    /**
     * The change password.
     */
    private ChangePassword password;

    /**
     * The change password action.
     */
    private ChangePasswordAction changePasswordAction;
    
    /**
     * The default constructor.
     */
    public AgentViewController() {
        super(Context.AGENT);
        password = new ChangePassword();
        changePasswordAction = new ChangePasswordAction(this, Context.BTS, Action.PASSWORD);
    }

    /**
     * Gets the change password action.
     *
     * @return the change password action.
     */
    public ChangePasswordAction getChangePasswordAction() {
        return changePasswordAction;
    }
    
    /**
     * Gets the change password.
     *
     * @return the change password.
     */
    public ChangePassword getPassword() {
        return password;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public Object open(String guid) {
        Object result = Navigation.TO_AGENT_EDIT;
        Agent tmp = service.getAgent(guid);
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
        service.deleteAgent(getModel().getGuid());
        setModel(null);
        return Navigation.TO_AGENT;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object save() throws Exception {
        Agent tmp = service.saveAgent(getModel());
        setModel(tmp);
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object close() throws Exception {
        setModel(null);
        return Navigation.TO_AGENT;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object create() throws Exception {
        setModel(new Agent());
        return Navigation.TO_AGENT_EDIT;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object openPasswordChange() throws Exception {
        password.clear();
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object changePassword() throws Exception {
        try {
            String tmp = passwordService.createPassword(password);
            getModel().setPassword(tmp.toCharArray());
        } catch (Exception ex) {
            FacesResourceUtil.addFacesErrorMessage(ValidationErrorKey.PASSWORD_DOES_NOT_MACH);
            FacesContext.getCurrentInstance().validationFailed();
        }
        return null;
    }    
}
