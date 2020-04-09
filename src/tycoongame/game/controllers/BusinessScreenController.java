package tycoongame.game.controllers;

import java.util.List;

import tycoongame.buildings.Building;
import tycoongame.buildings.BuildingType;
import tycoongame.buildings.StoreManager;
import tycoongame.controller.ScreenController;
import tycoongame.game.gui.event.BuisnessEvent;
import tycoongame.game.gui.event.BusinessLister;
import tycoongame.game.gui.event.ManagerEvent;
import tycoongame.game.gui.event.ManagerListener;
import tycoongame.game.gui.screeen.BusinessScreen;
import tycoongame.game.gui.screeen.ManagerScreen;
import tycoongame.gui.ScreenFramework;

public class BusinessScreenController extends ScreenController implements BusinessLister, ManagerListener {

    private ScreenFramework currentScreen;
    private BusinessScreen businessScreen;
    private ManagerScreen managerScreen;
    private BuildingType bT;
    private List<StoreManager> sMans;

    public BusinessScreenController( BuildingType bT ) 
    {
        this.bT = bT;
    }

    public void addBusinessScreen( BusinessScreen screen )
    {
        // remove current 
        if (  this.businessScreen != null )
            this.businessScreen.removeListener( this );

        this.businessScreen = screen;
        this.businessScreen.setListener(this);

        this.currentScreen = this.businessScreen;
    }

    /**
     * @param managerScreen the managerScreen to set
     */
    public void setManagerScreen(ManagerScreen screen) 
    {
        if ( this.managerScreen != null )
            this.managerScreen.removeListener( this );

        this.managerScreen = screen;
        this.managerScreen.setListener( this );

        this.currentScreen = this.managerScreen;
    }

    // private String configureTitle() 
    // {
    //     String bTName = "" + this.bT;
    //     String out = "";
    //     for (int i = 0; i < bTName.length(); i++ )
    //     {
    //         out += bTName.charAt(i);
    //         out += "";
    //     }
    //     return out;
    // }

    public void setsMans(List<StoreManager> sMans) 
    {
        this.sMans = sMans;
        for (StoreManager storeManager : sMans)
            businessScreen.loadManager(storeManager.getName() , storeManager.getID() );
    }

    @Override
    public void onManagerSelect(BuisnessEvent event) {
        if (event.getAction() == BuisnessEvent.MANAGER_SELECTED) 
        {
            setManagerScreen(event.getManagerName() , event.getId());
        }
    }

    private void setManagerScreen(String managerName, String id) 
    {
        for (StoreManager sMan : sMans) 
        {
            String sManID = sMan.getID();
            
            if (sMan.getName().equals(managerName) && sManID.equals(id))
            {
                setManagerScreen(sMan);
            }
        }
    }

    private void setManagerScreen(StoreManager sMan) 
    {
        List<Building> buildigns = sMan.get();
        managerScreen.setBuildings(buildigns);
        this.currentScreen = managerScreen;
        
    }

    @Override
    public void onBuildingSelect(ManagerEvent event) 
    {
        if ( event.getAction() == ManagerEvent.BUILDING_SELECTED )
        {
            System.out.println(event.getBuildingName());
        }
    }

    public ScreenFramework getBScreen() 
    {
		return this.businessScreen;
    }
    
    public ScreenFramework getMScreen ()
    {
        return this.managerScreen;
    }

    public ScreenFramework getScreen() 
    {
		return this.currentScreen;
	}

}