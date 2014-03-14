/**
 * @author Glavin Wiechert
 *
 */
public class BankAccount implements Comparable {

    /**
     * Fields
     */
    protected Double balance;
    
    /**
     * Constructor
     */
    public BankAccount()
    {
        balance = 0.00;
    }
    
    /**
     * Allows sorting by increasing balance.
     * @param other
     */
    @Override
    public int compareTo(Object other) {
        return this.getBalance() > ((BankAccount) other).getBalance() ? 1 : -1;
    }
    
    /**
     * Deposit money.
     * @param money
     */
    public void deposit(Double money)
    {
        balance += money;
    }
    
    /**
     * Withdraw money.
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
            System.out.println("Insufficient funds.");
            return false;
        }
    }
    
    /**
     * Get the current balance.
     * @return
     */
    public Double getBalance()
    {
        return balance;
    }
    
}
