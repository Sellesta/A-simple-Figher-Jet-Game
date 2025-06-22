
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class Game extends Ship{
	static JPanel panel=new JPanel();
	static JPanel panel2=new JPanel();
	static JFrame frame = new JFrame("FLY AND SKY");
	static boolean stop;
	static int high_score,diff;
	User user;
	
	Game(User user,int high_score,int diff){
		this.user=user;
		this.high_score=high_score;
		this.diff=diff;
	}
	
	public void game() {
		
		frame.setSize(800,700);
		frame.setVisible(true);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Ship ship=new Ship(frame,panel,user,high_score,diff);
		ship.play();
		
		final Background back=new Background(frame,panel);
	
		Thread t1 = new Thread(new Runnable() {
	         public void run() {
	             while(!stop){
	            	  
	            	  back.update();
	            	  
	            	  try {
						Thread.sleep(4);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	              }
	         }
	    });  
	    t1.start();
	}
}