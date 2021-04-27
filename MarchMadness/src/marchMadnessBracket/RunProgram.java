package marchMadnessBracket;

public class RunProgram {
	public static void main(String args[]) {
		
		CreateBracket bracketCreate = new CreateBracket();
		boolean quit=false;
		
		UserLogin login = new UserLogin();
		boolean loggedIn=false;

		while (!loggedIn) {
			String option = login.welomePage();
	        
	        if (option.equals("1")) {
	        		login.register();
	        		continue;
	        }
	        else if (option.equals("2")) {
	        		loggedIn=login.login();
	        		continue;
	        }
	        else {
	        		System.out.println("Wrong input. Please re-enter your choice.");
	        		continue;
	        }
		}

		while (!quit) {
			String option = bracketCreate.displayOptions();
	        
	        if (option.equals("1")) {
	        	bracketCreate.createBracket();
	        		continue;
	        }
	        /*else if (option.equals("2")) {
	        		Obj.modifyBracket();
	        		break;
	        }*/
	        else if(option.equals("3")) {
	        	quit = true;
	        }
	        else if(option.equals("4")) {
	        	bracketCreate.viewBracket();
	        }
	        else {
	        		System.out.println("Wrong input. Please re-enter your choice.");
	        		continue;
	        }
		}
		bracketCreate.close();
		System.out.println("Thanks for participating!");
	}
	
}
