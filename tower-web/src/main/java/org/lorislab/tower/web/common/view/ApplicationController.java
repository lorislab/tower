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
import org.lorislab.tower.store.model.enums.ApplicationScmRepository;

/**
 * The application controller.
 *
 * @author Andrej Petras
 */
@Named("applicationC")
@ApplicationScoped
public class ApplicationController implements Serializable {

    private static final long serialVersionUID = 6122723230744615314L;

    private static final Logger LOGGER = Logger.getLogger(ApplicationController.class.getName());

    private List<SelectItem> btsTypes = new ArrayList<>();

    private List<SelectItem> scmTypes = new ArrayList<>();

    private List<SelectItem> appScmTypes = new ArrayList<>();

    private Prm prm;

    private Converter enumConverter;
    
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
        appScmTypes = FacesResourceUtil.getEnumSelectItems(ApplicationScmRepository.class);

        enumConverter = new EnumConverter();
        
        // load version
        prm = PrmLoader.load(ApplicationController.class);
        LOGGER.log(Level.INFO, "********* Starting the Tower version {0} {1} *********",new Object[]{ prm.getVersion(), prm.getBuild()});
    }

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
