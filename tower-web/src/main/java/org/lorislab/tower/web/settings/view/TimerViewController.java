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
import org.lorislab.tower.timer.model.TimerConfig;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.view.ConfigurationViewController;

/**
 * The project view controller.
 *
 * @author Andrej Petras
 */
@Named("timerVC")
@SessionScoped
public class TimerViewController extends ConfigurationViewController<TimerConfig> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -1766808728513642285L;

    /**
     * The default constructor.
     */
    public TimerViewController() {
        super(Context.TIMER, TimerConfig.class);
    }

}
