package tycoongame.game.asests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tycoongame.buildings.Building;
import tycoongame.buildings.BuildingType;
import tycoongame.buildings.GrandManager;
import tycoongame.buildings.StoreManager;
import tycoongame.game.factory.BuildingFactory;
import tycoongame.game.save.SaveGameManager;
import tycoongame.zzzzzz.event.transaction.TransactionListner;

public class BuildingManager extends GrandManager {

    public BuildingManager() 
    {
        loadStock();
        loadOwned();
    }

    private void loadOwned() 
    {
		BuildingFactory factory = new BuildingFactory();
        HashMap<BuildingType, List<StoreManager>> ownedBuilding = new HashMap<BuildingType, List<StoreManager>>();

        factory.loadStock("res\\assets\\objects\\stockBuildings.txt");
        List<BuildingType> bTypes = factory.getTypes();

        factory.loadSMans("res\\assets\\saves\\buildings\\storeManagers");
        List<StoreManager> sMans = factory.getSMans();

        factory.loadUserBuildings("res\\assets\\saves\\buildings\\ownedBuildings");
        List<Building> buildings = factory.getUserBuildings();

        for (StoreManager sMan : sMans)
            sMan.add(findMathcing(sMan, buildings));

        for (BuildingType bT : bTypes) 
        {
            ownedBuilding.put(bT, new ArrayList<StoreManager>());
            if (findMatcing(bT, sMans) != null)
                ownedBuilding.get(bT).addAll(findMatcing(bT, sMans));
        }
        
        this.ownedBuildings = ownedBuilding;
    }

    private void loadStock() 
    {
		BuildingFactory factory = new BuildingFactory();
        factory.loadStock( "res\\assets\\objects\\stockBuildings.txt" );

        this.allBuildings = factory.getStockBuildings();
		this.buildingTypes = factory.getTypes();
    }

    private List<StoreManager> findMatcing(BuildingType bT, List<StoreManager> sMans) 
    {
        List<StoreManager> matching = null;
        for (StoreManager sMan : sMans)
        {
            if (sMan.getBT().equals(bT))
                matching = new ArrayList<StoreManager>();   
        }
        for (StoreManager sMan : sMans)
        {
            if (sMan.getBT().equals(bT))
                matching.add(sMan);   
        }
        return matching;
    }

    private List<Building> findMathcing(StoreManager sMan, List<Building> buildings) 
    {
        List<Building> mathcingBuildings = new ArrayList<Building>();
        for (Building b : buildings)
        {
            if (b.getSManID().equals(sMan.getID()))
            {
                mathcingBuildings.add(b);
                System.out.println("Match");
            }
        }
        return mathcingBuildings;
    }

    @Override
    protected void saveBuildings() 
    {
        SaveGameManager save = new SaveGameManager();
		List<String> storeManagers = this.getAllSManSaveData();

		save.save(storeManagers, "res\\assets\\saves\\buildings\\storeManagers");
    }

    @Override
    protected void saveSMans() 
    {
        SaveGameManager save = new SaveGameManager();
		List<String> buildings = this.getAllBuildingsSaveData();
		
		save.save(buildings, "res\\assets\\saves\\buildings\\ownedBuildings" );
    }

    @Override
    public void resetSave() 
    {
        SaveGameManager reset = new SaveGameManager();
        reset.reset("res\\assets\\saves\\buildings\\storeManagers");
        reset.reset("res\\assets\\saves\\buildings\\ownedBuildings");
    }

    public void addTListeners(List<TransactionListner> tListners) 
    {
        int size = tListners.size();
        TransactionListner[] tLArr = new TransactionListner[size()];
        for (int i = 0; i < size; i++)
            tLArr[i] = tListners.get(i);

        for (BuildingType bType : this.buildingTypes) 
        {
            List<StoreManager> sMans = this.ownedBuildings.get(bType);
            for (StoreManager sMan : sMans)
                sMan.add(tLArr);
        }
	}

}