import java.util.*;
public class MenuAndSettings {
	
	//creates scanner input
	private Scanner input;
	//holds the amount of teams
	private int amountOfTeams;
	//holds the amount of users
	private int numberOfUsers;
	
	
	public MenuAndSettings() {
		this.input = new Scanner(System.in);

	}
	
	public void initialMenu() {
		System.out.println("WELCOME TO THE FANTASY FOOTBALL SIMULATION!\n");
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.println("Select an option:");
			System.out.println("Start - (TYPE: S)");
			System.out.println("About the game - (TYPE: A)");
	
			String answer1 = input.nextLine();
			if(answer1.equals("S")){
				break;
			}
			else if(answer1.equals("A")) {
				System.out.println("\nThe fantasy football simulation is a single-player and local multiplayer game. You will decide \n"
		                 + "your settings, draft a team based on real players in the NFL, and compete in a 16-week season \n"
		                 + "followed by playoffs that last 2 weeks, which are based on the top 4 teams. The winning team will \n"
		                 + "be decided through their performance in the regular season and 1st place is given by staying \n"
		                 + "alive in the postseason.\n\n"
		                 + "Each week, teams will face another team in an organized schedule that is randomly generated.\n"
		                 + "The players on the teams will be given a random number of points determined by their draft rank. \n"
		                 + "The higher the draft rank, the higher the points can be. These points will be added to the team's \n"
		                 + "total points, and depending on which team has more, will determine the winner of that week.\n"
		                 + "The more wins and total points you have by the end of the season will determine if you make the \n"
		                 + "playoffs or not. Good luck!\n\n");

				continue;
			}
		}
	}
	
	
	//Initial menu for setting teams
	public void secondMenu() {
		while(true) {
			System.out.println("Select gamemode:\nSingplayer - (TYPE: S)\nMultiplyer - (TYPE: M)");
			String answer1 = input.nextLine();
			if(answer1.equals("S")){
				setNumberOfUsers(1);
				break;
			}
			else if(answer1.equals("M")) {
				System.out.println("How many users will be participating? \n(MIN: 2 | MAX: 12)");
				int numberOfUser = input.nextInt();
				setNumberOfUsers(numberOfUser);
				break;
			}
			else
				continue;
		}
		Scanner input = new Scanner(System.in);
        System.out.println("Input the amount of total teams in the league:\n(MIN 4 | MAX 12)");
        
        while (true) {
            if (input.hasNextInt()) {
                int answer2 = input.nextInt();
                if (answer2 % 2 != 0) {
                    System.out.println("Must be an even number");
                    continue;
                } else if (answer2 < 4 || answer2 > 12) {
                    System.out.println("Invalid number");
                    continue;
                } else {
                    setAmountOfTeams(answer2);
                    break;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                input.next(); 
            }
        }        
	}
		
	
	
	
	
	//method for obtaining draft order based on the amount of players selected
	public int[] DraftOrder(int count) {
		
		//using linked hash set for random order
		Set<Integer> uniqueSet = new LinkedHashSet<>();
		
		int min = 1;
		int max = Main.numOfTeams;
		
		while(uniqueSet.size() < count) {
			int randomNum = (int)(Math.random() * (max - min + 1)) + min;
			uniqueSet.add(randomNum);
		}

		int[] order = new int[count];
		int index = 0;
		for(int uniqueIntegers : uniqueSet) {
			order[index++] = uniqueIntegers;
		}
		return order;
	}
	
	//initial draft menu that allows access to the players ranks and the draft settings
	public void DraftInitializer() {
		

		System.out.println("\n\n\n\n\nWELCOME TO DRAFT DAY!\n");
		
		while(true) {
			
			System.out.println("\n--------------OPTIONS--------------");
			System.out.println("Start draft TYPE: START");
			System.out.println("See player rankins TYPE: RANKS");
			System.out.println("Show draft settings TYPE: SETTINGS");
			System.out.println("-----------------------------------\n");

			Scanner input2 = new Scanner(System.in);
			String answer1 = input2.nextLine();

		if(answer1.equals("START")) {
			break;
		}
		else if(answer1.equals("RANKS")) {
			ReadFileAndOrganize.printArray(Main.players);
		}
		else if(answer1.equals("SETTINGS")) {
			System.out.println("\nThe amount of teams in the league are: "+Main.numOfTeams );
			System.out.println("The amount of users controlling the teams are: "+Main.numOfUsers );

		}
		else
			System.out.println("That is not a valid response.");
		}
	
		
		

	}
	
	//menu displayed for the user during the draft when it is their turn to pick
	public static String UserDraftMenu() {
		Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println("\nTeam " + Draft.currentTeamPicking + " You're on the clock!");
            System.out.println("Type: PICK - to make a pick");
            System.out.println("Type: PLAYERS - to see remaining players");
            System.out.println("Type: ROSTER - to see your current roster\n");
            System.out.print("Enter your choice: ");
            input = scanner.nextLine().trim().toUpperCase();

            if (!(input.equals("PICK") || input.equals("PLAYERS") || input.equals("ROSTER"))) {
                System.out.println("Invalid input. Please try again.");
            }
        } while (!(input.equals("PICK") || input.equals("PLAYERS") || input.equals("ROSTER")));
        return input;
	}
	
	//menu that is shown at the beginning of each week
	public static int RegularSeasonWeeklyStandings() {
		Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("\n\nChoose an option:");
        System.out.println("\nStart the next week - (TYPE: 1)");
        System.out.println("See current matchups - (TYPE: 2)");
        System.out.println("See current standings - (TYPE: 3)");

        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scanner.next(); // Clear the invalid input
            }
        }

        return choice;
        
		
	}
	//used for post seaosn championship
	public static int RegularSeasonWeeklyStandings2() {
		Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("\n\nChoose an option:");
        System.out.println("\nStart the next week - (TYPE: 1)");
        System.out.println("See current matchups - (TYPE: 2)");

        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 2) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 2.");
                scanner.next(); // Clear the invalid input
            }
        }

        return choice;
        
		
	}
	//prints the teams that are being controlled by users and displays what team number they are
	public static void printTeams() {
		for(int i = 0; i < Main.playerDraftOrder.length; i++) {
			System.out.println("Player "+(i+1) +" is Team " + Main.playerDraftOrder[i]);
			}
	}
	
	
	
	//GETTERS AND SETTERS
	
	public void setNumberOfUsers(int a) {
		this.numberOfUsers = a;
	}
	
	public void setAmountOfTeams(int a) {
		this.amountOfTeams = a;
	}
	
	public int getNumberOfUsers() {
		return numberOfUsers;
	}
	public int getAmountOfTeams() {
		return amountOfTeams;
	}
	
	
}
