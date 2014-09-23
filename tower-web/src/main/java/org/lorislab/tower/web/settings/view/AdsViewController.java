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

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.guardian.user.ads.config.ConnectionConfig;
import org.lorislab.tower.timer.model.TimerConfig;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.view.ChangePasswordListener;
import org.lorislab.tower.web.common.view.ChangePasswordViewController;
import org.lorislab.tower.web.common.view.ConfigurationViewController;

/**
 * The ADS configuration view controller.
 *
 * @author Andrej Petras
 */
@Named("adsVC")
@SessionScoped
public class AdsViewController extends ConfigurationViewController<ConnectionConfig> implements ChangePasswordListener {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1766808728513642285L;

    /**
     * The change password view controller.
     */
    private final ChangePasswordViewController passwordVC;
    
    /**
     * The default constructor.
     */
    public AdsViewController() {
        super(Context.ADS, ConnectionConfig.class);
        passwordVC = new ChangePasswordViewController(this, Context.ADS);
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
   
}
