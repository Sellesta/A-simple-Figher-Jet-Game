package defaultpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.Rectangle;

public class Bullet {

	//x and y cords for bullet
	private double x;
	private double y;
	//x and y velocity for bullet
	private double velX = 5;
	private double velY = 5;
	//degree of bullet
	private double Deg = 0;
	//dimensions of bullet
	private double bulletWidth = 80;
	private double bulletHeight = 48;

	//image we use for bullet
	Image bullet = new ImageIcon(this.getClass().getResource("/Bullet.png")).getImage();

	//resized image of bullet
	Image newImage = bullet.getScaledInstance((int) bulletWidth, (int) bulletHeight, Image.SCALE_DEFAULT);

	//label we use to draw bullet
	JLabel label;

	/**
	 * Purpose: to instantiate the variables of the bullet
	 * @param x and y for x initial x and y cords of bullet, deg for intitial degree of bullet
	 */

	public Bullet(double x, double y, double Deg) {
		this.x = x;
		this.y = y;
		this.Deg = Deg;
	}

	/**
	 * Purpose: to draw the bullet
	 * @param graphics for the bullet
	 */
	public void draw(Graphics g) {
		g.drawImage(newImage, (int) x, (int) y, null);
	}

	/**
	 * Purpose: to make sure the bullets fire in the same way the planes are facing
	 */
	public void goingTheSameWay() {
		x += velX * Math.cos(Math.toRadians(Deg));
		y += velY * Math.sin(Math.toRadians(Deg));

	}

	/**
	 * Purpose: to get x midpoint of the bullet image
	 * @return x midpoint of the bullet image
	 */
	public double getMidX() {
		return (newImage.getWidth(label) / 2) + x;
	}
	/**
	 * Purpose: to get y midpoint of the bullet image
	 * @return y midpoint of the bullet image
	 */
	public double getMidY() {
		return (newImage.getHeight(label) / 2) + y;
	}

	/**
	 * Purpose: to get x cord of the bullet
	 * @return x cord of bullet
	 */
	public double getX() {
		return x;
	}

	/**
	 * Purpose: to get y cord of the bullet
	 * @return y cord of bullet
	 */
	public double getY() {
		return y;
	}

	/**
	 * Purpose: to get the bullet image
	 * @return bullet image
	 */
	public Image getImage() {
		return newImage;
	}

	/**
	 * Purpose: to get the degree of bullet
	 * @return Degree of bullet
	 */
	public double getDeg() {
		return Deg;
	}

	/**
	 * Purpose: to set the bounds of the bullet hitbox
	 * @return bullet rectangle width and height
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) bulletWidth, (int) bulletHeight);
	}

}
