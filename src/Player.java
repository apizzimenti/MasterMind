// David Wu and Anthony Pizzimenti
//
// Player class for insertion into the leaderboard

import javax.swing.JOptionPane;

public final class Player {

	private final int score;
	private String name;

	public Player(int s){
		score = s;
		setName();
	}

	public Player(int s, String n){
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
