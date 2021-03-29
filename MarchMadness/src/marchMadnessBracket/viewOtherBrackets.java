package marchMadnessBracket;

public class viewOtherBrackets {
	//instantiate users and bracket
	User bracketUser = new User();
	
	Bracket bracketToShow = new Bracket();
	
	public Bracket viewOtherBrackets(User toViewUser){
		bracketUser = toViewUser;
		
		bracketToShow = bracketUser.getBracket();
		bracketToShow.displayBracket();
	}
}
