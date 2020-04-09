package tycoongame.game.factory;

import tycoongame.buildings.Building;
import tycoongame.buildings.BuildingType;
import tycoongame.buildings.StoreManager;
import tycoongame.buildings.level.Level;
import tycoongame.buildings.level.LowLevel;
import tycoongame.buildings.level.MidLevel;
import tycoongame.buildings.level.HighLevel;

import java.util.List;
import java.util.ArrayList;

public class BuildingFactory {
	
	
    private List<Building> stockBuildings;
    private List<Building> userBuildings;
    private List<BuildingType> buildingTypes; 
    private List<StoreManager> sMans;

    public BuildingFactory ()
    {
        this.stockBuildings = new ArrayList<Building>();
        this.userBuildings  = new ArrayList<Building>();
        this.buildingTypes = new ArrayList<BuildingType>(); 
        this.sMans = new ArrayList<StoreManager>();
    }

    public List<Building> getUserBuildings ()
    {
        return this.userBuildings;
    }

    public List<Building> getStockBuildings ()
    {
        return this.stockBuildings;
    }

    public List<BuildingType> getTypes()
    {
        return this.buildingTypes;
    }

    public List<StoreManager> getSMans ()
    {
        return this.sMans;
    }

    public void loadStock (String filename)
    {
        List<String> buildingData = FileReader.readTextFile(filename);
        loadStockBuildings(buildingData);
    }

    private void loadStockBuildings(List<String> buildingData) 
    {
        for (String s : buildingData)
            loadStockBuilding(s);
    }

    private void loadStockBuilding(String s) 
    {
        String[] slices = s.split("\\|");

        int currentLevel = Integer.parseInt(slices[3]);
        int maxLevel = Integer.parseInt(slices[4]);  
        
        BuildingType bT = BuildingType.parseBT( slices[0] );
        int cost = Integer.parseInt( slices[1] );
        Level level = parseLevel ( slices[2] , currentLevel , maxLevel );

        Building b = new Building(bT, cost, level);
        stockBuildings.add(b);
        buildingTypes.add(bT);
    }

    public void loadUserBuildings(String filename)
    {
        List<String> buildingData = FileReader.readTextFile(filename);
        if (buildingData.size() == 0)
            return;
        this.loadUserBuildings ( buildingData );
    }

    private void loadUserBuildings(List<String> buildingData) 
    {
        for (String s : buildingData)
            this.loadUserBuilding(s);
    }

    private void loadUserBuilding(String string) 
    {
        String[] slices = string.split("\\|");

        int currentLevel = Integer.parseInt(slices[5]);
        int maxLevel = Integer.parseInt(slices[6]);  
        
        BuildingType bT = BuildingType.parseBT( slices[0] );
        double cost = Double.parseDouble( slices[3] );
        Level level = parseLevel ( slices[4] , currentLevel , maxLevel );
        String id = bT + "|" + slices[1];
        String name = slices[2];

        Building b = new Building(bT, cost, level);
        b.setSManID(id);
        b.setName(name);
        userBuildings.add(b);
    }

    public void loadSMans (String filename)
    {
        List<String> sManData = FileReader.readTextFile(filename);
        if (sManData == null || sManData.size() == 0)
            return;
        this.loadStoreManagers(sManData);
    }

    private void loadStoreManagers (List<String> sManData)
    {
        for (String sMan : sManData)
            loadStoreManager(sMan);
    }
 
    private void loadStoreManager(String sMan) 
    {
        String[] slices = sMan.split("\\|");

        BuildingType bT = BuildingType.parseBT(slices[0]);
        String id = slices[0] + "|" + slices[1];
        String name = slices[2];
        double baseUpkeepCost = Double.parseDouble(slices[3]);
        int upgradeCount = Integer.parseInt(slices[4]);
        int maxBuildings = Integer.parseInt(slices[5]);
        double multiplyer = Double.parseDouble(slices[6]);

        StoreManager storeMan = new StoreManager(bT , name);
        storeMan.setID( id );
        storeMan.setBaseUpkeepCost( baseUpkeepCost );
        storeMan.setUpgradeCount(upgradeCount);
        storeMan.setMaxBuildings ( maxBuildings );
        storeMan.setMultiplier ( multiplyer );
        this.sMans.add( storeMan );
    }

    private static Level parseLevel(String type, int current, int max)
    {
        Level level;
        if (type.equals("Low"))
        {
            level = new LowLevel(max);
            level.setCurrent(current);
        }
        else if (type.equals("Mid"))
        {
            level = new MidLevel(max);
            level.setCurrent(current);
        }
        else if (type.equals("High"))
        {
            level = new HighLevel(max);
            level.setCurrent(current);
        }
        else 
            return null;
        return level;
    }
}