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
	

	public ArrayList<Matchup> getMatchups(int round) {
		return rounds[round];
	}
	

	public String toString(int round) {
		String printRegion = "";
		if(round>=rounds.length) {
			//System.out.println("Enter a valid round");
			return printRegion;
		}
		printRegion = printRegion + this.name+": Round "+round + "\n"; 
		
		
		
		if(round == 1) {
			int counter = 0;
			for(Matchup game:rounds[round]) {
				counter++;
			}
			Matchup[] gameList = new Matchup[counter];;
			if(round == 2) {
				gameList = new Matchup[counter/2];
			}
			
			counter = 0;
			for(Matchup game:rounds[round]) {
				if(counter < gameList.length) {
					printRegion = printRegion + game + "\n\n";
					counter++;
				}
			}
		}
		else {
			for(Matchup game:rounds[round]) {
				printRegion = printRegion + game + "\n\n";
			}
		}
		return printRegion;

	}
}