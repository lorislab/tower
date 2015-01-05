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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.lorislab.jel.jsf.entity.controller.AbstractEntityViewController;
import org.lorislab.tower.store.criteria.ActivityCriteria;
import org.lorislab.tower.store.criteria.BuildCriteria;
import org.lorislab.tower.store.ejb.ActivityService;
import org.lorislab.tower.store.ejb.BuildService;
import org.lorislab.tower.store.model.Activity;
import org.lorislab.tower.store.model.ActivityChange;
import org.lorislab.tower.store.model.Build;
import org.lorislab.tower.store.model.BuildParameter;
import org.lorislab.tower.store.model.enums.BuildParameterType;
import org.lorislab.tower.web.common.action.Context;

/**
 * The builds view controller.
 *
 * @author Andrej_Petras
 */
@Named(value = "buildVC")
@SessionScoped
public class BuildViewController extends AbstractEntityViewController<Build> {

    /**
     * The UID for this class.
     */
    private static final long serialVersionUID = -4041231162850619024L;

    @EJB
    private BuildService service;

    @EJB
    private ActivityService activityService;

    private List<BuildParameter> manifest;

    private List<BuildParameter> custom;

    private final ButtonToolbarViewController typesVC;

    private final ButtonToolbarViewController statusesVC;

    private Activity activity;

    private List<ActivityChange> changes;

    private String tab;

    /**
     * The default constructor.
     */
    public BuildViewController() {
        super(Context.BUILD);
        typesVC = new ButtonToolbarViewController(this);
        statusesVC = new ButtonToolbarViewController(this);
    }

    @Override
    public Object close() throws Exception {
        setModel(null);
        return "toBuild";
    }

    @Override
    public Object view(String guid) throws Exception {
        activity = null;
        custom = null;
        manifest = null;
        typesVC.clear();
        statusesVC.clear();
        tab = "details";
        
        BuildCriteria criteria = new BuildCriteria();
        criteria.setGuid(guid);
        criteria.setFetchParameters(true);
        criteria.setFetchApplication(true);
        Build build = service.getBuild(criteria);
        setModel(build);
        if (build != null && build.getParameters() != null) {
            custom = new ArrayList<>();
            manifest = new ArrayList<>();
            for (BuildParameter parameter : build.getParameters()) {
                if (parameter.getType() == BuildParameterType.MANIFEST) {
                    manifest.add(parameter);
                } else {
                    custom.add(parameter);
                }
            }

            ActivityCriteria ac = new ActivityCriteria();
            ac.setBuild(guid);
            ac.setFetchChange(true);
            ac.setFetchChangeLog(true);
            ac.setFetchChangeLogBuild(true);
            activity = activityService.getActivity(ac);

            if (activity != null) {

                changes = new ArrayList<>(activity.getChanges());

                Set<String> tmpStatus = new HashSet<>();
                Set<String> tmpType = new HashSet<>();
                for (ActivityChange change : changes) {

                    String type = change.getType();
                    if (type != null && !type.isEmpty()) {
                        tmpType.add(change.getType());
                    }

                    String status = change.getStatus();
                    if (status != null && !status.isEmpty()) {
                        tmpStatus.add(status);
                    }
                }

                statusesVC.open(tmpStatus);
                typesVC.open(tmpType);
            }
        }
        return "toBuildView";
    }

    public List<ActivityChange> getChanges() {
        return changes;
    }

    public void filterChanges() {
        Set<String> s = statusesVC.getSelected();
        Set<String> t = typesVC.getSelected();
        changes = new ArrayList<>();
        if (!s.isEmpty() || !t.isEmpty()) {
            for (ActivityChange change : activity.getChanges()) {
                if (t.contains(change.getType()) && (change.getStatus() == null || s.contains(change.getStatus()))) {
                    changes.add(change);
                }
            }
        }
    }

    public ButtonToolbarViewController getStatusesVC() {
        return statusesVC;
    }

    public ButtonToolbarViewController getTypesVC() {
        return typesVC;
    }

    public Activity getActivity() {
        return activity;
    }

    public List<BuildParameter> getCustom() {
        return custom;
    }

    public List<BuildParameter> getManifest() {
        return manifest;
    }

    public String getTab() {
        return tab;
    }

    public void switchTab(String tab) {
        this.tab = tab;
    }

}
