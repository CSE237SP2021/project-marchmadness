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

	private HashMap<String, Region> regions;
	private Matchup[] finals;
	private Team winner;

	public Bracket() {
		regions = new HashMap<String, Region>();
		regions.put("East", new Region("East"));
		regions.put("West", new Region("West"));
		regions.put("South", new Region("South"));
		regions.put("Midwest", new Region("Midwest"));
		finals = new Matchup[3];
	}

	public static int score(String user, String bracketName) {
		File truthFile = new File("./src/marchMadnessBracket/groundtruth");
		File bracketFile = new File("./src/brackets/" + user + "-" + bracketName);
		int score = 0;
		try {
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
					return score;
				}
				if (truthRound > 1 && !truthLine.contentEquals("") && !truthLine.contains("Round")
						&& !truthLine.contains("Final") && !truthLine.contains("Winner")) {
					if (truthLine.contentEquals(bracketLine)) {
						score += Math.pow(2, truthRound - 2);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return 0;
		}
		return score;
	}

	private static int getRound(String line) {
		Pattern regex = Pattern.compile("\\d+");
		Matcher match = regex.matcher(line);
		match.find();
		return Integer.parseInt(match.group());
	}

	public void addFinals(Matchup game, int gameNum) {
		finals[gameNum] = game;
	}

	public void addWinner(Team team) {
		winner = team;
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
		for (int i = 0; i < finals.length; i++) {
			if (i == 2) {
				printBracket += "Finals\n";
			}
			if (finals[i] != null) {
				printBracket += finals[i] + "\n\n";
			}
		}
		return printBracket;
	}

}