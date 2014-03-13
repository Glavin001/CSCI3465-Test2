package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.lang.reflect.*;

import test.*;

public class TimeModel {

	/**
	 * 
	 */
	private int hours;
	private int minutes;
	private int seconds;
	
	private ArrayList<TimeView> observes;
	
	/**
	 * @param hostname
	 */
	public TimeModel(final String host, final Integer port )
	{
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
						try
						{
							Object b = (Object) a.readObject();
							Class<?> c = b.getClass();
							
							System.out.println(c.getName());
							Method[] dMethods = c.getDeclaredMethods();
					        for (int i = 0; i < dMethods.length; i++)
					        {
					        	Method m = dMethods[i];
					            Object j = (Object) m.invoke(b, null);
					            System.out.println(m.getName() +": "+ j);
					        }

							
							if (c.equals(StringTime.class))
							{
								/**
								 * getH()
								 * getM()
								 * getS()
								 */
							}
							else if (c.equals(SecondsClock.class))
							{
								/**
								 * getHours()
								 * getMinutes()
								 * getSeconds()
								 */
							}
							else if (c.equals(OddTime.class))
							{
								/**
								 * first()
								 * second()
								 * third()
								 */
							}
							else if (c.equals(OtherTime.class))
							{
								/**
								 * littleHand()
								 * bigHand()
								 * longSkinnyHand()
								 */
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
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
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
	
	public int getHours() {
		return hours;
	}
	
	public int getMinutes() {
		return minutes;
	}

	public int getSeconds() {
		return seconds;
	}
	
}
