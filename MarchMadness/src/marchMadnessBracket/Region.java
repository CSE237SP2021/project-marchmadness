package marchMadnessBracket;

import java.util.ArrayList;

public class Region {
	private ArrayList<Matchup>[] rounds;
	private String name;
	
	public Region(String name) {
		rounds=new ArrayList[4];
		for(int i=0; i<rounds.length;i++) {
			rounds[i]=new ArrayList<Matchup>();
		}
		this.name=name;
	}
	
	public void addMatchup(Matchup game,int round) {
		if(round<rounds.length) {
			rounds[round].add(game);
		}
	}
	
	public String toString(int round) {
		String printRegion = "";
		if(round>=rounds.length) {
			//System.out.println("Enter a valid round");
			return printRegion;
		}
		printRegion = printRegion + this.name+": Round "+round + "\n"; 
		for(Matchup game:rounds[round]) {
			printRegion = printRegion + game + "\n\n";
		}
		return printRegion;
	}
}