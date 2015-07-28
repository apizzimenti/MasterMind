
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class which describes a top 10 list of players
 * @author David Wu
 */

public class Top10 {
    
    /**
     * @param top ArrayList where player objects are stored
     */

	private final ArrayList<Player> top = new ArrayList<Player>();

	public Top10() {
		buildList();
	}

	public final void buildList() {
		try{
			Scanner in = new Scanner(new File("../MasterMind Game/src/top10.txt"));
			while(in.hasNext()){
				String match = in.nextLine();
				String[] score = match.split(":"+"\\t");
				int sc = Integer.parseInt(score[1]);
				Player build = new Player(sc, score[0]);
				top.add(build);
			}
			in.close();
		} catch (IOException except) {
	        throw new RuntimeException(except.toString());
	    }
	}

	public boolean enoughSpace() {
        return top.size() < 10;
	}

	public void deleteExcessPlayers() {
		while(top.size()>10){
			top.remove(top.size()-1);
		}
	}

	public void insertPlayer(Player ins) {
		for (int i = top.size(); i >= 0; i--) {
			if (top.isEmpty()) {
				top.add(ins);
				break;
			} if (i == 0) {
				top.add(i, ins);
				break;
			}
			
			Player x = top.get(i-1);
			if (x.getScore() > ins.getScore()) {
				top.add(i, ins);
				break;
			}
		}
	}

	public boolean getIn(int score) {
		for (Player x : top) {
			if (x.getScore() <= score) {
				return true;
			}
		}
		return false;
	}

	public void updateFile() {
		try {
			FileWriter writer = new FileWriter(new File("../MasterMind Game/src/top10.txt"));
	        PrintWriter out = new PrintWriter(writer);

	        String file = "";
	        for (Player n : top) {
	        	file += String.format(n.getName()+":\t"+n.getScore()+"%n");
	        }
	        out.println(file);
	        out.close();
	    } catch (IOException except) {
	        throw new RuntimeException(except.toString());
	    }
	}

	public ArrayList<Player> getList() {
		return top;
	}

	public void printList() {
		String file = "";
		for (Player n : top ) {
        	file += String.format(n.getName()+":\t"+n.getScore()+"%n");
		}
		System.out.print(file);
	}
}