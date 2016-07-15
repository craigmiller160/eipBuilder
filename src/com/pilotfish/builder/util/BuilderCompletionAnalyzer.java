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

package com.pilotfish.builder.util;

import com.pilotfish.builder.BuildConfigType;
import com.pilotfish.builder.ModuleCompletionModel;
import com.pilotfish.builder.modules.main.MainModel;

import static com.pilotfish.builder.modules.main.MainModel.*;
import static com.pilotfish.builder.modules.custom.CustomModel.*;
import static com.pilotfish.builder.modules.full.FullBuildModel.*;

/**
 * Created by craigmiller on 7/15/16.
 */
public class BuilderCompletionAnalyzer extends CompletionAnalyzer{

    protected BuilderCompletionAnalyzer(){}

    @Override
    public synchronized void checkCompletionStatus(){
        MainModel mainModel = (MainModel) getModels().get(MAIN_MODEL_NAME);
        if(mainModel.isConfigComplete()){
            BuildConfigType buildConfigType = mainModel.getBuildConfigType();
            if(BuildConfigType.FULL_APP.equals(buildConfigType)){
                ModuleCompletionModel fullModel = getModels().get(FULL_BUILD_MODEL_NAME);
                setCompletionStatus(fullModel.isConfigComplete());
                return;
            }
            else if(BuildConfigType.CUSTOM_MODULE.equals(buildConfigType)){
                ModuleCompletionModel customModel = getModels().get(CUSTOM_MODEL_NAME);
                setCompletionStatus(customModel.isConfigComplete());
                return;
            }
        }
        setCompletionStatus(false);
    }

}
