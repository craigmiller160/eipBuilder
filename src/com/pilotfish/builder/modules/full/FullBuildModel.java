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

package com.pilotfish.builder.modules.full;

import com.pilotfish.builder.ModuleCompletionModel;
import com.pilotfish.builder.ModuleModel;

/**
 * Created by craigmiller on 7/5/16.
 */
public class FullBuildModel extends ModuleCompletionModel {

    public static final String FULL_BUILD_MODEL_NAME = "FullBuildModel";
    public static final String FULL_BUILD_TYPE_PROP = "FullBuildType";
    public static final String BUILD_NAME_PROP = "BuildName";

    private FullBuildType fullBuildType;
    private String buildName;

    public FullBuildModel(){

    }

    public FullBuildType getFullBuildType() {
        return fullBuildType;
    }

    public void setFullBuildType(FullBuildType fullBuildType) {
        FullBuildType oldValue = this.fullBuildType;
        this.fullBuildType = fullBuildType;
        firePropertyChangeEvent(FULL_BUILD_TYPE_PROP, oldValue, fullBuildType);
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        String oldValue = this.buildName;
        this.buildName = buildName;
        firePropertyChangeEvent(BUILD_NAME_PROP, oldValue, buildName);
    }

    @Override
    public String getModelName() {
        return FULL_BUILD_MODEL_NAME;
    }

    @Override
    public boolean isConfigComplete() {
        return fullBuildType != null && fullBuildType != FullBuildType.NONE;
    }
}
