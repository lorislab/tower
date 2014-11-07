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
package org.lorislab.tower.service.activity.util;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.lorislab.tower.service.activity.model.BuildInfo;
import org.lorislab.tower.store.model.Build;

/**
 *
 * @author Andrej_Petras
 */
public final class BuildInfoUtil {

    private BuildInfoUtil() {
    }
    
    /**
     * Finds the build for the date in the list.
     *
     * @param infos the list of build info order ASC.
     * @param date the date.
     * @return the store build or <code>null</code> if no build find.
     */
    public static Build findBuild(List<BuildInfo> infos, Date date) {
        Build result = null;
        if (infos != null && date != null) {
            Iterator<BuildInfo> iter = infos.iterator();
            while (result == null && iter.hasNext()) {
                BuildInfo item = iter.next();
                if (!date.after(item.getDate())) {
                    result = item.getBuild();
                }
            }
        }
        return result;
    }    
    
}
