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
		time = t;
		time.registerObserver(this);
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
