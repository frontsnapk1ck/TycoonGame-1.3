package tycoongame.controller;

import java.util.ArrayList;
import java.util.List;

import tycoongame.controller.event.ScreenEvent;
import tycoongame.game.gui.event.ScreenChangeEvent;
import tycoongame.game.gui.event.ScreenChangeListener;
import tycoongame.gui.ScreenFramework;

public abstract class ScreenController {

    protected ScreenFramework currentScreen;
    protected List<ScreenChangeListener> observers;

    public abstract ScreenFramework getScreen( ScreenEvent e);

    public ScreenController ()
    {
        this.observers = new ArrayList<ScreenChangeListener>();
    }
    
    public void addObserver (ScreenChangeListener o)
    {
        this.observers.add(o);
    }

    public void removeObserver ( ScreenChangeListener o)
    {
        this.observers.remove(o);
    }

    public void fireChangeEvent (String name )
    {
        for (ScreenChangeListener screenChangeListener : observers) 
        {
            if (screenChangeListener != null)
                screenChangeListener.onScreenChange( new ScreenChangeEvent(this , name));    
        }
    }

}