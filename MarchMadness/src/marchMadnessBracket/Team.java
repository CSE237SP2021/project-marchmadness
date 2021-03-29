package marchMadnessBracket;

public class Team {
	private String name;
	private int rank;
	
	public Team(String name, int rank) {
		this.name=name;
		this.rank=rank;
	}
	
	public String getTeamname() {
		return this.name;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	public String toString() {
		return this.rank+". "+this.name;
	}
}
