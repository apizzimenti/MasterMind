import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Driver extends JFrame {
	
	private JTextArea area = new JTextArea();
	private Container container = getContentPane();
	
	public static void main(String[]args) throws IOException {
		Driver x = new Driver();
		x.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public Driver() throws IOException {
		super.setVisible(true);
		super.setSize(500, 350);
		area.setEditable(false);
		container.add(area);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		super.setLocation((int)(dim.getWidth() / 2) - 250, 0);
		
		Reader x = new Reader();
		String fw = x.getWord();
		int count = 0;
		
		for (int i = 0; i < 10; i++) {
			String tester = JOptionPane.showInputDialog(null, "Guess away!", "MasterMind", JOptionPane.INFORMATION_MESSAGE);
			
			if (tester.length() > 4) {
				throw new IOException("Too long, try again!");
			}
			
			String best = x.analyze(tester);
			
			if (tester.equals(fw)) {
				x.editPoints(18);
				break;
			} else {
				x.editPoints(10);
				area.append(best);
			}
			area.append("\t" + x.getPoints() + "\n");
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
	}
}