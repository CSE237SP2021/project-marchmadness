package marchMadnessBracket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateBracket {

	private Bracket bracket;
	private Scanner input;

	public CreateBracket() {
		input = new Scanner(System.in);
	}

	public static void main(String args[]) {

//		CreateBracket Obj = new CreateBracket();
//		boolean quit = false;
//
//		while (!quit) {
//			String option = Obj.displayOptions();
//
//			if (option.equals("1")) {
//				String bracketName = Obj.chooseBracketName();
//				Obj.createBracket(bracketName);
//				continue;
//			} else if (option.equals("2")) {
//				String bracketName = Obj.pickBracket();
//				Obj.modifyBracket(bracketName);
//				break;
//			} else if (option.equals("3")) {
//				quit = true;
//			} else if (option.equals("4")) {
//				String bracketName = Obj.pickBracket();
//				Obj.viewBracket(bracketName);
//			} else if (option.equals("5")) {
//				String bracketName = Obj.pickBracket();
//				Obj.deleteBracket(bracketName);
//			} else {
//				System.out.println("Wrong input. Please re-enter your choice.");
//				continue;
//			}
//		}
//		Obj.close();
	}

	/**
	 * Displays options for brackets and returns the input
	 * 
	 * @return the user's input
	 */
	public String displayOptions() {

		System.out.println(
				"Please select the following options: 1. Create a Bracket, 2. Modify a Bracket, 3. Quit, 4. View Bracket, 5. Delete Bracket");
		String option = this.input.next();

		return option;
	}

	/**
	 * Returns the name inputted by the user for a new bracket
	 * 
	 * @param user the user who is creating the bracket
	 * @return the name of the bracket if valid, or null
	 */
	public String chooseBracketName(String user) {
		System.out.println("Enter a Bracket Name:");
		// Scanner newBracketKeyboardIn = new Scanner(System.in);
		String bracketName = input.next();
		String filePathString = "./src/brackets/" + user + "-" + bracketName;
		File f = new File(filePathString);
		if (f.exists() && !f.isDirectory()) {
			System.out.println("Bracket with this name already exists.");
			// newBracketKeyboardIn.close();
			return null;
		}
		return bracketName;
	}

	/**
	 * Asks for a name for the bracket and then creates a bare bones bracket
	 * 
	 * @param bracketName name of bracket being created
	 * @param user        user creating the bracket
	 * @return true if successful and false otherwise
	 */
	public boolean createBracket(String bracketName, String user) {
		if (bracketName == null) {
			return false;
		}
		String filePathString = "./src/brackets/" + user + "-" + bracketName;
		bracket = new Bracket();
		Bracket newBracket = setTeams();
		outputFile(filePathString, newBracket);
		// modifyBracket(bracketName);
		return true;
	}

	// Helper function to set teams to use in new bracket, extracted from Teams
	// file.
	/**
	 * Helper function to return a bare bones bracket
	 * 
	 * @return the bracket object with the first round teams set
	 */
	private Bracket setTeams() {
		File teamFile = new File("./src/marchMadnessBracket/Teams");
		String region = "West";
		Scanner scanner;
		try {
			scanner = new Scanner(teamFile);
			while (scanner.hasNextLine()) {

				String teamList = scanner.nextLine();
				String[] parts = teamList.split(", ");

				if (parts[0].equals("West")) {
					region = "West";
				} else if (parts[0].equals("East")) {
					region = "East";
				} else if (parts[0].equals("South")) {
					region = "South";
				} else if (parts[0].equals("Midwest")) {
					region = "Midwest";
				} else {
					Team[] teams = new Team[16];
					for (int i = 0; i < parts.length; i++) {
						Team team1 = new Team(parts[i], i + 1);
						teams[i] = team1;
					}
					Matchup[] matchups = new Matchup[8];

					// order that normal bracket plays
					matchups[0] = new Matchup(teams[0], teams[15]);
					matchups[1] = new Matchup(teams[7], teams[8]);
					matchups[2] = new Matchup(teams[4], teams[11]);
					matchups[3] = new Matchup(teams[3], teams[12]);
					matchups[4] = new Matchup(teams[5], teams[10]);
					matchups[5] = new Matchup(teams[2], teams[13]);
					matchups[6] = new Matchup(teams[6], teams[9]);
					matchups[7] = new Matchup(teams[1], teams[14]);

					for (int i = 0; i < matchups.length; i++) {
						bracket.regions.get(region).addMatchup(matchups[i], 1);
					}
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return null;
		}

		return bracket;
	}

	/**
	 * Writes the bracket to a file at the path
	 * 
	 * @param pathString the path to the file to write the bracket
	 * @param bracket    the bracket to write
	 */

	private void outputFile(String pathString, Bracket bracket) {
		try {
			FileWriter loginInfoWriter = new FileWriter(pathString, true);
			loginInfoWriter.write(bracket.toString());
			loginInfoWriter.close();

			System.out.println("Successfully created bracket file.");
		} catch (IOException e) {
			System.out.println("An error occurred here.");
			e.printStackTrace();
		}

	}

	// ask user to pick a bracket
	/**
	 * Prompts user to pick a bracket with options
	 * 
	 * @param user the user that must be at the beginning of the filename, empty
	 *             string if all brackets
	 * @return the name of the bracket chosen
	 */
	public String pickBracket(String user) {
		String path = "./src/brackets";

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		System.out.println("Brackets: ");
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && listOfFiles[i].getName().indexOf(user) == 0) {
				System.out.println(listOfFiles[i].getName());
			}
		}

		System.out.println("\nChoose a Bracket:");
		Scanner bracketKeyboardIn = new Scanner(System.in);
		String bracketName = bracketKeyboardIn.nextLine();
		return bracketName;
	}

	/**
	 * Resets a bracket and gets all the picks
	 * 
	 * @param bracketName name of the bracket
	 * @return the new bracket object
	 */
	public Bracket modifyBracket(String bracketName) {
		String filePathString = "./src/brackets/" + bracketName;
		File f = new File(filePathString);
		if (!(f.exists() && !f.isDirectory())) {
			System.out.println("Bracket does not exist");
			return null;
		}

		f.delete();
		bracket = new Bracket();
		bracket = setTeams();
		bracket = getPicks(bracket);
		outputFile(filePathString, bracket);
		return bracket;
	}

	// ask user to choose winners
	/**
	 * Prompts the user through the picks for a bracket
	 * @param bracket bracket to modify
	 * @return the modified bracket
	 */
	private Bracket getPicks(Bracket bracket) {
		Scanner winnerIn = new Scanner(System.in);
		String[] regions = { "West", "East", "South", "Midwest" };
		Team[] eliteEightTeams = new Team[8];
		Team[] finalFourTeams = new Team[4];
		int countTeams = 0;
		Matchup[] finals = new Matchup[3];

		// each round after the first
		for (int round = 2; round <= 4; round++) {
			System.out.println("\nWho will win? Type in 1 (first team) or 2 (second team):\n\n");
			for (int i = 0; i < 4; i++) {
				System.out.println("\n\n\n" + regions[i] + ":\tRound " + (round - 1) + "\n");
				Region region = bracket.regions.get(regions[i]);

				// extract matchups from region arraylist
				ArrayList<Matchup> arraylist = region.getMatchups(round - 1);
				int counter = 0;

				// create new matchups in the next round from this round
				Team temp = null;
				for (Matchup game : arraylist) {
					int option = 0;
					// input winner
					while (option != 1 && option != 2) {
						System.out.println(game.getInfo());

						String inputWinner = winnerIn.next();
						option = Integer.parseInt(inputWinner);
					}
					game = game.pickWinner(option);
					if (counter % 2 == 0) {
						temp = game.getWinner();
						if (round == 4) {
							eliteEightTeams[countTeams] = game.getWinner();
							countTeams++;
						}
					} else {
						Matchup nextGame = new Matchup(temp, game.getWinner());
						if (round == 4) {
							eliteEightTeams[countTeams] = game.getWinner();
							countTeams++;
						}
						region.addMatchup(nextGame, round);

					}
					counter++;

				}
			}
		}

		System.out.println("Elite Eight:\nWho will win? Type in 1 (first team) or 2 (second team):\n\n");
		int eliteNumber = 0;
		// elite eight, round 4, separate because has no "next" round in the region
		for (int i = 0; i < 4; i++) {
			Region region = bracket.regions.get(regions[i]);
			Matchup game = region.getMatchups(4).get(0);
			System.out.println("\n\n\n" + regions[i] + ":\tRound 4\n");
			int option = 0;
			while (option != 1 && option != 2) {
				System.out.println(game.getInfo());

				String inputWinner = winnerIn.next();
				option = Integer.parseInt(inputWinner);
			}
			game = game.pickWinner(option);
			finalFourTeams[i] = game.getWinner();
		}

		// final four matches
		bracket.finalFour[0] = new Matchup(finalFourTeams[0], finalFourTeams[1]);
		bracket.finalFour[1] = new Matchup(finalFourTeams[2], finalFourTeams[3]);
		System.out.println("\n\n\nFinalFour:\n");
		int option = 0;
		String inputWinner = "";
		while (option != 1 && option != 2) {
			System.out.println(bracket.finalFour[0].getInfo());
			inputWinner = winnerIn.next();
			option = Integer.parseInt(inputWinner);
		}
		bracket.finalFour[0] = bracket.finalFour[0].pickWinner(option);
		Team finalist1 = bracket.finalFour[0].getWinner();
		option = 0;
		while (option != 1 && option != 2) {
			System.out.println(bracket.finalFour[1].getInfo());
			inputWinner = winnerIn.next();
			option = Integer.parseInt(inputWinner);
		}
		bracket.finalFour[1] = bracket.finalFour[1].pickWinner(option);
		Team finalist2 = bracket.finalFour[1].getWinner();

		// championship match
		bracket.championship = new Matchup(finalist1, finalist2);

		System.out.println("\n\n\nChampionship:\n");
		option = 0;
		while (option != 1 && option != 2) {
			System.out.println(bracket.championship.getInfo());
			inputWinner = winnerIn.next();
			option = Integer.parseInt(inputWinner);
		}
		bracket.championship = bracket.championship.pickWinner(option);

		// winner
		bracket.winner = bracket.championship.getWinner();

		return bracket;
	}

	/**
	 * closes the scanner
	 */
	public void close() {
		this.input.close();
	}

	// view bracket from file
	/**
	 * displays a bracket and score of the bracket
	 * @param name 
	 * @return
	 */
	public boolean viewBracket(String name) {

		String filePathString = "./src/brackets/" + name;
		try {
			Scanner bracketReader = new Scanner(new File(filePathString));
			while (bracketReader.hasNextLine()) {
				System.out.println(bracketReader.nextLine());
			}
			bracketReader.close();
			System.out.println("\nScore: " + Bracket.score(name));
		} catch (FileNotFoundException e) {
			System.out.println("No bracket found");
		}
		return true;
	}

	// delete bracket
	public boolean deleteBracket(String bracketName) {
		String filePathString = "./src/brackets/" + bracketName;
		File f = new File(filePathString);
		if (!(f.exists() && !f.isDirectory())) {
			System.out.println("Bracket does not exist");
			return false;
		}

		if (f.delete()) {
			System.out.println("Deleted " + bracketName);
			return true;
		} else {
			System.out.println("Couldn't delete " + bracketName);
			return false;
		}

	}
}
