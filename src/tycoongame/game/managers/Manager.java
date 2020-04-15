package tycoongame.game.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tycoongame.buildings.Building;
import tycoongame.buildings.BuildingType;
import tycoongame.buildings.StoreManager;
import tycoongame.buildings.level.LowLevel;
import tycoongame.controller.ScreenController;
import tycoongame.game.controllers.BuildingScreenController;
import tycoongame.game.controllers.BusinessScreenController;
import tycoongame.game.controllers.TypeScreenController;
import tycoongame.game.gui.Window;
import tycoongame.game.gui.event.screenchange.ScreenChangeEvent;
import tycoongame.game.gui.event.screenchange.ScreenChangeListener;
import tycoongame.game.gui.screeen.BuildingScreen;
import tycoongame.game.gui.screeen.BusinessScreen;
import tycoongame.game.gui.screeen.ManagerScreen;
import tycoongame.game.gui.screeen.TypeScreen;
import tycoongame.gui.ScreenFramework;

public class Manager implements ScreenChangeListener {

    private Window window;
    private ScreenFramework currentScreen;
    private ScreenController currentController;
    
    private BusinessScreenController businessSC;
    private BuildingScreenController buildingSC;
    private TypeScreenController typeSC;

    public Manager() {
        this.window = new Window();

        // TODO temp code
        HashMap <BuildingType , List<StoreManager>> ownedBuildings = new HashMap <BuildingType , List<StoreManager>>();

        BuildingType bT = BuildingType.LEMONADE_STAND;
        List<StoreManager> sMans = new ArrayList<StoreManager>();
        sMans.add(new StoreManager(bT, "Ligma 1"));
        sMans.add(new StoreManager(bT, "Sugdez 2"));
        sMans.add(new StoreManager(bT, "Joe 3"));

        for (StoreManager storeManager : sMans) {
            Building b = new Building(bT, 10, new LowLevel(10));
            b.setName("yes1");
            storeManager.add(b);
            b = new Building(bT, 10, new LowLevel(10));
            b.setName("yes2");
            storeManager.add(b);
        }
        ownedBuildings.put(bT, sMans);

        bT = BuildingType.TWO;
        sMans = new ArrayList<StoreManager>();
        sMans.add(new StoreManager(bT, "Ligma 1"));
        sMans.add(new StoreManager(bT, "Sugdez 2"));
        sMans.add(new StoreManager(bT, "Joe 3"));

        for (StoreManager storeManager : sMans) {
            Building b = new Building(bT, 10, new LowLevel(10));
            b.setName("yes1");
            storeManager.add(b);
            b = new Building(bT, 10, new LowLevel(10));
            b.setName("yes2");
            storeManager.add(b);
        }
        ownedBuildings.put(bT, sMans);

        bT = BuildingType.THREE;
        sMans = new ArrayList<StoreManager>();
        sMans.add(new StoreManager(bT, "Ligma 1"));
        sMans.add(new StoreManager(bT, "Sugdez 2"));
        sMans.add(new StoreManager(bT, "Joe 3"));

        for (StoreManager storeManager : sMans) {
            Building b = new Building(bT, 10, new LowLevel(10));
            b.setName("yes1");
            storeManager.add(b);
            b = new Building(bT, 10, new LowLevel(10));
            b.setName("yes2");
            storeManager.add(b);
        }
        ownedBuildings.put(bT, sMans);

        bT = BuildingType.FOUR;
        sMans = new ArrayList<StoreManager>();
        sMans.add(new StoreManager(bT, "Ligma 1"));
        sMans.add(new StoreManager(bT, "Sugdez 2"));
        sMans.add(new StoreManager(bT, "Joe 3"));

        for (StoreManager storeManager : sMans) {
            Building b = new Building(bT, 10, new LowLevel(10));
            b.setName("yes1");
            storeManager.add(b);
            b = new Building(bT, 10, new LowLevel(10));
            b.setName("yes2");
            storeManager.add(b);
        }
        ownedBuildings.put(bT, sMans);

        bT = BuildingType.FIVE;
        sMans = new ArrayList<StoreManager>();
        sMans.add(new StoreManager(bT, "Ligma 1"));
        sMans.add(new StoreManager(bT, "Sugdez 2"));
        sMans.add(new StoreManager(bT, "Joe 3"));

        for (StoreManager storeManager : sMans) {
            Building b = new Building(bT, 10, new LowLevel(10));
            b.setName("yes1");
            storeManager.add(b);
            b = new Building(bT, 10, new LowLevel(10));
            b.setName("yes2");
            storeManager.add(b);
        }
        ownedBuildings.put(bT, sMans);
        // TODO temp code

        this.businessSC = new BusinessScreenController();
        this.buildingSC = new BuildingScreenController();
        this.typeSC = new TypeScreenController();

        typeSC.setOwnedBuildings(ownedBuildings);

        this.businessSC.addManagerScreen(new ManagerScreen("TESTING"));
        this.businessSC.addBusinessScreen(new BusinessScreen(bT.toString()));
        this.buildingSC.addScreen( new BuildingScreen("TESTING"));
        this.typeSC.addScreen ( new TypeScreen("TYPES"));


        this.currentController = this.typeSC;
        this.currentController.addObserver(this);
        this.currentScreen = typeSC.getCurrentScreen();
    }

    public void display() {
        this.window.setVisible(true);
        this.window.setCurrentScreen(this.currentScreen);
    }

    @Override
    public void onScreenChange( ScreenChangeEvent e ) 
    {
        //TODO

        this.currentScreen = this.currentController.getCurrentScreen();
        this.window.setCurrentScreen(this.currentScreen);
    }

}