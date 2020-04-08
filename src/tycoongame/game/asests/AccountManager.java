package tycoongame.game.asests;

import java.util.List;

import tycoongame.finances.Account;
import tycoongame.finances.Loan;
import tycoongame.game.factory.LoanFactory;
import tycoongame.game.factory.LogFactory;
import tycoongame.game.save.SaveGameManager;
import tycoongame.records.Log;

public class AccountManager extends Account {

    private LogManager logManager;

    public AccountManager ()
    {
        LogFactory logFactory = new LogFactory();
        LoanFactory loanFactory = new LoanFactory();

        logFactory.load("res\\assets\\saves\\account\\account.txt");
        loanFactory.loadStock("res\\assets\\objects\\stockLoans.txt");
        loanFactory.loadOwned("res\\assets\\saves\\account\\ownedLoans.txt");

        List<Log> logs = logFactory.getLogs();

        logManager = new LogManager(logs);
        this.stockLoans = loanFactory.getStock();
        this.loans = loanFactory.getLoans();
        this.bal = logManager.getLastBal();
    }

    @Override
    public void save() 
    {
        logManager.save();
		SaveGameManager save = new SaveGameManager();
		save.save(getLoanSaveData(), "res\\assets\\saves\\account\\ownedLoans.txt");
	}

    @Override
    public void resetSave() {
        SaveGameManager reset = new SaveGameManager();
        reset.resetLog();
        reset.reset("res\\assets\\saves\\account\\ownedLoans.txt");
    }

    @Override
    protected void addLog(double newBal, double bal, String message) 
    {
        logManager.addLog(newBal, bal, message);
    }

    public List<Log> getLogs() 
    {
		return logManager.getLogs();
	}

    public int getNumLoans() 
    {
		return this.loans.size();
	}

    public List<Loan> getStockLoans() 
    {
		return stockLoans;
	}

    public int getLastDay() 
    {
		return logManager.getLastDay();
	}

    public void takeLoan(int in) 
    {
        this.loans.add(this.stockLoans.get(in).clone());
	}

}