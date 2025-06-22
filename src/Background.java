

import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Background extends Ship{

	private static JFrame frame;
	private JPanel panel;
	private JLabel label,highLabel,highscore;
	private JLabel label2,label3,label4,label5;
	private static Icon actor,score,kalp,star,highlogo;
	private int y;
	private int x;
	public Background(JFrame frame, JPanel panel) {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame=frame;
		this.panel=panel;
		if(getDiff()==1)
			actor=new ImageIcon(getClass().getResource("arkaplan.jpg"));
		else if(getDiff()==2)
			actor=new ImageIcon(getClass().getResource("yeni12.jpg"));
		score=new ImageIcon(getClass().getResource("score2.png"));
		kalp=new ImageIcon(getClass().getResource("kalp.png"));
		star=new ImageIcon(getClass().getResource("star.png"));
		highlogo=new ImageIcon(getClass().getResource("highscore.png"));
		y=0;
		label = new JLabel(actor);label2 = new JLabel(actor);
		label3= new JLabel(kalp);label5 = new JLabel(star);
		label4=new JLabel(score);
		label.setBounds(x, y, 800, 12690);
		label2.setBounds(x, y-12690, 800, 12690);
		label3.setBounds(7,600,50,50);
		label4.setBounds(500,50,100,42);label5.setBounds(20, 20, 90, 90);
		panel.add(label4);panel.add(label3);panel.add(label5);
		
		highLabel = new JLabel();highscore=new JLabel(highlogo);
		highLabel.setBounds(110, 10, 150, 130);highscore.setBounds(100, 20, 150, 31);
		highLabel.setForeground(Color.YELLOW);
		highLabel.setFont(new Font("", Font.ROMAN_BASELINE, 60));
		highLabel.setText(""+getHigh_score());
		panel.add(highLabel);panel.add(highscore);
		frame.add(panel);
		
	}

	public void update() {
		
		label.setBounds(x, y, 800,12690);
		label2.setBounds(x, y-12690, 800, 12690);;

			if(y==12690){
			
				y=0;
				label.setBounds(x, y, 800, 12690);
				label2.setBounds(x, y-12690, 800, 12690);
				
			}
			
		panel.add(label);panel.add(label2);
		frame.add(panel);
		
		y++;	
	}
}
