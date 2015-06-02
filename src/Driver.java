import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.IOException;

public class Driver extends JFrame {

	private JTextArea area = new JTextArea();
	private Container container = getContentPane();

	public static void main(String[]args) throws IOException {
		Driver x = new Driver();
		x.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public Driver() throws IOException {
		super.setVisible(true);
		super.setSize(500, 600);
		area.setEditable(false);
		container.add(area);
		area.append("Rules:\n1. only four-letter guesses are permitted;");
		area.append("\n2. win the game at all costs;\n");
		area.append("3. X's represent a letter in the correct spot;\n");
		area.append("4. O's represent a letter in the wrong spot;\n");
		area.append("5. blanks represent a letter that isn't in the word.\n");

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		super.setLocation((int)(dim.getWidth() / 2) - 250, 0);

		Reader x = new Reader();
		String fw = x.getWord();
		int count = 0;

		for (int i = 0; i < 10; i++) {
			if (i > 7) {
				JOptionPane.showMessageDialog(null, "You only have " +
						(10 - i) + " guess(es) remaining!", "MasterMind",
						JOptionPane.INFORMATION_MESSAGE);

			}

			String tester = JOptionPane.showInputDialog(null, "Guess away!",
					"MasterMind", JOptionPane.INFORMATION_MESSAGE);


			if (tester == null) {
				throw new IOException("Quitting already?");
			} else if (tester.equals("")) {
				throw new IOException("You didn't put anything in!");
			} else if (tester.length() < 4 || tester.length() > 4) {
				throw new IOException("Try again!");
			}

			String best = x.analyze(tester);

			if (tester.equals(fw)) {
				break;
			} else {
				x.editPoints(10);
				area.append(tester + "\t" + best + "\n");
			}

			count += 1;
		}

		if (count == 0) {
			area.append("\nWow, first try!");
			area.append("\nYou got " + x.getPoints() + " points!");
		} else if (count == 10){
			area.append("\nThe word you were looking for was " + fw + ". ");
			area.append("\nYou only got " + x.getPoints() + " points.");
		} else {
			area.append("\nNice work, you got " + x.getPoints() + " points.");
		}

		//Player finder = new Player(x.getPoints());
		Top10 last = new Top10();
		if(last.enoughSpace() == true){
			area.append("\n\nCongrats you aren't very good but you got in anyways.\n");
			Player finder = new Player(x.getPoints());
			last.insertPlayer(finder);
			last.printList();
			last.updateFile();
		}

		else if(last.getIn(x.getPoints()) == true){
			area.append("\n\nCongrats you got into the leaderboard!\n");
			Player finder = new Player(x.getPoints());
			last.insertPlayer(finder);
			last.deleteExcessPlayers();
			last.updateFile();
		}

		else if(last.getIn(x.getPoints()) == false){
			area.append("\nSorry you did not get in the leaderboard.\n");
		}

		int dec = JOptionPane.showConfirmDialog(null, "Would you like to see the"
				+ " leaderboard?", "MasterMind", JOptionPane.YES_NO_OPTION);

		if (dec == JOptionPane.YES_OPTION){
			area.append("\nLEADERBOARD:\n");
			area.append("Name\tScore\n");
			area.append("-------\t-------\n");
			ArrayList<Player> dis = last.getList();
			for(int i = 0; i < dis.size(); i++){
				Player out = dis.get(i);
				area.append(out.getName()+":\t"+out.getScore()+"\n");
			}
		}

		if (dec == JOptionPane.NO_OPTION){
			area.append("That's too bad.");
		}
	}
}