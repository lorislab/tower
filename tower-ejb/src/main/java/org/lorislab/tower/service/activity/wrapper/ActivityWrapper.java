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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.lorislab.jel.jpa.wrapper.AbstractPersistentWrapper;
import org.lorislab.tower.store.model.Activity;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.Project;

/**
 * The activity wrapper.
 *
 * @author Andrej_Petras
 */
public class ActivityWrapper extends AbstractPersistentWrapper<Activity> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -8296692954766508322L;

    /**
     * The set of statuses.
     */
    private final Set<String> statuses;

    /**
     * The set of types.
     */
    private final Set<String> types;

    /**
     * The list of activity changes.
     */
    private List<ActivityChangeWrapper> changes;

    /**
     * The application of the build.
     */
    private Application application;

    /**
     * The project of the build.
     */
    private Project project;

    /**
     * The default constructor.
     */
    public ActivityWrapper() {
        this.statuses = new HashSet<>();
        this.types = new HashSet<>();
    }

    /**
     * Sets the activity changes.
     *
     * @param changes the activity changes.
     */
    public void setChanges(List<ActivityChangeWrapper> changes) {
        this.changes = changes;
    }

    /**
     * Gets the list of activity changes.
     *
     * @return the list of activity changes.
     */
    public List<ActivityChangeWrapper> getChanges() {
        return changes;
    }

    /**
     * Gets the set of activity statuses.
     *
     * @return the set of activity statuses.
     */
    public Set<String> getStatuses() {
        return statuses;
    }

    /**
     * Gets the set of the activity types.
     *
     * @return the set of the activity types.
     */
    public Set<String> getTypes() {
        return types;
    }

    /**
     * Gets the application for the build.
     *
     * @return the application for the build.
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Sets the application for the build.
     *
     * @param application the application for the build.
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    /**
     * Gets the project for the build.
     *
     * @return the project for the build.
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets the project for the build.
     *
     * @param project the project.
     */
    public void setProject(Project project) {
        this.project = project;
    }
}
