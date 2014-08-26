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

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Timer;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.tower.timer.ejb.TimerService;
import org.lorislab.tower.timer.model.TimerConfig;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.view.ConfigurationViewController;
import org.lorislab.tower.web.settings.action.StartTimerAction;
import org.lorislab.tower.web.settings.action.StopTimerAction;

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
     * The timer service.
     */
    @EJB
    private TimerService timerService;

    /**
     * The timer next execution date.
     */
    private Date date;

    /**
     * The start action.
     */
    private StartTimerAction startAction;

    /**
     * The stop action.
     */
    private StopTimerAction stopAction;

    /**
     * The default constructor.
     */
    public TimerViewController() {
        super(Context.TIMER, TimerConfig.class);
        startAction = new StartTimerAction(this);
        stopAction = new StopTimerAction(this);
    }

    /**
     * Gets the start action.
     * 
     * @return the start action. 
     */
    public StartTimerAction getStartAction() {
        return startAction;
    }

    /**
     * Gets the stop action.
     *
     * @return the stop action.
     */
    public StopTimerAction getStopAction() {
        return stopAction;
    }

    /**
     * Gets the date.
     *
     * @return the timer next execution date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object open(String guid) {        
        Object result = super.open(guid);
        updateDate();
        return result;
    }

    /**
     * Stops the timer.
     *
     * @return the navigation path.
     */
    public String stop() {
        timerService.stop();
        updateDate();
        return null;
    }

    /**
     * Start the timer.
     *
     * @return navigation path.
     */
    public String start() {
        timerService.start();
        updateDate();
        return null;
    }
    
    private void updateDate() {
        date = null;
        Timer timer = timerService.getTimer();
        if (timer != null) {
            date = timer.getNextTimeout();
        }        
    }
}
