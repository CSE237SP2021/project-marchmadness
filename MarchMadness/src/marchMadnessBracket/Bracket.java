package marchMadnessBracket;

import java.util.HashMap;
import java.util.Map;

public class Bracket {

	final public static int rounds = 4;

	HashMap<String,Region> regions;
	Matchup[] finalFour;
	Matchup championship;
	Team winner;
	
	public Bracket() {
		regions = new HashMap<String,Region>();		
		regions.put("West", new Region("West"));
		regions.put("East", new Region("East"));
		regions.put("South", new Region("South"));
		regions.put("Midwest", new Region("Midwest"));
		finalFour=new Matchup[2];
		championship = new Matchup();
		winner = new Team();
	}
	

	public String toString() {
		String printBracket = "";
		for(int round=0;round<rounds-1;round++) {
			/*for(Map.Entry<String,Region> entry:regions.entrySet()) {
				Region region=entry.getValue();
				printBracket = printBracket + region.toString(round+1);
			}*/
			
			Region region= regions.get("West");
			printBracket = printBracket + region.toString(round+1);
			region= regions.get("East");
			printBracket = printBracket + region.toString(round+1);
			region= regions.get("South");
			printBracket = printBracket + region.toString(round+1);
			region= regions.get("Midwest");
			printBracket = printBracket + region.toString(round+1);
		}
		
		printBracket = printBracket + "FinalFour: \n"; 
		for(Matchup game: finalFour) {
			
			printBracket = printBracket + game + "\n\n";
		}
		
		printBracket = printBracket + "Championship: \n";
		printBracket = printBracket + championship + "\n\n";
		printBracket = printBracket + "Winner: " + winner.getTeamname();
		return printBracket;
	}
	
}