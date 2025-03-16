package defaultpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Rectangle;

public class player implements Runnable, KeyListener {
	//x and y cords for player
	private double x, y;
	//player id(1 = player 1, 2 = player 2)
	private int id;
	//degree of the player
	private double Deg = 0;
	//the amount of degree change per keyboard imput
	private double DegVel1;
	private double DegVel2;
	//the speed of the players moving forward
	private double velX = 2.5;
	private double velY = 2.5;
	//players width and height
	private double playerWidth = 100;
	private double playerHeight = 100;
	//variable to store which key is pressed
	private int key;
	//boolean variables for each key to know that they are pressed(true = pressed, false = released)
	//we need this to get input when there is 2 keys pressed at once
	private boolean left, right, D, A, G, shift;
	//arraylist with type bullet to keep track of bullets
	private ArrayList<Bullet> bullet = new ArrayList<Bullet>();
	//bullet object to store the values of the bullet arraylist
	private Bullet b;
	//long objects to create a cool down for shooting bullets
	private long lastAttack = 0;
	private long cooldown = 500;
	//dimension object to get the screen size
	private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	//label object to use when painting the planes
	JLabel label;
	//image for plane
	Image plane;
	//resized image of plane
	Image newImage;

	/**
	 * Purpose: to initialize the player and set its starting point and the players id
	 * 
	 * @param x for x and y for x and y cords, id for player id
	 */
	public player(double x, double y, int id) {
		this.x = x;
		this.y = y;
		this.id = id;
		
		//assign the correct image for player 1 and 2
		if (id == 1) {

			plane = new ImageIcon(this.getClass().getResource("/plane1Cropped.png")).getImage();
			newImage = plane.getScaledInstance((int) playerWidth, (int) playerHeight, Image.SCALE_DEFAULT);

		}
		if (id == 2) {

			plane = new ImageIcon(this.getClass().getResource("/plane2Cropped.png")).getImage();
			newImage = plane.getScaledInstance((int) playerWidth, (int) playerHeight, Image.SCALE_DEFAULT);
		}

	}

	/**
	 * Purpose: to return the mid point of the x cord of the plane
	 * 
	 * @return mid point of the x cord of the plane
	 */
	public double getMidX() {
		return (newImage.getWidth(label) / 2) + x;
	}
	/**
	 * Purpose: to return the mid point of the x cord of the plane
	 * 
	 * @return mid point of the x cord of the plane
	 */
	public double getMidY() {
		return (newImage.getHeight(label) / 2) + y;
	}
	

	/**
	 * Purpose: to get the bounds of the player rectangle
	 * this is going to be used as the players hit box
	 * 
	 * @return the bounds of the player rectangle
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) playerWidth, (int) playerHeight);
	}

	/**
	 * Purpose: to draw the players and rotate them
	 * 
	 */
	public void draw(Graphics g) {

		Graphics2D g2D = (Graphics2D) g;
		//going the same why method to makes plane fly in the way they are facing
		goingTheSameWay();
		//rotate plane
		g2D.rotate(Math.toRadians(Deg), (newImage.getWidth(label) / 2) + x, (newImage.getHeight(label) / 2) + y);
		//draw plane
		g2D.drawImage(newImage, (int) x, (int) y, null);
		//reset the rotation so that the plane is the only thing rotating
		g2D.rotate(-Math.toRadians(Deg), (newImage.getWidth(label) / 2) + x, (newImage.getHeight(label) / 2) + y);

		//run a for loop through the bullet arraylist to draw the and make sure they are going the same way
		for (int i = 0; i < bullet.size(); i++) {

			b = bullet.get(i);
			b.goingTheSameWay();
			g2D.rotate(Math.toRadians(b.getDeg()), (newImage.getWidth(label) / 2) + b.getX(),
					(newImage.getHeight(label) / 2) + b.getY());
			g2D.drawImage(b.getImage(), (int) ((newImage.getWidth(label) / 2) + b.getX()),
					(int) ((newImage.getHeight(label) / 2) + b.getY()), null);
			g2D.rotate(-Math.toRadians(b.getDeg()), (newImage.getWidth(label) / 2) + b.getX(),
					(newImage.getHeight(label) / 2) + b.getY());
		}
	}

	/**
	 * Purpose: to make the players go the same way the are facing
	 * used formula x = rcos(theta), y = rsin(theta)
	 * 
	 */
	public void goingTheSameWay() {
		x += velX * Math.cos(Math.toRadians(Deg));
		y += velY * Math.sin(Math.toRadians(Deg));

	}
	/**
	 * Purpose: to make the player shoot bullets
	 * 
	 */
	public void shoot() {

		//add a new bullet object to bullet array
		bullet.add(new Bullet(x, y, Deg));
	}

	/**
	 * Purpose: to return the bullet arraylist
	 * @return bullet arrayList
	 * 
	 */
	public ArrayList<Bullet> getBullets() {
		return bullet;
	}

	/**
	 * Purpose: to detect if any keys are pressed and set the boolean variables to true or false
	 * depending on the state of the key
	 * (inherited abstract method from KeyListener)
	 * 
	 */
	public void keyPressed(KeyEvent e) {
		//get the keycode of the key pressed
		key = e.getKeyCode();
		//set boolean values according to which key was pressed
		if (key == KeyEvent.VK_LEFT)
			left = true;
		if (key == KeyEvent.VK_RIGHT)
			right = true;
		if (key == KeyEvent.VK_D)
			D = true;
		if (key == KeyEvent.VK_A)
			A = true;
		if (key == KeyEvent.VK_G)
			G = true;
		if (key == KeyEvent.VK_SHIFT)
			shift = true;
		//set degVel to 2 since the plane should move when the keys are pressed
		DegVel1 = 2;
		DegVel2 = 2;

	}
	/**
	 * Purpose: to detect if any keys are released and set the boolean variables to true or false
	 * depending on the state of the key
	 * (inherited abstract method from KeyListener)
	 * 
	 */
	public void keyReleased(KeyEvent e) {
		//get the code of the key that is released
		key = e.getKeyCode();
		//set boolean variables according to which key was pressed
		//set degVel to 0 since plane has to stop rotating if key is released
		if (key == KeyEvent.VK_LEFT) {
			left = false;
			DegVel1 = 0;
		}
		if (key == KeyEvent.VK_RIGHT) {
			right = false;
			DegVel1 = 0;
		}
		if (key == KeyEvent.VK_D) {
			D = false;
			DegVel2 = 0;
		}
		if (key == KeyEvent.VK_A) {
			A = false;
			DegVel2 = 0;
		}
		if (key == KeyEvent.VK_G) {
			G = false;

		}
		if (key == KeyEvent.VK_SHIFT) {
			shift = false;

		}

	}

	/**
	 * Purpose: the code will run if we create a new thread and start it
	 * (inherited abstract method from Runnable)
	 * 
	 */
	@Override
	public void run() {

		//if plater id is 1, use arrow keys to move and shift to shoot
		if (id == 1) {
			if (right) {
				Deg += DegVel1;

			}
			if (left) {
				Deg -= DegVel1;
			}
			if (shift) {
				long time = System.currentTimeMillis();
				if (time > (lastAttack + cooldown)) {
					shoot();
					lastAttack = time;

				}
			}
		}
		//if plater id is 2, use wasd keys to move and g to shoot

		if (id == 2) {
			if (D) {
				Deg += DegVel2;
			}
			if (A) {
				Deg -= DegVel2;
			}
			if (G) {
				long time = System.currentTimeMillis();
				if (time > (lastAttack + cooldown)) {
					shoot();
					lastAttack = time;

				}

			}
		}
		//plane cannot go outside of screen
		// y = 900, x = 1800
		if (x <= 0)
			x = 0;
		if (x >= (int) size.getWidth() - 100)
			x = (int) size.getWidth() - 100;
		if (y <= 0)
			y = 0;
		if (y >= (int) size.getHeight() - 150)
			y = (int) size.getHeight() - 150;
	}

	//We do not use keyTyped
	@Override
	public void keyTyped(KeyEvent e) {

	}
}
