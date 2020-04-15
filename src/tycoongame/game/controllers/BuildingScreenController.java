package tycoongame.game.controllers;

import java.util.List;

import tycoongame.buildings.Building;
import tycoongame.controller.ScreenController;
import tycoongame.controller.event.ScreenEvent;
import tycoongame.game.gui.event.building.BuildingListener;
import tycoongame.game.gui.event.input.InputEvent;
import tycoongame.game.gui.event.input.InputListener;
import tycoongame.game.gui.screeen.BuildingScreen;

public class BuildingScreenController extends ScreenController implements BuildingListener {

    private BuildingScreen buildingScreen;
    private Building building;
    private List<InputListener> listeners;

    /**
     * @param building the building to set
     */
    public void setBuilding(Building building) {
        this.building = building;
    }

    /**
     * @param buildingScreen the buildingScreen to set
     */
    public void setBuildingScreen(BuildingScreen screen) {
        if (this.buildingScreen != null)
            this.buildingScreen.removeListner(this);

        this.buildingScreen = screen;
        this.buildingScreen.setLisener(this);
        this.buildingScreen.loadBuilding(this.building);

        this.currentScreen = this.buildingScreen;
    }

    @Override
    public void onAcionSelect(InputEvent event) 
    {
        for (InputListener o : listeners)
            o.onInputEvent(event);
    }
    
    /**
     * @param listeners the listeners to set
     */
    public void addListener(InputListener listener) 
    {
        this.listeners.add(listener);
    }

    /**
     * @param listeners the listeners to remove
     */
    public void removeListener(InputListener listener) 
    {
        this.listeners.remove(listener);
    }

    public void addScreen(BuildingScreen screen ) 
    {
        if (this.buildingScreen != null )
            this.buildingScreen.removeListner( this );

        this.buildingScreen = screen;
        this.buildingScreen.setLisener( this );
        this.buildingScreen.loadBuilding( this.building );

        this.currentScreen = this.buildingScreen;
	}

    

}