package testing;

import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import marchMadnessBracket.*;

public class CreateBracketTesting {
	String bracketName = "testBracket";
	
	@Test
	public void testCreateBracket() {
		CreateBracket obj = new CreateBracket();
		boolean bracketMade = obj.createBracket(bracketName,"testuser");
		assertTrue("Bracket not created",bracketMade);
		obj.deleteBracket("testuser-"+bracketName);
	}
	
	@Test
	public void testModifyBracket() {
		CreateBracket obj = new CreateBracket();
		obj.createBracket(bracketName,"testuser");
		System.out.println("Enter only 1's");
		Bracket bracketModified = obj.modifyBracket("testuser-"+bracketName);
		
		CreateBracket obj2 = new CreateBracket();
		Bracket bracketExpected = new Bracket();
		bracketExpected = createExpected(bracketExpected);
		bracketExpected = modifyExpected(bracketExpected);
		
		assertTrue("Expected Bracket not Created", bracketModified.toString().equals(bracketExpected.toString()));
		obj.deleteBracket("testuser-"+bracketName);
	}
	
	@Test
	public void testDeleteBracket() {
		CreateBracket obj = new CreateBracket();
		obj.createBracket(bracketName,"testuser");
		boolean bracketDeleted = obj.deleteBracket("testuser-"+bracketName);
		assertTrue("Bracket not deleted",bracketDeleted);
	}
	
	public Bracket modifyExpected(Bracket bracketExpected) {
		String[] regions = {"West", "East", "South", "Midwest"};
		Team[] eliteEightTeams = new Team[8];
		Team[] finalFourTeams = new Team[4];
		int countTeams = 0;
		Matchup[] finals = new Matchup[3];
		int option = 1;
		
		for(int round = 2; round <= 4; round++) {
			for(int i = 0; i < 4; i++) {
				Region region = bracketExpected.regions.get(regions[i]);

				//extract matchups from region arraylist
				ArrayList<Matchup> arraylist = region.getMatchups(round-1);
				int counter = 0;
				for(Matchup game:arraylist) {
					counter++;
				}
				Matchup[] gameList = new Matchup[counter];
				counter = 0;
				for(Matchup game:arraylist) {
					gameList[counter] = game;
					counter++;
				}

				//create new matchups in the next round from this round
				counter= 0;
				Team temp = null;
				for(Matchup game:gameList) {
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

		int eliteNumber = 0;
		//elite eight, round 4, separate because has no "next" round in the region
		for(int i = 0; i < 4; i++) {
			Region region = bracketExpected.regions.get(regions[i]);
			Matchup game = new Matchup(eliteEightTeams[eliteNumber], eliteEightTeams[eliteNumber+1]);
			region.addMatchup(game, 4);
			game = game.pickWinner(option);
			finalFourTeams[i] = game.getWinner();
			eliteNumber += 2;
		}

		//final four matches
		bracketExpected.finalFour[0] = new Matchup(finalFourTeams[0], finalFourTeams[1]);
		bracketExpected.finalFour[1] = new Matchup(finalFourTeams[2], finalFourTeams[3]);
		bracketExpected.finalFour[0] = bracketExpected.finalFour[0].pickWinner(option);
		Team finalist1 = bracketExpected.finalFour[0].getWinner();

		bracketExpected.finalFour[1] = bracketExpected.finalFour[1].pickWinner(option);
		Team finalist2 = bracketExpected.finalFour[1].getWinner();

		//championship match
		bracketExpected.championship = new Matchup(finalist1, finalist2);

		bracketExpected.championship = bracketExpected.championship.pickWinner(option);

		//winner
		bracketExpected.winner = bracketExpected.championship.getWinner();
		
		return bracketExpected;
	}
	
	public Bracket createExpected(Bracket bracketExpected) {
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
						bracketExpected.regions.get(region).addMatchup(matchups[i], 1);
					}
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bracketExpected;
	}
	
}
