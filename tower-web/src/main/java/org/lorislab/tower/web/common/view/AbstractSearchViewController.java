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

import java.util.List;
import org.lorislab.guardian.web.view.AbstractContextSearchViewController;
import org.lorislab.guardian.web.view.actions.ContextSearchAction;
import org.lorislab.jel.base.criteria.AbstractSearchCriteria;
import org.lorislab.tower.web.common.action.Action;
import org.lorislab.tower.web.common.action.Context;

/**
 * The abstract search view controller.
 *
 * @param <T> the entity type.
 * @param <S> the search criteria type.
 *
 * @author Andrej Petras
 */
public abstract class AbstractSearchViewController<T, S extends AbstractSearchCriteria> extends AbstractContextSearchViewController<T, S> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = 6562624144708822688L;

    /**
     * The search action.
     */
    private ContextSearchAction searchAction;

    /**
     * The default constructor.
     */
    public AbstractSearchViewController() {
        // empty constructor
    }

    /**
     * The default constructor.
     *
     * @param context the context.
     */
    public AbstractSearchViewController(Context context) {
        searchAction = new ContextSearchAction(this, context, Action.SEARCH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getResult() {
        List<T> tmp = super.getResult();
        if (tmp == null) {
            searchAction.execute();
        }
        return super.getResult();
    }

    /**
     * Gets the search action.
     *
     * @return the search action.
     */
    public ContextSearchAction getSearchAction() {
        return searchAction;
    }

}
