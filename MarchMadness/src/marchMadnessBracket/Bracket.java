package marchMadnessBracket;

import java.util.HashMap;
import java.util.Map;

public class Bracket {

	final public static int rounds = 4;

	HashMap<String,Region> regions;
	Matchup[] finals;
	public Bracket() {
		regions = new HashMap<String,Region>();
		regions.put("East", new Region("East"));
		regions.put("West", new Region("West"));
		regions.put("South", new Region("South"));
		regions.put("Midwest", new Region("Midwest"));
		finals=new Matchup[3];
		
	}
	

	public String toString() {
		String printBracket = "";
		for(int round=0;round<rounds;round++) {
			for(Map.Entry<String,Region> entry:regions.entrySet()) {
				Region region=entry.getValue();
				printBracket = printBracket + region.toString(round+1);
			}
		}
		
		/*for(Matchup game: finals) {
			printBracket = printBracket + game + "\n\n";
		}*/
		return printBracket;
	}
	
	
}