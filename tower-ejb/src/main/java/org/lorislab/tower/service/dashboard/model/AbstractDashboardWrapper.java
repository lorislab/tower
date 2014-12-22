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
package org.lorislab.tower.service.dashboard.model;

import java.util.List;
import org.lorislab.jel.jpa.model.Persistent;
import org.lorislab.jel.jpa.wrapper.AbstractPersistentWrapper;

/**
 *
 * @author Andrej_Petras
 * @param <T>
 * @param <E>
 */
public abstract class AbstractDashboardWrapper<T extends Persistent, E> extends AbstractPersistentWrapper<T>  {
    
    private static final long serialVersionUID = -850087441975510956L;
    
    private List<E> items;

    public List<E> getItems() {
        return items;
    }

    public void setItems(List<E> items) {
        this.items = items;
    }
    
}
