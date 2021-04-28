package marchMadnessBracket;

public class RunProgram {
	public static void main(String args[]) {

		UserLogin login = new UserLogin();
		boolean loggedIn = false;
		String user = "";

		while (!loggedIn) {
			String option = login.welomePage();

			if (option.equals("1")) {
				login.register();
				continue;
			} else if (option.equals("2")) {
				user = login.login();
				if (user != null) {
					loggedIn = true;
				}
				continue;
			} else {
				System.out.println("Wrong input. Please re-enter your choice.");
				continue;
			}
		}

		CreateBracket bracketCreate = new CreateBracket();
		boolean quit = false;

		while (!quit) {
			String option = bracketCreate.displayOptions();

			if (option.equals("1")) {
				String bracketName = bracketCreate.chooseBracketName(user);
				if (bracketName != null) {
					bracketCreate.createBracket(bracketName, user);
				}
			} else if (option.equals("2")) {
				String bracketName = bracketCreate.pickBracket(user);
				bracketCreate.modifyBracket(bracketName);
			} else if (option.equals("3")) {
				quit = true;
			} else if (option.equals("4")) {
				String bracketName = bracketCreate.pickBracket("");
				bracketCreate.viewBracket(bracketName);
			} else if (option.equals("5")) {
				String bracketName = bracketCreate.pickBracket(user);
				bracketCreate.deleteBracket(bracketName);
			} else {
				System.out.println("Wrong input. Please re-enter your choice.");
				continue;
			}
		}
		bracketCreate.close();

		
	}

}
