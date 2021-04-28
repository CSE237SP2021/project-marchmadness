package marchMadnessBracket;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bracket {

	final public static int rounds = 4;

	public HashMap<String, Region> regions;
	public Matchup[] finalFour;
	public Matchup championship;
	public Team winner;

	public Bracket() {
		regions = new HashMap<String, Region>();
		regions.put("West", new Region("West"));
		regions.put("East", new Region("East"));
		regions.put("South", new Region("South"));
		regions.put("Midwest", new Region("Midwest"));

		finalFour = new Matchup[2];
		championship = new Matchup();
		winner = new Team();
	}

	/**
	 * Scores a bracket by comparing it to the groundtruth bracket
	 * @param bracketName name of bracket to score
	 * @return the score of the bracket
	 */
	public static int score(String bracketName) {
		int score = 0;
		try {
			File truthFile = new File("./src/marchMadnessBracket/groundtruth");
			File bracketFile = new File("./src/brackets/" + bracketName);
			Scanner truthScanner = new Scanner(truthFile);
			Scanner bracketScanner = new Scanner(bracketFile);
			int truthRound = 1;
			int bracketRound = 1;
			while (truthScanner.hasNextLine() && bracketScanner.hasNextLine()) {
				String truthLine = truthScanner.nextLine();
				String bracketLine = bracketScanner.nextLine();
				if (truthLine.contains("Round")) {
					truthRound = getRound(truthLine);
				} else if (truthLine.contains("Final") || truthLine.contains("Winner")) {
					truthRound++;
				}
				if (bracketLine.contains("Round")) {
					bracketRound = getRound(bracketLine);
				} else if (bracketLine.contains("Final") || bracketLine.contains("Winner")) {
					bracketRound++;
				}
				if (bracketRound != truthRound) {
					truthScanner.close();
					bracketScanner.close();
					return score;
				}
				if (truthRound > 1 && !truthLine.contentEquals("") && !truthLine.contains("Round")
						&& !truthLine.contains("Final") && !truthLine.contains("Winner")) {
					if (truthLine.contentEquals(bracketLine)) {
						score += Math.pow(2, truthRound - 2);
					}
				}
			}
			truthScanner.close();
			bracketScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			return 0;
		}

		return score;
	}

	/**
	 * Takes an line from a bracket (with round in it) and passes back the round number
	 * @param line the line to parse
	 * @return the round number found
	 */
	private static int getRound(String line) {
		Pattern regex = Pattern.compile("\\d+");
		Matcher match = regex.matcher(line);
		match.find();
		return Integer.parseInt(match.group());
	}

	/**
	 * @return the string representing all the rounds of each region
	 */
	public String toString() {
		String printBracket = "";

		for (int round = 0; round < rounds; round++) {
			for (Map.Entry<String, Region> entry : regions.entrySet()) {
				Region region = entry.getValue();
				printBracket = printBracket + region.toString(round + 1);
			}
		}
		printBracket += "Final Four\n";
		for (Matchup game : finalFour) {
			if (game != null) {
				printBracket = printBracket + game + "\n\n";
			}
		}
		printBracket += "Finals\n";
		if (championship != null) {
			printBracket = printBracket + championship + "\n\n";
		}
		printBracket = printBracket + "Winner:\n";
		if (winner.getTeamname() != null) {
			printBracket += winner.getTeamname();
		}
		return printBracket;
	}

}