import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeeklyScores {

	//holds the winners for each round of the playoffs
	public static ArrayList<Integer> winners = new ArrayList<>();
	//holds the losers for each round of the playoffs
	public static ArrayList<Integer> losers = new ArrayList<>();
	
	//used for printStandings method to check if it is the post season or not
	private static int currentWeek = 0;
	
	private Scanner input;

	public WeeklyScores() {
		this.input = new Scanner(System.in);
	}
	
	//method for the regular season
	public void regularSeason(){
		
		//outer for loop of each week (16 weeks in season)
		for(int i =1; i<17;i++) {
			
			ScheduleGenerator.makeCurrentWeekMatchup(ScheduleGenerator.schedule, i);
			
			int menuChoice1 = 0;
			
			while(menuChoice1 != 1) {
				menuChoice1 = MenuAndSettings.RegularSeasonWeeklyStandings();
				
				//See the current matchups for week i
				if(menuChoice1 == 2) {
					ScheduleGenerator.printCurrentWeekMatchups();
			        
				}
				//See the current league standings
				else if(menuChoice1 == 3) {
					HashMap<String, int[]> teams = new HashMap<>();
					
					for (int s = 1; s <= Main.numOfTeams; s++) {
			            String teamName = "Team " + s;
			            
			            int wins = Main.teams.get(s-1).getWins();
			           
			            int score = Main.teams.get(s-1).getTotalScore();
			            
			            teams.put(teamName, new int[]{wins, score});
					}
					
			        printStandings(teams);

				}
				
				else if (menuChoice1 > 3 || menuChoice1 <= 0){
					System.out.println("That isn't a valid answer");
				}
			}
				
			
				//inner for loop for each teams individual weekly score
				for(int j =1; j < Main.numOfTeams+1;j++) {
					int[] playerRankVals = {0,0,0,0,0,0,0};
					playerRankVals[0] = Main.teams.get(j-1).getQB();
					playerRankVals[1] = Main.teams.get(j-1).getRB1();
					playerRankVals[2] = Main.teams.get(j-1).getRB2();
					playerRankVals[3] = Main.teams.get(j-1).getWR1();
					playerRankVals[4] = Main.teams.get(j-1).getWR2();
					playerRankVals[5] = Main.teams.get(j-1).getTE();
						
					//inner for loop for each player on a team
					for(int z = 0; z < playerRankVals.length; z++) {
						int min = 0;
						int max = 50;
						
						int playerScore = 0;
						
						if(playerRankVals[z] <= 5) {
							min = 5;
							max = 55;
							int randomNum = (int)(Math.random() * (max - min + 1)) + min;
							playerScore += randomNum;
							
						}
						else if(playerRankVals[z] > 5 && playerRankVals[z] <= 10) {
							min = 4;
							max = 48;
							int randomNum = (int)(Math.random() * (max - min + 1)) + min;
							playerScore += randomNum;
							
						}
						else if(playerRankVals[z] > 10 && playerRankVals[z] <= 15) {
							min = 3;
							max = 46;
							int randomNum = (int)(Math.random() * (max - min + 1)) + min;
							playerScore += randomNum;

						}
						else if(playerRankVals[z] > 15 && playerRankVals[z] <= 20) {
							min = 2;
							max = 45;
							int randomNum = (int)(Math.random() * (max - min + 1)) + min;
							playerScore += randomNum;

						}
						else if(playerRankVals[z] > 20 && playerRankVals[z] <= 30) {
							min = 1;
							max = 44;
							int randomNum = (int)(Math.random() * (max - min + 1)) + min;
							playerScore += randomNum;

						}
						else if(playerRankVals[z] > 30 && playerRankVals[z] <= 40) {
							min = 0;
							max = 43;
							int randomNum = (int)(Math.random() * (max - min + 1)) + min;
							playerScore += randomNum;

						}
						else if(playerRankVals[z] > 40 && playerRankVals[z] <= 50) {
							min = 0;
							max = 42;
							int randomNum = (int)(Math.random() * (max - min + 1)) + min;
							playerScore += randomNum;

						}
						else if(playerRankVals[z] > 50 && playerRankVals[z] <= 70) {
							min = 0;
							max = 35;
							int randomNum = (int)(Math.random() * (max - min + 1)) + min;
							playerScore += randomNum;

						}
						else if(playerRankVals[z] > 70 && playerRankVals[z] <= 90) {
							min = 0;
							max = 25;
							int randomNum = (int)(Math.random() * (max - min + 1)) + min;
							playerScore += randomNum;

						}
						else if(playerRankVals[z] > 90 && playerRankVals[z] <= 105) {
							min = 0;
							max = 15;
							int randomNum = (int)(Math.random() * (max - min + 1)) + min;
							playerScore += randomNum;

						}
						
						if(z == 0) {
							Main.teams.get(j-1).setCurrentQBScore(playerScore);
							}
						
						else if(z == 1) {
							Main.teams.get(j-1).setCurrentRB1Score(playerScore);
							}
						else if(z == 2) {
							Main.teams.get(j-1).setCurrentRB2Score(playerScore);
							}
							
						else if(z == 3) {
							Main.teams.get(j-1).setCurrentWR1Score(playerScore);
							}
							
						else if(z == 4) {
							Main.teams.get(j-1).setCurrentWR2Score(playerScore);
							}
							
						else if(z == 5) {
							Main.teams.get(j-1).setCurrentTEScore(playerScore);
							}
							
					}
					
					Main.teams.get(j-1).setCurrentTotalWeeklyScore(Main.teams.get(j-1).getCurrentQBScore() + Main.teams.get(j-1).getCurrentRB1Score()+Main.teams.get(j-1).getCurrentRB2Score()+Main.teams.get(j-1).getCurrentWR1Score()+Main.teams.get(j-1).getCurrentWR2Score()+Main.teams.get(j-1).getCurrentTEScore());
	
				}
				
				System.out.println("\n\n------------------------");
				System.out.println("\nWeek " + i +" Summary:");
				for(int p = 1; p < Main.teams.size()+1; p++) {
					
					System.out.println("\nTEAM "+ p);
					int wOrL = WinOrLoss(p, Main.teams.get(p-1).getCurrentWeekMatchup());
					if(wOrL == 1) {
						System.out.println("\n"+ Main.teams.get(p-1).getCurrentTotalWeeklyScore() +"-"+ Main.teams.get(Main.teams.get(p-1).getCurrentWeekMatchup()-1).getCurrentTotalWeeklyScore()+ " Victory!");
					}
					else if(wOrL == 0) {
						System.out.println("\n"+ Main.teams.get(p-1).getCurrentTotalWeeklyScore() +"-"+ Main.teams.get(Main.teams.get(p-1).getCurrentWeekMatchup()-1).getCurrentTotalWeeklyScore()+ " Loss");					
					}
					System.out.println(Main.teams.get(p-1).getWins() + "-" + Main.teams.get(p-1).getLosses());
					System.out.println("\nTotal: " + Main.teams.get(p-1).getCurrentTotalWeeklyScore());
					
					for(int o = 1; o < 7; o++) {
						
						if(o == 1) 
							System.out.println(Draft.findPlayerName(Main.players, Main.teams.get(p-1).getQB())+ ": " + Main.teams.get(p-1).getCurrentQBScore());
						
						else if (o == 2)
							System.out.println(Draft.findPlayerName(Main.players, Main.teams.get(p-1).getRB1()) + ": " + Main.teams.get(p-1).getCurrentRB1Score());

						else if (o == 3)
							System.out.println(Draft.findPlayerName(Main.players, Main.teams.get(p-1).getRB2()) + ": " + Main.teams.get(p-1).getCurrentRB2Score());

						else if (o == 4)
							System.out.println(Draft.findPlayerName(Main.players, Main.teams.get(p-1).getWR1()) + ": " + Main.teams.get(p-1).getCurrentWR1Score());

						else if (o == 5)
							System.out.println(Draft.findPlayerName(Main.players, Main.teams.get(p-1).getWR2()) + ": " + Main.teams.get(p-1).getCurrentWR2Score());

						else if (o == 6)
							System.out.println(Draft.findPlayerName(Main.players, Main.teams.get(p-1).getTE()) + ": " + Main.teams.get(p-1).getCurrentTEScore());

					}
					
					System.out.println("\n------------------------");

				}
			}
			
			currentWeek++;
			HashMap<String, int[]> teams = new HashMap<>();
			
			for (int s = 1; s <= Main.numOfTeams; s++) {
	            String teamName = "Team " + s;
	            
	            int wins = Main.teams.get(s-1).getWins();
	           
	            int score = Main.teams.get(s-1).getTotalScore();
	            
	            teams.put(teamName, new int[]{wins, score});
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	System.out.println("\nEND OF REGULAR SEASON\n");

        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        printStandings(teams);

		}
	
	
	//method for post season
	public void postSeason(){
		
		int menuChoice1 = 0;
		
		if(Main.flag < 1) {
			while(menuChoice1 != 1) {
				menuChoice1 = MenuAndSettings.RegularSeasonWeeklyStandings();
				
				//See the current matchups for week i
				if(menuChoice1 == 2) {
					
					
					System.out.println("Team: "+ Main.topFourTeams.get(0)+ " VS " + "Team: "+ Main.topFourTeams.get(3));
					System.out.println("Team: "+ Main.topFourTeams.get(1)+ " VS " + "Team: "+ Main.topFourTeams.get(2));
						
				}
				//See the current league standings
				else if(menuChoice1 == 3 ) {
					HashMap<String, int[]> teams = new HashMap<>();
					
					for (int s = 1; s <= Main.numOfTeams; s++) {
			            String teamName = "Team " + s;
			            
			            int wins = Main.teams.get(s-1).getWins();
			           
			            int score = Main.teams.get(s-1).getTotalScore();
			            
			            teams.put(teamName, new int[]{wins, score});
					}
					
			        printStandings(teams);
	
				}
				
				else if (menuChoice1 > 3 || menuChoice1 <= 0){
					System.out.println("That isn't a valid answer");
				}
			}
		}
		else {
			while(menuChoice1 != 1) {
				menuChoice1 = MenuAndSettings.RegularSeasonWeeklyStandings2();
				
				//See the current matchups for week i
				if(menuChoice1 == 2) {
					
					
					System.out.println("Team: "+ Main.topFourTeams.get(0)+ " VS " + "Team: "+ Main.topFourTeams.get(3));
					System.out.println("Team: "+ Main.topFourTeams.get(1)+ " VS " + "Team: "+ Main.topFourTeams.get(2));
						
				}
				
				else if (menuChoice1 > 2 || menuChoice1 <= 0){
					System.out.println("That isn't a valid answer");
				}
			}
		}
		
		//inner for loop for each teams individual weekly score
		for(int j =1; j < Main.numOfTeams+1;j++) {
			int[] playerRankVals = {0,0,0,0,0,0,0};
			playerRankVals[0] = Main.teams.get(j-1).getQB();
			playerRankVals[1] = Main.teams.get(j-1).getRB1();
			playerRankVals[2] = Main.teams.get(j-1).getRB2();
			playerRankVals[3] = Main.teams.get(j-1).getWR1();
			playerRankVals[4] = Main.teams.get(j-1).getWR2();
			playerRankVals[5] = Main.teams.get(j-1).getTE();
				
			//inner for loop for each player on a team
			for(int z = 0; z < playerRankVals.length; z++) {
				int min = 0;
				int max = 50;
				
				int playerScore = 0;
				
				if(playerRankVals[z] <= 5) {
					min = 5;
					max = 55;
					int randomNum = (int)(Math.random() * (max - min + 1)) + min;
					playerScore += randomNum;
					
				}
				else if(playerRankVals[z] > 5 && playerRankVals[z] <= 10) {
					min = 4;
					max = 48;
					int randomNum = (int)(Math.random() * (max - min + 1)) + min;
					playerScore += randomNum;
					
				}
				else if(playerRankVals[z] > 10 && playerRankVals[z] <= 15) {
					min = 3;
					max = 46;
					int randomNum = (int)(Math.random() * (max - min + 1)) + min;
					playerScore += randomNum;

				}
				else if(playerRankVals[z] > 15 && playerRankVals[z] <= 20) {
					min = 2;
					max = 45;
					int randomNum = (int)(Math.random() * (max - min + 1)) + min;
					playerScore += randomNum;

				}
				else if(playerRankVals[z] > 20 && playerRankVals[z] <= 30) {
					min = 1;
					max = 44;
					int randomNum = (int)(Math.random() * (max - min + 1)) + min;
					playerScore += randomNum;

				}
				else if(playerRankVals[z] > 30 && playerRankVals[z] <= 40) {
					min = 0;
					max = 43;
					int randomNum = (int)(Math.random() * (max - min + 1)) + min;
					playerScore += randomNum;

				}
				else if(playerRankVals[z] > 40 && playerRankVals[z] <= 50) {
					min = 0;
					max = 42;
					int randomNum = (int)(Math.random() * (max - min + 1)) + min;
					playerScore += randomNum;

				}
				else if(playerRankVals[z] > 50 && playerRankVals[z] <= 70) {
					min = 0;
					max = 35;
					int randomNum = (int)(Math.random() * (max - min + 1)) + min;
					playerScore += randomNum;

				}
				else if(playerRankVals[z] > 70 && playerRankVals[z] <= 90) {
					min = 0;
					max = 25;
					int randomNum = (int)(Math.random() * (max - min + 1)) + min;
					playerScore += randomNum;

				}
				else if(playerRankVals[z] > 90 && playerRankVals[z] <= 105) {
					min = 0;
					max = 15;
					int randomNum = (int)(Math.random() * (max - min + 1)) + min;
					playerScore += randomNum;

				}
				
				if(z == 0) {
					Main.teams.get(j-1).setCurrentQBScore(playerScore);
					}
				
				else if(z == 1) {
					Main.teams.get(j-1).setCurrentRB1Score(playerScore);
					}
				else if(z == 2) {
					Main.teams.get(j-1).setCurrentRB2Score(playerScore);
					}
					
				else if(z == 3) {
					Main.teams.get(j-1).setCurrentWR1Score(playerScore);
					}
					
				else if(z == 4) {
					Main.teams.get(j-1).setCurrentWR2Score(playerScore);
					}
					
				else if(z == 5) {
					Main.teams.get(j-1).setCurrentTEScore(playerScore);
					}
					
			}
			
			
			Main.teams.get(j-1).setCurrentTotalWeeklyScore(Main.teams.get(j-1).getCurrentQBScore() + Main.teams.get(j-1).getCurrentRB1Score()+Main.teams.get(j-1).getCurrentRB2Score()+Main.teams.get(j-1).getCurrentWR1Score()+Main.teams.get(j-1).getCurrentWR2Score()+Main.teams.get(j-1).getCurrentTEScore());



		}
		
		System.out.println("\n\n------------------------");
		System.out.println("\nSummary:");
		for(int p = 0; p < Main.topFourTeams.size(); p++) {
			int wOrL = 0;
			System.out.println("\nTEAM "+ Main.topFourTeams.get(p));
			if(p==0) {
				wOrL = WinOrLoss(Main.topFourTeams.get(p), Main.teams.get(Main.topFourTeams.get(3)-1).getTeamNum());

			}
			else if(p==1) {
				wOrL = WinOrLoss(Main.topFourTeams.get(p), Main.teams.get(Main.topFourTeams.get(2)-1).getTeamNum());

			}
			else if(p==2) {
				wOrL = WinOrLoss(Main.topFourTeams.get(p), Main.teams.get(Main.topFourTeams.get(1)-1).getTeamNum());

			}
			else if(p==3) {
				wOrL = WinOrLoss(Main.topFourTeams.get(p), Main.teams.get(Main.topFourTeams.get(0)-1).getTeamNum());

			}
			
			if(wOrL == 1) {
				if(p==0) {
					System.out.println("\n"+ Main.teams.get(Main.topFourTeams.get(p)-1).getCurrentTotalWeeklyScore() +"-"+ Main.teams.get(Main.topFourTeams.get(3)-1).getCurrentTotalWeeklyScore()+ " Victory!");
				}
				else if(p==1) {
					System.out.println("\n"+ Main.teams.get(Main.topFourTeams.get(p)-1).getCurrentTotalWeeklyScore() +"-"+ Main.teams.get(Main.topFourTeams.get(2)-1).getCurrentTotalWeeklyScore()+ " Victory!");
				}
				else if(p==2) {
					System.out.println("\n"+ Main.teams.get(Main.topFourTeams.get(p)-1).getCurrentTotalWeeklyScore() +"-"+ Main.teams.get(Main.topFourTeams.get(1)-1).getCurrentTotalWeeklyScore()+ " Victory!");
				}
				else if(p==3) {
					System.out.println("\n"+ Main.teams.get(Main.topFourTeams.get(p)-1).getCurrentTotalWeeklyScore() +"-"+ Main.teams.get(Main.topFourTeams.get(0)-1).getCurrentTotalWeeklyScore()+ " Victory!");
				}
				
				winners.add(Main.topFourTeams.get(p));
				if(Main.flag < 1) {
					Main.teams.get(Main.topFourTeams.get(p)-1).addChampionship();
				}
			}
			else if(wOrL == 0) {
				if(p==0) {
					System.out.println("\n"+ Main.teams.get(Main.topFourTeams.get(p)-1).getCurrentTotalWeeklyScore() +"-"+ Main.teams.get(Main.topFourTeams.get(3)-1).getCurrentTotalWeeklyScore()+ " Loss");
				}
				else if(p==1) {
					System.out.println("\n"+ Main.teams.get(Main.topFourTeams.get(p)-1).getCurrentTotalWeeklyScore() +"-"+ Main.teams.get(Main.topFourTeams.get(2)-1).getCurrentTotalWeeklyScore()+ " Loss");
				}
				else if(p==2) {
					System.out.println("\n"+ Main.teams.get(Main.topFourTeams.get(p)-1).getCurrentTotalWeeklyScore() +"-"+ Main.teams.get(Main.topFourTeams.get(1)-1).getCurrentTotalWeeklyScore()+ " Loss");
				}
				else if(p==3) {
					System.out.println("\n"+ Main.teams.get(Main.topFourTeams.get(p)-1).getCurrentTotalWeeklyScore() +"-"+ Main.teams.get(Main.topFourTeams.get(0)-1).getCurrentTotalWeeklyScore()+ " Loss");
				}
				losers.add(Main.topFourTeams.get(p));
			}
			System.out.println(Main.teams.get(Main.topFourTeams.get(p)-1).getWins() + "-" + Main.teams.get(Main.topFourTeams.get(p)-1).getLosses());
			System.out.println("\nTotal: " + Main.teams.get(Main.topFourTeams.get(p)-1).getCurrentTotalWeeklyScore());
			
			for(int o = 1; o < 7; o++) {
				
				if(o == 1) 
					System.out.println(Draft.findPlayerName(Main.players, Main.teams.get(Main.topFourTeams.get(p)-1).getQB())+ ": " + Main.teams.get(Main.topFourTeams.get(p)-1).getCurrentQBScore());
				
				else if (o == 2)
					System.out.println(Draft.findPlayerName(Main.players, Main.teams.get(Main.topFourTeams.get(p)-1).getRB1()) + ": " + Main.teams.get(Main.topFourTeams.get(p)-1).getCurrentRB1Score());

				else if (o == 3)
					System.out.println(Draft.findPlayerName(Main.players, Main.teams.get(Main.topFourTeams.get(p)-1).getRB2()) + ": " + Main.teams.get(Main.topFourTeams.get(p)-1).getCurrentRB2Score());

				else if (o == 4)
					System.out.println(Draft.findPlayerName(Main.players, Main.teams.get(Main.topFourTeams.get(p)-1).getWR1()) + ": " + Main.teams.get(Main.topFourTeams.get(p)-1).getCurrentWR1Score());

				else if (o == 5)
					System.out.println(Draft.findPlayerName(Main.players, Main.teams.get(Main.topFourTeams.get(p)-1).getWR2()) + ": " + Main.teams.get(Main.topFourTeams.get(p)-1).getCurrentWR2Score());

				else if (o == 6)
					System.out.println(Draft.findPlayerName(Main.players, Main.teams.get(Main.topFourTeams.get(p)-1).getTE()) + ": " + Main.teams.get(Main.topFourTeams.get(p)-1).getCurrentTEScore());

			}
			
			System.out.println("\n------------------------");

		}
	
	currentWeek++;
	HashMap<String, int[]> teams = new HashMap<>();
	
	for (int s = 1; s <= Main.topFourTeams.size(); s++) {
        String teamName = "Team " + s;
        
        int wins = Main.teams.get(Main.topFourTeams.get(s-1)-1).getWins();
       
        int score = Main.teams.get(Main.topFourTeams.get(s-1)-1).getTotalScore();
        
        teams.put(teamName, new int[]{wins, score});
	}
	
	Main.topFourTeams.clear();
	Main.topFourTeams.add(winners.get(0));
	Main.topFourTeams.add(losers.get(0));
	Main.topFourTeams.add(losers.get(1));
	Main.topFourTeams.add(winners.get(1));

    printStandings(teams);
	
	if(Main.flag < 1) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	System.out.println("\nCHAMPIONSHIP GAME: Team "+ winners.get(0)+" VS Team "+winners.get(1));
	
	System.out.println("Fighting for 3rd place: Team " +losers.get(0)+" VS Team "+losers.get(1));
	}
	
	else {
	
		for(int i = 0; i < winners.size(); i++) {
			if(Main.teams.get(winners.get(i)-1).getChampionship() > 0) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Team " + winners.get(i) + " is this years champion!");

			}
		}

	}
	
}

	//method to determine if a team lost or won
	public int WinOrLoss(int team1, int team2) {
		
		if(Main.teams.get(team1-1).getCurrentTotalWeeklyScore() > Main.teams.get(team2-1).getCurrentTotalWeeklyScore()) {
			Main.teams.get(team1-1).addWins();
			return 1;
		}
		
		else {
			Main.teams.get(team1-1).addLosses();
			return 0;
		}
	}
	
	
	//method to print the current standings
	public static void printStandings(HashMap<String, int[]> teams) {
		
		
            //create a list of team names
            List<String> teamNames = new ArrayList<>(teams.keySet());
            
            //sort the team names based on number of wins and total score (tiebreaker)
            Collections.sort(teamNames, (team1, team2) -> {
                int[] stats1 = teams.get(team1);
                int[] stats2 = teams.get(team2);
                
                //compare number of wins first
                int compareWins = Integer.compare(stats2[0], stats1[0]);
                
                if (compareWins != 0) {
                    return compareWins;
                } else {
                    //if wins are equal, use total score as tiebreaker
                    return Integer.compare(stats2[1], stats1[1]);
                }
            });
            
         //print league standings
            if(currentWeek == 0) {
            
	            System.out.println("League Standings:");
	            System.out.println("=================");
	            for (int i = 0; i < teamNames.size(); i++) {
	                String team = teamNames.get(i);
	                int[] stats = teams.get(team);
	                //System.out.println((i+1)+". "+team+" ("+stats[0]+"-"+Main.teams.get(i).getLosses()+" "+stats[1]+ " pts");
	                System.out.printf("%d. %s - Wins: %d, Total Score: %d%n", (i + 1), team, stats[0], stats[1]);
	            }
            }
            
            else if(currentWeek == 1) {
            	System.out.println("Teams Going to Playoffs:");
	            System.out.println("=================");
	            for (int i = 0; i < 4; i++) {
	                String team = teamNames.get(i);
	                int[] stats = teams.get(team);
	                //System.out.println((i+1)+". "+team+" ("+stats[0]+"-"+Main.teams.get(i).getLosses()+" "+stats[1]+ " pts");
	                System.out.printf("%d. %s - Wins: %d, Total Score: %d%n", (i + 1), team, stats[0], stats[1]);
	            }
	            System.out.println("=================");

	            Pattern pattern = Pattern.compile("\\d+");
	            int count = 0;
	            
	            for (String s : teamNames) {
	                Matcher matcher = pattern.matcher(s);
	               
	                if (matcher.find()) {
	                    // Extracting the number from the string and converting it to an integer
	                    int number = Integer.parseInt(matcher.group());
		                Main.topFourTeams.add(number);
		                count++;
	                }
	                if(count > 3) {
	                	break;
	                }
	            }
            }
           
	}
		
}
