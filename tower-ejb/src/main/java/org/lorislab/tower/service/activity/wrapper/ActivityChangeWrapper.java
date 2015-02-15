/*
 * Copyright 2015 lorislab.org.
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
package org.lorislab.tower.service.activity.wrapper;

import java.util.List;
import org.lorislab.jel.jpa.wrapper.AbstractPersistentWrapper;
import org.lorislab.tower.store.model.ActivityChange;

/**
 * The activity change wrapper.
 * 
 * @author Andrej Petras
 */
public class ActivityChangeWrapper extends AbstractPersistentWrapper<ActivityChange> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -897702400663914279L;

    /**
     * The activity change logs.
     */
    private List<ActivityLogWrapper> logs;

    /**
     * Gets the activity change logs.
     *
     * @return the activity change logs.
     */
    public List<ActivityLogWrapper> getLogs() {
        return logs;
    }

    /**
     * Sets the activity change logs.
     *
     * @param logs the activity change logs.
     */
    public void setLogs(List<ActivityLogWrapper> logs) {
        this.logs = logs;
    }

}
