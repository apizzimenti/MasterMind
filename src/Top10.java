import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Top10 {

	private ArrayList<Player> top = new ArrayList<Player>();

	public Top10(){
		buildList();
	}

	public void buildList(){
		String match = null;
		try{
			Scanner in = new Scanner(new File("../MasterMind Game/src/top10.txt"));
			while(in.hasNext()){
				match = in.nextLine();
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

	public boolean enoughSpace(){
		if(top.size()<10){
			return true;
		}
		else
			return false;
	}

	public void deleteExcessPlayers(){
		while(top.size()>10){
			top.remove(top.size()-1);
		}
	}

	public void insertPlayer(Player ins){
		for(int i = top.size(); i >= 0; i--){
			if(top.size() == 0){
				top.add(ins);
				break;
			}
			
			if(i == 0){
				top.add(i, ins);
				break;
			}
			
			Player x = top.get(i-1);
			if(x.getScore() > ins.getScore()){
				top.add(i, ins);
				break;
			}
			else if(x.getScore() <= ins.getScore()){
				continue;
			}	
		}
		
	}

	public boolean getIn(int score){
		for(int i = 0; i < top.size(); i++){
			Player x = top.get(i);
			if(x.getScore() > score){
				continue;
			}
			else if(x.getScore() <= score){
				return true;
			}
		}
		return false;
	}

	public void updateFile(){
		try {
			FileWriter writer = new FileWriter(new File("../MasterMind Game/src/top10.txt"));
	        PrintWriter out = new PrintWriter(writer);

	        String file = "";
	        for (int i = 0; i < top.size(); i++) {
	        	Player n = top.get(i);
	        	file += String.format(n.getName()+":\t"+n.getScore()+"%n");
	        }
	        out.println(file);
	        out.close();
	    } catch (IOException except) {
	        throw new RuntimeException(except.toString());
	    }
	}

	public ArrayList<Player> getList(){
		return top;
	}

	public void printList(){
		String file = "";
		for (int i = 0; i < top.size(); i++) {
        	Player n = top.get(i);
        	file += String.format(n.getName()+":\t"+n.getScore()+"%n");
		}
		System.out.print(file);
	}
}