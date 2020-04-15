package tycoongame.controller;

import java.util.ArrayList;
import java.util.List;

import tycoongame.game.gui.event.screenchange.ScreenChangeEvent;
import tycoongame.game.gui.event.screenchange.ScreenChangeListener;
import tycoongame.gui.ScreenFramework;

public abstract class ScreenController {

    protected ScreenFramework currentScreen;
    protected List<ScreenChangeListener> observers;

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

    public void fireChangeEvent (String name , Object sorceEvent)
    {
        for (ScreenChangeListener screenChangeListener : observers) 
        {
            if (screenChangeListener != null)
                screenChangeListener.onScreenChange( new ScreenChangeEvent( sorceEvent , name ));    
        }
    }

    /**
     * @param currentScreen the currentScreen to set
     */
    public void setCurrentScreen(ScreenFramework currentScreen) {
        this.currentScreen = currentScreen;
    }

    /**
     * @return the currentScreen
     */
    public ScreenFramework getCurrentScreen() {
        return currentScreen;
    }
}