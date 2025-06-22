

import java.awt.event.ActionEvent;
import java.sql.Time;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;


public class Enemy extends Rocket implements Runnable {
	private int xActor,xActor1,xActor2;
	private int yActor,yActor1,yActor2;
	private JFrame frame;
	private JLabel label1x,label2x,label3x;
	private static Icon actor;
	private JPanel panel;
	private static int damage=10,diff;
	private static int count=0,count2=0,count3=0;
	private static int aa1=0,aa2=0,aa3=0;
	private boolean visible1,visible2,visible3;
	Timer time;

public Enemy(JFrame frame,JPanel panel,int diff){
	
	this.frame=frame;this.panel=panel;this.diff=diff;
	actor=new ImageIcon(getClass().getResource("enemy21.png"));
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	yActor=-100;
	xActor = 100;
	aa1=0;aa2=0;aa3=0;
	
	xActor1=600;
	xActor2=350;
	
	yActor1=yActor-10;
	yActor2=yActor+50;
	
	time=new Timer(3000,this);
	time.start();
	if(diff==1){
		Thread xx=new Thread(new Rocket(frame,panel,xActor,yActor,2));
		xx.start();
		Thread xx1=new Thread(new Rocket(frame,panel,xActor1,yActor1,2));
		xx1.start();
		Thread xx2=new Thread(new Rocket(frame,panel,xActor2,yActor2,2));
		xx2.start();
	}else if(diff==2){
		Thread xx1=new Thread(new Rocket(frame,panel,xActor1,yActor1,2));
		xx1.start();
	}
	
}
@Override
public void actionPerformed(ActionEvent arg0) {
	if(diff==1){
		Thread xx=new Thread(new Rocket(frame,panel,xActor,yActor,2));
		xx.start();
		Thread xx1=new Thread(new Rocket(frame,panel,xActor1,yActor1,2));
		xx1.start();
		Thread xx2=new Thread(new Rocket(frame,panel,xActor2,yActor2,2));
		xx2.start();
	}else if(diff==2){
		Thread xx1=new Thread(new Rocket(frame,panel,xActor1,yActor1,2));
		xx1.start();
	}
	
	if(frame.isVisible()==false)
		time.stop();
}
@Override
public void run() {
	if(diff==1){
		this.label1x = new JLabel(actor);
		this.label1x.setBounds(xActor, yActor, 69, 130);
		this.label2x= new JLabel(actor);
		this.label2x.setBounds(xActor1, yActor1, 69, 130);
		this.label3x = new JLabel(actor);
		this.label3x.setBounds(xActor2, yActor2, 69, 130);
	}else if(diff==2){
		this.label2x= new JLabel(actor);
		this.label2x.setBounds(xActor1, yActor1, 69, 130);
	}
	while(yActor!=650){
			 try {
		
		yActor=yActor+1;
		xActor=xActor+1;
		xActor1=xActor1-1;
		yActor1=yActor1+1;
		yActor2=yActor2+1;
		if(diff==1){
			this.label1x.setBounds(xActor, yActor, 69, 130);
			this.label2x.setBounds(xActor1, yActor1, 69, 130);
			this.label3x.setBounds(xActor2, yActor2, 69, 130);
		}else if(diff==2){
			this.label2x.setBounds(xActor1, yActor1, 69, 130);
		}
		
		this.check2();
		if(diff==1){
			if(aa1==0 && gameover!=true && getLabel()!=null && this.label1x.getBounds().intersects(getLabel())){
				gameover=true;
				aa1=1;
			}if(aa2==0 && gameover!=true && getLabel()!=null && this.label2x.getBounds().intersects(getLabel())){
				gameover=true;
				aa2=1;
			}if(aa3==0 && gameover!=true && getLabel()!=null && this.label3x.getBounds().intersects(getLabel())){
				gameover=true;
				aa3=1;
			}
		}else if(diff==2){
			if(aa2==0 && gameover!=true && getLabel()!=null && this.label2x.getBounds().intersects(getLabel())){
				gameover=true;
				aa2=1;
			}
		}
	
		if(diff==1){
			panel.add(label1x);panel.add(label2x);panel.add(label3x);
		}else if(diff==2){
			panel.add(label2x);
		}
		
		frame.add(panel);

		Thread.sleep(30);
			 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	if(diff==1){
		this.label1x.setIcon(null);panel.remove(this.label1x);this.label2x.setIcon(null);
		panel.remove(this.label2x);this.label3x.setIcon(null);panel.remove(this.label3x);
	}else if(diff==2){
		this.label2x.setIcon(null);panel.remove(this.label2x);
	}
	frame.add(panel);
	
}
private void check2() {
	if(diff==1 && getLabel1b()!=null && this.label1x.getBounds().intersects(getLabel1b())){
		if(this.count>=this.damage){
			
			this.label1x.setIcon(null);panel.remove(this.label1x);
			frame.add(panel);
			this.count=0;
			if(this.visible1==false){
				
				Thread t1 = new Thread(new Explosion(panel,frame,xActor,yActor,2));  
				t1.start();
				int score=getScore();
				score=score+5;
				setScore(score);
				aa1=1;
				time.stop();
			}
			this.visible1=true;
		}else{
			this.count++;
		}
		
	}
	else if(getLabel1b()!=null && this.label2x.getBounds().intersects(getLabel1b())){
		if(this.count2>=this.damage){
			
			this.label2x.setIcon(null);panel.remove(this.label2x);
			frame.add(panel);
			count2=0;
			if(this.visible2==false){
				
				Thread t2 = new Thread(new Explosion(panel,frame,xActor1,yActor1,2));  
				t2.start();
				int score=getScore();
				score=score+5;
				setScore(score);
				aa2=1;
				time.stop();
			}
			this.visible2=true;
		}else{
			this.count2++;
		}
		
	}else if(diff==1 && getLabel1b()!=null && this.label3x.getBounds().intersects(getLabel1b())){
		if(this.count3>=this.damage){
			
			this.label3x.setIcon(null);panel.remove(this.label3x);
			frame.add(panel);
			this.count3=0;
			if(this.visible3==false){
				
				Thread t3 = new Thread(new Explosion(panel,frame,xActor2,yActor2,2));  
				t3.start();
				int score=getScore();
				score=score+5;
				setScore(score);
				aa3=1;
				time.stop();
			}
			this.visible3=true;
		}else{
			this.count3++;
		}
		

	}else if(diff==1 && getLabel11b()!=null && this.label1x.getBounds().intersects(getLabel11b()) 
			|| diff==1 && getLabel2b()!=null && this.label1x.getBounds().intersects(getLabel2b())
			|| diff==1 && getLabel3b()!=null && this.label1x.getBounds().intersects(getLabel3b())){
		
		
		this.label1x.setIcon(null);panel.remove(this.label1x);
		frame.add(panel);
		
		if(this.visible1==false){
			
			Thread t1 = new Thread(new Explosion(panel,frame,xActor,yActor,2));  
			t1.start();
			int score=getScore();
			score=score+5;
			setScore(score);
			aa1=1;
			time.stop();
		}
		this.visible1=true;
		
	}else if(getLabel11b()!=null && this.label2x.getBounds().intersects(getLabel11b()) 
			|| getLabel2b()!=null && this.label2x.getBounds().intersects(getLabel2b())
			|| getLabel3b()!=null && this.label2x.getBounds().intersects(getLabel3b())){
		
		this.label2x.setIcon(null);panel.remove(this.label2x);
		frame.add(panel);
		
		if(this.visible2==false){
			
			Thread t2 = new Thread(new Explosion(panel,frame,xActor1,yActor1,2));  
			t2.start();
			int score=getScore();
			score=score+5;
			setScore(score);
			aa2=1;
			time.stop();
		}
		this.visible2=true;
		
	}else if(diff==1 && getLabel11b()!=null && this.label3x.getBounds().intersects(getLabel11b()) 
			|| diff==1 && getLabel2b()!=null && this.label3x.getBounds().intersects(getLabel2b())
			|| diff==1 && getLabel3b()!=null && this.label3x.getBounds().intersects(getLabel3b())){
		
		this.label3x.setIcon(null);panel.remove(this.label3x);
		frame.add(panel);
		
		if(this.visible3==false){
			
			Thread t3 = new Thread(new Explosion(panel,frame,xActor2,yActor2,2));  
			t3.start();
			int score=getScore();
			score=score+5;
			setScore(score);
			aa3=1;
			time.stop();
		}
		this.visible3=true;
	}
}
}
