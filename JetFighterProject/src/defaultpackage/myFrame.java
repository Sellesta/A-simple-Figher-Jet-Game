package defaultpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

//JFrame class, has main method
public class myFrame extends JFrame implements ActionListener {
	// button for resetting the game
	JButton resetButton;
	// gameManager object
	gameManager gm;

	/**
	 * Purpose: constructor set up the Jframe and add all the attributes to the game
	 * into the JFrame
	 * 
	 * 
	 */
	myFrame() {
		gm = new gameManager();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(gm);

		this.pack();
		this.setLocationRelativeTo(null);
		resetButton = new JButton();
		resetButton.setText("Play Again!");
		resetButton.setSize(100, 50);
		resetButton.addActionListener(this);
		resetButton.setFocusable(false);
		gm.add(resetButton);

		this.setVisible(true);
	}

	// main method
	public static void main(String[] args) {
		new myFrame();
	}

	/**
	 * Purpose: to return all the info in string
	 * 
	 * @param ActionEvent e that checks if the button is pressed
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// if the actions source is the reset button, reset the game
		if (e.getSource() == resetButton) {
			this.remove(gm);
			gm = new gameManager();
			this.add(gm);
			gm.setFocusable(true);
			gm.add(resetButton);

		}
	}

}
