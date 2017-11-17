package Default;

public class Driver {
	
	public static void main(String[] args) {
		Node head = null;
		
		boolean done = false;
		while (!done) {
			System.out.println(""
					+ "⛰Welcome to Happy Trails!  Select an option below:				⛰\n"
					+ "⛰1) add a hiking trail							 	⛰\n"
					+ "⛰2) remove a hiking trail						 	⛰\n"
					+ "⛰3) display hiking trails alphabetically					⛰\n"
					+ "⛰4) display hiking trails >= a certain rating					⛰\n"
					+ "⛰5) display hiking trails in a specified state					⛰\n"
					+ "⛰6) display the nearest hiking trail to specified hiking trail 		⛰\n"
					+ "⛰7) list all trails with a specified landmark					⛰\n"
					+ "⛰8) list all hiking trails the user has yet to hike				⛰\n"
					+ "⛰9) quit									⛰\n"
					+ "⛰Select an option above:							⛰");
			int option = TextIO.getlnInt();
			
			//add
			if(option == 1){
				System.out.println("Enter the name of the hiking trail below:");
				String newName = TextIO.getlnString();
				System.out.println("How would you rate this trail?(Star 1-5)");
				int newRating = TextIO.getlnInt();
				System.out.println("Enter the latitude of your trail:");
				double newLat = TextIO.getlnDouble();
				System.out.println("Enter the longtitude of your trail:");
				double newLong = TextIO.getlnDouble();
				System.out.println("Enter the state of your trail:");
				String newState = TextIO.getln();
				System.out.println("Have you hiked this trail before? (Y/N)");
				boolean newHaveHiked = TextIO.getlnBoolean();
				boolean done1 = false;
				String[] newLandmarksList = new String[0];
				
				int index = 0;
				while(!done1){
					System.out.println("Enter name of one landmark around this trail:");
					String newLandmarks = TextIO.getln();
					String[] temp = new String[newLandmarksList.length + 1];
					temp[index] = newLandmarks;
					for (int i=0; i< index; i++)
						temp[i] = newLandmarksList[i];
					newLandmarksList = temp;
					index += 1;
					System.out.println("To add more landmark press 1.\n"
							+ "To exit press 0");
					int option1 = TextIO.getlnInt();
					if(option1 == 0){
						done1 = true;
					}
				}
				
				//Add new node once all the info has been entered.
				Trail newTrail = new Trail(newName, newRating, newLat, newLong, newState, newHaveHiked, newLandmarksList);
				if(head == null) 
					head = new Node(newTrail, null);	
				else 
					head.add(newTrail);
				}
			
			//remove
			if(option == 2){
				boolean doneRemove = false;
				while(!doneRemove){
					System.out.println("Enter the name of the trail you want to remove:");
					String name = TextIO.getln();
					head = head.remove(name);
					System.out.println("Trail" + name + " " + "has been removed!");
					System.out.println("Press 0 to Main Menu");
					int option1 = TextIO.getlnInt();
					if(option1 == 0){
						doneRemove = true;
					}
				}
			}
			// display
			if(option == 3){
				boolean doneRead = false;
				while(!doneRead){
					System.out.println(head.toString());
					System.out.println("Press 0 to Main Menu");
					int option1 = TextIO.getlnInt();
					if(option1 == 0){
						doneRead = true;
					}
				}
				continue;}
			// display hiking trails >= a certain rating
			if(option == 4){
				boolean doneFind = false;
				while(!doneFind){
					System.out.println("You Minimum Rating Require?");
					int newRating = TextIO.getlnInt();
					System.out.println(head.findRating(newRating));
					System.out.println("Press 0 to Main Menu");
					int option1 = TextIO.getlnInt();
					if(option1 == 0){
						doneFind = true;
					}
					
				}
			}
			//display trails in a state
			if(option == 5){
				boolean doneFind = false;
				while(!doneFind){
					System.out.println("Which state are you looking at?");
					System.out.println("Only the trails NOT HIKED of the chosen state will be displayed!");
					String newState = TextIO.getln();
					System.out.println(head.findState(newState));
					System.out.println("Press 0 to Main Menu");
					int option1 = TextIO.getlnInt();
					if(option1 == 0){
						doneFind = true;
					}
				}
			}
			//6) display the nearest hiking trail to specified hiking trail
			if(option == 6){
				boolean doneFind = false;
				while(!doneFind){
					System.out.println("Enter the name of the trail in which you want to find the closest trail:");
					String trailName = TextIO.getln();
					Trail trail = head.getTrail(trailName);
					Node shortest = head.getShortest(trail);
					System.out.println("Below is the closest trail to " + trailName);
					System.out.println(shortest.trail.toString());
					System.out.println("Go back to menu press 0");
					int temp = TextIO.getlnInt();
					if(temp == 0){
						doneFind = true;
					}
				}
			}

			
			//7) list all trails with a specified landmark
			if(option == 7){
				boolean doneFind = false;
				while(!doneFind){
					System.out.println("Which landmark you are looking for?");
					String landmark = TextIO.getln();
					System.out.println(head.findLandmark(landmark));
					System.out.println("Press 0 to Main Menu");
					int option1 = TextIO.getlnInt();
					if(option1 == 0){
						doneFind = true;
					}
				}
			}
			
			//8) list all hiking trails the user has yet to hike
			if(option == 8){
				boolean doneFind = false;
				while(!doneFind){
					System.out.println("You have not yet try the following trails:");
					System.out.println(head.findNotYet());
					System.out.println("Press 0 to Main Menu");
					int option1 = TextIO.getlnInt();
					if(option1 == 0){
						doneFind = true;
					}
				}
			}
			
			if (option == 9) {
				System.out.println("Quit? Anything not saved will be lost!(Y/N)");
				boolean sure = TextIO.getlnBoolean();
				if (sure) {
					done = true;
				} else {
					continue;
				}
			}
		}
	}
	
}

