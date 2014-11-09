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
 * The version mapper.
 *
 * @author Andrej Petras
 */
public final class VersionMapper {

    /**
     * The default constructor.
     */
    private VersionMapper() {
        // empty constructor
    }

    /**
     * Maps the version to the map.
     *
     * @param data the version data.
     * @return the build.
     */
    public static Build map(Version data) {
        Build result = new Build();
        result.setUid(data.getUid());
        result.setBuild(data.getBuild());
        result.setArtifactId(data.getArtifactId());
        result.setGroupId(data.getGroupId());
        result.setMavenVersion(data.getVersion());
        result.setDate(data.getDate());
        result.setScm(data.getScm());
        result.setService(data.getService());
        result.setVer(data.getVer());
        result.setKey(data.getKey());
        result.setProjectVersion(data.getProject());
        result.setParameters(new HashSet<BuildParameter>());
        result.getParameters().addAll(createStoreBuildParameter(data.getManifest(), BuildParameterType.MANIFEST));
        result.getParameters().addAll(createStoreBuildParameter(data.getOther(), BuildParameterType.OTHER));
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
