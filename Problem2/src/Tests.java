import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * 
 */

/**
 * @author Glavin Wiechert
 *
 */
public class Tests {

    @Test
    public void testA() {
        // Prepare
        ArrayList<BankAccount> a = new ArrayList<BankAccount>();
        double rangeMin = 0.00;
        double rangeMax = 10000.00;
        for (int i = 0; i<100; i++) {
            Random r = new Random();
            double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            
            BankAccount b1 = new BankAccount();
            b1.deposit(randomValue);
            a.add(b1);
        }   
        
        /*
        // Print Result
        for (BankAccount c : a)
        {
            System.out.println(c.getBalance());
        }
        System.out.println();
        */
        
        // Sort
        Collections.sort(a);
        
        // Evaluate Test
        Boolean isIncreasing = true;
        Double lastValue = 0.00;
        for (BankAccount c : a)
        {
            //System.out.println(lastValue+", "+c.getBalance());
            if (lastValue >= c.getBalance())
            {
                isIncreasing = false;
                break;
            }
            lastValue = c.getBalance();
        }
        if (!isIncreasing)
        {
            fail("Not yet implemented");
        }
        else
        {
            System.out.println("Test 2a: Passed.");
        }

        /*
        // Print Result
        for (BankAccount c : a)
        {
            System.out.println(c.getBalance());
        }
        System.out.println();
        */
    }
    
    @Test
    public void testB() {
        // Preparation
        final BankAccount b = new BankAccount();
        ArrayList<Thread> allThreads = new ArrayList<Thread>();
        
        // Operation(s)
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread() {
                public void run()
                {
                    for (int j = 0; j < 1000; j++)
                    {
                        b.deposit(11.00);
                        b.withdraw(1.00);
                    }
                    //System.out.println(b.getBalance());                   
                }
            };
            t.start();
            allThreads.add(t);
        }
        
        boolean waiting = false;
        do
        {
            int alive = 0;
            for (Thread t : allThreads)
            {
                if (t.isAlive())
                {
                    alive += 1;
                    break;
                }
            }
            if (alive > 0)
            {
                waiting = true;
            }
            else
            {
                waiting = false;
            }
        } while (waiting);
        
        double goalBalance = (10*1000*(11.00-1.00));
        //System.out.println("Balance is: "+b.getBalance());        
        //System.out.println("Balance should be: "+goalBalance);
        
        if (b.getBalance() != goalBalance)
        {
            // Intentional failure, as per Problem 2b.
            //fail("Balance is not what the balance should be.");
            System.out.println("Test 2b: Passed.");
        }
        else
        {
            fail("This should have failed, however both balances are "+goalBalance);
        }
        
    }
    
    @Test
    public void testC() {
        // Preparation
        final BankAccountSafe b = new BankAccountSafe();
        ArrayList<Thread> allThreads = new ArrayList<Thread>();
        
        // Operation(s)
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread() {
                public void run()
                {
                    for (int j = 0; j < 1000; j++)
                    {
                        b.deposit(11.00);
                        b.withdraw(1.00);
                    }
                    //System.out.println(b.getBalance());                   
                }
            };
            t.start();
            allThreads.add(t);
        }
        
        boolean waiting = false;
        do
        {
            int alive = 0;
            for (Thread t : allThreads)
            {
                if (t.isAlive())
                {
                    alive += 1;
                    break;
                }
            }
            if (alive > 0)
            {
                waiting = true;
            }
            else
            {
                waiting = false;
            }
        } while (waiting);
        
        double goalBalance = (10*1000*(11.00-1.00));
        //System.out.println("Balance is: "+b.getBalance());        
        //System.out.println("Balance should be: "+goalBalance);
        
        if (b.getBalance() != goalBalance)
        {
            // Intentional failure, as per Problem 2b.
            fail("Balance is not what the balance should be.");
        }
        else
        {
            System.out.println("Test 2c: Passed.");
        }
        
    }

}
