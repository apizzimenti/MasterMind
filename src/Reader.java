import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Reader {

	private final String word;
	private int points = 100;

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
		File newer = new File("../MasterMind Game/src/Words.txt");

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

		// splits words into arrays, initializes count
		String [] data = word.split("");
		String [] guess = a.split("");
		int count = 16;

		// checks same-index similarities
		for (int i = 0; i < data.length; i++) {
			if (data[i].equals(guess[i])) {
				count--;
				best += "X";
				points += 2;
			}
		}

		// checks differing-index similarities
		for (int j = 0; j < data.length; j++) {
			int sin = 0;
			for (int k = 0; k < data.length; k++) {
				if (!(data[j].equals(guess[k]))) {
					sin++;
				}
			}
			if (sin <= 2)
				count -= 3;
			else
				count -= sin;
		}

		for (int l = 0; l < count; l++) {
			best += "O";
			points += 1;
		}

		return best;
	}
}