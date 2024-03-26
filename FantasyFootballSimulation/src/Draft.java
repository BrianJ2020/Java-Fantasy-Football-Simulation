import java.util.ArrayList;
import java.util.Scanner;

public class Draft {
	private Scanner input;
	
	//array list that holds all the players info that will be accessed and removed during the draft
	private ArrayList<Player> playersLeft = new ArrayList<>(Main.players);
	
	//2D array that holds the value of a drafted player for a specific team ROWS = team, COLUMNS = PLAYER ID
	public int draftedPlayers[][] = {{0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0},{0,0,0,0,0,0},
									{0,0,0,0,0,0},{0,0,0,0,0,0},
									{0,0,0,0,0,0},{0,0,0,0,0,0},
									{0,0,0,0,0,0},{0,0,0,0,0,0},
									{0,0,0,0,0,0},{0,0,0,0,0,0},
									{0,0,0,0,0,0},{0,0,0,0,0,0}};
	
	public static int currentTeamPicking;
	
	public Draft() {
		this.input = new Scanner(System.in);
	}
	
	//Method for the entire draft
	public void StandardDraft() {
		
		//variable that holds a value to help check if its a USERS turn to draft
		int checkArrayDraft;
		//holds the ID of the player that the user picks
		int playerPickedId;
		
		//outer for loop for each round of the draft, starts at 1, ends at 6 (<7)
		for(int i = 1; i < 7; i++) {
			
			System.out.println("\nRound #" +(i));
						
			//for loop for each team to make a pick for round i, starts at team 1, ends until less than numOfTeams+1 
			for(int j = 1; j < Main.numOfTeams+1; j++) {
				
				//Will check if it is a users turn to pick
				checkArrayDraft = searchOrderArray(Main.playerDraftOrder, j);
				//if value equals 1, the USER j will pick
				if(checkArrayDraft == 1){
					
					//used to loop through picking menu until it equals 1 (a valid player was chosen and removed from the list)
					int testPlayerPicked = 0;
					//used to loop through player picking menu to make sure that the user picks a player that hasn't been chosen yet
					int testPlayerValid = 0;
					
					while(testPlayerPicked < 1) {
						
						//test to see if the roster spot was filled without any problems
						while(testPlayerValid < 1) {
							
							//sets current team picking to j and calls the UserDraftMenu method to display menu options
							currentTeamPicking = j;
							
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							//starts the menu
							String choice = MenuAndSettings.UserDraftMenu();
							
							//used to display remaining players to pick from 
							if (choice.equals("PLAYERS")) {
								System.out.println("Remaining players");
								for(int z = 0; z < playersLeft.size(); z ++) {
									playersLeft.get(z).printPlayer();
								}
							}
							//used to display current team roster
							else if (choice.equals("ROSTER")) {
								displayRoster(Main.players,j);
							}
							
							//used to make a pick
							else if(choice.equals("PICK")) {
								
								System.out.println("Enter the ID of your player:");

						        while (true) {
						            if (input.hasNextInt()) {
						                playerPickedId = input.nextInt();
						                break;
						            } else {
						                System.out.println("Invalid input. Please enter a valid ID.");
						                input.next(); // Clear the invalid input
						            }
						        }
						       
							
								//Searches for the player in the player list to see player is still available
								int playerCheck = findPlayer(playersLeft,playerPickedId);
								if(playerCheck == -1){
									System.out.println("Player was already picked");
									break;
								}
							
						
								else {
									//holds the string to check which position the player has that was picked
									String positionCheck = findPosition(playersLeft,playerPickedId);
								
									//each if statement will check the positionCheck variable and see if it equal to one of the following String values
									//If it is, the player ID will be put into its correct spot on the 2D array, and testPlayerValid will increase to 1, ending the turn
									if(positionCheck.equals("QB")) {
										if(draftedPlayers[j-1][0] == 0) {
											draftedPlayers[j-1][0] = playerPickedId;
											testPlayerValid++;
										}
										else {
											System.out.println("Your QBs are full, select another player.");
											break;
										}
										
									}
									else if(positionCheck.equals("RB")) {
										if(draftedPlayers[j-1][1] == 0) {
											draftedPlayers[j-1][1] = playerPickedId;
											testPlayerValid++;
										}
										else if(draftedPlayers[j-1][2] == 0) {
											draftedPlayers[j-1][2] = playerPickedId;
											testPlayerValid++;
										}
										else {
											System.out.println("Your RBs are full, select another player.");
											break;
										}
										
									}
									else if(positionCheck.equals("WR")) {
										if(draftedPlayers[j-1][3] == 0) {
											draftedPlayers[j-1][3] = playerPickedId;
											testPlayerValid++;
										}
										else if(draftedPlayers[j-1][4] == 0) {
											draftedPlayers[j-1][4] = playerPickedId;
											testPlayerValid++;
										}
										else {
											System.out.println("Your WRs are full, select another player.");
											break;
										}
										
									}
									else if(positionCheck.equals("TE")) {
										if(draftedPlayers[j-1][5] == 0) {
											draftedPlayers[j-1][5] = playerPickedId;
											testPlayerValid++;
										}
									
										else {
											System.out.println("Your TEs are full, select another player.");
											break;
										}
										
									}
								
								}
							
								//will remove the player that was picked from the playersLeft arrayList
								System.out.println("Team "+j+" Picked: " + playersLeft.get(playerCheck).getName() +"");
								playersLeft.remove(playerCheck);
								testPlayerPicked++;
							}
						}
						
					}
					
				}
				
			
				//COMPUTER team will pick a player
				else if(checkArrayDraft != 1){
					
					//forloop to loop through the playersLeft array and pick the best player available
					for(int z = 1; z < 104; z++) {
						
						int testPlayerPicked = 0;
						playerPickedId = z;
						
						int playerCheck = findPlayer(playersLeft,playerPickedId);
						if(playerCheck == -1){
						}
						
						
						else {
							int testPlayerValid = 0;

							//while loop will check if the current team has the position available
							while(testPlayerValid < 1) {
								//holds the string to check which position the player has that was picked
								String positionCheck = findPosition(playersLeft,playerPickedId);
						
								if(positionCheck.equals("QB")) {
									if(draftedPlayers[j-1][0] == 0) {
										draftedPlayers[j-1][0] = playerPickedId;
										testPlayerValid++;
										testPlayerPicked++;
									}
									else {
										break;
									}
								}
								else if(positionCheck.equals("RB")) {
									if(draftedPlayers[j-1][1] == 0) {
										draftedPlayers[j-1][1] = playerPickedId;
										testPlayerValid++;
										testPlayerPicked++;

									}
									else if(draftedPlayers[j-1][2] == 0) {
										draftedPlayers[j-1][2] = playerPickedId;
										testPlayerValid++;
										testPlayerPicked++;

									}
									else {
										break;
									}
								}
								else if(positionCheck.equals("WR")) {
									if(draftedPlayers[j-1][3] == 0) {
										draftedPlayers[j-1][3] = playerPickedId;
										testPlayerValid++;
										testPlayerPicked++;

									}
									else if(draftedPlayers[j-1][4] == 0) {
										draftedPlayers[j-1][4] = playerPickedId;
										testPlayerValid++;
										testPlayerPicked++;

									}
									else {
										break;
									}
								}
								else if(positionCheck.equals("TE")) {
									if(draftedPlayers[j-1][5] == 0) {
										draftedPlayers[j-1][5] = playerPickedId;
										testPlayerValid++;
										testPlayerPicked++;

									}
							
									else {
										break;
									}
								}
						
							}
						}
						if(testPlayerPicked > 0) {
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						System.out.println("Team "+j+": Picked: "  + findPlayerName(playersLeft,playerPickedId));
						playersLeft.remove(playerCheck);

						
						break;
						}
					}
				}
		
			}
		
		}
	
		//assigns all player values to the 2d array draftedplayers
		for(int i = 0; i < Main.numOfTeams; i++) {
			int teamNum = i+1;
			int QB = draftedPlayers[i][0];
			int RB1 = draftedPlayers[i][1];
			int RB2 = draftedPlayers[i][2];
			int WR1 = draftedPlayers[i][3];
			int WR2 = draftedPlayers[i][4];
			int TE = draftedPlayers[i][5];
			
			Team team = new Team(teamNum,QB,RB1,RB2,WR1,WR2,TE);
			Main.teams.add(team);
			
			
		}
		
		System.out.println("DRAFT COMPLETE");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int z = 0; z < Main.teams.size(); z ++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Main.teams.get(z).printTeam();
		}
	}
		

	//Linear Search for DRAFT ORDER
	public static int searchOrderArray(int arr[], int x){
		int n = arr.length;
	 
	    // Traverse array arr[]
	    for (int i = 0; i < n; i++) {
	 
	    // If element found then
	    // return that index
	        if (arr[i] == x)
	        	return 1;
	    }
	    return -1;
	}
		
	//find players through the ID inputed 
	public static int findPlayer(ArrayList<Player> players, int ID) {
		for(int i = 0; i < players.size() ; i++) {
			if(players.get(i).getID() == ID){
				return i;
			}
		}
		return -1;
	}
	
	//find players NAME through the ID inputed 
		public static String findPlayerName(ArrayList<Player> players, int ID) {
			for(int i = 0; i < players.size() ; i++) {
				if(players.get(i).getID() == ID){
					return players.get(i).getName();
				}
			}
			return "UNKOWN";
		}
	
	//finds the position of the player ID selected
	public static String findPosition(ArrayList<Player> players, int ID) {
		
		for(int i = 0; i < players.size() ; i++) {
			if(players.get(i).getID() == ID){
				if(players.get(i).getPosition().equals("QB")) {
					return "QB";
				}
				else if(players.get(i).getPosition().equals("RB")) {
					return "RB";
				}
				else if(players.get(i).getPosition().equals("WR")) {
					return "WR";
				}
				else if(players.get(i).getPosition().equals("TE")) {
					return "TE";
				}
			}
		}
		return "ERROR";
	}
	
	//method for displaying the current roster of the user
	public String displayRoster(ArrayList<Player> players, int team) {
		for(int i = 0; i < players.size() ; i++) {
			
			for(int j = 0; j < 6; j++) {
				if(players.get(i).getID() == draftedPlayers[team-1][j]){
					players.get(i).printPlayer();
				
				}
			}
		
		}
		return "ERROR";
		
	}
	
}

