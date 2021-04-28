package testing;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import marchMadnessBracket.Team;

public class TeamTesting {
	
	@Test
	public void testGetTeamName() {
		String teamname="Gonzaga";
		int teamRank=1;
		Team gonzaga=new Team(teamname, teamRank);
		assertTrue("The given name does not match the name of the team",gonzaga.getTeamname().equals(teamname));
	}
	
	@Test
	public void testGetTeamRank() {
		String teamname="Gonzaga";
		int teamRank=1;
		Team gonzaga=new Team(teamname, teamRank);
		assertTrue("The given rank does not match the rank of the team",gonzaga.getRank()==teamRank);
	}
}
