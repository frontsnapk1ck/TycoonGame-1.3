package tycoongame.game.managers;

import java.util.ArrayList;
import java.util.List;

import tycoongame.buildings.BuildingType;
import tycoongame.buildings.StoreManager;
import tycoongame.game.controllers.BusinessScreenController;
import tycoongame.game.gui.Window;
import tycoongame.game.gui.screeen.BusinessScreen;
import tycoongame.gui.ScreenFramework;

public class Manager {

    private Window window;
    private ScreenFramework currentScreen;

    public Manager() {
        this.window = new Window();

        //TODO temp code
        BuildingType bT = BuildingType.LEMONADE_STAND;
        List<StoreManager> sMans = new ArrayList<StoreManager>();
        sMans.add (new StoreManager(bT, "Ligma"));
        sMans.add (new StoreManager(bT, "Sugdez"));
        sMans.add (new StoreManager(bT, "Joe"));
        sMans.add (new StoreManager(bT, "Ligma"));
        sMans.add (new StoreManager(bT, "Sugdez"));
        sMans.add (new StoreManager(bT, "Joe"));
        sMans.add (new StoreManager(bT, "Ligma"));
        sMans.add (new StoreManager(bT, "Sugdez"));
        sMans.add (new StoreManager(bT, "Joe"));

        //TODO temp code
        
        BusinessScreenController bSC = new BusinessScreenController ( bT );
        bSC.addBusinessScreen( new BusinessScreen( bT.toString() ) );
        bSC.setsMans(sMans);
        this.currentScreen = bSC.getBScreen();
    }

    public void display() 
    {
        this.window.setCurrentScreen( this.currentScreen );
        this.window.setVisible( true );
	}

}