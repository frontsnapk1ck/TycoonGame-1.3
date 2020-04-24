package tycoongame.game.asests;

import java.util.ArrayList;
import java.util.List;

import tycoongame.buildings.Building;
import tycoongame.buildings.StoreManager;
import tycoongame.finances.Loan;
import tycoongame.game.factory.NameFactory;
import tycoongame.records.Log;
import tycoongame.zzzzzz.event.transaction.TransactionListner;

public class Player {

    private AccountManager account = new AccountManager();
	private BuildingManager bManager = new BuildingManager();
	private NameFactory nameFactory = new NameFactory (); 
	private NameFactory streetFactory = new NameFactory();

    public void startGame() 
    {
		nameFactory.load("res\\assets\\objects\\stockNames.txt");
		streetFactory.load("res\\assets\\objects\\stockStreetNames.txt");
        // TODO Auto-generated method stub

    }
    
	public List<Log> getLogs() 
	{
		return this.account.getLogs();
	}

	public List<Loan> getAvalibleLoans() 
	{
		List<Loan> avaliableLoans = new ArrayList<Loan>();
		if (account.getMaxLoans() == account.getNumLoans())
			return avaliableLoans;
		return this.account.getStockLoans();
	}

	public int getLastDay() 
	{
		return this.account.getLastDay();
	}

	public double getIncrease()
	{
		return this.bManager.getIncrease();
	}

	public void addBalance(double increase , String message) 
	{
		this.account.addBal(increase , message);
	}

	/**
	 * 
	 * @param i index of the hashMap to return
	 * @return {@link ArrayList} filled with {@link StoreManager}s for all of the {@link BuildingType}s at the index
	 */
	public List<StoreManager> getSMans (int i)
	{
		return this.bManager.getHash(i);
	}

	/**
	 * creats list of {@link Building}s adds all buildings to that list reguardless of any upgrades on 
	 * 	the {@link StoreManager} <br></br>
	 * this will remove the multiplier on the {@link StoreManager}, and thus is not aplicalbe to any 
	 * 	method in which it is important to have accurate monetary values.
	 * 
	 * @param index the index of the {@link BuildingType} to look up
	 * @return list of all {@link Building}s for a {@link BuildingType}
	 */
	public List<Building> getOwnedBuildings (int index)
	{
		List<StoreManager> sMans = getSMans(index);
		List<Building> buildings = new ArrayList<Building>();
		if (sMans != null)
		{
			for (int i = 0; i< sMans.size(); i++)
			{
				buildings.addAll(sMans.get(i).get());
			}
			if (buildings.size() == 0)
				return null;
		}
		return buildings;
	}

	/**
	 * 
	 * @return the number of different types of buildings that are currently available to the player
	 */
	public int getNumTypes() 
	{
		return this.bManager.maxSize();
	}
	/**
	 * 
	 * @param 	i index of the {@link BuildingType} to get the name of
	 * @return 	the name of the building type in a string form
	 */
	public String getTypeName(int i) 
	{
		return this.bManager.getTypeName(i);
	}
	/**
	 * @return all the stock {@link Building}s in the Manager
	 */
	public List<tycoongame.buildings.Building> getStockBuildings() 
	{
		return this.bManager.getStock();
	}

	/**
	 * 
	 * @param buildingNum the index of the {@link Building} to add to ownedBuildings of this {@link Player}
	 */
	public void addBuilding(int buildingNum) 
	{
		String name = streetFactory.getName();
		if (bManager.canAddBuilding(buildingNum))
			this.bManager.addBuilding( buildingNum , name );
	}

	/**
	 * 
	 * @param i index of the {@link Building} to check if availible to add
	 * @return if the {@link Player} can add a {@link Building} 
	 */
	public boolean canAddBuilding (int i)
	{
		return this.bManager.canAddBuilding(i);
	}

	/**
	 * 
	 * @return the balance of this accoutnt
	 */
	public double getBalance() 
	{
		return this.account.getBal();
	}
	
	public boolean validWithdraw (double withBal)
	{
		return account.validWithdraw(withBal);
	}
	
	public void withdraw (double withBal , String message)
	{
		this.account.subtractBal(withBal , message);
	}

	/**
	 * 
	 * @param 	in the index of the building to be selected
	 * @return 	the cos of the building
	 */
	public double getCost(int in) 
	{
		return this.bManager.getCost(in);
	}

	/**
	 * 
	 * @param classNum the index of the {@link BuildingType} to look for {@link StoreManager}s in
	 * @param index the index of the {@link StoreManager} in the {@link java.util.HashMap}
	 * @return {@link StoreManager} of the type at <code>classNum</code> at <code>index</code>
	 */
	public StoreManager getSMan(int classNum, int index) 
	{
		return this.getSMans(classNum).get(index);
	}

	/**
	 * 
	 * @return the <code>cost</code> of a {@link StoreManager}
	 */
	public double getSManCost() 
	{
		return StoreManager.COST;
	}

	public void addSMan(int classNum) 
	{
		bManager.addSMan ( classNum , nameFactory.getName() );
	}

	public Building getBuilding(int classNum, int index, int building) 
	{
		return bManager.getBuilding(classNum , index , building);
	}

	public void upgradeBuilding(int classNum, int in, int building , String message) 
	{
		if (validWithdraw(bManager.getBuilding(classNum, in, building).getUpgradeCost()))
		{
			double upgradeCost = bManager.getBuildingUpgradeCost(classNum, in, building);
			bManager.addUpgrade(bManager.getBuilding(classNum, in, building)	);
			withdraw(upgradeCost, "upgraded building");
		}
		else
		{
			message = "You could not afford the upgrade to the Building";
		}
	}

	public void sellBuilding(int classNum, int in, int building) 
	{
		//TODO auto generated
	}

	public double getBuildingUpgradeCost(int classNum, int in, int building) 
	{
		Building b = getBuilding(classNum, in, building);
		return b.getUpgradeCost();
	}

	public void save() 
	{
		bManager.save();
		account.save();
	}

	public void resetSave() 
	{
		bManager.resetSave();
		account.resetSave();
	}

	public List<Loan> getLoans() 
	{
		return this.account.getLoans();
	}

	public int getNumAvalibleLoans() 
	{
		List<Loan> loans = getAvalibleLoans();
		if (loans == null)
			return 0;
		return loans.size();
	}

	public void takeLoan(int in) 
	{
		account.takeLoan ( in );
	}

	public TransactionListner getTListner() 
	{
		return this.account;
	}

	public void addTListeners(List<TransactionListner> tListners) 
	{
		this.bManager.addTListeners( tListners );
	}
	
}