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
		input=new Scanner(System.in);
	}

	//public static void bracketOptions() {
	public static void main(String args[]) {



		CreateBracket Obj = new CreateBracket();
		boolean quit=false;

		while (!quit) {
			String option = Obj.displayOptions();

			if (option.equals("1")) {
				Obj.createBracket();
				continue;
			}
			else if (option.equals("2")) {
	          		String bracketName = Obj.pickModifyBracket();
	        		Obj.modifyBracket(bracketName);
	        		break;
	        }
			else if(option.equals("3")) {
				quit = true;
			}
			else if(option.equals("4")) {
				Obj.viewBracket();
			}
			else {
				System.out.println("Wrong input. Please re-enter your choice.");
				continue;
			}
		}
		//Obj.close();
	}

	//Bracket InterFace
	private String displayOptions() {
		//System.out.println("Please select the following options: 1. Create a Bracket");
		System.out.println("Please select the following options: 1. Create a Bracket, 2. Modify a Bracket, 3. Quit, 4. View Bracket");
		//Scanner keyboardIn = new Scanner(System.in);
		String option = this.input.next();
		//input.nextLine();
		//keyboardIn.close();

		return option;
	}

	//Generate new Bracket
	public boolean createBracket() {
		System.out.println("Enter a Bracket Name:");
		//Scanner newBracketKeyboardIn = new Scanner(System.in);
		String bracketName = input.next();
		String filePathString = "./src/brackets/"+bracketName;
		File f = new File(filePathString);
		if(f.exists() && !f.isDirectory()) { 
			System.out.println("Bracket with this name already exists.");
			//newBracketKeyboardIn.close();
			return false;
		}
		bracket = new Bracket();
		Bracket newBracket = setTeams();
		outputFile(filePathString, newBracket);
		//modifyBracket(bracketName);
		return true;
	}

	//Helper function to set teams to use in new bracket, extracted from Teams file. 
	private Bracket setTeams() {
		File teamFile = new File("./src/marchMadnessBracket/Teams"); 
		String region = "West";
		Scanner scanner;
		try {
			scanner = new Scanner(teamFile);
			while (scanner.hasNextLine()) {

				String teamList = scanner.nextLine(); 
				String[] parts = teamList.split(", ");

				if(parts[0].equals("West")) {
					region = "West";
				}
				else if(parts[0].equals("East")) {
					region = "East";
				}
				else if(parts[0].equals("South")) {
					region = "South";
				}
				else if(parts[0].equals("Midwest")) {
					region = "Midwest";
				}
				else {
					Team[] teams = new Team[16];
					for(int i = 0; i < parts.length; i++) {
						Team team1 = new Team(parts[i], i+1);
						teams[i] = team1;
					}
					Matchup[] matchups = new Matchup[8];

					//order that normal bracket plays
					matchups[0] = new Matchup(teams[0], teams[15]);
					matchups[1] = new Matchup(teams[7], teams[8]);
					matchups[2] = new Matchup(teams[4], teams[11]);
					matchups[3] = new Matchup(teams[3], teams[12]);
					matchups[4] = new Matchup(teams[5], teams[10]);
					matchups[5] = new Matchup(teams[2], teams[13]);
					matchups[6] = new Matchup(teams[6], teams[9]);
					matchups[7] = new Matchup(teams[1], teams[14]);

					for(int i = 0; i < matchups.length; i++) {
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

	//output new bracket into bracket folder
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

	//ask user for which bracket to modify
	private String pickModifyBracket() {
		String path = "./src/brackets";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		System.out.println("Brackets: ");
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println(listOfFiles[i].getName());
			}
		}

		System.out.println("\nEnter a Bracket to Modify:");
		Scanner bracketKeyboardIn = new Scanner(System.in);
		String bracketName = bracketKeyboardIn.nextLine();
		return bracketName;
	}

	//reset bracket and ask user to choose winners for each match
	public Bracket modifyBracket(String bracketName) {
		String filePathString = "./src/brackets/"+bracketName;
		File f = new File(filePathString);
		if(!(f.exists() && !f.isDirectory())) { 
			System.out.println("Bracket does not exist");
			return null;
		}
		//bracket = readBracket(bracketName);

		f.delete();
		bracket = new Bracket();
		bracket = setTeams();
		bracket = getPicks(bracket);
		outputFile(filePathString, bracket);
		return bracket;
	}

	//read bracket from file (not used for now)
	private Bracket readBracket(String bracketName) {
		boolean flag = true;
		bracket = new Bracket();
		File bracketFile = new File("./src/brackets/"+bracketName); 
		String region = "West";
		int round = 0;
		Scanner scanner;
		int amountTeams = 0;
		try {
			scanner = new Scanner(bracketFile);
			while (scanner.hasNextLine()) {

				String readIn = scanner.nextLine(); 
				String[] titleParts = readIn.split(": Round ");

				if(titleParts[0].equals("West")) {
					region = "West";
					flag = true;
				}
				else if(titleParts[0].equals("East")) {
					region = "East";
					flag = true;
				}
				else if(titleParts[0].equals("South")) {
					region = "South";
					flag = true;
				}
				else if(titleParts[0].equals("Midwest")) {
					region = "Midwest";
					flag = true;
				}
				else {
					flag = false;
				}
				if(flag) {
					round = Integer.parseInt(titleParts[1]);

					amountTeams = (int) (16 / (Math.pow(2, round-1)));

					Matchup[] matchups = readMatchups(scanner, amountTeams);

					for(int i = 0; i < matchups.length; i++) {
						bracket.regions.get(region).addMatchup(matchups[i], round);
					}
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return null;
		}
		scanner.close();
		return bracket;
	}

	//helper function to read matchups from file
	private Matchup[] readMatchups(Scanner scanner, int amountTeams) {
		int amountMatchups = amountTeams/2;
		Matchup[] matchups = new Matchup[amountMatchups];
		for(int i = 0; i < amountMatchups; i++) {
			String team1test = scanner.nextLine();
			String team2test = scanner.nextLine();
			String[] team1Info = team1test.split(". ");
			String[] team2Info = team2test.split(". ");
			Team team1 = new Team(team1Info[1], Integer.parseInt(team1Info[0]));
			Team team2 = new Team(team2Info[1], Integer.parseInt(team2Info[0]));
			matchups[i] = new Matchup(team1, team2);
			String removeExcess = scanner.nextLine();
		}
		return matchups;
	}

	//ask user to choose winners
	private Bracket getPicks(Bracket bracket) {
		Scanner winnerIn = new Scanner(System.in);
		String[] regions = {"West", "East", "South", "Midwest"};
		Team[] eliteEightTeams = new Team[8];
		Team[] finalFourTeams = new Team[4];
		int countTeams = 0;
		Matchup[] finals = new Matchup[3];

		//each round after the first
		for(int round = 2; round <= 4; round++) {
			System.out.println("\nWho will win? Type in 1 (first team) or 2 (second team):\n\n");
			for(int i = 0; i < 4; i++) {
				System.out.println("\n\n\n"+regions[i]+":\tRound " + (round-1)+ "\n");
				Region region = bracket.regions.get(regions[i]);

				//extract matchups from region arraylist
				ArrayList<Matchup> arraylist = region.getMatchups(round-1);
				int counter = 0;
				for(Matchup game:arraylist) {
					counter++;
				}
				Matchup[] gameList = new Matchup[counter];
				/*
				if(round == 2) {
					gameList = new Matchup[counter/2];
				}

				counter = 0;
				for(Matchup game:arraylist) {
					if(counter < gameList.length) {
						gameList[counter] = game;
						counter++;
					}
				}*/
				counter = 0;
				for(Matchup game:arraylist) {
					gameList[counter] = game;
					counter++;
				}

				//create new matchups in the next round from this round
				counter= 0;
				Team temp = null;
				for(Matchup game:gameList) {
					//input winner
					System.out.println(game.getInfo());

					String inputWinner = winnerIn.next();
					int option = Integer.parseInt(inputWinner);
					game = game.pickWinner(option);
					if(counter % 2 == 0) {
						temp = game.getWinner();
						if(round == 4) {
							eliteEightTeams[countTeams] = game.getWinner();
							countTeams++;
						}
					}
					else {
						Matchup nextGame = new Matchup(temp, game.getWinner());
						if(round == 4) {
							eliteEightTeams[countTeams] = game.getWinner();
							countTeams++;
						}
						else {
							region.addMatchup(nextGame, round);
						}
					}
					counter++;

				}
			}
		}

		System.out.println("Elite Eight:\nWho will win? Type in 1 (first team) or 2 (second team):\n\n");
		int eliteNumber = 0;
		//elite eight, round 4, separate because has no "next" round in the region
		for(int i = 0; i < 4; i++) {
			Region region = bracket.regions.get(regions[i]);
			Matchup game = new Matchup(eliteEightTeams[eliteNumber], eliteEightTeams[eliteNumber+1]);
			region.addMatchup(game, 4);
			System.out.println("\n\n\n"+regions[i]+":\tRound 4\n");
			System.out.println(game.getInfo());
			String inputWinner = winnerIn.next();
			int option = Integer.parseInt(inputWinner);
			game = game.pickWinner(option);
			finalFourTeams[i] = game.getWinner();
			eliteNumber += 2;
		}

		//final four matches
		bracket.finalFour[0] = new Matchup(finalFourTeams[0], finalFourTeams[1]);
		bracket.finalFour[1] = new Matchup(finalFourTeams[2], finalFourTeams[3]);
		System.out.println("\n\n\nFinalFour:\n");
		System.out.println(bracket.finalFour[0].getInfo());
		String inputWinner = winnerIn.next();
		int option = Integer.parseInt(inputWinner);
		bracket.finalFour[0] = bracket.finalFour[0].pickWinner(option);
		Team finalist1 = bracket.finalFour[0].getWinner();

		System.out.println(bracket.finalFour[1].getInfo());
		inputWinner = winnerIn.next();
		option = Integer.parseInt(inputWinner);
		bracket.finalFour[1] = bracket.finalFour[1].pickWinner(option);
		Team finalist2 = bracket.finalFour[1].getWinner();

		//championship match
		bracket.championship = new Matchup(finalist1, finalist2);

		System.out.println("\n\n\nChampionship:\n");
		System.out.println(bracket.championship.getInfo());
		inputWinner = winnerIn.next();
		option = Integer.parseInt(inputWinner);
		bracket.championship = bracket.championship.pickWinner(option);

		//winner
		bracket.winner = bracket.championship.getWinner();

		return bracket;
	}

	public void close() {
		this.input.close();
	}

	//view bracket from file
	public boolean viewBracket() {
		System.out.println("What is the name of the bracket you would like to view");
		String name = input.next();
		String filePathString = "./src/brackets/"+name;
		try {
			Scanner bracketReader=new Scanner(new File(filePathString));
			while(bracketReader.hasNextLine()) {
				System.out.println(bracketReader.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.out.println("No bracket found");
			e.printStackTrace();
		}
		return true;
	}
}
