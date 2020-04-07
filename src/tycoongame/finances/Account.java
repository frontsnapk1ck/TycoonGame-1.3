package tycoongame.finances;

import java.util.ArrayList;
import java.util.List;

/**
 * part of the {@link Player} <br>
 * </br>
 * holds all the informatoin about
 * <ul>
 * <li>balance</li>
 * <li>loans</li>
 * </ul>
 */
public abstract class Account {
	/** current balance of the {@link Player} */
	protected double bal;
	/** list of all the {@link Loan}s the {@link Player} currently has out */
	protected List<Loan> loans;
	/** list of all stock {@link Loan}s a Player can take out */
	protected List<Loan> stockLoans;
	/** the max number of {@link Loan}s a player can have out at once */
	private int maxLoans;

	public abstract void save();
	protected abstract void addLog( double newBal , double bal , String source );
	
	/**
	 * adds money to a {@link Player}'s balance
	 * 
	 * @param increase the amount of money to increase the {@link Player}'s balance
	 *                 by
	 */
	public void addBal(double increase, String source) {
		addLog(this.bal + increase, this.bal, "" + source);
		this.bal += increase;
	}

	/**
	 * @return the amount of money the {@link Player} currently has
	 */
	public double getBal() {
		return this.bal;
	}

	/**
	 * takes money away from a {@link Player} <br>
	 * </br>
	 * note -- this does not preform a check on if the new balnce will be negitive
	 * 
	 * @param toRemove the amount of money to remove
	 */
	public void subtractBal(double toRemove, String source) {
		addLog(this.bal - toRemove, this.bal, "" + source);
		this.bal -= toRemove;
	}

	/**
	 * 
	 * @param toRemove the amount of money to check if can be removed
	 * @return if the balance after subtracting the input is greater than or equal
	 *         to $0.00
	 */
	public boolean validWithdraw(double toRemove) {
		return this.bal - toRemove >= 0;
	}

	/**
	 * @return the max number of {@link Loan}s a {@link Player} can take out
	 */
	public int getMaxLoans() {
		return this.maxLoans;
	}

	/**
	 * 
	 * @return the current number of {@link Loan}s the player has out
	 */
	public int getLoansSize() {
		return this.loans.size();
	}

	/**
	 * 
	 * @return {@link ArrayList} of {@link Loan}s that is the current {@link Loan}s
	 *         the {@link Player} has out
	 */
	public List<Loan> getLoans() {
		return this.loans;
	}

	protected List<String> getLoanSaveData() {
		List<String> data = new ArrayList<String>();
		for (Loan loan : this.loans)
			data.add(loan.getSaveData());
		return data;
	}

	public abstract void resetSave(); 

}
