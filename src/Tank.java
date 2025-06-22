

import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Tank extends Rocket implements Runnable{
	private int xActor;
	private int yActor;
	private JFrame frame;
	private  JLabel label1;
	private static Icon actor;
	private JPanel panel;
	private static int damage=10;
	private static int count=0,count2=0,aa1=0;
	private boolean visible1;
	
	public Tank(JFrame frame,JPanel panel){
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame=frame;
		this.panel=panel;
		actor=new ImageIcon(getClass().getResource("tank3.png"));
		Random rand = new Random(); 
		yActor=-20;xActor = rand.nextInt(200);
		label1 = new JLabel(actor);
		label1.setBounds(xActor, yActor, 72, 110);
		aa1=0;
	}

	@Override
	public void run() { 
		
		
		Thread xx=new Thread(new Rocket(frame,panel,xActor+27,yActor,3));
		xx.start();
		while(yActor!=650){
			try {
			
			yActor=yActor+1;
			label1.setBounds(xActor, yActor, 72, 110);
			
			this.check();
			if(aa1==0 && gameover!=true && getLabel()!=null && this.label1.getBounds().intersects(getLabel())){
				gameover=true;
				aa1=1;
			}
			panel.add(label1);
			frame.add(panel);
			
			Thread.sleep(50);			

		} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		label1.setIcon(null);panel.remove(label1);
		panel.revalidate();panel.repaint();
		frame.add(panel);
		
	}

	private void check() {
		if(getLabel1b()!=null && this.label1.getBounds().intersects(getLabel1b())){
			if(this.count>this.damage){
				this.label1.setIcon(null);panel.remove(this.label1);
				panel.revalidate();panel.repaint();frame.add(panel);
				count=0;
				if(this.visible1==false){
					
					Thread t1 = new Thread(new Explosion(panel,frame,xActor,yActor,2));  
					t1.start();
					int score=getScore();
					score=score+10;
					setScore(score);
					aa1=1;
				}
				this.visible1=true;
			}else{
				this.count++;
			}
		}
			
	if(getLabel11b()!=null && this.label1.getBounds().intersects(getLabel11b()) 
				|| getLabel2b()!=null && this.label1.getBounds().intersects(getLabel2b())
				|| getLabel3b()!=null && this.label1.getBounds().intersects(getLabel3b())){
			if (this.count>this.damage){
				this.label1.setIcon(null);panel.remove(this.label1);
				panel.revalidate();panel.repaint();frame.add(panel);
				this.count=0;
				if(this.visible1==false){
					
					Thread t1 = new Thread(new Explosion(panel,frame,xActor,yActor,2));  
					t1.start();
					int score=getScore();
					score=score+10;
					setScore(score);
					aa1=1;
				}
				this.visible1=true;
			}else{
				this.count=this.count+4;
				
			}
		}	
	}

}
