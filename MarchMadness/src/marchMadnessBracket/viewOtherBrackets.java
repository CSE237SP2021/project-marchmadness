package marchMadnessBracket;

public class viewOtherBrackets {
	//instantiate users and bracket
	User bracketUser = new User("Test");
	
	Bracket bracketToShow = new Bracket();
	
	public void viewOtherBrackets(User toViewUser){
		bracketUser = toViewUser;
		
		bracketToShow = bracketUser.getBracket();
		bracketToShow.displayBracket();
	}
}
