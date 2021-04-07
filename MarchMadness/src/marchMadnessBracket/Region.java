package marchMadnessBracket;

import java.util.ArrayList;

public class Region {
	private ArrayList<Matchup> games;
	private String name;
	
	public Region(String name) {
		games=new ArrayList<Matchup>();
		this.name=name;
	}
	
	public void addMatchup(Matchup game) {
		games.add(game);
	}
	
	public String toString() {
		String region="";
		region+=this.name+":\n";
		for(Matchup game:games) {
			region+=game.getInfo();
			region+="\n";
		}
		
		return region;
	}
	
	public String getName() {
		return name;
	}
}
