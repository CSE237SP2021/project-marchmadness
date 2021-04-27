package marchMadnessBracket;

public class Matchup {
	private Team team1;
	private Team team2;
	private boolean hasPlayed;
	private int chosenWinner;
	private int realWinner;

	public Matchup(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
		this.hasPlayed = false;
		this.chosenWinner = 0;
		this.realWinner = 0;
	}

	public Matchup() {
		this.team1 = null;
		this.team2 = null;
		this.hasPlayed = false;
		this.chosenWinner = 0;
	}

	public Team getWinner() {
		if (this.chosenWinner == 1) {

			return this.team1;
		} else if (this.chosenWinner == 2) {
			return this.team2;
		}
		return null;
	}

	/**
	 * @return the team that is the loser and null if there is no loser
	 */
	private Team getLoser() {
		if (this.chosenWinner == 1) {
			return this.team2;
		} else if (this.chosenWinner == 2) {
			return this.team1;
		}
		return null;
	}

	public Matchup pickWinner(int whoWins) {
		if (whoWins == 1) {
			chosenWinner = 1;
		} else if (whoWins == 2) {
			chosenWinner = 2;
		} else {
			System.out.println("Invalid Choice");
			return this;
		}

		return this;
	}

	public String getInfo() {
		if (this.hasPlayed) {
			return getWinner().toString() + " beat " + getLoser().toString();
		}
		return team1.toString() + " vs. " + team2.toString();
	}

	public String toString() {

		String game = "";
		game += team1 == null ? "\n" : team1.toString();
		game += "\n";
		game += team2 == null ? "\n" : team2.toString();
		return game;
	}

}
