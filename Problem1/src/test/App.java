package test;

import java.awt.Container;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Glavin Wiechert
 *
 */
public class App {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        // Start up (Multiple) Viewers
        int numOfViews = 1;
        for (int i = 0; i<numOfViews; i++)
        {
            // Setup
            JFrame frame = new JFrame("Time MVC & Socket");
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           Container contentPane = frame.getContentPane();
           contentPane.setLayout(new GridBagLayout());
           
            // Connect the Model to the Socket server 
           TimeModel t = new TimeModel("cs.smu.ca", 3465);
           TimeView v = new TimeView(t);
           contentPane.add(v);
           
            // Display the window.
           frame.setSize(200, 100);
           frame.setResizable(true);
           frame.setVisible(true);
       }
       
   }

}
