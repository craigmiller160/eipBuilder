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

import com.pilotfish.builder.modules.custom.CustomModule;
import com.pilotfish.builder.modules.full.FullBuildModule;
import com.pilotfish.builder.modules.main.MainModule;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by craigmiller on 7/5/16.
 */
public class EipBuilder{

    //TODO have required items
    //TODO add logging

    public static final String APP_TITLE = "eipBuilder";
    private static final String NIMBUS_LF = "Nimbus";

    private static EipBuilder instance;
    private static final Object instanceLock = new Object();

    private final Map<BuildConfigType,Module> modules = new HashMap<>();

    public static void main(String[] args){
        EipBuilder builder = EipBuilder.getInstance();
        builder.start();
    }

    public static EipBuilder getInstance(){
        if(instance == null){
            synchronized (instanceLock){
                if(instance == null){
                    instance = new EipBuilder();
                }
            }
        }

        return instance;
    }

    private EipBuilder(){

    }

    public void start(){
        initLookAndFeel();
        initModules();
        showFrame();
    }

    private void initLookAndFeel(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if (NIMBUS_LF.equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                }
                catch(Exception ex){
                    //TODO improve this
                    ex.printStackTrace();
                }
            }
        });
    }

    private void initModules(){
        modules.put(BuildConfigType.FULL_APP, new FullBuildModule());
        modules.put(BuildConfigType.CUSTOM_MODULE, new CustomModule());
        //This one always needs to be last
        modules.put(BuildConfigType.NONE, new MainModule());
    }

    public Module getModule(BuildConfigType type){
        return modules.get(type);
    }

    private void showFrame(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                modules.get(BuildConfigType.NONE).getUI().getComponent();
            }
        });
    }
}
