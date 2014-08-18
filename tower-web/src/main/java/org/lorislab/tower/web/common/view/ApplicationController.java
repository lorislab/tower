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
package org.lorislab.tower.web.common.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.lorislab.jel.jsf.converter.EnumConverter;
import org.lorislab.jel.jsf.util.FacesResourceUtil;
import org.lorislab.tower.base.loader.PrmLoader;
import org.lorislab.tower.base.model.Prm;
import org.lorislab.tower.bts.service.BtsService;
import org.lorislab.tower.scm.service.ScmService;
import org.lorislab.tower.store.model.enums.AgentType;
import org.lorislab.tower.store.model.enums.ApplicationScmRepository;

/**
 * The application controller.
 *
 * @author Andrej Petras
 */
@Named("applicationC")
@ApplicationScoped
public class ApplicationController implements Serializable {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 6122723230744615314L;
    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger(ApplicationController.class.getName());

    /**
     * The bug tracking system types.
     */
    private final List<SelectItem> btsTypes = new ArrayList<>();

    /**
     * The source control management types.
     */
    private final List<SelectItem> scmTypes = new ArrayList<>();

    /**
     * The application source control management types.
     */
    private final List<SelectItem> appScmTypes = new ArrayList<>();

    /**
     * The list of agent types.
     */
    private final List<SelectItem> agentTypes = new ArrayList<>();

    /**
     * The project release model.
     */
    private Prm prm;

    /**
     * The enumeration converter.
     */
    private Converter enumConverter;

    /**
     * The post constructor method.
     */
    @PostConstruct
    public void init() {
        // load BTS types
        Map<String, String> tmp = BtsService.getTypes();
        for (Map.Entry<String, String> item : tmp.entrySet()) {
            btsTypes.add(new SelectItem(item.getKey(), item.getValue()));
        }

        // load SCM types
        Map<String, String> tmp2 = ScmService.getTypes();
        for (Map.Entry<String, String> item : tmp2.entrySet()) {
            scmTypes.add(new SelectItem(item.getKey(), item.getValue()));
        }

        // load application source code managment repository types.
        appScmTypes.addAll(FacesResourceUtil.getEnumSelectItems(ApplicationScmRepository.class));

        // load agent types
        agentTypes.addAll(FacesResourceUtil.getEnumSelectItems(AgentType.class));

        // create enum converter
        enumConverter = new EnumConverter();

        // load version
        prm = PrmLoader.load(ApplicationController.class);
        LOGGER.log(Level.INFO, "********* Starting the Tower version {0} {1} *********", new Object[]{prm.getVersion(), prm.getBuild()});
    }

    /**
     * Gets the list of agent types.
     *
     * @return the list of agent types.
     */
    public List<SelectItem> getAgentTypes() {
        return agentTypes;
    }

    /**
     * Gets the enumeration converter.
     *
     * @return the enumeration converter.
     */
    public Converter getEnumConverter() {
        return enumConverter;
    }

    /**
     * Gets the list of all application source control management types.
     *
     * @return the list of all application source control management types.
     */
    public List<SelectItem> getAppScmTypes() {
        return appScmTypes;
    }

    /**
     * Gets the list of all source control management types.
     *
     * @return the list of all source control management types.
     */
    public List<SelectItem> getScmTypes() {
        return scmTypes;
    }

    /**
     * Gets the list of all bug tracking system types.
     *
     * @return the list of all bug tracking system types.
     */
    public List<SelectItem> getBtsTypes() {
        return btsTypes;
    }

    /**
     * Gets the version of the application.
     *
     * @return the version of the application.
     */
    public Prm getPrm() {
        return prm;
    }

}
