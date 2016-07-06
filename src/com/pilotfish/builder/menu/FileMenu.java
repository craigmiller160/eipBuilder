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
import java.awt.*;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;

/**
 * Created by craigmiller on 7/6/16.
 */
public class FileMenu extends ModuleUI<JMenu> {

    private JMenu menu;
    private JMenuItem newItem;
    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JMenuItem saveAsItem;
    private JMenuItem exitItem;

    public FileMenu(){
        initMenu();
        initMenuItems();
        buildMenu();
    }

    private void initMenu(){
        menu = new JMenu("File");
    }

    private void initMenuItems(){
        newItem = createMenuItem("New Build", "Start a new build configuration", KeyEvent.VK_N, KeyEvent.VK_N, Event.CTRL_MASK);
        openItem = createMenuItem("Open Build", "Open an existing build configuration", KeyEvent.VK_O, KeyEvent.VK_O, Event.CTRL_MASK);
        saveItem = createMenuItem("Save Build", "Save an existing build configuration", KeyEvent.VK_S, KeyEvent.VK_S, Event.CTRL_MASK);
        saveAsItem = createMenuItem("Save Build As", "Save an existing build configuration as a new file", KeyEvent.VK_A);
        exitItem = createMenuItem("Exit Builder", "Exit the application", KeyEvent.VK_X, KeyEvent.VK_X, Event.CTRL_MASK);
    }

    private void buildMenu(){
        menu.add(newItem);
        menu.addSeparator();
        menu.add(openItem);
        menu.add(saveItem);
        menu.add(saveAsItem);
        menu.addSeparator();
        menu.add(exitItem);
    }

    @Override
    public JMenu getComponent() {
        return menu;
    }

    @Override
    protected void handlePropertyChange(PropertyChangeEvent event) {

    }

    private JMenuItem createMenuItem(String title, String toolTip){
        JMenuItem item = new JMenuItem(title);
        item.setToolTipText(toolTip);
        return item;
    }

    private JMenuItem createMenuItem(String title, String toolTip, int mnemoicKeyCode){
        JMenuItem item = createMenuItem(title, toolTip);
        item.setMnemonic(mnemoicKeyCode);
        return item;
    }

    private JMenuItem createMenuItem(String title, String toolTip, int mnemonicKeyCode, int acceleratorKeycode, int acceleratorModifiers){
        JMenuItem item = createMenuItem(title, toolTip, mnemonicKeyCode);
        item.setAccelerator(KeyStroke.getKeyStroke(acceleratorKeycode, acceleratorModifiers));

        return item;
    }
}
