package marchMadnessBracket;

import java.util.Scanner;

public class UserLogin {
	
	private static String username;
	private static String password;
	private static Scanner keyboardIn;

	public UserLogin(String u, String p) {
		super();
		this.username = u;
		this.password = p;
		Scanner keyboardIn = new Scanner(System.in);
	}

	public static void main(String args[]) {
		
		UserLogin Obj = new UserLogin(username, password);
		
		
		
		//while (true) {
			String option = Obj.welomePage();
	        
	        if (option.equals("1")) {
	        		Obj.register();
	        		//break;
	        }
	        else if (option.equals("2")) {
	        		Obj.login();
	        		//break;
	        }
	        else {
	        		System.out.println("Wrong input. Please re-enter your choice.");
	        		//continue;
	        }
		//}
		
	}
	
	private String welomePage() {
		System.out.println("Welcome! Please select the following options: 1. Register 2. Log In");
        String option = keyboardIn.nextLine();
		return option;
	}
	
	private boolean register() {
		System.out.println("Create Username:");
        String newUsername = keyboardIn.nextLine();
        System.out.println("Password:");
        String newPassword = keyboardIn.nextLine();
        username = newUsername;
        password = newPassword;
        
        return true;
	}

	private boolean login() {
		System.out.println("Username:");
        String usernameInput = keyboardIn.nextLine();
        
        System.out.println("Password:");
        String passwordInput = keyboardIn.nextLine();
        
        if(usernameInput == username && passwordInput == password) {
        		System.out.println(display());
        		return true;
        }
        else {
        		System.out.println("Incorrect username or password, try again.");
        		return false;
        }
        
	}

	private String display() {
		return ("Login Success");
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		
}
