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
import javax.inject.Inject;
import javax.inject.Named;
import org.lorislab.guardian.api.model.UserData;
import org.lorislab.jel.jsf.api.interceptor.annotations.FacesServiceMethod;
import org.lorislab.postman.api.model.EmailConfig;
import org.lorislab.tower.process.ejb.TestService;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.view.ChangePasswordListener;
import org.lorislab.tower.web.common.view.ChangePasswordViewController;
import org.lorislab.tower.web.common.view.ConfigurationViewController;
import org.lorislab.tower.web.settings.action.SendTestAction;

/**
 * The project view controller.
 *
 * @author Andrej Petras
 */
@Named("mailVC")
@SessionScoped
public class MailViewController extends ConfigurationViewController<EmailConfig> implements ChangePasswordListener {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1766808728513642285L;

    /**
     * The change password view controller.
     */
    private final ChangePasswordViewController passwordVC;

    /**
     * Send test email action.
     */
    private final SendTestAction sendTestAction;

    /**
     * The test service.
     */
    @EJB
    private TestService testService;

    /**
     * The user data.
     */
    @Inject
    private UserData userData;
    
    /**
     * The default constructor.
     */
    public MailViewController() {
        super(Context.MAIL, EmailConfig.class);
        passwordVC = new ChangePasswordViewController(this, Context.MAIL);
        sendTestAction = new SendTestAction(this);
    }

    /**
     * Gets the send test email action.
     *
     * @return the send test email action.
     */
    public SendTestAction getSendTestAction() {
        return sendTestAction;
    }

    /**
     * Gets the password view controller.
     *
     * @return the password view controller.
     */
    public ChangePasswordViewController getPasswordVC() {
        return passwordVC;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void changePassword(String data) {
        getModel().setPassword(data);
    }

    /**
     * {@inheritDoc }
     */
    @FacesServiceMethod
    public Object send() {
        try {
            String email = userData.getProfile().getEmail();
            testService.sendTestEmail(email);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
