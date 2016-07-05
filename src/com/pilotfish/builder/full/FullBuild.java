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

package com.pilotfish.builder.full;

import com.pilotfish.builder.BuilderModule;

import javax.swing.*;
import java.awt.*;

/**
 * Created by craigmiller on 7/5/16.
 */
public class FullBuild implements BuilderModule{

    private FullBuildModel fullBuildModel;
    private FullBuildUI fullBuildUI;

    public FullBuild(){
        init();
        initUI();
    }

    private void init(){
        fullBuildModel = new FullBuildModel();
    }

    private void initUI(){
        if(SwingUtilities.isEventDispatchThread()){
            fullBuildUI = new FullBuildUI();
        }
        else{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    fullBuildUI = new FullBuildUI();
                }
            });
        }
    }

    @Override
    public Component getUI() {
        return fullBuildUI;
    }
}
