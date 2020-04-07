package tycoongame;

import java.util.ArrayList;
import java.util.List;

import tycoongame.zzzzzz.GameFramework;
import tycoongame.zzzzzz.event.EventListener;
import tycoongame.zzzzzz.event.GameEvent;

/**
 * @author	S38392
 *
 */

public class TycoonGame extends GameFramework {

    private static TycoonGame game;
    private static String message;
    private static int day;

    private List<EventListener> listeners;

    public static void main(String[] args) 
    {
        // game = getInstance(game);
        // game.setupGame();
        // game.start();
    }

    private void start() {
        guiManager.display();
        day = player.getLastDay();
        game.loop();

    }

    private void loop() {
        while (playing) {
            menu.line(10);

            menu.out("Day: " + day);
            menu.out(this.getStats());
            message(message);

            message = null;

            mainMenu();
        }
    }

    private void mainMenu() {
        ArrayList<Integer> intList = numbersXThruX(0, 4);
        intList.add(9);

        menu.main();
        int in = input.getUserInt("> ", intList);
        operate(in);
    }

    private String getStats() {
        return "$" + fixBal(player.getBalance());
    }

    private void operate(int in) {
        final int BUY_BUILDING = 1;
        final int SHOW_STORE_MANAGERS = 2;
        final int BUY_STORE_MANAGER = 3;
        final int MANAGE_LOANS = 4;
        final int NEW_DAY = 9;
        final int PAUSE = 0;

        if (in == BUY_BUILDING) {
            menu.out(dispAdpt.getStockBuilings(this.player));
            menu.line();
            menu.out("0\tback");
            menu.line();

            ArrayList<Integer> intList = numbersXThruX(0, this.player.getNumTypes());
            int buildingNum = input.getUserInt("> ", intList);

            buyBuilding(buildingNum);
        } else if (in == SHOW_STORE_MANAGERS) {
            menu.out(dispAdpt.getAllClasses(this.player));
            menu.line();
            menu.out("0\tback");
            menu.line();

            ArrayList<Integer> intList = numbersXThruX(0, this.player.getNumTypes());
            int classNum = input.getUserInt("> ", intList);

            showStoreManagers(classNum, in);
            return;
        } else if (in == BUY_STORE_MANAGER) {
            menu.out(dispAdpt.getAllClasses(this.player));
            menu.line();
            menu.out("0\tback");
            menu.line();

            ArrayList<Integer> intList = numbersXThruX(0, this.player.getNumTypes());
            int classNum = input.getUserInt("> ", intList);

            buyStoreManager(classNum);
        } else if (in == MANAGE_LOANS) {
            manageLoans();
        } else if (in == NEW_DAY) {
            nextDay();
            return;
        } else if (in == PAUSE) {
            pause();
        }
        input.next();

    }

    private void manageLoans() {
        final int BACK = 0;
        final int BUY_LOAN = 1;
        final int VIEW_LAONS = 2;
        final int VIEW_LOG = 3;

        menu.manageLoans();

        ArrayList<Integer> intList = numbersXThruX(0, 3);
        int in = input.getUserInt("> ", intList);

        if (in == BACK)
            return;
        else if (in == BUY_LOAN)
            buyLoan();
        else if (in == VIEW_LAONS)
            viewLoans();
        else if (in == VIEW_LOG)
            viewLog();
    }

    private void viewLog() {
        List<String> list = dispAdpt.getLogs(this.player);
        menu.out(list);
        menu.line();
        menu.out("0\tback");

        input.getUserInt("> ", 0);
    }

    private void viewLoans() {
        List<String> list = dispAdpt.getLoans(this.player);
        menu.out(list);
        menu.line();
        menu.out("0\tback");

        input.getUserInt("> ", 0);
    }

    private void buyLoan() {
        final int BACK = 0;

        menu.out(dispAdpt.getAvailbleLoans(player));
        menu.line();
        menu.out("0\tback");

        List<Integer> intList = numbersXThruX(0, player.getNumAvalibleLoans());
        int in = input.getUserInt("> ", intList);

        if (in == BACK)
            return;
        in--;
        player.takeLoan(in);
    }

    private void pause() {
        final int BACK = 0;
        final int SAVE = 1;
        final int SAVE_EXIT = 2;
        final int DONT_SAVE = 3;
        final int RESET = 4;

        menu.end();
        List<Integer> intList = numbersXThruX(0, 4);
        int in = input.getUserInt("> ", intList);

        if (in == BACK)
            return;
        else if (in == SAVE)
            player.save();
        else if (in == SAVE_EXIT) {
            player.save();
            this.playing = false;
            game.destroy();
        } else if (in == DONT_SAVE) {
            this.playing = false;
            game.destroy();
        } else if (in == RESET) {
            player.resetSave();
            this.playing = false;
            game.destroy();
        }
    }

    private void buyStoreManager(int classNum) {
        final int BACK = 0;

        if (classNum == BACK)
            return;
        if (!player.validWithdraw(player.getSManCost())) {
            message = "You could not afford to by a new Store Manager";
            return;
        }
        classNum--;
        player.withdraw(player.getSManCost(), "bought Store Manager");
        player.addSMan(classNum);
    }

    private void showStoreManagers(int classNum, int recalValue) {
        final int BACK = 0;

        if (classNum == BACK)
            return;
        classNum--;
        selectStoreManager(classNum, recalValue);
    }

    private void selectStoreManager(int classNum, int recalValue) {
        final int BACK = 0;

        List<String> list = dispAdpt.getSMansWithStats(this.player, classNum);
        List<Integer> intList = numbersXThruX(0, list.size());
        menu.out(list);
        menu.out("0\tback");
        menu.line();

        int in = input.getUserInt("> ", intList);
        if (in == BACK) {
            operate(recalValue);
            return;
        }
        in--;
        viewStoreManager(classNum, in, recalValue);
    }

    private void viewStoreManager(int classNum, int in, int recalValue) {
        String sMan = dispAdpt.viewStoreManager(this.player, classNum, in);
        if (sMan == null) {
            selectStoreManager(classNum, recalValue);
            return;
        }

        menu.out(sMan);
        menu.storeManager();
        ArrayList<Integer> intList = numbers1ThruX(2);
        intList.add(0);
        int choice = input.getUserInt("> ", intList);
        operateSingleStoreManager(classNum, in, choice);
    }

    private void operateSingleStoreManager(int classNum, int in, int choice) {
        final int BUY = 1;
        final int SHOW_BUILDINGS = 2;
        final int BACK = 0;

        if (choice == BUY) {
            double cost = this.player.getSMan(classNum, in).getUpkeepCost(1);
            menu.storeManagerUpgrades(fixBal(cost));

            ArrayList<Integer> intList = numbersXThruX(0, 2);
            int upgrade = input.getUserInt("> ", intList);

            operateStoreManagetUpgrade(classNum, in, upgrade);
        } else if (choice == SHOW_BUILDINGS) {
            ArrayList<String> buildingList = dispAdpt.getStoreManagerBuildings(player, classNum, in);
            menu.out(buildingList);
            menu.line();
            menu.out("0\tback");

            ArrayList<Integer> intList = numbersXThruX(0, buildingList.size());
            int building = input.getUserInt(">", intList);
            if (building == BACK)
                return;
            else {
                building--;
                viewSingleBuilding(classNum, in, building);
            }
        } else if (choice == BACK) {
            return;
        }
    }

    private void viewSingleBuilding(int classNum, int in, int building) {
        final int BACK = 0;
        final int UPGRADE = 1;
        final int SELL = 2;

        menu.out("" + player.getBuilding(classNum, in, building));
        double cost = player.getBuildingUpgradeCost(classNum, in, building);
        menu.building(fixBal(cost));
        ArrayList<Integer> intList = numbersXThruX(0, 2);
        int operation = input.getUserInt(">", intList);

        if (operation == BACK)
            return;
        else if (operation == UPGRADE)
            player.upgradeBuilding(classNum, in, building, message);
        else if (operation == SELL)
            player.sellBuilding(classNum, in, building);
    }

    private void operateStoreManagetUpgrade(int classNum, int index, int upgrade) {
        double cost = this.player.getSMan(classNum, index).getUpkeepCost(1);
        if (upgrade == 0)
            return;
        if (!player.validWithdraw(cost)) {
            message = "You could not afford that upgrade for that Store Manager";
            return;
        }
        if (upgrade == 1)
            this.player.getSMan(classNum, index).increaseMaxBuildings();
        else if (upgrade == 2)
            this.player.getSMan(classNum, index).increaseMultiplier();
        this.player.withdraw(cost, "Upgraded Store Manager");

    }

    private void buyBuilding(int in) {
        if (in == 0) {
            return;
        }
        in--;
        boolean validBuy = player.validWithdraw(player.getCost(in)) && player.canAddBuilding(in);
        if (validBuy) {
            player.withdraw(player.getCost(in), "bought Building");
            addBuilding(in);
        } else if (!player.validWithdraw(player.getCost(in)))
            message = "you could not buy that building because you are broke";
        else if (!player.canAddBuilding(in))
            message = "you could not buy that building becuase there isnt an availble Store Manager";
        else
            message = "well this is awkward idk why you cant buy that building";
    }

    private void nextDay() 
    {
        GameEvent event = new GameEvent(this, this.player.getIncrease(), day);
        for (EventListener listener : this.listeners)
            listener.onDayChange(event);
    }

    private void addBuilding(int buildingNum) {
        player.addBuilding(buildingNum);
    }

    public static TycoonGame getInstance(TycoonGame g) {
        if (g == null) {
            g = new TycoonGame();
		}
		return g;
	}

	private void destroy ()
	{
		game = null;
	}

	private void newDayEvent ()
	{
		GameEvent event = new GameEvent( this , player.getIncrease() , day ) {	};
		for (EventListener eventListener : listeners)
			eventListener.onDayChange(event);
	}

	private void purchaceEvent ()
	{
		//TODO this
	}
	
	//=============================================
	//				Utility
	//=============================================

	private ArrayList<Integer> numbers1ThruX(int max) 
	{
		ArrayList<Integer> intList = new ArrayList<Integer>();
		for (int i = 1; i <= max; i++)
			intList.add(i);
		return intList;
	}

	private ArrayList<Integer> numbersXThruX( int min , int max ) 
	{
		ArrayList<Integer> intList = new ArrayList<Integer>();
		for (int i = min; i <= max; i++)
			intList.add(i);
		return intList;
	}

	private String fixBal (double bal)
	{
		if ((int) bal == bal 		||
			(int) bal * 10 == bal * 10)
		{
			return "" + bal + "0";
		}
		return "" + ((int) (bal * 100))/100;
	}

}
