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

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

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
    
    private static final String MAVEN_FILE = "/META-INF/maven/org.lorislab.tower/tower-web/pom.properties";

    private static final String MAVEN_KEY = "version";
    
    private static final String DEFAULT_VERSION = "0.0.0";
    
    private String version;

    @PostConstruct
    public void init() {
        Properties properties = new Properties();
        InputStream in = null;
        try {
            try {
                in = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(MAVEN_FILE);
                properties.load(in);
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        } catch (IOException ex) {
            // do nothing
        }
        
        version = properties.getProperty(MAVEN_KEY, DEFAULT_VERSION);
        LOGGER.log(Level.INFO, "********* Starting the Tower version {0} *********", version);
    }

    public String getVersion() {
        return version;
    }

}
