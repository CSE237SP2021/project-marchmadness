package testing;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import marchMadnessBracket.*;

class viewOtherBracketsTesting {

	@Test
	void test() {
		User userToShow=new User("Bob");
		
		assertTrue("Failed to display bracket", userToShow.getBracket().displayBracket().equals("displayed bracket"));
		
	}

}
