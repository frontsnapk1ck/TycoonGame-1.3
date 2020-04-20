package tycoongame.game.controllers;

import java.util.List;

import tycoongame.buildings.Building;
import tycoongame.buildings.StoreManager;
import tycoongame.controller.ScreenController;
import tycoongame.game.gui.screeen.BuildingScreen;
import tycoongame.game.gui.screeen.ManagerScreen;
import tycoongame.gui.ScreenFramework;

public class ManAndBuildingController extends ScreenController {

    private ManagerScreen managerScreen;
    private BuildingScreen buildingScreen;
    private List<InputListener> listeners;

    private Building building;
    private StoreManager sMan;

    // =========================================
    // add screens
    // =========================================

    /**
     * @param managerScreen the managerScreen to set
     */
    public void addManagerScreen(ManagerScreen screen) {
        if (this.managerScreen != null)
            this.managerScreen.removeListener(this);

        this.managerScreen = screen;
        this.managerScreen.setListener(this);

        this.currentScreen = this.managerScreen;
    }

    public void addBuildingScreen(BuildingScreen screen) {
        if (this.buildingScreen != null)
            this.buildingScreen.removeListner(this);

        this.buildingScreen = screen;
        this.buildingScreen.setLisener(this);
        this.buildingScreen.loadBuilding(this.building);

        this.currentScreen = this.buildingScreen;
    }

    // =========================================
    // Actions
    // =========================================

    @Override
    public void onAcionSelect(InputEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBuildingSelect(ManagerEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBackButtonSelect(ScreenChangeEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBuildingSell(SellEvent e) 
    {
        Building b = e.getB();
        this.sMan.sell( b );
    }

    // =========================================
    // Listners
    // =========================================

    /**
     * @param listeners the listeners to set
     */
    public void addListener(InputListener listener) {
        this.listeners.add(listener);
    }

    /**
     * @param listeners the listeners to remove
     */
    public void removeListener(InputListener listener) {
        this.listeners.remove(listener);
    }

    // =========================================
    // getters
    // =========================================

    public ScreenFramework getMScreen() {
        return this.managerScreen;
    }

    // =========================================
    // setters
    // =========================================

    private void setManagerScreen(StoreManager sMan) {
        managerScreen.setBuildings(sMan);
        this.currentScreen = managerScreen;
    }

    /**
     * @param building the building to set
     */
    public void setBuilding(Building building) {
        this.building = building;
    }

}