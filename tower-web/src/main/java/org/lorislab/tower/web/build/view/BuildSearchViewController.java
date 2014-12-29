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
package org.lorislab.tower.web.build.view;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.tower.store.criteria.BuildCriteria;
import org.lorislab.tower.store.ejb.BuildService;
import org.lorislab.tower.store.model.Build;
import org.lorislab.tower.web.common.action.Context;
import org.lorislab.tower.web.common.view.AbstractDefaultSearchViewController;

/**
 * The build search view controller.
 * 
 * @author Andrej_Petras
 */
@Named(value = "buildSVC")
@SessionScoped
public class BuildSearchViewController extends AbstractDefaultSearchViewController<Build, BuildCriteria> {
    
    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 6816494623072606288L;

    /**
     * The build service.
     */
    @EJB
    private BuildService service;

    /**
     * The default constructor.
     */
    public BuildSearchViewController() {
        super(Context.BUILD);       
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    protected List<Build> doSearch() throws Exception {
        return service.getBuilds();
    }
    
}
