

import java.awt.Rectangle;
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
import javax.swing.WindowConstants;

public class Rocket extends Ship implements Runnable{
	
	private int xActor;
	private int yActor;
	private static int x,y;
	private JFrame frame;
	private JLabel label1,label2,label3,label4,label5,label6,label7,label8;
	private JLabel label11,label12;
	private static Rectangle label1b,label11b,label2b,label3b;
	private Icon actor;
	private JPanel panel;
	private int belirli;
	
	Rocket(){}
    
	public Rocket(JFrame frame,JPanel panel, int xActor, int yActor) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.xActor=xActor;
		x=xActor;
		this.yActor=yActor;
		y=yActor;
		this.frame=frame;
		this.panel=panel;
		actor=new ImageIcon(getClass().getResource("beam.png"));
		this.belirli=0;
		
	}
	public Rocket(JFrame frame,JPanel panel, int xActor, int yActor,int i) {
		this.xActor=xActor;
		this.yActor=yActor;
		this.frame=frame;
		this.panel=panel;
		this.belirli=i;
		
		if(belirli==1){
		actor=new ImageIcon(getClass().getResource("vv1.png"));
		}else if(belirli==2){
			
			actor=new ImageIcon(getClass().getResource("rocket4.png"));
		}else if(belirli==3){
			
			actor=new ImageIcon(getClass().getResource("beamm.png"));
		}else if(belirli==4){
			
			actor=new ImageIcon(getClass().getResource("boss1gun.png"));
			
		}else if(belirli==5){
			
			actor=new ImageIcon(getClass().getResource("boss2gun.png"));
			
		}else if(belirli==6){
			actor=new ImageIcon(getClass().getResource("boss3gun.png"));
		}else if(belirli==7){
			actor=new ImageIcon(getClass().getResource("bomb2.png"));
		}
		
	}
	@Override
	public void run() {
		
		try {
		  label1 = new JLabel(actor); label2 = new JLabel(actor);
		  label3 = new JLabel(actor); label4 = new JLabel(actor); 
		  label5 = new JLabel(actor); label6 = new JLabel(actor);
		  label7 = new JLabel(actor); label8 = new JLabel(actor);
		  label11=new JLabel(actor); label12= new JLabel(actor);
		if(this.belirli==0){
			while(yActor!=0){
				y=yActor;
				this.label11.setBounds(xActor+30, yActor, 22, 50);
				this.label12.setBounds(xActor+65, yActor, 22, 50);
				label1b=this.label11.getBounds();
				panel.add(this.label11);
				panel.add(this.label12);
				frame.add(panel);
				yActor=yActor-1;
				
				Thread.sleep(7);
				
			}
			label1b=null;
			this.label11.setIcon(null);this.label12.setIcon(null);
			panel.remove(this.label12);panel.remove(this.label11);
			frame.add(panel);
		}else if (this.belirli==1){ //Mini JET
			
			int kk=0;
			for(int i=yActor; i<frame.getHeight(); i++){

				label1.setBounds(xActor+10, yActor, 40, 40);
				label2.setBounds(xActor-10, yActor, 40, 40);
				
				if(kk==0 && gameover!=true && getLabel()!=null && label1.getBounds().intersects(getLabel())){
					gameover=true;
					kk=1;
				}
				panel.add(label1);panel.add(label2);frame.add(panel);
				yActor=yActor+3;
				
				Thread.sleep(20);
			}
			label1.setIcon(null);label2.setIcon(null);
			panel.remove(label2);panel.remove(label1);
			frame.add(panel);
				
			
			
		}else if(this.belirli==2){ //ENEMY
			int cc=0;
			for(int i=yActor; i<frame.getHeight(); i++){

				label1.setBounds(xActor+15, yActor, 40, 40);
				label2.setBounds(xActor, yActor, 40, 40);
				label3.setBounds(xActor-15, yActor, 40, 40);
				
				if(cc==0 && gameover!=true && getLabel()!=null && label2.getBounds().intersects(getLabel())){
					gameover=true;
					cc=1;
				}
				
				panel.add(label1);panel.add(label2);
				panel.add(label3);frame.add(panel);
				yActor=yActor+8;
				xActor++;

				Thread.sleep(40);
			}
			label1.setIcon(null);label2.setIcon(null);label3.setIcon(null);
			panel.remove(label2);panel.remove(label1);panel.remove(label3);
			frame.add(panel);
		}
		else if(this.belirli==3){//TANK
			int dd=0;
			yActor=yActor+150;
			for(int i=yActor; i<frame.getHeight(); i++){
				
				label1.setBounds(xActor, yActor, 14, 30);
				label2.setBounds(xActor, yActor, 14, 30);
				label3.setBounds(xActor+20, yActor-80, 14, 30);
				label4.setBounds(xActor-20, yActor-80, 14, 30);
				label5.setBounds(xActor+30, yActor-160, 14, 30);
				label6.setBounds(xActor-30, yActor-160, 14, 30);
				
				if(dd==0 && gameover!=true && getLabel()!=null && label3.getBounds().intersects(getLabel())){
					gameover=true;
					dd=1;
				}
				
				panel.add(label1);panel.add(label2);panel.add(label3);
				panel.add(label4);panel.add(label5);panel.add(label6);
				frame.add(panel);
				yActor=yActor+1;

				Thread.sleep(7);
			}
			label1.setIcon(null);label2.setIcon(null);label3.setIcon(null);
			label4.setIcon(null);label5.setIcon(null);label6.setIcon(null);
			panel.remove(label2);panel.remove(label1);panel.remove(label3);
			panel.remove(label4);panel.remove(label5);panel.remove(label6);
			
			frame.add(panel);
			
		}else if(this.belirli==4){  //BOSS1
			
			int ee=0,ee1=0;
			xActor=xActor+100;yActor=yActor+100;
			int xActor1=xActor;int yActor1=yActor;
			int xActor2=xActor;int yActor2=yActor;
			int xActor3=xActor;int yActor3=yActor;
			int xActor4=xActor;int yActor4=yActor;
			
				
				while(xActor!=800){

				label1.setBounds(xActor, yActor, 50, 50);
				label2.setBounds(xActor1, yActor1, 50, 50);
				label3.setBounds(xActor2, yActor2, 50, 50);
				label4.setBounds(xActor3, yActor3, 50, 50);
				label5.setBounds(xActor4, yActor4, 50, 50);
				
				if(ee==0 && gameover!=true && getLabel()!=null && label1.getBounds().intersects(getLabel())){
					gameover=true;
					ee=1;
				}if(ee1==0 && gameover!=true && getLabel()!=null && label3.getBounds().intersects(getLabel())){
					gameover=true;
					ee1=1;
				}
				
				xActor3--;yActor3++;
				xActor1++;
				xActor2--;yActor2++;
				xActor4--;yActor4--;
				xActor++;yActor++;
				
				panel.add(label1);panel.add(label2);panel.add(label3);
				panel.add(label4);panel.add(label5);
				frame.add(panel);
				yActor=yActor+1;

				Thread.sleep(20);
				
			}
			label1.setIcon(null);label2.setIcon(null);label3.setIcon(null);
			label4.setIcon(null);label5.setIcon(null);
			panel.remove(label2);panel.remove(label1);panel.remove(label3);
			panel.remove(label4);panel.remove(label5);
			
			frame.add(panel);
			
		}else if(this.belirli==5){ //BOSS2
			
			int ff=0,ff1=0,ff2=0,ff3=0;
			xActor=xActor+100;yActor=yActor+100;
			int xActor1=xActor;int yActor1=yActor;int xActor2=xActor;int yActor2=yActor;
			int xActor3=xActor;int yActor3=yActor;int xActor4=xActor;int yActor4=yActor;
			int xActor5=xActor;int yActor5=yActor;int xActor6=xActor;int yActor6=yActor;
			int xActor7=xActor;int yActor7=yActor;
			Random rand = new Random(); 
			int x =rand.nextInt(50);
			int yy =rand.nextInt(50);
				
				while(xActor!=700){

				label1.setBounds(xActor+20, yActor, 100, 40);
				label2.setBounds(xActor1+40, yActor1+20, 100, 40);
				label3.setBounds(xActor2+60, yActor2+40, 100, 40);
				label4.setBounds(xActor3-20, yActor3, 100, 40);
				label5.setBounds(xActor4-40, yActor4+20, 100, 40);
				label6.setBounds(xActor5-60, yActor5+40, 100, 40);
				label7.setBounds(xActor6+x, yActor6+yy, 100, 40);
				label8.setBounds(xActor7+yy, yActor7+x, 100, 40);
				
				if(ff==0 && gameover!=true && getLabel()!=null && label1.getBounds().intersects(getLabel())){
					gameover=true;
					ff=1;
				}if(ff1==0 && gameover!=true && getLabel()!=null && label2.getBounds().intersects(getLabel())){
					gameover=true;
					ff1=1;
				}if(ff2==0 && gameover!=true && getLabel()!=null && label4.getBounds().intersects(getLabel())){
					gameover=true;
					ff2=1;
				}if(ff3==0 && gameover!=true && getLabel()!=null && label8.getBounds().intersects(getLabel())){
					gameover=true;
					ff3=1;
				}
				
				xActor3--;yActor3++;xActor1++;yActor1++;xActor2++;yActor2++;
				xActor4--;yActor4++;xActor++;yActor++;xActor5--;yActor5++;yActor6++;yActor7++;
				
				panel.add(label1);panel.add(label2);panel.add(label3);
				panel.add(label4);panel.add(label5);panel.add(label6);
				panel.add(label7);panel.add(label8);
				frame.add(panel);
				yActor=yActor+1;

				Thread.sleep(10);
				
			}
			label1.setIcon(null);label2.setIcon(null);label3.setIcon(null);
			label4.setIcon(null);label5.setIcon(null);label6.setIcon(null);
			label7.setIcon(null);label8.setIcon(null);
			panel.remove(label2);panel.remove(label1);panel.remove(label3);
			panel.remove(label4);panel.remove(label5);panel.remove(label6);
			panel.remove(label7);panel.remove(label8);
			
			frame.add(panel);
		}else if(this.belirli==6){ //BOSS3
			int gg=0,gg1=0,gg2=0,gg3=0;
			Icon actor2=new ImageIcon(getClass().getResource("boss3gun3.png"));
			Icon actor3=new ImageIcon(getClass().getResource("boss3gun4.png"));
			
			
			label1 = new JLabel(actor2);
			label2 = new JLabel(actor2);
			label3 = new JLabel(actor3);
			label4 = new JLabel(actor3);
			
			xActor=xActor+100;yActor=yActor+100;
			
			int xActor1=xActor;int yActor1=yActor;int xActor2=xActor;int yActor2=yActor;
			int xActor3=xActor;int yActor3=yActor;int xActor4=xActor;int yActor4=yActor;
			int xActor5=xActor;int yActor5=yActor;int xActor6=xActor;int yActor6=yActor;
			int xActor7=xActor;int yActor7=yActor;
			int x;
				while(xActor!=700){

				label1.setBounds(xActor3-20, yActor, 50, 83);
				label2.setBounds(xActor+20, yActor, 50, 83);
				label3.setBounds(xActor3-50, yActor2, 100, 100);
				label4.setBounds(xActor2+50, yActor2, 100, 100);
				label5.setBounds(xActor4-80, yActor4, 50, 84);
				label6.setBounds(xActor5+80, yActor5, 50, 84);
				label7.setBounds(xActor6-80, yActor6, 50, 84);
				label8.setBounds(xActor7+80, yActor7, 50, 84);
				
				if(gg==0 && gameover!=true && getLabel()!=null && label1.getBounds().intersects(getLabel())){
					gameover=true;
					gg=1;
				}if(gg1==0 && gameover!=true && getLabel()!=null && label2.getBounds().intersects(getLabel())){
					gameover=true;
					gg1=1;
				}if(gg2==0 && gameover!=true && getLabel()!=null && label4.getBounds().intersects(getLabel())){
					gameover=true;
					gg2=1;
				}if(gg3==0 && gameover!=true && getLabel()!=null && label8.getBounds().intersects(getLabel())){
					gameover=true;
					gg3=1;
				}
				
				xActor3--;yActor3++;xActor2=xActor2+2;yActor2++;
				xActor4--;yActor4--;xActor++;yActor++;
				xActor5++;yActor5--;xActor6--;yActor6++;xActor7++;yActor7++;
				
				panel.add(label1);panel.add(label2);panel.add(label3);
				panel.add(label4);panel.add(label5);panel.add(label6);
				panel.add(label7);panel.add(label8);
				frame.add(panel);
				yActor=yActor+1;

				Thread.sleep(10);
				
			}
			label1.setIcon(null);label2.setIcon(null);label3.setIcon(null);
			label4.setIcon(null);label5.setIcon(null);label6.setIcon(null);
			label7.setIcon(null);label8.setIcon(null);
			panel.remove(label2);panel.remove(label1);panel.remove(label3);
			panel.remove(label4);panel.remove(label5);panel.remove(label6);
			panel.remove(label7);panel.remove(label8);
			
			frame.add(panel);
		}else if(this.belirli==7){ //BOMB
			
			int i=0;
			int x=xActor;
			int x1=xActor;
			while(yActor!=0){
				
				label1.setBounds(xActor, yActor, 100, 100);
				label2.setBounds(x, yActor, 100, 100);
				label3.setBounds(x1, yActor, 100, 100);
				label11b=label1.getBounds();
				label2b=label2.getBounds();
				label3b=label3.getBounds();
				
				panel.add(label1);panel.add(label2);panel.add(label3);
				frame.add(panel);
				yActor=yActor-1;
				x++;
				x1--;
				
				Thread.sleep(7);
			}
			label11b=null;label2b=null;label3b=null;
			label1.setIcon(null);label2.setIcon(null);label3.setIcon(null);
			panel.remove(label1);panel.remove(label2);panel.remove(label3);
			frame.add(panel);
			
		}

		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static Rectangle getLabel1b() {
		return label1b;
	}
	public static Rectangle getLabel11b() {
		return label11b;
	}

	public static Rectangle getLabel2b() {
		return label2b;
	}
	public static Rectangle getLabel3b() {
		return label3b;
	}
	public JLabel getLabel11() {
		return label11;
	}

}