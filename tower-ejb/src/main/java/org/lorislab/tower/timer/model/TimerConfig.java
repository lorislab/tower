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

package org.lorislab.tower.timer.model;

import java.io.Serializable;

/**
 * The timer configuration model.
 * 
 * @author Andrej Petras
 */
public class TimerConfig implements Serializable {
    
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 2489003456653865087L;
   
    /**
     * Start the timer after restart.
     */
    private boolean start = false;
    /**
     * Enable the execution of the timer.
     */
    private boolean enabled = false;
    /**
     * The timeout configuration: second.
     */
    private String second = "*/30";
    /**
     * The timeout configuration: minute.
     */    
    private String minute = "*";
    /**
     * The timeout configuration: hour.
     */    
    private String hour = "*";

    /**
     * @return the start
     */
    public boolean isStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(boolean start) {
        this.start = start;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the second
     */
    public String getSecond() {
        return second;
    }

    /**
     * @param second the second to set
     */
    public void setSecond(String second) {
        this.second = second;
    }

    /**
     * @return the minute
     */
    public String getMinute() {
        return minute;
    }

    /**
     * @param minute the minute to set
     */
    public void setMinute(String minute) {
        this.minute = minute;
    }

    /**
     * @return the hour
     */
    public String getHour() {
        return hour;
    }

    /**
     * @param hour the hour to set
     */
    public void setHour(String hour) {
        this.hour = hour;
    }
    
    
}
