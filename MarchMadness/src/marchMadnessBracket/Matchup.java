package marchMadnessBracket;

public class Matchup {
	private Team team1;
	private Team team2;
	private boolean hasPlayed;
	private int winner;
	
	public Matchup(Team team1,Team team2) {
		this.team1=team1;
		this.team2=team2;
		this.hasPlayed=false;
		this.winner=0;
	}
	
	private Team getWinner() {
		if(this.winner==1) {
			return this.team1;
		}else if(this.winner==2) {
			return this.team2;
		}
		return null;
	}
	
	private Team getLoser() {
		if(this.winner==1) {
			return this.team2;
		}else if(this.winner==2) {
			return this.team1;
		}
		return null;
	}
	
	public String getInfo() {
		if(this.hasPlayed) {
			return getWinner().toString()+" beat "+getLoser().toString();
		}
		return team1.toString()+" vs. "+team2.toString();
	}
	
}
