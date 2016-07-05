package com.pilotfish.builder;

import com.pilotfish.builder.listener.ViewEventSupport;
import com.pilotfish.builder.listener.ViewListener;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by craig on 7/5/16.
 */
public abstract class ModuleUI<T extends Component> implements PropertyChangeListener {

    private final ViewEventSupport support = new ViewEventSupport();

    @Override
    public final void propertyChange(final PropertyChangeEvent evt) {
        if(SwingUtilities.isEventDispatchThread()){
            handlePropertyChange(evt);
        }
        else{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    handlePropertyChange(evt);
                }
            });
        }
    }

    protected abstract void handlePropertyChange(PropertyChangeEvent event);

    protected abstract T getComponent();

    protected void fireViewEvent(Object source){
        support.fireViewEvent(source);
    }

    protected void fireViewValueChangeEvent(Object source, String key, Object value){
        support.fireViewValueChangeEvent(source, key, value);
    }

    public void addViewListener(ViewListener listener){
        support.addListener(listener);
    }

    public void removeViewListener(ViewListener listener){
        support.removeListener(listener);
    }
}
