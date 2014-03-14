/**
 * 
 */

/**
 * @author Glavin Wiechert
 *
 */
public class BankAccount implements Comparable {

	private Double balance;
	
	public BankAccount()
	{
		balance = 0.00;
	}
	
	@Override
	public int compareTo(Object other) {
		return this.getBalance() > ((BankAccount) other).getBalance() ? 1 : -1;
	}
	
	/**
	 * 
	 * @param money
	 */
	public void deposit(Double money)
	{
		balance += money;
	}
	
	/**
	 * 
	 * @param money
	 */
	public boolean withdraw(Double money)
	{
		if (balance >= money)
		{
			balance -= money;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Double getBalance()
	{
		return balance;
	}
	
	
	
	
}
