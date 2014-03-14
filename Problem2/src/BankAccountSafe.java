import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 */

/**
 * @author glavin
 *
 */
public class BankAccountSafe extends BankAccount {

	private final ReentrantLock lock =  new ReentrantLock(); 

	/**
	 * 
	 * @param money
	 */
	public void deposit(Double money)
	{
		lock.lock();
		balance += money;
		lock.unlock();
	}
	
	/**
	 * 
	 * @param money
	 */
	public boolean withdraw(Double money)
	{
		lock.lock();
		if (balance >= money)
		{
			balance -= money;
			lock.unlock();
			return true;
		}
		else
		{
			lock.unlock();
			return false;
		}
	}

	
}
