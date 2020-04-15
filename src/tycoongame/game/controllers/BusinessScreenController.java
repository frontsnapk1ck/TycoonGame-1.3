package tycoongame.game.controllers;

import java.util.List;

import tycoongame.buildings.BuildingType;
import tycoongame.buildings.StoreManager;
import tycoongame.controller.ScreenController;
import tycoongame.game.gui.event.business.BuisnessEvent;
import tycoongame.game.gui.event.business.BusinessLister;
import tycoongame.game.gui.event.manager.ManagerEvent;
import tycoongame.game.gui.event.manager.ManagerListener;
import tycoongame.game.gui.event.screenchange.ScreenChangeEvent;
import tycoongame.game.gui.event.type.TypeEvent;
import tycoongame.game.gui.screeen.BusinessScreen;
import tycoongame.game.gui.screeen.ManagerScreen;
import tycoongame.gui.ScreenFramework;

public class BusinessScreenController extends ScreenController implements BusinessLister, ManagerListener {

    private BusinessScreen businessScreen;
    private ManagerScreen managerScreen;
    private BuildingType bT;
    private List<StoreManager> sMans;

    /**
     * @param bT the bT to set
     */
    public void setbT(BuildingType bT) {
        this.bT = bT;
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

    @Override
    public void onManagerSelect(BuisnessEvent event) {
        setManagerScreen(event.getManager());
        fireChangeEvent(event.getManager().getName() , event);
    }

    private void setManagerScreen(StoreManager sMan) {
        managerScreen.setBuildings(sMan);
        this.currentScreen = managerScreen;
    }

    @Override
    public void onBuildingSelect(ManagerEvent event) 
    {
            System.out.println("BusinessScreenController.onBuildingSelect()" + event.getBuilfing());
    }

    public ScreenFramework getBScreen() {
        return this.businessScreen;
    }

    public ScreenFramework getMScreen() {
        return this.managerScreen;
    }

    @Override
    public void onBackButtonSelect(ScreenChangeEvent event) 
    {
        this.currentScreen = this.businessScreen;
        fireChangeEvent(event.getName() , event);
    }

    /**
     * @param sMans the sMans to set
     */
    public void setsMans(List<StoreManager> sMans) {
        this.sMans = sMans;
    }

}