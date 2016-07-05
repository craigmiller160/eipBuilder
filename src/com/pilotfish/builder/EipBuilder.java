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

package com.pilotfish.builder;

import com.pilotfish.builder.modules.main.MainModule;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by craigmiller on 7/5/16.
 */
public class EipBuilder{

    //TODO have required items

    public static final String APP_TITLE = "eipBuilder";

    private static EipBuilder instance;

    private final Map<RunConfigType,Module> modules = new HashMap<>();

    public static void main(String[] args){
        instance = new EipBuilder();
    }

    public static EipBuilder getInstance(){
        return instance;
    }

    public EipBuilder(){
        initModules();
        showFrame();
    }

    private void initModules(){
        modules.put(RunConfigType.NONE, new MainModule());
        modules.put(RunConfigType.FULL_APP, null); //TODO fix this
        modules.put(RunConfigType.CUSTOM_MODULE, null); //TODO fix this
    }

    public Module getModule(RunConfigType type){
        return modules.get(type);
    }

    private void showFrame(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                modules.get(RunConfigType.NONE).getUI().getComponent();
            }
        });
    }
}
