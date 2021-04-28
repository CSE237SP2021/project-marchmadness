package marchMadnessBracket;

import java.util.ArrayList;

public class Region {
	private ArrayList<Matchup>[] rounds;
	private String name;

	public Region(String name) {
		rounds = new ArrayList[4];
		for (int i = 0; i < rounds.length; i++) {
			rounds[i] = new ArrayList<Matchup>();
		}
		this.name = name;
	}

	/**
	 * adds a matchup to a given round
	 * @param game game to add
	 * @param round round to add game to
	 */
	public void addMatchup(Matchup game, int round) {
		if (round <= rounds.length) {
			rounds[round - 1].add(game);
		}
	}

	public ArrayList<Matchup> getMatchups(int round) {
		return rounds[round - 1];
	}

	public String toString(int round) {
		String printRegion = "";
		if (round > rounds.length) {
			// System.out.println("Enter a valid round");
			return printRegion;
		}
		printRegion = printRegion + this.name + ": Round " + round + "\n";
		for (Matchup game : rounds[round - 1]) {
			printRegion = printRegion + game + "\n\n";

		}
		return printRegion;

	}
}