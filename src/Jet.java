
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;


public class Jet extends Rocket implements Runnable {
	
	private static int xActor,xActor1,xActor2;
	private static int yActor,yActor1,yActor2;
	private JFrame frame;
	private JLabel label1,label2,label3,label4;
	private static Icon actor;
	private static Icon exp;
	private JPanel panel;
	private boolean visible1,visible2,visible3;
	private static int count,aa1=0,aa2=0,aa3=0;
	private static int damage=1,diff;
	Timer time;
	
	
	public Jet(JFrame frame,JPanel panel,int diff){
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame=frame;this.diff=diff;
		this.panel=panel;
		actor=new ImageIcon(getClass().getResource("jet1.png"));
		Random rand = new Random(); 
		yActor=-30;
		xActor = rand.nextInt(600);
		xActor1=rand.nextInt(600);
		xActor2=rand.nextInt(600);
		this.visible1=false;
		this.visible2=false;
		this.visible3=false;
		aa1=0;aa2=0;aa3=0;
		
		time=new Timer(3000,this);
		time.start();
		if(diff==1){
			Thread xx=new Thread(new Rocket(frame,panel,xActor,yActor,1));
			xx.start();
			Thread xx1=new Thread(new Rocket(frame,panel,xActor1,yActor+10,1));
			xx1.start();
			Thread xx2=new Thread(new Rocket(frame,panel,xActor2,yActor-80,1));
			xx2.start();
		}else if(diff==2){
			Thread xx=new Thread(new Rocket(frame,panel,xActor,yActor,1));
			xx.start();
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(diff==1){
			Thread xx=new Thread(new Rocket(frame,panel,xActor,yActor,1));
			xx.start();
			Thread xx1=new Thread(new Rocket(frame,panel,xActor1,yActor+10,1));
			xx1.start();
			Thread xx2=new Thread(new Rocket(frame,panel,xActor2,yActor-80,1));
			xx2.start();
		}else if(diff==2){
			Thread xx=new Thread(new Rocket(frame,panel,xActor,yActor,1));
			xx.start();
		}
		
		Clip clips;
		try {
			clips = AudioSystem.getClip();
			clips.open(AudioSystem.getAudioInputStream(new File("fuze.wav")));
			clips.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		if(frame.isVisible()==false)
		time.stop();
	}
	@Override
	public void run() { 
		
		try {
			
			if(diff==1){
				this.label1 = new JLabel(actor);
				this.label1.setBounds(xActor, yActor, 50, 71);
				this.label2= new JLabel(actor);
				this.label2.setBounds(xActor1, yActor+10, 50, 71);
				this.label3 = new JLabel(actor);
				this.label3.setBounds(xActor2, yActor-80, 50, 71);
			}else if(diff==2){
				this.label1 = new JLabel(actor);
				this.label1.setBounds(xActor, yActor, 50, 71);
			}
		count++;
		
		while(yActor!=650){
			
			yActor=yActor+1;
			
			yActor1=yActor+10;
			yActor2=yActor-80;
			
			
			if(diff==1){
				this.label1.setBounds(xActor, yActor, 50, 71);
				this.label2.setBounds(xActor1, yActor1, 50,71);
				this.label3.setBounds(xActor2, yActor2, 50, 71);
			}else if(diff==2){
				this.label1.setBounds(xActor, yActor, 50, 71);
			}
			
			
			this.check1();
			if(diff==1){
				if(aa1==0 && gameover!=true && getLabel()!=null && this.label1.getBounds().intersects(getLabel())){
					gameover=true;
					aa1=1;
				}if(aa2==0 && gameover!=true && getLabel()!=null && this.label2.getBounds().intersects(getLabel())){
					gameover=true;
					aa2=1;
				}if(aa3==0 && gameover!=true && getLabel()!=null && this.label3.getBounds().intersects(getLabel())){
					gameover=true;
					aa3=1;
				}
			}else if(diff==2){
				if(aa1==0 && gameover!=true && getLabel()!=null && this.label1.getBounds().intersects(getLabel())){
					gameover=true;
					aa1=1;
				}
			}
			
			if(diff==1){
				panel.add(this.label1);
				panel.add(this.label2);
				panel.add(this.label3);
			}else if(diff==2){
				panel.add(this.label1);
			}
			
			frame.add(panel);
	
			Thread.sleep(70);
		}
		if(diff==1){
			this.label1.setIcon(null);panel.remove(this.label1);
			this.label2.setIcon(null);panel.remove(this.label2);
			this.label3.setIcon(null);panel.remove(this.label3);
		}else if(diff==2){
			this.label1.setIcon(null);panel.remove(this.label1);
		}
		
		frame.add(panel);
		time.stop();
		
		} catch (InterruptedException e) {
				e.printStackTrace();
		}	
	}
	private void check1(){
		
		if(getLabel1b()!=null && this.label1.getBounds().intersects(getLabel1b())){
			
			
			this.label1.setIcon(null);panel.remove(this.label1);
			frame.add(panel);
			
			if(this.visible1==false){

				Thread t1 = new Thread(new Explosion(panel,frame,xActor,yActor,1));  
				t1.start();
				int score=getScore();
				score++;
				setScore(score);
				aa1=1;
				time.stop();
			}
			this.visible1=true;
		}
		else if(diff==1 && getLabel1b()!=null && this.label2.getBounds().intersects(getLabel1b())){
			this.label2.setIcon(null);panel.remove(this.label2);
			frame.add(panel);
			if(this.visible2==false){
			
				Thread t2 = new Thread(new Explosion(panel,frame,xActor1,yActor1,1));  
				t2.start();
				int score=getScore();
				score++;
				setScore(score);
				aa2=1;
				time.stop();
			}
			this.visible2=true;
			
		}else if(diff==1 && getLabel1b()!=null && this.label3.getBounds().intersects(getLabel1b())){
			this.label3.setIcon(null);panel.remove(this.label3);
			frame.add(panel);
			if(this.visible3==false){
				
				Thread t3 = new Thread(new Explosion(panel,frame,xActor2,yActor2,1));  
				t3.start();
				int score=getScore();
				score++;
				setScore(score);
				aa3=1;
				time.stop();
			}
			this.visible3=true;
			
		}else if(getLabel11b()!=null && this.label1.getBounds().intersects(getLabel11b()) 
				|| getLabel2b()!=null && this.label1.getBounds().intersects(getLabel2b())
				|| getLabel3b()!=null && this.label1.getBounds().intersects(getLabel3b())){
			
			this.label1.setIcon(null);panel.remove(this.label1);
			frame.add(panel);
			
			if(this.visible1==false){
				
				Thread t1 = new Thread(new Explosion(panel,frame,xActor,yActor,1));  
				t1.start();
				int score=getScore();
				score++;
				setScore(score);
				aa1=1;
				time.stop();
			}
			this.visible1=true;
			
			
		}else if(diff==1 && getLabel11b()!=null && this.label2.getBounds().intersects(getLabel11b()) 
				|| diff==1 && getLabel2b()!=null && this.label2.getBounds().intersects(getLabel2b())
				|| diff==1 && getLabel3b()!=null && this.label2.getBounds().intersects(getLabel3b())){
			
			this.label2.setIcon(null);panel.remove(this.label2);
			frame.add(panel);
			
			if(this.visible2==false){
				
				Thread t2 = new Thread(new Explosion(panel,frame,xActor1,yActor1,1));  
				t2.start();
				int score=getScore();
				score++;
				setScore(score);
				aa2=1;
				time.stop();
			}
			this.visible2=true;
			
		}else if(diff==1 && getLabel11b()!=null && this.label3.getBounds().intersects(getLabel11b()) 
				|| diff==1 && getLabel2b()!=null && this.label3.getBounds().intersects(getLabel2b())
				|| diff==1 && getLabel3b()!=null && this.label3.getBounds().intersects(getLabel3b())){
			
			this.label3.setIcon(null);panel.remove(this.label3);
			frame.add(panel);
			
			if(this.visible3==false){
				
				Thread t3 = new Thread(new Explosion(panel,frame,xActor2,yActor2,1));  
				t3.start();
				int score=getScore();
				score++;
				setScore(score);
				aa3=1;
				time.stop();
			}
			this.visible3=true;
		}
	}
	
}
