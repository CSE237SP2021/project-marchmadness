package marchMadnessBracket;

public class RunProgram {
	public static void main(String args[]) {
		
		System.out.println(Bracket.score("admin", "test"));

		CreateBracket bracketCreate = new CreateBracket();
		boolean quit = false;

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

		while (!quit) {
			String option = bracketCreate.displayOptions();

			if (option.equals("1")) {
				bracketCreate.createBracket(user);
				continue;
			}
			/*
			 * else if (option.equals("2")) { Obj.modifyBracket(); break; }
			 */
			else if (option.equals("3")) {
				quit = true;
			} else if (option.equals("4")) {
				bracketCreate.viewBracket(user);
			} else {
				System.out.println("Wrong input. Please re-enter your choice.");
				continue;
			}
		}
		bracketCreate.close();
		System.out.println("Thanks for participating!");
	}

}
