

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
import javax.swing.WindowConstants;

public class Explosion implements Runnable {
	private int xActor;
	private int yActor;
	private JFrame frame;
	private JLabel label1;
	private Icon actor;
	private JPanel panel;
	private int i,num,size;
	
	Explosion(JPanel panel, JFrame frame, int xActor, int yActor,int i){
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.xActor=xActor;
		this.yActor=yActor;
		this.frame=frame;
		this.panel=panel;
		JLabel label1=new JLabel();
		this.i=i;
		
		if(i==1){
			this.num=16;
			this.size=64;
		}else if(i==2){
			this.num=24;
			this.size=130;
		}else if(i==3){
			this.num=16;
			this.size=250;
		}
	}

	@Override
	public void run() {
		
			if(i==1){
				
				Clip clip;
				try {
					clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(new File("exp1.wav")));
					clip.start();
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
			}
			else if(i==2){
				
				Clip clip;
				try {
					clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(new File("exp2.wav")));
					clip.start();
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
			}
			else if(i==3){
				
				Clip clip;
				try {
					clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(new File("exp3.wav")));
					clip.start();
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					e.printStackTrace();
				}
			}
			int x=1;
			while(x!=num){
	        	if(i==1)
	        		actor=new ImageIcon(getClass().getResource("exp"+x+".png"));
	        	else if(i==2)
	        		 actor=new ImageIcon(getClass().getResource(x+".png"));
	        	else if(i==3)
	        		 actor=new ImageIcon(getClass().getResource("a"+x+".png"));
	        		 
	        	label1 = new JLabel(actor);
	        	label1.setBounds(xActor, yActor, size, size);
	        	panel.add(label1);
        	 	frame.add(panel);
        	 	x++;
        		
        	 	try {
            		
					Thread.sleep(65);
				} catch (InterruptedException e) {
					e.printStackTrace();
					
				}
         
        	 	label1.setIcon(null);panel.remove(label1);
        	 	panel.revalidate();panel.repaint();frame.add(panel); 
        	}
	}

}