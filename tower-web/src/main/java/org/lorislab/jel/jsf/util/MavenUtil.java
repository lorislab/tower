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

package org.lorislab.jel.jsf.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.faces.context.FacesContext;

/**
 * The MAVEN utility class.
 * 
 * @author Andrej Petras
 */
public final class MavenUtil {
    
    private static final String MAVEN_PROPERTIES = "pom.properties";    
    
    private static final String MAVEN_DIR = "/META-INF/maven/";
    
    private static final String MAVEN_KEY = "version";

    private static final String PATH = "/";
    /**
     * The default constructor
     */
    private MavenUtil() {
        // empty constructor
    }
    
    public static String getVersion(String groupId, String artifactId) {
        
        StringBuilder sb = new StringBuilder();
        sb.append(MAVEN_DIR).append(groupId).append(PATH);
        sb.append(artifactId).append(PATH).append(MAVEN_PROPERTIES);
        
        Properties properties = new Properties();
        InputStream in = null;
        try {
            try {                
                in = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(sb.toString());
                properties.load(in);
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        } catch (IOException ex) {
            // do nothing
        }    
        
        return properties.getProperty(MAVEN_KEY);
    }    
}
