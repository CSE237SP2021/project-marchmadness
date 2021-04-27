package marchMadnessBracket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserLogin {
	
	private static Scanner keyboardIn;

	public UserLogin() {
		super();
	}

	public static void main(String args[]) {
		
		Scanner keyboardIn = new Scanner(System.in);
		
		UserLogin Obj = new UserLogin();

		while (true) {
			String option = Obj.welomePage();
	        
	        if (option.equals("1")) {
	        		Obj.register();
	        		continue;
	        }
	        else if (option.equals("2")) {
	        		Obj.login();
	        		break;
	        }
	        else {
	        		System.out.println("Wrong input. Please re-enter your choice.");
	        		continue;
	        }
		}
		
	}
	
	protected String welomePage() {
		System.out.println("Welcome! Please select the following options: 1. Register 2. Log In");
		Scanner keyboardIn = new Scanner(System.in);
        String option = keyboardIn.next();
		return option;
	}
	
	protected boolean register() {
		System.out.println("Create Username:");
		Scanner newUserNameKeyboardIn = new Scanner(System.in);
        String newUsername = newUserNameKeyboardIn.nextLine();
        System.out.println("Password:");
        Scanner newPasswordKeyboardIn = new Scanner(System.in);
        String newPassword = newPasswordKeyboardIn.nextLine();
        
        try {
            FileWriter loginInfoWriter = new FileWriter("./src/marchMadnessBracket/LoginInfo", true);
            loginInfoWriter.write("\n");
            loginInfoWriter.write("Username: " + newUsername);
            loginInfoWriter.write(" & ");
            loginInfoWriter.write("Password: " + newPassword);
            loginInfoWriter.close();
            System.out.println("Successfully registered.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        
        return true;
	}

	protected String login() {
		System.out.println("Username:");
		Scanner usernameKeyboardIn = new Scanner(System.in);
        String usernameInput = usernameKeyboardIn.nextLine();
        
        System.out.println("Password:");
        Scanner passwordKeyboardIn = new Scanner(System.in);
        String passwordInput = passwordKeyboardIn.nextLine();
        
        File loginFile = new File("./src/marchMadnessBracket/LoginInfo"); 
        boolean loginSuccess = false;
        String username="";
        
        try {
        	Scanner scanner = new Scanner(loginFile);
        	while (scanner.hasNextLine()) {

            	String loginTest = scanner.nextLine(); 
            	String[] parts = loginTest.split(" & ");
            	username = parts[0];
            	String password = parts[1];
            	
               
               if(username.equals("Username: " + usernameInput) && password.equals("Password: " + passwordInput)) { 
                   System.out.println(display());
                   loginSuccess = true;
                   break;
               }
            }
        	
        	
        } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
        
        if (loginSuccess == false) {
        	System.out.println("Incorrect Login Info.");
        	return null;
        }
        
        return usernameInput;
        
	}

	private String display() {
		return ("Login Success");
	}
		
}
