package marchMadnessBracket;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
	Bracket bracket;
	String username;
	HashMap<Matchup,Team> picks;
	
	
	public User(String username) {
		this.username=username;
		bracket=new Bracket();
		picks=new HashMap<Matchup,Team>();
	}
	
	public Bracket getBracket() {
		return bracket;
	}
	
	public void setBracket() {
		bracket.setBracket();
	}
	
	public void makePick(Matchup game, Team pick) {
		picks.put(game, pick);
	}
	
	public Team getPick(Matchup game) {
		return picks.get(game);
	}
}
