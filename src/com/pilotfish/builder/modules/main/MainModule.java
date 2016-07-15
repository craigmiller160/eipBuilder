package com.pilotfish.builder.modules.main;

import com.pilotfish.builder.*;
import com.pilotfish.builder.listener.ViewEvent;
import com.pilotfish.builder.listener.ViewValueChangeEvent;
import com.pilotfish.builder.util.CompletionAnalyzerFactory;
import com.pilotfish.builder.util.CompletionCallback;

import javax.swing.*;

import static com.pilotfish.builder.modules.main.MainModel.*;

/**
 * Created by craig on 7/5/16.
 */
public class MainModule implements Module<JFrame>, CompletionCallback{

    private MainModel mainModel;
    private MainUI mainUI;

    public MainModule(){
        init();
        initUI();
        initAnalyzer();
    }

    private void init(){
        mainModel = new MainModel();
    }

    private void initUI(){
        if(SwingUtilities.isEventDispatchThread()){
            mainUI = new MainUI();
            mainUI.addViewListener(MainModule.this);
            mainModel.addPropertyChangeListener(mainUI);
        }
        else{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    mainUI = new MainUI();
                    mainUI.addViewListener(MainModule.this);
                    mainModel.addPropertyChangeListener(mainUI);
                }
            });
        }
    }

    private void initAnalyzer(){
        CompletionAnalyzerFactory.getInstance().getAnalyzer().addCallback(this);
//        CompletionAnalyzerFactory.getInstance().getAnalyzer().checkCompletionStatus();
    }

    @Override
    public ModuleModel getModel() {
        return mainModel;
    }

    @Override
    public ModuleUI<JFrame> getUI() {
        if(!SwingUtilities.isEventDispatchThread()){
            throw new IllegalStateException("getUI() can only be called on the EDT");
        }

        return mainUI;
    }

    @Override
    public void viewEvent(ViewEvent viewEvent) {
        if(viewEvent instanceof ViewValueChangeEvent){
            ViewValueChangeEvent vvce = (ViewValueChangeEvent) viewEvent;
            String key = vvce.getKey();
            switch (key){
                case RUN_CONFIG_TYPE_PROP:
                    mainModel.setBuildConfigType((BuildConfigType) vvce.getValue());
                    break;
                case DEV_DIRECTORY_PROP:
                    mainModel.setDevDirectory((String) vvce.getValue());
                    break;
            }
        }
    }

    @Override
    public void completionStatus(boolean complete) {
        mainUI.setExecuteEnabled(complete);
    }
}
