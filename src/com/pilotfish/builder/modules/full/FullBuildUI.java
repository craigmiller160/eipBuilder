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

package com.pilotfish.builder.modules.full;

import com.pilotfish.builder.ModuleUI;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

/**
 * Created by craigmiller on 7/5/16.
 */
public class FullBuildUI extends ModuleUI<JPanel> {

    private JPanel panel;

    private JLabel typeLabel;
    private JLabel nameLabel;
    private JComboBox<FullBuildType> typeComboBox;
    private JTextField nameField;

    public FullBuildUI(){
        initComponents();
        initUI();
        buildPanel();
    }

    private void initUI(){
        panel = new JPanel();
        panel.setLayout(new MigLayout("fillx"));
    }

    private void initComponents(){
        typeLabel = new JLabel("Build Type: ");
        nameLabel = new JLabel("Build Name: ");
        typeComboBox = new JComboBox<>(FullBuildType.values());
        nameField = new JTextField();
    }

    private void buildPanel(){
        panel.add(typeLabel, "");
        panel.add(typeComboBox, "wrap, growx");
        panel.add(nameLabel);
        panel.add(nameField, "growx");
    }

    @Override
    protected void handlePropertyChange(PropertyChangeEvent event) {

    }

    @Override
    public JPanel getComponent() {
        return panel;
    }
}
