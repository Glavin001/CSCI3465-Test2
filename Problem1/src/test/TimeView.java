package test;
import javax.swing.JLabel;


public class TimeView extends JLabel implements TimeObserver {

    /**
     * Reference to the model it is observing.
     */
    private TimeModel time;
    
    /**
     * Constructor
     * @param t The model to observer.
     */
    public TimeView(TimeModel t)
    {
        // Remember the model that is observing
        time = t;
        // Register with the Model as an observer
        time.registerObserver(this);
        // Refresh the display for the first time
        refreshTime();
    }

    /**
     * Refresh and re-render with the latest value from the model.
     */
    @Override
    public void refreshTime() {
        this.setText(time.getHours()+":"+time.getMinutes()+":"+time.getSeconds());
    }
    
}
