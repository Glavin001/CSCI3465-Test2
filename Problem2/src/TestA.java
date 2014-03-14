import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;

/**
 * 
 */

/**
 * @author Glavin Wiechert
 *
 */
public class TestA {

	@Test
	public void test() {
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
			System.out.println("Pass.");
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

}
