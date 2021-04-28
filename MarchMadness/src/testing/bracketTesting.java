
package testing;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import marchMadnessBracket.Bracket;

class bracketTesting {

	@Test
	void testScoringReal() {
		int score = Bracket.score("test-real");
		assertTrue("Perfect Score should get 192", score == 192);
	}

	@Test
	void testScoring55() {
		int score = Bracket.score("test-55");
		assertTrue("Bracket should have a score of 55", score == 55);
	}

	@Test
	void testScoringEmptyBracket() {
		int score = Bracket.score("test-empty");
		assertTrue("Bracket has no picks and should have a score of 0", score == 0);
	}

}

