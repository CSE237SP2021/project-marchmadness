package marchMadnessBracket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
	        		Obj.modifyBracket();
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
		//System.out.println("Please select the following options: 1. Create a Bracket 2. Modify a Bracket");
		Scanner keyboardIn = new Scanner(System.in);
        String option = keyboardIn.next();
		return option;
	}
	
	public boolean createBracket() {
		System.out.println("Enter a Bracket Name:");
		Scanner newBracketKeyboardIn = new Scanner(System.in);
        String bracketName = newBracketKeyboardIn.nextLine();
        String filePathString = "./src/marchMadnessBracket/Bracket/"+bracketName;
        File f = new File(filePathString);
        if(f.exists() && !f.isDirectory()) { 
        	System.out.println("Bracket with this name already exists.");
            return false;
        }
        
        Bracket newBracket = setTeams();
        outputFile(filePathString, newBracket);
        System.out.println("Successfully created.");
        bracket = new Bracket();
        
        return true;
	}
	
	private Bracket setTeams() {
		File teamFile = new File("./src/marchMadnessBracket/Teams"); 
        boolean bracketSuccess = false;
        String region = "West";
        
        try {
        	Scanner scanner = new Scanner(teamFile);
        	while (scanner.hasNextLine()) {

            	String loginTest = scanner.nextLine(); 
            	String[] parts = loginTest.split(", ");
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
            		for(int i = 0; i < parts.length/2; i++) {
            			Team team1 = new Team(parts[i], i+1);
            			Team team2 = new Team(parts[parts.length-i-1], parts.length-i-1);
            			Matchup matchup = new Matchup(team1, team2);
            			System.out.println(region);
            			System.out.println(bracket.getRegion(region).toString());
            			bracket.getRegion(region).addMatchup(matchup);
            		}
            	}
            }
        	
        } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        return null;
        }
        return bracket;
	}
	
	private void outputFile(String pathString, Bracket bracket) {
		try {
            FileWriter loginInfoWriter = new FileWriter(pathString, true);
            for(int i = 0; i < 4; i++) {
            	Region region = bracket.getRegion(i);
            	loginInfoWriter.write("\n");
                loginInfoWriter.write(region.getName()+"\n");
                loginInfoWriter.write(region.toString());
                loginInfoWriter.write("\n");
                loginInfoWriter.close();
            }
            
            System.out.println("Successfully created bracket file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
	}
	
	public boolean modifyBracket() {
		String path = "./src/marchMadnessBracket/Bracket";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
			}
		}
		
		System.out.println("Enter a Bracket to Modify:");
		Scanner bracketKeyboardIn = new Scanner(System.in);
        String bracketName = bracketKeyboardIn.nextLine();
        String filePathString = "./src/marchMadnessBracket/Bracket/"+bracketName;
        File f = new File(filePathString);
        if(!(f.exists() && !f.isDirectory())) { 
        	System.out.println("Bracket does not exist");
            return false;
        }
        
        try {
            FileWriter bracketWriter = new FileWriter(filePathString, true);
            
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
          }
        
        return true;
	}
	
	
		
}