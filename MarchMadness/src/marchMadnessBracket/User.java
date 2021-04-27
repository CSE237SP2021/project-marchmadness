package marchMadnessBracket;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
	Bracket bracket;
	String username;
	
	
	public User(String username) {
		this.username=username;
		bracket=new Bracket();
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
}
