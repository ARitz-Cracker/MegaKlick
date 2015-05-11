//GameShape.java a JButton with a few more functions useful to the game.
package megak;

import javax.swing.JButton;

public class GameShape extends JButton {
	int id;
	boolean correct;

	public void SetID(int val) {
		id = val;
	}

	public int GetID() {
		return id;
	}

	public void SetCorrect(boolean val) {
		correct = val;
	}

	public boolean IsCorrect() {
		return correct;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8034845604833830878L;

}
