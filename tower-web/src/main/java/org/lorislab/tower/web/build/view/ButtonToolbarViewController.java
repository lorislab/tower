/*
 * Copyright 2015 lorislab.org.
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.lorislab.tower.web.build.model.ToggleButton;

/**
 *
 * @author Andrej_Petras
 */
public class ButtonToolbarViewController implements Serializable {

    private static final long serialVersionUID = 4357422787432115259L;

    private Map<String, ToggleButton> tmp;

    private List<ToggleButton> buttons;

    private final BuildViewController parent;
    
    public ButtonToolbarViewController(BuildViewController parent) {
        this.parent = parent;
    }

    
    public void clear() {
        tmp = null;
        buttons = null;
    }
    
    public void open(Set<String> data) {
        if (data != null) {
            tmp = new HashMap<>(data.size());
            buttons = new ArrayList<>(data.size());
            for (String id : data) {
                ToggleButton button = new ToggleButton(id);
                tmp.put(id, button);
                buttons.add(button);
                data.add(id);
            }
        }
    }

    public List<ToggleButton> getButtons() {
        return buttons;
    }

    public void clickButton(String id) {
        if (tmp != null) {
            ToggleButton button = tmp.get(id);
            if (button != null) {
                button.setActive(!button.isActive());
                parent.filterChanges();
            }
        }
    }
    
    public Set<String> getSelected() {
        Set<String> result = new HashSet<>();
        for (ToggleButton button: buttons) {
            if (button.isActive()) {
                result.add(button.getId());
            }
        }
        return result;
    }
}
