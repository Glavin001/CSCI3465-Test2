import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 */

/**
 * @author glavin
 *
 */
public class BankAccountSafe extends BankAccount {
    
    // Create a Lock for this class
    private final ReentrantLock lock =  new ReentrantLock(); 

    /**
     * 
     * @param money
     */
    public void deposit(Double money)
    {
        lock.lock();      // Lock
        balance += money; // Operation
        lock.unlock();    // Unlock
    }
    
    /**
     * 
     * @param money
     */
    public boolean withdraw(Double money)
    {
        lock.lock();          // Lock
        // Operation
        if (balance >= money)
        {
            balance -= money;
            lock.unlock(); // Unlock
            return true;
        }
        else
        {
            lock.unlock(); // Unlock
            return false;
        }
    }

    
}
