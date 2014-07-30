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

package org.lorislab.guardian.web.view.actions;

import javax.faces.context.FacesContext;
import org.lorislab.guardian.web.view.ContextOpenViewController;

/**
 *
 * @author Andrej Petras
 */
public class ContextOpenAction<T extends ContextOpenViewController> extends AbstractContextControllerAction<T> {
    
    private static final long serialVersionUID = -401771961900364439L;

    private String guid;
        
    public ContextOpenAction(T parent, Enum context, Enum action) {
        super(parent, context, action);
    }    
    
    public void setGuid(String guid) {
        this.guid = guid;
    }    
    
    @Override
    protected Object doExecute() throws Exception {
        if (guid == null) {
            guid = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("guid");
        }
        return getParent().open(guid);
    }       
}
