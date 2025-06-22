

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
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


public class Ship implements KeyListener, ActionListener{
	  private JFrame frame;
	  private JPanel panel;
	  private static Icon actor,bar,bombr;
	  private static JLabel label11,label12,label13,scoreLabel;
	  private static int xActor=200;
	  private static int yActor=450;
	  private int x=0;
	  static boolean gameover,bomb=true;
	  private static int count,count2,count3,number;
	  private static int count4,count5;
	  private static int ok=0,ok2=0,ok3=0;
	  private static Rectangle label;
	  private static int Score,high_score,diff;
	  User user;
	  private Timer time1,time2;
	  
	  Ship(){}
	  Ship(final JFrame frame,JPanel panel,User user,int high_score,int diff){
		  this.high_score=high_score;this.diff=diff;
		  this.user=user;
		  this.frame=frame;
		  this.panel=panel;
		  this.gameover=false;
		  this.number=1;
		  bar=new ImageIcon(getClass().getResource("can"+getNumber()+".jpg"));label12=new JLabel(bar);
		  label12.setBounds(60,605,189,31);panel.add(label12);
		  bombr=new ImageIcon(getClass().getResource("bombready.png"));label13=new JLabel(bombr);
		  label13.setBounds(600,500,150,31);panel.add(label13);
		  this.Score=0;

		  scoreLabel = new JLabel();
		  scoreLabel.setBounds(620, 20, 100, 100);
		  scoreLabel.setForeground(Color.BLACK);
		  scoreLabel.setFont(new Font("0", Font.BOLD, 50));
		  scoreLabel.setText(""+Score);
		  panel.add(scoreLabel);
		  frame.add(panel);
		  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		  
	  }
	
	 public void play(){

		panel.setLayout(null);
		actor=new ImageIcon(getClass().getResource("arac11.png"));
		label11 = new JLabel(actor);  
		label11.setBounds(xActor, yActor, 115, 85); 
				 
		panel.add(label11); 
		frame.addKeyListener(this);  
		panel.setOpaque( false );
		frame.setLocationRelativeTo(null);
				  
		frame.add(panel);
		frame.setResizable(false);
		frame.setVisible(true);
				  
		time1=new Timer(20000,this);time1.start();
		time2=new Timer(50000,this);time2.start();
	}
	 
	public static int getxActor() {
		return xActor;
	}
	  
	public static int getyActor() {
		return yActor;
	}
	
	public void moveBox(int newX,int newY)
	{
		  label11.setBounds(newX, newY, 115, 85);
		  label=label11.getBounds();
	}
	@Override
	public void keyPressed(KeyEvent event) {
		
		String whichKey=KeyEvent.getKeyText(event.getKeyCode());
		
		if(whichKey.compareTo("Left")==0)
		{
			if(x==0){
				x++;
				Thread aa=new Thread(new Jet(frame,panel,getDiff()));
				aa.start();
				Clip clips;
				try {
					clips = AudioSystem.getClip();
					clips.open(AudioSystem.getAudioInputStream(new File("fuze.wav")));
					clips.start();
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					
					e.printStackTrace();
				}
			}
			changeLayoutLeft();
			checkOver();
			scoreLabel.setText(""+Score);
			panel.add(scoreLabel);
			frame.add(panel);
		}else if(whichKey.compareTo("Right")==0){
			if(x==0){
				x++;
				Thread aa=new Thread(new Jet(frame,panel,getDiff()));
				aa.start();
				Clip clips;
				try {
					clips = AudioSystem.getClip();
					clips.open(AudioSystem.getAudioInputStream(new File("fuze.wav")));
					clips.start();
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					
					e.printStackTrace();
				}
			}
			changeLayoutRight();
			checkOver();
			scoreLabel.setText(""+getScore());
			panel.add(scoreLabel);
			frame.add(panel);
		}else if(whichKey.compareTo("Up")==0){
			if(x==0){
				x++;
				Thread aa=new Thread(new Jet(frame,panel,getDiff()));
				aa.start();
				Clip clips;
				try {
					clips = AudioSystem.getClip();
					clips.open(AudioSystem.getAudioInputStream(new File("fuze.wav")));
					clips.start();
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					
					e.printStackTrace();
				}
			}
			changeLayoutUp();
			checkOver();
			scoreLabel.setText(""+Score);
			panel.add(scoreLabel);
			frame.add(panel);
		}else if(whichKey.compareTo("Down")==0){
			if(x==0){
				x++;
				Thread aa=new Thread(new Jet(frame,panel,getDiff()));
				aa.start();
				Clip clips;
				try {
					clips = AudioSystem.getClip();
					clips.open(AudioSystem.getAudioInputStream(new File("fuze.wav")));
					clips.start();
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					
					e.printStackTrace();
				}
			}
			changeLayoutDown();	
			checkOver();
			scoreLabel.setText(""+Score);
			panel.add(scoreLabel);
			frame.add(panel);
		}else if(whichKey.compareTo("Space")==0){
			changeSpace();
			scoreLabel.setText(""+Score);
			panel.add(scoreLabel);
			frame.add(panel);
			
		}else if(whichKey.compareTo("Shift")==0){
			changeShift();
			scoreLabel.setText(""+Score);
			panel.add(scoreLabel);
			frame.add(panel);
		}
	}

	public void changeLayoutLeft()
	{
		if(xActor>0)
			xActor=xActor-30;	
		moveBox(xActor,yActor);
	}
	public void changeLayoutRight()
	{
		if(xActor<700)
			xActor=xActor+30;
		moveBox(xActor,yActor);
	}
	public void changeLayoutUp()
	{
		yActor=yActor-30;
		moveBox(xActor,yActor);
		
	}
	public void changeLayoutDown()
	{
		if(yActor<580)
			yActor=yActor+50;
		moveBox(xActor,yActor);
	
	}
	public void changeSpace()
	{
		Thread thx=new Thread(new Rocket(frame,panel,xActor,yActor));
		thx.start();
		 Clip clip;
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("fuzee.wav")));
			clip.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			
			e.printStackTrace();
		}
	}
	private void changeShift() {
		if(bomb==true){
			Thread thy=new Thread(new Rocket(frame,panel,xActor,yActor,7));
			thy.start();
			
			 Clip clip;
				try {
					clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(new File("bomb.wav")));
					clip.start();
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					
					e.printStackTrace();
				}
				bomb=false;
				panel.remove(label13);
		}
	}
	public void checkOver(){
		
		if(gameover==true){
			
			if(number==11){
				
				time1.stop();time2.stop();
				label11.setIcon(null);panel.remove(label11);
				frame.add(panel);
				Thread t1 = new Thread(new Explosion(panel,frame,xActor,yActor,3));  
				t1.start();
				
				frame.dispose();
				JFrame finish = new JFrame("GAME OVER");
				Icon over=new ImageIcon(getClass().getResource("gameover.jpg"));
				JLabel labl=new JLabel(over);
				JLabel your=new JLabel();
			
				your.setBounds(100, 150, 610, 610);
				your.setForeground(Color.WHITE);
				your.setFont(new Font("0", Font.BOLD, 50));
				your.setText("YOUR SCORE: "+Score);
				user.setScore(Score);
				finish.add(your);
				
				if(Score>this.getHigh_score()){
					Icon newhighh=new ImageIcon(getClass().getResource("High_scoree.png"));
					JLabel newhigh=new JLabel(newhighh);
					newhigh.setBounds(500, 250, 290, 250);
					finish.add(newhigh);
				}
			
				finish.add(labl);
				finish.setSize(800,700);
				finish.setVisible(true);
				finish.setLocationRelativeTo(null);
				finish.setResizable(false);
				frame.setVisible(false);
			}
			else{
				
				label12.setIcon(null);panel.remove(label12);
				number=number+1;
				bar=new ImageIcon(getClass().getResource("can"+getNumber()+".jpg"));label12=new JLabel(bar);
				label12.setBounds(60,605,189,31);panel.add(label12);
				
				gameover=false;
			}	
		}
	}
	
	public static int getHigh_score() {
		return high_score;
	}
	public static void setHigh_score(int high_score) {
		Ship.high_score = high_score;
	}
	public static int getScore() {
		return Score;
	}
	public static void setScore(int score) {
		Score = score;
	}
	public void setX(int x) {
		this.x = x;
	}
	public static int getNumber() {
		return number;
	}
	public static Rectangle getLabel() {
		return label;
	}
	public static int getOk() {
		return ok;
	}
	public static void setOk(int ok) {
		Ship.ok = ok;
	}

	public static int getOk2() {
		return ok2;
	}
	public static void setOk2(int ok2) {
		Ship.ok2 = ok2;
	}
	public static int getOk3() {
		return ok3;
	}
	public static void setOk3(int ok3) {
		Ship.ok3 = ok3;
	}
	public static int getDiff() {
		return diff;
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource()==time2){
			bomb=true;
			panel.add(label13);
		}
		else{
			if(ok!=1 && ok2!=1 && ok3!=1){
	
				count++;
				Thread aa=new Thread(new Jet(frame,panel,getDiff()));
				aa.start();
				
			}
			if (count==3){
				count--;
				count2++;
				Thread enemy=new Thread(new Enemy(frame,panel,getDiff()));
				enemy.start();
			}
			if(count2==4){
				count2=count2-2;
				count3++;
				count4++;
				count5++;
				Thread tank=new Thread(new Tank(frame,panel));
				tank.start();
				
			}
			if(count3==3){
				count3++;
				ok=1;
				Thread th=new Thread(new Boss(frame,panel));
				th.start();
			}
			
			if(count4==6){
				count4++;
				ok2=1;
				Thread thh=new Thread(new Boss(frame,panel));
				thh.start();
			}
			if(count5==9){
				count5++;
				ok3=1;
				Thread thhh=new Thread(new Boss(frame,panel));
				thhh.start();
			}
		}
	}
	}

