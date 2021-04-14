package marchMadnessBracket;

public class RunProgram {
	public static void main(String args[]) {
		
		CreateBracket Obj = new CreateBracket();
		boolean quit=false;

		while (!quit) {
			String option = Obj.displayOptions();
	        
	        if (option.equals("1")) {
	        		Obj.createBracket();
	        		continue;
	        }
	        /*else if (option.equals("2")) {
	        		Obj.modifyBracket();
	        		break;
	        }*/
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
		Obj.close();
		System.out.println("Thanks for participating!");
	}
	
}
