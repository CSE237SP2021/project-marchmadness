package marchMadnessBracket;

public class viewOtherBrackets {
	//instantiate users and bracket
	User bracketUser = new User("Test");
	
	Bracket bracketToShow = new Bracket();
	
	public String viewBracket(User toViewUser){
		bracketUser = toViewUser;
		
		bracketToShow = bracketUser.getBracket();
		return bracketToShow.toString();
	}
}
