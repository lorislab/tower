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

package org.lorislab.tower.agent.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.lorislab.tower.base.dto.model.Version;
import org.lorislab.tower.store.model.Build;
import org.lorislab.tower.store.model.BuildParameter;
import org.lorislab.tower.store.model.enums.BuildParameterType;

/**
 *
 * @author Andrej Petras
 */
public final class VersionMapper {
    
    private VersionMapper() {
        // empty constructor
    }
    
    public static Build map(Version data) {
        Build result = new Build();
        result.setUid(data.uid);
        result.setBuild(data.build);
        result.setArtifactId(data.artifactId);
        result.setGroupId(data.groupId);
        result.setMavenVersion(data.version);
        result.setDate(data.date);
        result.setScm(data.scm);
        result.setService(data.service);
        result.setVer(data.ver);
        result.setKey(data.key);
        result.setParameters(new HashSet<BuildParameter>());
        result.getParameters().addAll(createStoreBuildParameter(data.manifest, BuildParameterType.MANIFEST));
        result.getParameters().addAll(createStoreBuildParameter(data.other, BuildParameterType.OTHER));
        return result;
    }

    /**
     * Creates the list of store build parameters.
     *
     * @param params the map of parameters.
     * @param type the type of parameters.
     * @return the corresponding list of parameter.
     */
    private static List<BuildParameter> createStoreBuildParameter(Map<String, String> params, BuildParameterType type) {
        List<BuildParameter> result = new ArrayList<>();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                BuildParameter param = new BuildParameter();
                param.setType(type);
                param.setName(entry.getKey());
                param.setValue(entry.getValue());
                result.add(param);
            }
        }
        return result;
    }
    
}
