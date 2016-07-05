package com.pilotfish.builder.modules.custom;

import com.pilotfish.builder.Module;
import com.pilotfish.builder.ModuleModel;
import com.pilotfish.builder.ModuleUI;
import com.pilotfish.builder.listener.ViewEvent;
import com.pilotfish.builder.modules.full.FullBuildUI;
import com.sun.org.apache.xpath.internal.operations.Mod;

import javax.swing.*;

/**
 * Created by craig on 7/5/16.
 */
public class CustomModule implements Module<JPanel> {

    private CustomBuildUI customBuildUI;

    public CustomModule(){
        init();
        initUI();
    }

    private void init(){

    }

    private void initUI(){
        if(SwingUtilities.isEventDispatchThread()){
            customBuildUI = new CustomBuildUI();
        }
        else{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    customBuildUI = new CustomBuildUI();
                }
            });
        }
    }

    @Override
    public ModuleModel getModel() {
        return null;
    }

    @Override
    public ModuleUI<JPanel> getUI() {
        return customBuildUI;
    }

    @Override
    public void viewEvent(ViewEvent viewEvent) {

    }
}
