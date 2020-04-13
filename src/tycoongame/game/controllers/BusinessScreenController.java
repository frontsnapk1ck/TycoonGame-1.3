package tycoongame.game.controllers;

import java.util.List;

import tycoongame.buildings.BuildingType;
import tycoongame.buildings.StoreManager;
import tycoongame.controller.ScreenController;
import tycoongame.controller.event.ScreenEvent;
import tycoongame.game.gui.event.BuisnessEvent;
import tycoongame.game.gui.event.BusinessLister;
import tycoongame.game.gui.event.ManagerEvent;
import tycoongame.game.gui.event.ManagerListener;
import tycoongame.game.gui.event.ScreenChangeEvent;
import tycoongame.game.gui.screeen.BusinessScreen;
import tycoongame.game.gui.screeen.ManagerScreen;
import tycoongame.gui.ScreenFramework;

public class BusinessScreenController extends ScreenController implements BusinessLister, ManagerListener {

    private BusinessScreen businessScreen;
    private ManagerScreen managerScreen;
    private BuildingType bT;
    private List<StoreManager> sMans;

    public BusinessScreenController(BuildingType bT) {
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
    public void onManagerSelect(BuisnessEvent event) 
    {
        if (event.getAction() == BuisnessEvent.MANAGER_SELECTED) {
            setManagerScreen( event.getManager() );
            fireChangeEvent( event.getManager().getName() , ScreenChangeEvent.SWITCH_TO_MANAGER );
        }
    }

    private void setManagerScreen( StoreManager sMan ) 
    {
        managerScreen.setBuildings(sMan);
        this.currentScreen = managerScreen;
    }

    @Override
    public void onBuildingSelect(ManagerEvent event) {
        if (event.getAction() == ManagerEvent.BUILDING_SELECTED) 
        {
            //TODO
            System.out.println("BusinessScreenController.onBuildingSelect()" + event.getBuilfing() );
        }
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
        if ( event.getAction() == ScreenChangeEvent.SWITCH_TO_BUSINESS )
            this.currentScreen = this.businessScreen;
        
        fireChangeEvent( event.getName(), event.getAction() );
    }

    /**
     * @param sMans the sMans to set
     */
    public void setsMans(List<StoreManager> sMans) {
        this.sMans = sMans;
    }

    @Override
    public void processScreenEvent(ScreenEvent e) 
    {
        if ( e instanceof ScreenChangeEvent )
        {
            ScreenChangeEvent change = (ScreenChangeEvent) e;
            this.managerScreen.setName(change.getName());
            this.businessScreen.setName(change.getName());
        }
    }

}