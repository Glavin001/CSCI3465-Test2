package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.lang.reflect.*;

import javax.swing.text.html.HTMLDocument.Iterator;

import test.*;

public class TimeModel {

	/**
	 * 
	 */
	private int hours;
	private int minutes;
	private int seconds;
	
	private ArrayList<TimeObserver> observers;
	
	/**
	 * @param hostname
	 */
	public TimeModel(final String host, final Integer port )
	{
		observers = new ArrayList<TimeObserver>();
		
		new Thread() {
			public void run()
			{
				try
				{
					System.out.println("Starting Connection");
					Socket s = new Socket(host, port);
					ObjectInputStream a = new ObjectInputStream(s.getInputStream());
					
					while(isRunning())
					{
						System.out.println("Test");
						try
						{
							Object b = (Object) a.readObject();
							Class<?> c = b.getClass();
							System.out.println(c.getName());
							
							if (c.equals(StringTime.class))
							{
								/**
								 * getH()
								 * getM()
								 * getS()
								 */
								updateValues(b, "getH", "getM", "getS");
							}
							else if (c.equals(SecondsClock.class))
							{
								/**
								 * getHours()
								 * getMinutes()
								 * getSeconds()
								 */
								updateValues(b, "getHours", "getMinutes", "getSeconds");
							}
							else if (c.equals(OddTime.class))
							{
								/**
								 * first()
								 * second()
								 * third()
								 */
								updateValues(b, "first", "second", "third");
							}
							else if (c.equals(OtherTime.class))
							{
								/**
								 * littleHand()
								 * bigHand()
								 * longSkinnyHand()
								 */
								updateValues(b, "littleHand", "bigHand", "longSkinnyHand");
							}
							else
							{
								System.out.println("Unknown class: "+c);
							}
							
						}
						catch(ClassNotFoundException e)
						{
							System.err.println("Error");
							e.printStackTrace();
							System.exit(1);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}        
				//Host not found
				catch (UnknownHostException e) 
				{
					System.err.println("Don't know about host : " + e);
					System.exit(1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}.start();
	}

	/**
	 * 
	 * @return
	 */
	public boolean isRunning()
	{
		return true;
	}
	
	/**
	 * 
	 * @param to
	 */
	public void registerObserver(TimeObserver to)
	{
		observers.add(to);
	}
	
	/**
	 * HELPER
	 * @param i
	 */
	private void inspectClass(Class c)
	{
		/*
		Method[] methods = c.getMethods();
        for (int i = 0; i < methods.length; i++)
        {
            System.out.println("Method: " +
                methods[i].toString());
        }
        */
		Method[] dMethods = c.getDeclaredMethods();
        for (int i = 0; i < dMethods.length; i++)
        {
            System.out.println("Declared Method: " +
            		dMethods[i].toString());
        }
	}
	
	private void updateValues(Object k, String hoursMethodName, String minutesMethodName, String secondsMethodName)
	{
		try {
			// 
			hours = extractValue(k, hoursMethodName);
			minutes = extractValue(k, minutesMethodName);
			seconds = extractValue(k, secondsMethodName);
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			// Observer Pattern: Broadcast notification to all observers.
			broadcastUpdate();
			
		}
		
	}
	
	/**
	 * 
	 * @param k
	 * @param methodName
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	private int extractValue(Object k, String methodName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException
	{
		Class<?> c = k.getClass();
		Method m = c.getMethod(methodName, null);
	    Object j = (Object) m.invoke(k, null);
        return (Integer) j;
	}
	
	/**
	 * 
	 */
	private void broadcastUpdate()
	{
		for (TimeObserver t : observers)
		{
			t.refreshTime();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getHours() {
		return hours;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMinutes() {
		return minutes;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getSeconds() {
		return seconds;
	}
	
}
