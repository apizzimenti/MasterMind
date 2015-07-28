// Anthony Pizzimenti and David Wu
//
// reads words from the source file and determines points

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class Reader {

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
		String last = "";
		int finder = (int)(Math.random() * 50);
        InputStream in = getClass().getResourceAsStream("Words.txt");
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        try {
            for (int i = 0; i < finder; i++) {
                last = input.readLine();
            }
            return last;
            
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }


	public String analyze(String a) {
		String best = "";

		// splits words into arrays, initializes count
		String[] data = word.split("");
		String[] guess = a.split("");
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
			if (sin <= 2) {
				count -= 3;
            } else {
				count -= sin;
            }
		}

		for (int l = 0; l < count; l++) {
			best += "O";
			points += 1;
		}
		return best;
	}
}