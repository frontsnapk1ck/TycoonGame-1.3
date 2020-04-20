package tycoongame.controller;

import java.util.ArrayList;
import java.util.List;

import tycoongame.controller.event.InputListener;
import tycoongame.gui.ScreenFramework;

public abstract class ScreenController {

    protected ScreenFramework currentScreen;
    private List<InputListener> observers;

    public ScreenController ()
    {
        this.observers = new ArrayList<InputListener>();
    }

    public abstract void fireChangeEvent ( );

    public void addObserver (InputListener o)
    {
        if (this.observers == null)
            this.observers = new ArrayList<InputListener>();
        this.observers.add(o);
    }

    public void removeObserver ( InputListener o)
    {
        this.observers.remove(o);
    }

    /**
     * @param currentScreen the currentScreen to set
     */
    public void setCurrentScreen(ScreenFramework currentScreen) 
    {
        this.currentScreen = currentScreen;
    }

    /**
     * @return the currentScreen
     */
    public ScreenFramework getCurrentScreen() {
        return currentScreen;
    }
}