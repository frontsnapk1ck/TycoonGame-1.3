package tycoongame.game.controllers;

import java.util.List;

import tycoongame.buildings.BuildingType;
import tycoongame.buildings.StoreManager;
import tycoongame.controller.ScreenController;
import tycoongame.game.gui.screeen.BusinessScreen;
import tycoongame.gui.ScreenFramework;

public class BusinessScreenController extends ScreenController {

    private BusinessScreen businessScreen;
    private BuildingType bT;
    private List<StoreManager> sMans;

    /**
     * @param bT the bT to set
     */
    public void setbT(BuildingType bT) 
    {
        this.bT = bT;
        this.businessScreen.setName( bT.toString() );
    }

    public void addBusinessScreen(BusinessScreen screen) {
        // remove current
        if (this.businessScreen != null)
            this.businessScreen.removeListener(this);

        this.businessScreen = screen;
        this.businessScreen.setListener(this);
        this.businessScreen.loadManagers(this.sMans);

        this.currentScreen = this.businessScreen;
    }



    public ScreenFramework getBScreen() 
    {
        return this.businessScreen;
    }

    /**
     * @param sMans the sMans to sety
     */
    public void setsMans(List<StoreManager> sMans) {
        this.sMans = sMans;
    }

}