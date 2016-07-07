package com.pilotfish.builder.modules.custom;

import com.pilotfish.builder.Module;
import com.pilotfish.builder.ModuleModel;
import com.pilotfish.builder.ModuleUI;
import com.pilotfish.builder.listener.ViewEvent;

import javax.swing.*;

/**
 * Created by craig on 7/5/16.
 */
public class CustomModule implements Module<JPanel> {

    private CustomUI customUI;

    public CustomModule(){
        init();
        initUI();
    }

    private void init(){

    }

    private void initUI(){
        if(SwingUtilities.isEventDispatchThread()){
            customUI = new CustomUI();
        }
        else{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    customUI = new CustomUI();
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
        return customUI;
    }

    @Override
    public void viewEvent(ViewEvent viewEvent) {

    }
}
