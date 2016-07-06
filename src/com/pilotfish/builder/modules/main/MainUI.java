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

package com.pilotfish.builder.modules.main;

import com.pilotfish.builder.BuildConfigType;
import com.pilotfish.builder.EipBuilder;
import com.pilotfish.builder.ModuleUI;
import com.pilotfish.builder.menu.BuilderMenuBar;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;

/**
 * Created by craigmiller on 7/5/16.
 */
public class MainUI extends ModuleUI<JFrame> implements ItemListener, DocumentListener{

    private static final String BUILD_CONFIG_TOOLTIP = "Select the type of build configuration to run";
    private static final String DEV_DIR_TOOLTIP = "Open File Chooser to find dev directory";
    private static final String EXECUTE_TOOLTIP = "Execute the build";

    private JFrame frame;

    private JLabel buildTypeLabel;
    private JLabel devDirLabel;
    private JComboBox<BuildConfigType> buildTypeComboBox;
    private JTextField devDirField;
    private JButton devDirFileChooserBtn;
    private JButton executeButton;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel blankPanel;

    public MainUI(){
        initComponents();
        initUI();
        buildWindow();
    }

    @Override
    protected void handlePropertyChange(PropertyChangeEvent event) {

    }

    @Override
    public JFrame getComponent() {
        if(!frame.isVisible()){
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

        return frame;
    }

    private void initUI(){
        frame = new JFrame();
        frame.setTitle(EipBuilder.APP_TITLE);
        frame.setContentPane(createContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents(){
        buildTypeLabel = new JLabel("Build Config: ");
        buildTypeLabel.setToolTipText(BUILD_CONFIG_TOOLTIP);

        buildTypeComboBox = new JComboBox<>(BuildConfigType.values());
        buildTypeComboBox.addItemListener(this);
        buildTypeComboBox.setToolTipText(BUILD_CONFIG_TOOLTIP);

        devDirLabel = new JLabel("Dev Directory: ");
        devDirLabel.setToolTipText(DEV_DIR_TOOLTIP);

        devDirField = new JTextField();
        devDirField.getDocument().addDocumentListener(this);

        devDirFileChooserBtn = new JButton("...");
        devDirFileChooserBtn.setPreferredSize(new Dimension(25, 25));
        devDirFileChooserBtn.setToolTipText(DEV_DIR_TOOLTIP);

        executeButton = new JButton("Execute");
        executeButton.setEnabled(false);
        executeButton.setToolTipText(EXECUTE_TOOLTIP);

        blankPanel = new JPanel();
        blankPanel.setPreferredSize(new Dimension(500, 300));

        cardLayout = new CardLayout();
        cardPanel = createCardPanel();
    }

    private JPanel createCardPanel(){
        JPanel cardPanel = new JPanel(cardLayout);
        cardPanel.setBorder(BorderFactory.createTitledBorder("Configure Build"));

        EipBuilder builder = EipBuilder.getInstance();
        for(BuildConfigType bct : BuildConfigType.values()){
            if(bct.equals(BuildConfigType.NONE)){
                cardPanel.add(blankPanel, BuildConfigType.NONE.toString());
            }
            else{
                cardPanel.add(builder.getModule(bct).getUI().getComponent(), bct.toString());
            }
        }

        return cardPanel;
    }

    private void buildWindow(){
        frame.getContentPane().add(createBuildPanel(), "dock north");
        frame.getContentPane().add(cardPanel, "dock center");
        frame.getContentPane().add(executeButton, "dock south");

        BuilderMenuBar menuBar = new BuilderMenuBar(); //TODO add listeners
        frame.setJMenuBar(menuBar.getComponent());
    }

    private JPanel createContentPane(){
        JPanel contentPane = new JPanel(new MigLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        return contentPane;
    }

    private JPanel createBuildPanel(){
        JPanel buildPanel = new JPanel(new MigLayout("fillx"));
        buildPanel.setBorder(BorderFactory.createTitledBorder("Choose Build"));

        buildPanel.add(buildTypeLabel);
        buildPanel.add(buildTypeComboBox, "growx, wrap");
        buildPanel.add(devDirLabel);
        buildPanel.add(devDirField, "growx, split 2");
        buildPanel.add(devDirFileChooserBtn, "");

        return buildPanel;
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == buildTypeComboBox && e.getStateChange() == ItemEvent.SELECTED){
            BuildConfigType buildConfigType = (BuildConfigType) e.getItem();
            cardLayout.show(cardPanel, buildConfigType.toString());
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateTooltip();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateTooltip();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateTooltip();
    }

    private void updateTooltip(){
        devDirField.setToolTipText(devDirField.getText());
    }
}
