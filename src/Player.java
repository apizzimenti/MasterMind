
import javax.swing.JOptionPane;

/**
 * constructs the Player objects, which are loaded into an ArrayList
 * and compiled for use in the top10 list
 * @author Anthony Pizzimenti
 */

public final class Player {

	private final int score;
	private String name;

	public Player(int s) {
        /**
         * Parametric constructor, invokes setName()
         * @param s final score
         */
        
		score = s;
		setName();
	}

	public Player(int s, String n) {
        /**
         * Parametric constructor invoked after setName() is called
         * @param s final score
         * @param n name
         */
        
		score = s;
		name = n;
	}

	public void setName(){
		name = JOptionPane.showInputDialog(null, "What's your name?",
				"MasterMind", JOptionPane.INFORMATION_MESSAGE);
	}

	public int getScore(){
		return score;
	}

	public String getName(){
		return name;
	}
}
