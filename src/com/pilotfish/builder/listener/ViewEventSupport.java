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

package com.pilotfish.builder.listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by craigmiller on 7/5/16.
 */
public class ViewEventSupport {

    private final List<ViewListener> listeners = new ArrayList<>();

    public ViewEventSupport(){

    }

    public void addListener(ViewListener listener){
        listeners.add(listener);
    }

    public void removeListener(ViewListener listener){
        listeners.remove(listener);
    }

    public void fireViewEvent(Object source){
        ViewEvent event = new ViewEvent(source);
        callListeners(event);
    }

    public void fireViewValueChangeEvent(Object source, String key, Object value){
        ViewEvent event = new ViewValueChangeEvent(source, key, value);
        callListeners(event);
    }

    private void callListeners(ViewEvent event){
        for(ViewListener listener : listeners){
            listener.viewEvent(event);
        }
    }

}
