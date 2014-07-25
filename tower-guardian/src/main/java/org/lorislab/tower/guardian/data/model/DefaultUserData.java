

package org.lorislab.tower.guardian.data.model;

import java.util.Set;
import org.lorislab.guardian.service.model.AbstractUserData;
import org.lorislab.tower.guardian.config.model.UserConfig;

/**
 *
 * @author Andrej Petras
 */
public class DefaultUserData extends AbstractUserData<UserConfig> {
    
    private static final long serialVersionUID = 6567170752876311544L;

    public DefaultUserData(String principal, Set<String> roles, Set<String> actions) {
        super(principal, roles, actions);
    }
    
}
