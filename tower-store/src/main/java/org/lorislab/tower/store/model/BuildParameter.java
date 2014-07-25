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
package org.lorislab.tower.store.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import org.lorislab.jel.jpa.model.Persistent;
import org.lorislab.tower.store.model.enums.BuildParameterType;

/**
 * The build parameter.
 *
 * @author Andrej Petras
 */
@Entity
@Table(name = "TW_ST_BUILD_PARAM")
public class BuildParameter extends Persistent {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -5508967118564416749L;

    @Column(name = "C_TYPE")
    @Enumerated(EnumType.STRING)
    private BuildParameterType type;

    @Column(name = "C_NAME")
    private String name;

    @Column(name = "C_VALUE")
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BuildParameterType getType() {
        return type;
    }

    public void setType(BuildParameterType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
