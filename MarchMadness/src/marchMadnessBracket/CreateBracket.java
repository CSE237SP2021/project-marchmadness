package marchMadnessBracket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateBracket {
	
	private static Scanner keyboardIn;
	private Bracket bracket;

	public CreateBracket() {
		super();
	}

	//public static void bracketOptions() {
	public static void main(String args[]) {
		
		Scanner keyboardIn = new Scanner(System.in);
		
		CreateBracket Obj = new CreateBracket();

		while (true) {
			String option = Obj.displayOptions();
	        
	        if (option.equals("1")) {
	        		Obj.createBracket();
	        		continue;
	        }
	        /*else if (option.equals("2")) {
	          		String bracketName = Obj.pickModifyBracket();
	        		Obj.modifyBracket(bracketName);
	        		break;
	        }*/
	        else {
	        		System.out.println("Wrong input. Please re-enter your choice.");
	        		continue;
	        }
		}
		
	}
	
	private String displayOptions() {
		System.out.println("Please select the following options: 1. Create a Bracket");
		//System.out.println("Please select the following options: 1. Create a Bracket\t 2. Modify a Bracket");
		Scanner keyboardIn = new Scanner(System.in);
        String option = keyboardIn.next();
		return option;
	}
	
	public boolean createBracket() {
		System.out.println("Enter a Bracket Name:");
		Scanner newBracketKeyboardIn = new Scanner(System.in);
        String bracketName = newBracketKeyboardIn.next();
        String filePathString = "./src/brackets/"+bracketName;
        File f = new File(filePathString);
        if(f.exists() && !f.isDirectory()) { 
        	System.out.println("Bracket with this name already exists.");
        	newBracketKeyboardIn.close();
            return false;
        }
        bracket = new Bracket();
        Bracket newBracket = setTeams();
        outputFile(filePathString, newBracket);
        modifyBracket(bracketName);
        return true;
	}
	
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
        	bracket.rounds++;
        	
        } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        return null;
        }
        scanner.close();
        return bracket;
	}
	
	private void outputFile(String pathString, Bracket bracket) {
		try {
            FileWriter loginInfoWriter = new FileWriter(pathString, true);
            /*for(int i = 0; i < 4; i++) {
            	Region region = bracket.getRegion(i);
            	loginInfoWriter.write("\n");
                loginInfoWriter.write(region.getName()+"\n");
                loginInfoWriter.write(region.toString());
                loginInfoWriter.write("\n");
                loginInfoWriter.close();
            }*/
            loginInfoWriter.write(bracket.toString());
            loginInfoWriter.close();
            
            System.out.println("Successfully created bracket file.");
          } catch (IOException e) {
            System.out.println("An error occurred here.");
            e.printStackTrace();
          }
	}
	
	public String pickModifyBracket() {
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
        bracketKeyboardIn.close();
        return bracketName;
	}
	
	public Bracket modifyBracket(String bracketName) {
        String filePathString = "./src/brackets/"+bracketName;
        File f = new File(filePathString);
        if(!(f.exists() && !f.isDirectory())) { 
        	System.out.println("Bracket does not exist");
            return null;
        }
        //bracket = readBracket(bracketName);
        
        f.delete();
        bracket = setTeams();
        bracket = getPicks(bracket);
        try {
            FileWriter loginInfoWriter = new FileWriter(filePathString, true);
            loginInfoWriter.write(bracket.toString());
            loginInfoWriter.close();
            
            System.out.println("Successfully modified bracket file.");
          } catch (IOException e) {
            System.out.println("An error occurred here.");
            e.printStackTrace();
          }
        return bracket;
	}
	
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
        	bracket.rounds = round;
        	
        } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        return null;
        }
        scanner.close();
        return bracket;
	}
	
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
	
	private Bracket getPicks(Bracket bracket) {
		Scanner winnerIn = new Scanner(System.in);
		String[] regions = {"West", "East", "South", "Midwest"};
		Team[] eliteEightTeams = new Team[8];
		Team[] finalFourTeams = new Team[4];
		int countTeams = 0;
		Matchup[] finals = new Matchup[3];
		for(int round = 2; round <= 4; round++) {
			System.out.println("\nWho will win? Type in 1 (first team) or 2 (second team):\n\n");
			for(int i = 0; i < 4; i++) {
				System.out.println("\n\n\n"+regions[i]+":\tRound " + (round-1)+ "\n");
				Region region = bracket.regions.get(regions[i]);
				ArrayList<Matchup> arraylist = region.getMatchups(round-1);
				int counter = 0;
				for(Matchup game:arraylist) {
					counter++;
				}
				Matchup[] gameList = new Matchup[counter];;
				if(round == 2) {
					gameList = new Matchup[counter/2];
				}
				
				counter = 0;
				for(Matchup game:arraylist) {
					if(counter < gameList.length) {
						gameList[counter] = game;
						counter++;
					}
				}
				counter= 0;
				Team temp = null;
				for(Matchup game:gameList) {
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
		for(int i = 0; i < 4; i++) {
			Region region = bracket.regions.get(regions[i]);
			Matchup game = new Matchup(eliteEightTeams[i], eliteEightTeams[i+1]);
			region.addMatchup(game, 5);
			System.out.println("\n\n\n"+regions[i]+":\tRound 4\n");
			System.out.println(game.getInfo());
			String inputWinner = winnerIn.next();
	        int option = Integer.parseInt(inputWinner);
	        game = game.pickWinner(option);
	        finalFourTeams[i] = game.getWinner();
			
		}
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
        
        bracket.championship = new Matchup(finalist1, finalist2);
        
        System.out.println("\n\n\nChampionship:\n");
        System.out.println(bracket.championship.getInfo());
		inputWinner = winnerIn.next();
        option = Integer.parseInt(inputWinner);
        bracket.championship = bracket.championship.pickWinner(option);
        bracket.winner = bracket.championship.getWinner();
        
		bracket.rounds = 5;
		return bracket;
	}
	
}
