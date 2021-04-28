package testing;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import marchMadnessBracket.Bracket;

class bracketTesting {

	@Test
	void testScoringReal() {
		int score = Bracket.score("jeff-real");
		assertTrue("Perfect Score should get 192", score == 192);
	}

}
