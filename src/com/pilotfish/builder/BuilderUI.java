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

import com.pilotfish.builder.listener.ViewEventSupport;
import com.pilotfish.builder.listener.ViewListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by craigmiller on 7/5/16.
 */
public class BuilderUI extends JFrame implements ItemListener{

    private final ViewEventSupport support = new ViewEventSupport();

    private JLabel buildTypeLabel;
    private JLabel devDirLabel;
    private JComboBox<RunConfigType> buildTypeComboBox;
    private JTextField devDirField;
    private JButton devDirFileChooserBtn;
    private JButton executeButton;
    private JPanel cardPanel;

    public BuilderUI(){
        initComponents();
        buildWindow();
    }

    public void addViewListener(ViewListener listener){
        support.addListener(listener);
    }

    public void removeViewListener(ViewListener listener){
        support.removeListener(listener);
    }

    private void initComponents(){
        buildTypeLabel = new JLabel("Run Build: ");
        buildTypeComboBox = new JComboBox<>(RunConfigType.values());
        buildTypeComboBox.addItemListener(this);
        devDirLabel = new JLabel("Dev Directory: ");
        devDirField = new JTextField();
        devDirFileChooserBtn = new JButton("...");
        devDirFileChooserBtn.setPreferredSize(new Dimension(25, 25));
        executeButton = new JButton("Execute");
        executeButton.setEnabled(false);

        cardPanel = new JPanel(new CardLayout()); //TODO enlarge
    }

    private void buildWindow(){
        setTitle(EipBuilder.APP_TITLE);
        setContentPane(createContentPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().add(createBuildPanel(), BorderLayout.NORTH);
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        getContentPane().add(executeButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createContentPane(){
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        return contentPane;
    }

    private JPanel createBuildPanel(){
        JPanel buildPanel = new JPanel(new BorderLayout());
        buildPanel.setBorder(BorderFactory.createTitledBorder("Build Settings"));

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
