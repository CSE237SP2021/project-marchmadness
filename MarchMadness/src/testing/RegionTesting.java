
package testing;

import org.junit.Test;

import marchMadnessBracket.*;

import static org.junit.Assert.assertTrue;

public class RegionTesting {
	@Test
	public void testPrintRegion() {
		Team team1=new Team("Gonzaga",1);
		Team team2=new Team("Michigan",1);
		Matchup game1=new Matchup(team1,team2);
		Region region1 = new Region("name");
		region1.addMatchup(game1, 1);
		
		String output = region1.toString(1);
		String expectedOutput = "name: Round 1\n1. Gonzaga\n1. Michigan\n\n"; 
	
		assertTrue("Output was not returned as expected", output.equals(expectedOutput));
	}
}

