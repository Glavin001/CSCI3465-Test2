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
     * Fields
     */
    private int hours;
    private int minutes;
    private int seconds;
    /**
     * Observer Pattern
     * See more below.
     */
    private ArrayList<TimeObserver> observers;
    
    /**
     * Constructor
     * @param host
     * @param port
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
     * Check if currently running.
     * @return
     */
    public boolean isRunning()
    {
        return true;
    }
    
    /**
     * Register Observer.
     * @param to
     */
    public void registerObserver(TimeObserver to)
    {
        observers.add(to);
    }
    
    /**
     * Helper method. Prints out methods of class.
     * @param c
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
    
    /**
     * Extract the time values from the instance given the respective method names.
     * @param k
     * @param hoursMethodName
     * @param minutesMethodName
     * @param secondsMethodName
     */
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
     * Get the return value of a method by name.
     * @param k
     * @param methodName
     * @return Value returned from specified method.
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
     * Observer Pattern: notify all observers.
     */
    private void broadcastUpdate()
    {
        for (TimeObserver t : observers)
        {
            t.refreshTime();
        }
    }
    
    /**
     * Get the hours.
     * @return
     */
    public int getHours() {
        return hours;
    }
    
    /**
     * Get the minutes.
     * @return
     */
    public int getMinutes() {
        return minutes;
    }
    
    /**
     * Get the seconds.
     * @return
     */
    public int getSeconds() {
        return seconds;
    }
    
}
