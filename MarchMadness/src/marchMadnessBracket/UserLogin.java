package marchMadnessBracket;

import java.util.Scanner;

public class UserLogin {
	
	private static String username;
	private static String password;

	public UserLogin(String u, String p) {
		super();
		this.username = u;
		this.password = p;
	}

	public static void main() {
		
		UserLogin Obj = new UserLogin(username, password);
		
		while (true) {
			String option = Obj.welomePage();
	        
	        if (option == "1") {
	        		Obj.register();
	        		break;
	        }
	        else if (option == "2") {
	        		Obj.login();
	        		break;
	        }
	        else {
	        		System.out.println("Wrong input. Please re-enter your choice.");
	        		continue;
	        }
		}
		
	}
	
	private String welomePage() {
		Scanner keyboardIn = new Scanner(System.in);
		System.out.print("Welcome! Please select the following options: 1. Register 2. Log In");
        String option = keyboardIn.nextLine();
        keyboardIn.close();
		return option;
	}
	
	private boolean register() {
		Scanner keyboardIn = new Scanner(System.in);
		System.out.print("Create Username:");
        String newUsername = keyboardIn.nextLine();
        System.out.print("Password:");
        String newPassword = keyboardIn.nextLine();
        keyboardIn.close();
        
        username = newUsername;
        password = newPassword;
        
        return true;
	}

	private boolean login() {
		Scanner keyboardIn = new Scanner(System.in);
		System.out.print("Username:");
        String usernameInput = keyboardIn.nextLine();
        System.out.print("Password:");
        String passwordInput = keyboardIn.nextLine();
        keyboardIn.close();
        
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
