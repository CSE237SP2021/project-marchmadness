package testing;

import org.junit.Test;

import marchMadnessBracket.Matchup;
import marchMadnessBracket.Team;
import marchMadnessBracket.User;

import static org.junit.Assert.assertTrue;

public class UserTesting {
	@Test
	public void testMakePick() {
		User testUser=new User("Test");
		Team gonzaga=new Team("Gonzaga",1);
		Team michigan=new Team("Michigan",1);
		Matchup game= new Matchup(gonzaga,michigan);
		testUser.makePick(game, michigan);
		assertTrue("Couldn't find the pick",testUser.getPick(game)==michigan);
	}
}
