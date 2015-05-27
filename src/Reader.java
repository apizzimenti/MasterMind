import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Reader {
	
	private final String word;
	private int points = 110;
	
	public Reader() {
		word = read();
	}
	
	public String getWord() {
		return word;
	}
	
	public void editPoints(int add) {
		points -= add;
	}
	
	public int getPoints() {
		return points;
	}
	
	public String read() {
		String last = null;	
		int finder = (int)(Math.random() * 50);
		File newer = new File("../MasterMind-Game-master/src/Words.txt");
		
		try {
			Scanner in = new Scanner(newer);
			for (int i = 0; i < finder; i++){
					 last = in.nextLine();
			}
			in.close();
		} catch (IOException e) {
			throw new RuntimeException(e.toString());
		}
		return last;
	}

	
	public String analyze(String a) {
		String best = "";
		
		// splits words into arrays
		String [] data = word.split("");
		String [] guess = a.split("");

		// checks letters
		outerloop: for(int i = 0; i < 4; i++){
			if(data[i].equals(guess[i])){
				best += "X";
				points += 2;
				continue;
			}
			for(int j = 0; j < 4; j++){
				if(j == i)
					continue;
				if(data[i].equals(guess[j])){
					best += "O";
					points += 1;
					continue outerloop;
				}
			}
		}
		
		return best;
	}
}