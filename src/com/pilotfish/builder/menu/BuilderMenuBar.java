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

package com.pilotfish.builder.menu;

import com.pilotfish.builder.ModuleUI;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

/**
 * Created by craigmiller on 7/6/16.
 */
public class BuilderMenuBar extends ModuleUI<JMenuBar> {

    private JMenuBar menuBar;

    public BuilderMenuBar(){
        initMenuBar();
        buildMenuBar();
    }

    private void initMenuBar(){
        menuBar = new JMenuBar();
    }

    private void buildMenuBar(){
        FileMenu menu = new FileMenu(); //TODO add listeners
        menuBar.add(menu.getComponent());
    }

    @Override
    public JMenuBar getComponent() {
        return menuBar;
    }

    @Override
    protected void handlePropertyChange(PropertyChangeEvent event) {

    }
}
