package test;
import javax.swing.JLabel;


public class TimeView extends JLabel implements TimeObserver {

	private TimeModel time;
	
	public TimeView(TimeModel t)
	{
		time = t;
		time.registerObserver(this);
		refreshTime();
	}

	@Override
	public void refreshTime() {
		this.setText(time.getHours()+":"+time.getMinutes()+":"+time.getSeconds());
	}
	
}
