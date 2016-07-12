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

import com.pilotfish.builder.ModuleCompletionModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by craigmiller on 7/12/16.
 */
public class CompletionAnalyzer implements PropertyChangeListener{

    private static final Object instanceLock = new Object();
    private static CompletionAnalyzer instance;

    private final Map<String,ModuleCompletionModel> models = new HashMap<>();

    public static CompletionAnalyzer getInstance(){
        if(instance == null){
            synchronized (instanceLock){
                if(instance == null){
                    instance = new CompletionAnalyzer();
                }
            }
        }
        return instance;
    }

    public void addModel(ModuleCompletionModel model){
        models.put(model.getModelName(), model);
        model.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
