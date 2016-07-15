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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;

import static com.pilotfish.builder.modules.full.FullBuildModel.*;

/**
 * Created by craigmiller on 7/5/16.
 */
public class FullBuildUI extends ModuleUI<JPanel> implements DocumentListener, ItemListener {

    private static final String TYPE_TOOLTIP = "Choose the application type to build";
    private static final String NAME_TOOLTIP = "Add an optional name to this build";

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
        typeLabel = new JLabel("Application Type: ");
        typeLabel.setToolTipText(TYPE_TOOLTIP);

        nameLabel = new JLabel("Build Name: ");
        nameLabel.setToolTipText(NAME_TOOLTIP);

        typeComboBox = new JComboBox<>(FullBuildType.values());
        typeComboBox.setToolTipText(TYPE_TOOLTIP);
        typeComboBox.addItemListener(this);

        nameField = new JTextField();
        nameField.setToolTipText(NAME_TOOLTIP);
        nameField.getDocument().addDocumentListener(this);
    }

    private void buildPanel(){
        panel.add(typeLabel, "");
        panel.add(typeComboBox, "wrap, growx");
        panel.add(nameLabel);
        panel.add(nameField, "growx");
    }

    @Override
    protected void handlePropertyChange(PropertyChangeEvent event) {
        if(event.getPropertyName().equals(BUILD_NAME_PROP)){
            nameField.setText((String) event.getNewValue());
        }
        else if(event.getPropertyName().equals(FULL_BUILD_TYPE_PROP)){
            typeComboBox.setSelectedItem((FullBuildType) event.getNewValue());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            fireViewValueChangeEvent(this, FULL_BUILD_TYPE_PROP, typeComboBox.getSelectedItem());
        }
    }

    @Override
    public JPanel getComponent() {
        return panel;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateName();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateName();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateName();
    }

    private void updateName(){
        fireViewValueChangeEvent(this, BUILD_NAME_PROP, nameField.getText());
    }
}
