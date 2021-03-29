package testing;

import org.junit.Test;

import marchMadnessBracket.*;

import static org.junit.Assert.assertTrue;

public class RegionTesting {
	@Test
	public void testPrintRegion() {
		Region west=new Region("West");
		west.addMatchup(new Matchup(new Team("Gonzaga",1),new Team("Iowa",2)));
		west.addMatchup(new Matchup(new Team("USC",6),new Team("Oregon",7)));
		String expectedOutput="West:\n1. Gonzaga vs. 2. Iowa\n6. USC vs. 7. Oregon\n";
		assertTrue("Region did not print as expected",expectedOutput.equals(west.toString()));
	}
}
