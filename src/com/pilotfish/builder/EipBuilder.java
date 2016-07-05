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

import com.pilotfish.builder.listener.ViewEvent;
import com.pilotfish.builder.listener.ViewListener;
import com.pilotfish.builder.listener.ViewValueChangeEvent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by craigmiller on 7/5/16.
 */
public class EipBuilder implements ViewListener, BuilderModule{

    //TODO have required items

    public static final String APP_TITLE = "eipBuilder";
    public static final String RUN_CONFIG_TYPE_PROP = "RunConfigType";

    private BuilderModel builderModel;
    private BuilderUI builderUI;

    public static void main(String[] args){
        new EipBuilder();
    }

    public EipBuilder(){
        initUI();
    }

    private void init(){
        builderModel = new BuilderModel();
    }

    private void initUI(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                builderUI = new BuilderUI();
                builderUI.addViewListener(EipBuilder.this);
            }
        });
    }

    @Override
    public Component getUI(){
        return builderUI;
    }

    @Override
    public void viewEvent(ViewEvent viewEvent) {
        if(viewEvent instanceof ViewValueChangeEvent){
            ViewValueChangeEvent vvce = (ViewValueChangeEvent) viewEvent;
            String key = vvce.getKey();
            switch (key){
                case RUN_CONFIG_TYPE_PROP:
                    builderModel.setRunConfigType((RunConfigType) vvce.getValue());
                    break;
            }
        }
    }
}
