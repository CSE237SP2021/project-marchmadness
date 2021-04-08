package marchMadnessBracket;

import java.util.HashMap;
import java.util.Map;

public class Bracket {
	int created = 0;
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
	
	public void displayBracket() {
		for(int round=0;round<4;round++) {
			for(Map.Entry<String,Region> entry:regions.entrySet()) {
				Region region=entry.getValue();
				region.printRound(round);
			}
		}
		
		for(Matchup game: finals) {
			System.out.println(game);
			System.out.println();
		}
	}
	
	public void setBracket() {
		created = 24;
	}
}
