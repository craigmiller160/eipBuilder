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

package com.pilotfish.builder.modules.custom;

import com.pilotfish.builder.ModuleUI;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

/**
 * Created by craigmiller on 7/5/16.
 */
public class CustomBuildUI extends ModuleUI<JPanel> {

    private JPanel panel;

    private JLabel titleLabel;
    private JLabel versionLabel;
    private JTextField titleField;
    private JTextField versionField;


    public CustomBuildUI(){
        createPanel();
        initComponents();
        buildPanel();
    }

    private void createPanel(){
        panel = new JPanel(new MigLayout());
    }

    private void initComponents(){

    }

    private void buildPanel(){

    }


    @Override
    protected void handlePropertyChange(PropertyChangeEvent event) {

    }

    @Override
    public JPanel getComponent() {
        return panel;
    }
}
