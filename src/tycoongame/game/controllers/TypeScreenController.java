package tycoongame.game.controllers;

import java.util.HashMap;
import java.util.List;

import tycoongame.buildings.Building;
import tycoongame.buildings.BuildingType;
import tycoongame.buildings.StoreManager;
import tycoongame.controller.ScreenController;
import tycoongame.game.gui.screeen.TypeScreen;

public class TypeScreenController extends ScreenController {

    private HashMap<BuildingType, List<StoreManager>> ownedBuildings;
    private TypeScreen typeScreen;

    /**
     * @param ownedBuildings the ownedBuildings to set
     */
    public void setOwnedBuildings(HashMap<BuildingType, List<StoreManager>> ownedBuildings) {
        this.ownedBuildings = ownedBuildings;
    }

    public void addScreen(TypeScreen screen) 
    {
        if (this.typeScreen != null)
            this.typeScreen.removeLister(this);

        this.typeScreen = screen;
        this.typeScreen.setListener(this);
        this.typeScreen.loadTypes(ownedBuildings);

        this.currentScreen = this.typeScreen;
    }

}