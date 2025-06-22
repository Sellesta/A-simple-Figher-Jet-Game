

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

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


public class Boss extends Rocket implements Runnable {
	private int xActor;
	private int yActor;
	private JFrame frame;
	private static JLabel label1;
	private static Icon actor;
	private JPanel panel;
	private static int number,ok;
	private static int damage=666666666;
	private static int count=0,aa1=0;
	private boolean visible1;
	Timer time;
	
	public Boss(JFrame frame,JPanel panel){
		
		this.frame=frame;
		this.panel=panel;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.number++;
		if(number>3)
			number=3;
		actor=new ImageIcon(getClass().getResource("boss"+this.number+".png")); 
		yActor=0;
		xActor = 250;
		label1 = new JLabel(actor);
		label1.setBounds(xActor, yActor, 250, 250);
		aa1=0;
		time=new Timer(2000,this);
		time.start();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Thread xx=new Thread(new Rocket(frame,panel,xActor,yActor,number+3));
		xx.start();
		
		if(number==2){
			Clip clips;
			try {
				clips = AudioSystem.getClip();
				clips.open(AudioSystem.getAudioInputStream(new File("boss2.wav")));
				clips.start();
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				
				e.printStackTrace();
			}
			
		}else{
			
			Clip clips;
			try {
				clips = AudioSystem.getClip();
				clips.open(AudioSystem.getAudioInputStream(new File("bos12.wav")));
				clips.start();
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				
				e.printStackTrace();
			}
		}
		if(frame.isVisible()==false)
			time.stop();
	}
	
	@Override
	public void run() {
		
		for(int i=0; i<150; i++){
			
			yActor=yActor+1;
			label1.setBounds(xActor, yActor, 250, 250);
		
			panel.add(label1);
			frame.add(panel);
			
			if(aa1==0 && gameover!=true && getLabel()!=null && label1.getBounds().intersects(getLabel())){
				gameover=true;
				aa1=1;
			}
			try {
				Thread.sleep(30);
			
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}	
		}
		ok=1;
		
		while(ok!=0){
			this.check();
		}		
		frame.setVisible(true);
	}
	private void check() {
		if(getLabel1b()!=null && this.label1.getBounds().intersects(getLabel1b())){
			
			if(this.count>this.damage){
				
				this.label1.setIcon(null);panel.remove(this.label1);
				panel.revalidate();panel.repaint();frame.add(panel);
					
				this.ok=0;
				this.count=0;
				if(this.visible1==false){
					
					Thread t1 = new Thread(new Explosion(panel,frame,xActor,yActor,3));  
					t1.start();
					if (this.number==1){
						setOk(0);
						setX(1);
					}	
					else if (this.number==2){
						setOk2(0);
						setX(1);
					}	
					else if (this.number==3){
						setOk3(0);
						setX(1);
					}
					int score=getScore();
					score=score+15;
					setScore(score);
					aa1=1;
					time.stop();
				}
				visible1=true;
			}else{
				
				this.count++;
			}
		}else if(getLabel11b()!=null && this.label1.getBounds().intersects(getLabel11b()) 
				|| getLabel2b()!=null && this.label1.getBounds().intersects(getLabel2b())
				|| getLabel3b()!=null && this.label1.getBounds().intersects(getLabel3b())){
			if(this.count>this.damage){
				
				
				this.label1.setIcon(null);panel.remove(this.label1);
				panel.revalidate();panel.repaint();frame.add(panel);
				
				this.ok=0;
				this.count=0;
				if(this.visible1==false){
					
					Thread t1 = new Thread(new Explosion(panel,frame,xActor,yActor,3));  
					t1.start();
					if (this.number==1){
						setOk(0);
						setX(1);
					}	
					else if (this.number==2){
						setOk2(0);
						setX(1);
					}	
					else if (this.number==3){
						setOk3(0);
						setX(1);
					}	
					int score=getScore();
					score=score+15;
					setScore(score);
					aa1=1;
					time.stop();
				}
				visible1=true;
			}else{
				
				this.count=this.count+2;
			}
		}
		
	}

}