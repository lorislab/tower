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
package org.lorislab.tower.process.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.lorislab.tower.store.model.Activity;
import org.lorislab.tower.store.model.Application;
import org.lorislab.tower.store.model.Build;

/**
 *
 * @author Andrej_Petras
 */
public class InstallResult {
    

    private Activity activity;
    
    private Build build;
    
    private Application application;
    
    private boolean install;

    public boolean isInstall() {
        return install;
    }

    public void setInstall(boolean install) {
        this.install = install;
    }
        
    public Object[] getValues() {
        return new Object[] {activity, build, application};
    }
    
    /**
     * @return the activity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * @param activity the activity to set
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * @return the build
     */
    public Build getBuild() {
        return build;
    }

    /**
     * @param build the build to set
     */
    public void setBuild(Build build) {
        this.build = build;
    }

    /**
     * @return the application
     */
    public Application getApplication() {
        return application;
    }

    /**
     * @param application the application to set
     */
    public void setApplication(Application application) {
        this.application = application;
    }
    
    
}
