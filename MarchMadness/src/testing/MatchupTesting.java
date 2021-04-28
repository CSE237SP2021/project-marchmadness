package testing;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import marchMadnessBracket.Matchup;
import marchMadnessBracket.Team;

public class MatchupTesting {
	@Test
	public void testGetMatchupInfo() {
		Team team1=new Team("Gonzaga",1);
		Team team2=new Team("Michigan",1);
		Matchup game1=new Matchup(team1,team2);
		String info=game1.getInfo();
		String expectedInfo="1. Gonzaga vs. 1. Michigan";
		assertTrue("Info was not returned as expected",info.equals(expectedInfo));
		
	}
}
