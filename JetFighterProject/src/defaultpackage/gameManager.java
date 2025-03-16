package defaultpackage;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.Rectangle;

public class gameManager extends JPanel implements ActionListener {
	// Dimensions for the screen
	private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	// timer to control how fast the planes move with delay
	private Timer timer;
	// players 1 and 2
	private player p1;
	private player p2;
	// player Health points
	private int p1HP = 5;
	private int p2HP = 5;
	// how much damage the players do
	private int p1Dmg = 1;
	private int p2Dmg = 1;
	// Jlabel for planes and the background image
	JLabel label, background;
	// background image
	Image space = new ImageIcon(this.getClass().getResource("/space.jpg")).getImage();
	// modify the background image size to fit the screen
	Image newSpace = space.getScaledInstance((int) size.getWidth(), (int) size.getHeight(), Image.SCALE_DEFAULT);

	/**
	 * Purpose: to create and manage the basic stuffs in the game such as players
	 * starting points, JLabel for players health points, starting the timer setting
	 * the layout to flowLayout, adding key listener to players and finally, setting
	 * the focasable to true to that the keyListeners will work
	 */
	gameManager() {
		p2 = new player(0, 0, 2);
		p1 = new player(1000, 800, 1);
		label = new JLabel("Player 2 HP: " + p2HP + "     ||      Player 1 HP: " + p1HP);

		this.setPreferredSize(new Dimension(600, 600));
		// this.setBackground(Color.BLACK);
		timer = new Timer(1, this);
		timer.start();
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		label.setForeground(Color.WHITE);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(label, BorderLayout.PAGE_START);
		addKeyListener(p1);
		addKeyListener(p2);
		setFocusable(true);
	}

	/**
	 * Purpose: to paint everything in the game
	 * 
	 * @param Graphics g, which is the image to draw
	 * 
	 * (method inherited from JComponent)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(newSpace, 0, 0, null);
		// draw players 1 and 2
		p1.draw(g);
		p2.draw(g);
		//create threads for player 1 and 2
		Thread player1 = new Thread(p1);
		Thread player2 = new Thread(p2);
		//start threads for player 1 and 2
		player1.start();
		player2.start();

		//check if the bullets hit the players
		checkCollision();
		//determine the winner of the game by checking the players health points
		if (p1HP <= 0) {
			Winner(2);
		} else if (p2HP <= 0) {
			Winner(1);
		}
	}

	
	/**
	 * Purpose: to check if the bullets have hit the players
	 */
	public void checkCollision() {
		//get the bounds of the players(width, height)
		Rectangle player1 = p1.getBounds();
		Rectangle player2 = p2.getBounds();
		//run through the bullets with a for loop and see if any of them hit the players
		
		for (int i = p2.getBullets().size() - 1; i >= 0; i--) {
			Rectangle Bullet = p2.getBullets().get(i).getBounds();
			//if the bullet hit the player, remove the bullet
			//substract the damage from player 1s health point and show it on the label
			if (player1.intersects(Bullet)) {
				p2.getBullets().remove(i);
				p1HP -= p2Dmg;
				label.setText("Player 2 HP: " + p2HP + "     ||      Player 1 HP: " + p1HP);
			}

		}
		
		//same thing but for player 2 
		for (int i = p1.getBullets().size() - 1; i >= 0; i--) {
			Rectangle Bullet = p1.getBullets().get(i).getBounds();
			if (player2.intersects(Bullet)) {
				p1.getBullets().remove(i);
				p2HP -= p1Dmg;
				label.setText("Player 2 HP: " + p2HP + "     ||      Player 1 HP: " + p1HP);

			}

		}

	}

	/**
	 * Purpose: to return the winner of the game
	 * 
	 * @param int variable to know which player won, 1 = player 1, 2 = player 2
	 */
	public void Winner(int winner) {
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 54));
		label.setText("GAME OVER, PLAYER " + winner + " WINS");
	}


	/**
	 * Purpose: to repaint so that the players can see the planes moving and what is happening
	 * 
	 * (method implemented from actionlistener)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
