package marchMadnessBracket;

public class Bracket {
	private Region[] regions;
	public Bracket() {
		regions = new Region[4];
		regions[0]=new Region("West");
		regions[1]=new Region("East");
		regions[2]=new Region("South");
		regions[3]=new Region("Midwest");
	}
	
	public String displayBracket() {
		return "displayed bracket";
	}
	
	public Region getRegion(String regionName) {
		if(regionName.equals("West")) {
			return regions[0];
		}
		else if(regionName.equals("East")) {
			return regions[1];
		}
		else if(regionName.equals("South")) {
			return regions[2];
		}
		else if(regionName.equals("Midwest")) {
			return regions[3];
		}
		return null;
	}
	
	public Region getRegion(int regionNum) {
		if(regionNum == 0) {
			return regions[0];
		}
		else if(regionNum == 1) {
			return regions[1];
		}
		else if(regionNum == 2) {
			return regions[2];
		}
		else if(regionNum == 3) {
			return regions[3];
		}
		return null;
	}
}
