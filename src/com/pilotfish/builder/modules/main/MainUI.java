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

import com.pilotfish.builder.EipBuilder;
import com.pilotfish.builder.ModuleUI;
import com.pilotfish.builder.RunConfigType;
import com.pilotfish.builder.listener.ViewEventSupport;
import com.pilotfish.builder.listener.ViewListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;

/**
 * Created by craigmiller on 7/5/16.
 */
public class MainUI extends ModuleUI<JFrame> implements ItemListener{

    private JFrame frame;

    private JLabel buildTypeLabel;
    private JLabel devDirLabel;
    private JComboBox<RunConfigType> buildTypeComboBox;
    private JTextField devDirField;
    private JButton devDirFileChooserBtn;
    private JButton executeButton;
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
    protected JFrame getComponent() {
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
        buildTypeLabel = new JLabel("Build: ");
        buildTypeComboBox = new JComboBox<>(RunConfigType.values());
        buildTypeComboBox.addItemListener(this);
        devDirLabel = new JLabel("Dev Directory: ");
        devDirField = new JTextField();
        devDirFileChooserBtn = new JButton("...");
        devDirFileChooserBtn.setPreferredSize(new Dimension(25, 25));
        executeButton = new JButton("Execute");
        executeButton.setEnabled(false);

        cardPanel = new JPanel(new CardLayout());
        cardPanel.setBorder(BorderFactory.createTitledBorder("Configure Build"));

        blankPanel = new JPanel();
        blankPanel.setPreferredSize(new Dimension(500, 300));
    }

    private void buildWindow(){
        cardPanel.add(blankPanel, RunConfigType.NONE.toString());
        frame.getContentPane().add(createBuildPanel(), BorderLayout.NORTH);
        frame.getContentPane().add(cardPanel, BorderLayout.CENTER);
        frame.getContentPane().add(executeButton, BorderLayout.SOUTH);
    }

    private JPanel createContentPane(){
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        return contentPane;
    }

    private JPanel createBuildPanel(){
        JPanel buildPanel = new JPanel(new BorderLayout());
        buildPanel.setBorder(BorderFactory.createTitledBorder("Choose Build"));

        JPanel runPanel = new JPanel();
        runPanel.setLayout(new BoxLayout(runPanel, BoxLayout.LINE_AXIS));
        runPanel.add(buildTypeLabel);
        runPanel.add(buildTypeComboBox);

        JPanel devPanel = new JPanel();
        devPanel.setLayout(new BoxLayout(devPanel, BoxLayout.LINE_AXIS));
        devPanel.add(devDirLabel);
        devPanel.add(devDirField);
        devPanel.add(devDirFileChooserBtn);

        buildPanel.add(runPanel, BorderLayout.NORTH);
        buildPanel.add(devPanel, BorderLayout.SOUTH);

        return buildPanel;
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == buildTypeComboBox && e.getStateChange() == ItemEvent.SELECTED){

        }
    }
}
