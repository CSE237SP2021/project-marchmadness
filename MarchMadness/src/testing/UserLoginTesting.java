package testing;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import marchMadnessBracket.UserLogin;

class UserLoginTesting {
	

	@Test
	void test(){
		UserLogin Obj = new UserLogin("abcd", "1234");
		String input = "1 abcd 1234";
		InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
	    
	    assertEquals ("Login Success", UserLogin.main());
	}
	
	
}
