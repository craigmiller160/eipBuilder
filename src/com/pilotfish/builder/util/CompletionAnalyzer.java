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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by craigmiller on 7/12/16.
 */
public abstract class CompletionAnalyzer implements PropertyChangeListener{

    private final Map<String,ModuleCompletionModel> models = new HashMap<>();
    private final List<CompletionCallback> callbacks = new ArrayList<>();

    public synchronized void addModel(ModuleCompletionModel model){
        models.put(model.getModelName(), model);
        model.addPropertyChangeListener(this);
    }

    public synchronized void addCallback(CompletionCallback callback){
        this.callbacks.add(callback);
    }

    public synchronized void removeCallback(CompletionCallback callback){
        this.callbacks.remove(callback);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        checkCompletionStatus();
    }

    protected synchronized Map<String,ModuleCompletionModel> getModels(){
        return models;
    }

    protected abstract void checkCompletionStatus();

    protected synchronized void setCompletionStatus(boolean complete){
        for(CompletionCallback callback : callbacks){
            callback.completionStatus(complete);
        }
    }
}
