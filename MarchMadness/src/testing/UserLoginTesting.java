package testing;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import marchMadnessBracket.Team;

public class UserLoginTesting {
	
	@Test
	public void testLoginSuccess() {
		String usernameInput = "admin";
		String passwordInput = "123456";
		
		File loginFile = new File("./src/marchMadnessBracket/LoginInfo"); 
	        boolean loginSuccess = false;
	        
	        try {
	        	Scanner scanner = new Scanner(loginFile);
	        	while (scanner.hasNextLine()) {

	            	String loginTest = scanner.nextLine(); 
	            	String[] parts = loginTest.split(" & ");
	            	String username = parts[0];
	            	String password = parts[1];
	            	
	               
	               if(username.equals("Username: " + usernameInput) && password.equals("Password: " + passwordInput)) { 
	                   loginSuccess = true;
	                   break;
	               }
	            }
	        	
	        	
	        } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	        }

		assertTrue("Login should have been successful",loginSuccess == true);
	}
}
