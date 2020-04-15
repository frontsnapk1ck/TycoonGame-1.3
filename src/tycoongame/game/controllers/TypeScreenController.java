package tycoongame.game.controllers;

import java.util.HashMap;
import java.util.List;

import tycoongame.buildings.Building;
import tycoongame.buildings.BuildingType;
import tycoongame.buildings.StoreManager;
import tycoongame.controller.ScreenController;
import tycoongame.controller.event.ScreenEvent;
import tycoongame.game.gui.event.input.InputEvent;
import tycoongame.game.gui.event.input.InputListener;
import tycoongame.game.gui.event.screenchange.ScreenChangeEvent;
import tycoongame.game.gui.event.type.TypeEvent;
import tycoongame.game.gui.event.type.TypeListener;
import tycoongame.game.gui.screeen.TypeScreen;

public class TypeScreenController extends ScreenController implements InputListener, TypeListener {

    private HashMap<BuildingType, List<StoreManager>> ownedBuildings;
    private TypeScreen typeScreen;

    /**
     * @param ownedBuildings the ownedBuildings to set
     */
    public void setOwnedBuildings(HashMap<BuildingType, List<StoreManager>> ownedBuildings) {
        this.ownedBuildings = ownedBuildings;
    }

    @Override
    public void onInputEvent(InputEvent event) 
    {
        sellBuilding(event.getSource());
    }

    private void sellBuilding(Object source) {
        if (source instanceof Building) {
            Building b = (Building) source;
            BuildingType bT = b.getBuildingType();
            StoreManager sMan = findMatchingSMan(b, bT);
            sMan.get().remove(b);
        }
    }

    private StoreManager findMatchingSMan(Building b, BuildingType bT) {
        String bID = b.getSManID();
        List<StoreManager> sMans = this.ownedBuildings.get(bT);
        for (StoreManager storeManager : sMans) {
            if (storeManager.getID().equals(bID))
                return storeManager;
        }
        return null;
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

    @Override
    public void onTypeSelected(TypeEvent event) 
    {
            fireChangeEvent(event.getbT().toString() , event);   
    }

}