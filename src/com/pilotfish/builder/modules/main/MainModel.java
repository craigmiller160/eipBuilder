/*
 * Copyright {yyyy} Craig Miller
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pilotfish.builder.modules.main;

import com.pilotfish.builder.BuildConfigType;
import com.pilotfish.builder.ModuleCompletionModel;
import com.pilotfish.builder.ModuleModel;
import org.apache.commons.lang.StringUtils;

/**
 * Created by craigmiller on 7/5/16.
 */
public class MainModel extends ModuleCompletionModel {

    public static final String MAIN_MODEL_NAME = "MainModel";
    public static final String RUN_CONFIG_TYPE_PROP = "BuildConfigType";
    public static final String DEV_DIRECTORY_PROP = "DevDirectory";

    private BuildConfigType buildConfigType;
    private String devDirectory;

    public void setBuildConfigType(BuildConfigType buildConfigType){
        BuildConfigType oldValue = this.buildConfigType;
        this.buildConfigType = buildConfigType;
        firePropertyChangeEvent(RUN_CONFIG_TYPE_PROP, oldValue, buildConfigType);
    }

    public BuildConfigType getBuildConfigType(){
        return buildConfigType;
    }

    public String getDevDirectory() {
        return devDirectory;
    }

    public void setDevDirectory(String devDirectory) {
        String oldValue = this.devDirectory;
        this.devDirectory = devDirectory;
        firePropertyChangeEvent(DEV_DIRECTORY_PROP, oldValue, devDirectory);
    }

    @Override
    public String getModelName() {
        return MAIN_MODEL_NAME;
    }

    @Override
    public boolean isConfigComplete() {
        return !StringUtils.isEmpty(devDirectory) &&
                (buildConfigType != null && buildConfigType != BuildConfigType.NONE);
    }
}
