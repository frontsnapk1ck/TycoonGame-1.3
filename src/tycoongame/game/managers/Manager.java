package tycoongame.game.managers;

import java.util.ArrayList;
import java.util.List;

import tycoongame.buildings.Building;
import tycoongame.buildings.BuildingType;
import tycoongame.buildings.StoreManager;
import tycoongame.buildings.level.LowLevel;
import tycoongame.controller.ScreenController;
import tycoongame.game.controllers.BusinessScreenController;
import tycoongame.game.gui.Window;
import tycoongame.game.gui.event.ScreenChangeEvent;
import tycoongame.game.gui.event.ScreenChangeListener;
import tycoongame.game.gui.screeen.BusinessScreen;
import tycoongame.game.gui.screeen.ManagerScreen;
import tycoongame.gui.ScreenFramework;

public class Manager implements ScreenChangeListener {

    private Window window;
    private ScreenFramework currentScreen;
    private ScreenController screenController;

    public Manager() {
        this.window = new Window();

        // TODO temp code
        BuildingType bT = BuildingType.LEMONADE_STAND;
        List<StoreManager> sMans = new ArrayList<StoreManager>();
        sMans.add(new StoreManager(bT, "Ligma 1"));
        sMans.add(new StoreManager(bT, "Sugdez 2"));
        sMans.add(new StoreManager(bT, "Joe 3"));
        sMans.add(new StoreManager(bT, "Ligma 4"));
        sMans.add(new StoreManager(bT, "Sugdez 5"));
        sMans.add(new StoreManager(bT, "Joe 6"));
        sMans.add(new StoreManager(bT, "Ligma 7"));
        sMans.add(new StoreManager(bT, "Sugdez 8"));
        sMans.add(new StoreManager(bT, "Joe 9"));
        for (StoreManager storeManager : sMans) {
            Building b = new Building(bT, 10, new LowLevel(10));
            b.setName("yes");
            storeManager.add(b);
            storeManager.add(b);
            storeManager.add(b);
            storeManager.add(b);
            storeManager.add(b);

        }
        // TODO temp code

        BusinessScreenController bSC = new BusinessScreenController(bT);
        bSC.setsMans(sMans);
        bSC.addManagerScreen(new ManagerScreen("TESTING"));
        bSC.addBusinessScreen(new BusinessScreen(bT.toString()));
        this.screenController = bSC;
        this.screenController.addObserver(this);
        this.currentScreen = bSC.getCurrentScreen();
    }

    public void display() {
        this.window.setVisible(true);
        this.window.setCurrentScreen(this.currentScreen);
    }

    @Override
    public void onScreenChange( ScreenChangeEvent e ) 
    {
        //TODO set the new screen controller
        this.currentScreen = null;
        this.screenController.processScreenEvent(e);
        this.currentScreen = this.screenController.getCurrentScreen();
        this.window.setCurrentScreen(this.currentScreen);
    }

}