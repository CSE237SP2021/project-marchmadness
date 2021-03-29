package marchMadnessBracket;

public class Bracket {
	
	Region[] regions;
	public Bracket() {
		regions = new Region[4];
		regions[0]=new Region("East");
		regions[1]=new Region("West");
		regions[2]=new Region("South");
		regions[3]=new Region("Midwest");
	}
	
	public static void main(String[] args) {
		System.out.println("hello world");
	}
	
	public void displayBracket() {
		
	}
}
