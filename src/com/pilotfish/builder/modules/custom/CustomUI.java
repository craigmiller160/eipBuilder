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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.util.List;

import static com.pilotfish.builder.modules.custom.CustomModel.*;

/**
 * Created by craigmiller on 7/5/16.
 */
public class CustomUI extends ModuleUI<JPanel> implements ActionListener, TableModelListener, DocumentListener{

    private static final String TITLE_TOOLTIP = "The title of the module";
    private static final String VERSION_TOOLTIP = "The version number of the module";
    private static final String ADD_TOOLTIP = "Add a new file";
    private static final String REMOVE_TOOLTIP = "Remove the selected file";
    private static final String DOCUMENT_PROP_NAME = "DocumentPropName";

    private JPanel panel;

    private JLabel titleLabel;
    private JLabel versionLabel;
    private JTextField titleField;
    private JTextField versionField;
    private FileTableModel srcFileTableModel;
    private JTable srcFileTable;
    private JScrollPane tableScrollPane;

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
        titleField.getDocument().putProperty(DOCUMENT_PROP_NAME, JAR_TITLE_PROP);
        titleField.getDocument().addDocumentListener(this);

        versionLabel = new JLabel("Version: ");
        versionLabel.setToolTipText(VERSION_TOOLTIP);

        versionField = new JTextField();
        versionField.setToolTipText(VERSION_TOOLTIP);
        versionField.getDocument().putProperty(DOCUMENT_PROP_NAME, JAR_VERSION_PROP);
        versionField.getDocument().addDocumentListener(this);

        srcFileTableModel = new FileTableModel();
        srcFileTableModel.addTableModelListener(this);
        srcFileTable = new JTable(srcFileTableModel);
        srcFileTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        srcFileTable.getColumnModel().getColumn(1).setMaxWidth(100);

        tableScrollPane = new JScrollPane(srcFileTable);

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
        panel.add(removeButton, "wrap");
    }


    @Override
    protected void handlePropertyChange(PropertyChangeEvent event) {
        if(event.getPropertyName().equals(SRC_FILES_PROP)){
            srcFileTableModel.setSrcFiles((List<SrcFile>) event.getNewValue());
        }
        else if(event.getPropertyName().equals(JAR_TITLE_PROP)){
            titleField.setText((String) event.getNewValue());
        }
        else if(event.getPropertyName().equals(JAR_VERSION_PROP)){
            versionField.setText((String) event.getNewValue());
        }
    }

    @Override
    public JPanel getComponent() {
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == removeButton){
            int row = srcFileTable.getSelectedRow();
            if(row >= 0){
                srcFileTableModel.removeRow(row);
            }
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        List<SrcFile> srcFiles = srcFileTableModel.getSrcFiles();
        fireViewValueChangeEvent(this, SRC_FILES_PROP, srcFiles);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateValue(e);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateValue(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateValue(e);
    }

    private void updateValue(DocumentEvent e){
        Document doc = e.getDocument();
        String propName = (String) doc.getProperty(DOCUMENT_PROP_NAME);
        if(JAR_TITLE_PROP.equals(propName)){
            fireViewValueChangeEvent(this, JAR_TITLE_PROP, titleField.getText());
        }
        else if(JAR_VERSION_PROP.equals(propName)){
            fireViewValueChangeEvent(this, JAR_VERSION_PROP, versionField.getText());
        }
    }


}
