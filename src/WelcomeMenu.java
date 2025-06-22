

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class WelcomeMenu implements ActionListener {
	JFrame welcome= new JFrame("-----HEY!   WELCOME!-----");
	Icon picture= new ImageIcon(getClass().getResource("bluea.jpg"));
	JLabel label= new JLabel(picture);
	JButton button1 = new JButton("START-INTERMEDIATE");
	JButton button11 = new JButton("START-NOVICE");
	JButton button2 = new JButton("SCORES");
	JButton button3 = new JButton("QUIT");
	JTextField username;
	JPasswordField password;
	JMenuBar menuBar;
	JMenu FileMenu,HelpMenu;
	JMenuItem RegisterItem;
	JMenuItem PlayItem,ScoreItem,QuitItem,CreditItem;
	static int high_score,high;
	static ArrayList<User> Users = new ArrayList<User>();
	
	WelcomeMenu(){
		high_score=0;
		label.setSize(800, 700);
		welcome.setBounds(0,0,800, 700);
		welcome.add(label);
		welcome.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		welcome.setLocationRelativeTo(null);
		welcome.setVisible(true);
		welcome.setResizable(false);
		
		menuBar = new JMenuBar();
		   
	    FileMenu = new JMenu("File");
	    RegisterItem = new JMenuItem("Register");
	    PlayItem = new JMenuItem("Play Game");
	    ScoreItem = new JMenuItem("ScoreBoard");
	    QuitItem = new JMenuItem("Quit");
	    
	    
	    RegisterItem.addActionListener(this);
	    PlayItem.addActionListener(this);
	    ScoreItem.addActionListener(this);
	    QuitItem.addActionListener(this);
	    FileMenu.add(RegisterItem);
	    FileMenu.add(PlayItem);
	    FileMenu.add(ScoreItem);
	    FileMenu.add(QuitItem);

	  
	    HelpMenu = new JMenu("Help");
	    CreditItem = new JMenuItem("Credits");
	    CreditItem.addActionListener(this);
	    HelpMenu.add(CreditItem);

	    menuBar.add(FileMenu);
	    menuBar.add(HelpMenu);

	    welcome.setJMenuBar(menuBar);
	    
	    button1.addActionListener(this); button11.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
	
		button1.setBackground(Color.red);
		button1.setFont(new Font("Arial", Font.BOLD, 14));button1.setBounds(195,300,200,80);
		label.add(button1);
		button11.setBackground(Color.GRAY);
		button11.setFont(new Font("Arial", Font.BOLD, 14));button11.setBounds(405,300,200,80);
		label.add(button11);
		button2.setBackground(Color.green);
		button2.setFont(new Font("Arial", Font.PLAIN, 25));button2.setBounds(260,402,252,80);
		label.add(button2);
		button3.setBackground(Color.CYAN);
		button3.setFont(new Font("Arial", Font.PLAIN, 25));button3.setBounds(260,502,252,80);
		label.add(button3);
		welcome.add(label);
		
	}
	public static void main(String[] args){
		
		WelcomeMenu welcomemenu=new WelcomeMenu();
		try{
			
			File myfile=new File("ScoresFile.txt");
			FileInputStream file=new FileInputStream(myfile);
			ObjectInputStream stream =new ObjectInputStream(file);
			
			while(true){
				try{
					User obj =(User)stream.readObject();
					Users.add(obj);
				}
				catch(EOFException b){
					
					break;
				}
			}
			stream.close();
		}
		catch(Exception b){
			
		}
		Clip clips;
		try {
			clips = AudioSystem.getClip();
			clips.open(AudioSystem.getAudioInputStream(new File("baslangic.wav")));
			clips.loop(15);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<Users.size(); i++){
			
			if(Users.get(i).getScore()>high_score){
				high_score=Users.get(i).getScore();
				high=i;
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==RegisterItem){
			
			JPanel user = new JPanel();
	        user.setLayout(new GridLayout(5,5));
	        JLabel label=new JLabel("LOGIN:");
	        JLabel usernameLabel = new JLabel("Username:");
	        JLabel passwordLabel = new JLabel("Password:");
	        JTextField username = new JTextField();
	        JPasswordField password = new JPasswordField();
	        
	        user.add(label);
	        user.add(usernameLabel);
	        user.add(username);
	        user.add(passwordLabel);
	        user.add(password);
	        int ok = JOptionPane.showConfirmDialog(welcome, user, "LOGIN :"
                    ,JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        
	        if (ok==0){
	        	
	        	char[] Password = password.getPassword();
	        	User userr=new User(username.getText(),Password);
	        	Users.add(userr);
	        }
	        for(int i=0; i<Users.size();i++){
	        	System.out.println(Users.get(i).getUsername()+"-----"+Users.get(i).getPassword());
	        	
	        }
		}
		
		else if(e.getSource()==PlayItem || e.getSource() == button1){
			
			JPanel user = new JPanel();
	        user.setLayout(new GridLayout(5,5));
	        JLabel label=new JLabel("SIGN IN:");
	        JLabel usernameLabel = new JLabel("Please Enter your Username:");
	        JLabel passwordLabel = new JLabel("Please Enter your Password:");
	        JTextField username = new JTextField();
	        JPasswordField password = new JPasswordField();
	        
	        user.add(label);
	        user.add(usernameLabel);
	        user.add(username);
	        user.add(passwordLabel);
	        user.add(password);
	        int ok = JOptionPane.showConfirmDialog(welcome, user, "SIGN IN :"
                    ,JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        
	        if (ok==0){
	        	int okk=0;
	        	char[] Password = password.getPassword();
	        	String name =username.getText();
	        	for(int i=0; i<Users.size(); i++){
	        		if(Users.get(i).getUsername().equals(name)&& Arrays.equals(Password, Users.get(i).getPassword())){
	        			Game newgame=new Game(Users.get(i),high_score,1);
	        			newgame.game();
	        			okk=1;
	        		}
	        	}
	        	if(okk!=1){
        			JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
	        	}
	        }
		}else if(e.getSource()==button11){
			JPanel user = new JPanel();
	        user.setLayout(new GridLayout(5,5));
	        JLabel label=new JLabel("SIGN IN:");
	        JLabel usernameLabel = new JLabel("Please Enter your Username:");
	        JLabel passwordLabel = new JLabel("Please Enter your Password:");
	        JTextField username = new JTextField();
	        JPasswordField password = new JPasswordField();
	        
	        user.add(label);
	        user.add(usernameLabel);
	        user.add(username);
	        user.add(passwordLabel);
	        user.add(password);
	        int ok = JOptionPane.showConfirmDialog(welcome, user, "SIGN IN :"
                    ,JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        
	        if (ok==0){
	        	int okk=0;
	        	char[] Password = password.getPassword();
	        	String name =username.getText();
	        	for(int i=0; i<Users.size(); i++){
	        		if(Users.get(i).getUsername().equals(name)&& Arrays.equals(Password, Users.get(i).getPassword())){
	        			Game newgame=new Game(Users.get(i),high_score,2);
	        			newgame.game();
	        			okk=1;
	        		}
	        	}
	        	if(okk!=1){
        			JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
	        	}
	        }
		}
		else if(e.getSource()==ScoreItem || e.getSource() == button2){
			
			for(int i=0; i<Users.size(); i++){
			
				if(Users.get(i).getScore()>high_score){
					System.out.println("girdi");
					high_score=Users.get(i).getScore();
					high=i;
				}
			}
			String name;
			int score;
			
			JTextArea textArea = new JTextArea("\tUsername :"+"\t\t"+"Score :\t"+"\n\n");
			textArea.setFont(new Font("Serif", Font.BOLD, 16)); 
			textArea.setEditable(false);
			textArea.append("\t---HIGH SCORE---\t\t"+"\n"+"\t"+Users.get(high).getUsername()
					+" \t "+Users.get(high).getScore()+"\t\t\n\n");

			for(int i=0; i<Users.size(); i++){
				name=Users.get(i).getUsername();
				score=Users.get(i).getScore();
				textArea.append("\t"+name+"\t\t"+score+"\t"+"\n");
			}
			
			JOptionPane.showConfirmDialog(welcome, textArea, "SCORES :"
                    ,JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
			
		}
		else if(e.getSource()==QuitItem || e.getSource() == button3){
			
			try{
				File myfile=new File("ScoresFile.txt");
				FileOutputStream file=new FileOutputStream(myfile);
				ObjectOutputStream stream =new ObjectOutputStream(file);
				
				for(int i=0; i<Users.size(); i++){
					stream.writeObject(Users.get(i));	
				}
				stream.close();
				
			}
			catch(Exception a){
				a.printStackTrace();
			}
			
			System.exit(0);
		}else if(e.getSource()==CreditItem){
			JTextArea textArea = new JTextArea("\t\t"+"CREDITS :"+"\n\n");
			textArea.setFont(new Font("Serif", Font.ITALIC, 20)); 
			textArea.setEditable(false);
			
			textArea.append("     Welcome to my Game.In this game you will pilot a blue warcraft named Aspasia.\t"+"\n");
			textArea.append("This game has three different enemy vehicles.Two of them are enemycrafts and one of them is a tank.\t"+"\n");
			textArea.append("Also we have three different boss ships in this game.\t"+"\n");
			textArea.append("You can control your warcraft with the ----DIRECTION----- keys \t"+"\n");
			textArea.append("When you press the ----SPACE---- key your craft will fire beams.\t"+"\n");
			textArea.append("When you press the ----SHIFT---- key your craft will fire bombs\t"+"\n");
			textArea.append("\tARE YOU READY FOR THIS ADVENTURE?\t"+"\n");
			JOptionPane.showConfirmDialog(welcome, textArea, "CREDITS :"
                    ,JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
			
		}
		
	}

}