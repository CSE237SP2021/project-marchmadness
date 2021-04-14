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
	
	// add login and createNewUser methods here?
	
	// should this be a void method or return a User?
	public void login() {
		// login logic
		// if successful, print success message
		// else print password incorrect or username not found
	}
	
	public Bracket getBracket() {
		return bracket;
	}
	
	public void generateBracket() {
		CreateBracket Obj = new CreateBracket();
		boolean works = Obj.createBracket();
	}
	
	public void makePick(Matchup game, Team pick) {
		picks.put(game, pick);
	}
	
	public Team getPick(Matchup game) {
		return picks.get(game);
	}
}
