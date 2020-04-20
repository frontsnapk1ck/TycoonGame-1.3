package tycoongame.game.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tycoongame.buildings.Building;
import tycoongame.buildings.BuildingType;
import tycoongame.buildings.StoreManager;
import tycoongame.buildings.level.LowLevel;
import tycoongame.controller.ScreenController;
import tycoongame.game.controllers.BusinessScreenController;
import tycoongame.game.controllers.ManAndBuildingController;
import tycoongame.game.controllers.TypeScreenController;
import tycoongame.game.gui.GameWindow;
import tycoongame.game.gui.event.manger.building.BuildingListner;
import tycoongame.game.gui.event.manger.building.BuildingViewEvent;
import tycoongame.game.gui.event.manger.building.PropertyExchangeEvent;
import tycoongame.game.gui.event.manger.building.PropertyUpgradeEvent;
import tycoongame.game.gui.event.manger.manager.BuyManagerEvent;
import tycoongame.game.gui.event.manger.manager.ManagerListener;
import tycoongame.game.gui.event.manger.manager.ManagerUpgradeEvent;
import tycoongame.game.gui.event.manger.manager.ViewManagerEvent;
import tycoongame.game.gui.event.manger.type.TypeListner;
import tycoongame.game.gui.event.manger.type.TypeViewEvent;
import tycoongame.game.gui.screeen.BuildingScreen;
import tycoongame.game.gui.screeen.BusinessScreen;
import tycoongame.game.gui.screeen.ManagerScreen;
import tycoongame.game.gui.screeen.TypeScreen;
import tycoongame.gui.ScreenFramework;

public class Manager implements BuildingListner, ManagerListener, TypeListner {

    private GameWindow window;
    private ScreenFramework currentScreen;
    private ScreenController currentController;

    private BusinessScreenController businessSC;
    private TypeScreenController typeSC;
    private ManAndBuildingController manAndBuildSC;

    public Manager() {
        this.window = new GameWindow();

        // TODO temp code
        HashMap<BuildingType, List<StoreManager>> ownedBuildings = new HashMap<BuildingType, List<StoreManager>>();

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
        this.manAndBuildSC = new ManAndBuildingController();
        this.typeSC = new TypeScreenController();

        typeSC.setOwnedBuildings(ownedBuildings);

        this.businessSC.addBusinessScreen(new BusinessScreen(bT.toString()));
        this.manAndBuildSC.addBuildingScreen(new BuildingScreen("TESTING"));
        this.manAndBuildSC.addManagerScreen(new ManagerScreen("TESTING"));
        this.typeSC.addScreen(new TypeScreen("TYPES"));

        this.currentController = this.typeSC;
        this.currentController.addObserver(this);
        this.currentScreen = typeSC.getCurrentScreen();
    }

    public void display() {
        this.window.setVisible(true);
        this.window.setCurrentScreen(this.currentScreen);
    }

    @Override
    public void onTypeView(TypeViewEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onManagerPurchace(BuyManagerEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onManagerUpgrade(ManagerUpgradeEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onManagerView(ViewManagerEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBuildingSold(PropertyExchangeEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBuildingBought(PropertyExchangeEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBuildingUpgrade(PropertyUpgradeEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBuildingView(BuildingViewEvent e) {
        // TODO Auto-generated method stub

    }

}