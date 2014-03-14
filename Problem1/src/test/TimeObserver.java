/**
 * 
 */
package test;

/**
 * @author Glavin Wiechert
 *
 */
public interface TimeObserver {
    
    /**
     * Notify observer that it should request the new value and re-render.
     */
    public void refreshTime();
    
}
