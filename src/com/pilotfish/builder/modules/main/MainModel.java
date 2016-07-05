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

import com.pilotfish.builder.ModuleModel;
import com.pilotfish.builder.RunConfigType;

/**
 * Created by craigmiller on 7/5/16.
 */
public class MainModel extends ModuleModel {

    public static final String RUN_CONFIG_TYPE_PROP = "RunConfigType";

    private RunConfigType runConfigType;

    public void setRunConfigType(RunConfigType runConfigType){
        RunConfigType oldValue = this.runConfigType;
        this.runConfigType = runConfigType;
        firePropertyChangeEvent(RUN_CONFIG_TYPE_PROP, oldValue, runConfigType);
    }

    public RunConfigType getRunConfigType(){
        return runConfigType;
    }

}
