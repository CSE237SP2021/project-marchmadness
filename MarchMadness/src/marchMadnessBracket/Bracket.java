package marchMadnessBracket;

public class Bracket {
	int created = 0;
	Region[] regions;
	public Bracket() {
		regions = new Region[4];
		regions[0]=new Region("East");
		regions[1]=new Region("West");
		regions[2]=new Region("South");
		regions[3]=new Region("Midwest");
	}
	
	public String displayBracket() {
		return "displayed bracket";
	}
	
	public void setBracket() {
		created = 24;
	}
}
