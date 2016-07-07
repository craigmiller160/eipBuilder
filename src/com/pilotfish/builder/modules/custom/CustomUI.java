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
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

/**
 * Created by craigmiller on 7/5/16.
 */
public class CustomUI extends ModuleUI<JPanel> implements ActionListener{

    private static final String TITLE_TOOLTIP = "The title of the module";
    private static final String VERSION_TOOLTIP = "The version number of the module";
    private static final String ADD_TOOLTIP = "Add a new file";
    private static final String REMOVE_TOOLTIP = "Remove the selected file";

    private JPanel panel;

    private JLabel titleLabel;
    private JLabel versionLabel;
    private JTextField titleField;
    private JTextField versionField;
    private FileTableModel srcFileTableModel;
    private JTable srcFileTable;
    private JScrollPane tableScrollPane;

    private JButton addButton;
    private JButton removeButton;

    public CustomUI(){
        createPanel();
        initComponents();
        buildPanel();
    }

    private void createPanel(){
        panel = new JPanel(new MigLayout("fillx"));
    }

    private void initComponents(){
        titleLabel = new JLabel("Title: ");
        titleLabel.setToolTipText(TITLE_TOOLTIP);

        titleField = new JTextField();
        titleField.setToolTipText(TITLE_TOOLTIP);

        versionLabel = new JLabel("Version: ");
        versionLabel.setToolTipText(VERSION_TOOLTIP);

        versionField = new JTextField();
        versionField.setToolTipText(VERSION_TOOLTIP);

        srcFileTableModel = new FileTableModel(); //TODO consider using listeners to update the model
        srcFileTable = new JTable(srcFileTableModel);
        srcFileTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        srcFileTable.getColumnModel().getColumn(1).setMaxWidth(100);

        tableScrollPane = new JScrollPane(srcFileTable);

        addButton = new JButton("Add");
        addButton.setToolTipText(ADD_TOOLTIP);
        addButton.addActionListener(this);

        removeButton = new JButton("Remove");
        removeButton.setToolTipText(REMOVE_TOOLTIP);
        removeButton.addActionListener(this);
    }

    private void buildPanel(){
        panel.add(titleLabel);
        panel.add(titleField, "growx, wrap");
        panel.add(versionLabel);
        panel.add(versionField, "growx, wrap");
        panel.add(tableScrollPane, "growx, span 2, wrap");
        panel.add(addButton, "");
        panel.add(removeButton, "wrap");
    }


    @Override
    protected void handlePropertyChange(PropertyChangeEvent event) {

    }

    @Override
    public JPanel getComponent() {
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addButton){
            srcFileTableModel.addRow();
        }
        else if(e.getSource() == removeButton){
            int row = srcFileTable.getSelectedRow();
            if(row >= 0){
                srcFileTableModel.removeRow(row);
            }
        }
    }
}
