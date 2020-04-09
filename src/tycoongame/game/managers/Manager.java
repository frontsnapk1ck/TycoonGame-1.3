package tycoongame.game.managers;

import java.util.ArrayList;
import java.util.List;

import tycoongame.buildings.Building;
import tycoongame.buildings.BuildingType;
import tycoongame.buildings.StoreManager;
import tycoongame.buildings.level.LowLevel;
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
        sMans.add (new StoreManager(bT, "Ligma me"));
        sMans.add (new StoreManager(bT, "Sugdez"));
        sMans.add (new StoreManager(bT, "Joe"));
        sMans.add (new StoreManager(bT, "Ligma"));
        sMans.add (new StoreManager(bT, "Sugdez"));
        sMans.add (new StoreManager(bT, "Joe"));
        sMans.add (new StoreManager(bT, "Ligma"));
        sMans.add (new StoreManager(bT, "Sugdez"));
        sMans.add (new StoreManager(bT, "Joe"));
        sMans.get(0).add(new Building(bT, 10, new LowLevel(10)));
        //TODO temp code
        
        BusinessScreenController bSC = new BusinessScreenController ( bT );
        bSC.addBusinessScreen( new BusinessScreen( bT.toString() ) );
        bSC.setsMans(sMans);
        this.currentScreen = bSC.getScreen();
    }

    public void display() 
    {
        this.window.setCurrentScreen( this.currentScreen );
        this.window.setVisible( true );
	}

}