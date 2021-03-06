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

import javax.annotation.PostConstruct;
import org.lorislab.jel.base.criteria.AbstractSearchCriteria;
import org.lorislab.jel.jsf.search.controller.AbstractSearchViewController;

/**
 * The abstract search view controller.
 *
 * @param <T> the entity type.
 * @param <S> the search criteria type.
 *
 * @author Andrej Petras
 */
public abstract class AbstractDefaultSearchViewController<T, S extends AbstractSearchCriteria> extends AbstractSearchViewController<T, S> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 6562624144708822688L;

    /**
     * The default constructor.
     */
    public AbstractDefaultSearchViewController() {
        this(null);
    }

    /**
     * The default constructor.
     *
     * @param context the context.
     */
    public AbstractDefaultSearchViewController(Enum context) {
        super(context);
    }
    
    /**
     * The initialise method.
     */
    @PostConstruct
    protected void init () {
        getSearchAction().execute();
    }
    
}
