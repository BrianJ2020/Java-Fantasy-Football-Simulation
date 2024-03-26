/**
 * Program Summary:
 * 
 * The Java program manages a simulated fantasy football league season, incorporating regular season and playoff phases. 
 * It handles tasks such as generating weekly matchups, tracking team scores, determining winners and losers, 
 * and displaying league standings. The program utilizes object-oriented programming principles, including classes 
 * and methods, along with various Java language features such as loops, conditional statements, collections, 
 * random number generation, exception handling, string manipulation, regular expressions, and output formatting.
 *
 * Java Knowledge Utilized:
 * 
 * 1. Object-Oriented Programming: Classes represent entities like teams, players, and schedule. Methods encapsulate functionality for generating matchups, printing standings, etc.
 * 2. Loops: Utilized for iterating over weeks, teams, and players.
 * 3. Conditional Statements: Used for menu choices, determining winners/losers, and setting score ranges.
 * 4. Collections: ArrayList stores team rankings, winners, and losers. HashMap stores team names and corresponding wins/scores.
 * 5. Random Number Generation: Math.random() generates random scores based on player rankings.
 * 6. Exception Handling: try-catch blocks handle interruptions in program execution (e.g., Thread.sleep()).
 * 7. String Manipulation: Extracts team names and numbers from strings.
 * 8. Regular Expressions: Used to extract numeric values from strings, e.g., team numbers.
 * 9. Formatting Output: printf method formats output for structured presentation, e.g., league standings.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

	//public static variables for other classes to use
	public static int numOfTeams = 0;
	public static int numOfUsers = 0;
	
	//variable to hold the draft order
	public static int[] playerDraftOrder;
	
	//flag for post season rounds
	public static int flag = 0;


	
	//array list that will store the sorted player data
	public static ArrayList<Player> players = new ArrayList<>();
	
	//array list that will store all the finalized teams
	public static ArrayList<Team> teams = new ArrayList<>();

	//list that will store the schedule for each week
	public static List<List<Integer>> schedule = new ArrayList<>();
	
	//array list that will store the top four teams at the end of the season
	public static ArrayList<Integer> topFourTeams = new ArrayList<>();
	
	public static void main(String[] args) {

		//Input file containing the player data
		File myFile = new File("Players.txt");
		//takes the players array list and sorts it throught the method ofreadFile
		players = ReadFileAndOrganize.readFile(myFile);
	
		
		
		//***SETTING UP THE LEAGUE***
		
		//creates an obj of MenuAndSettings
		MenuAndSettings menu = new MenuAndSettings();
		//uses the obj and call the initial menu method
		menu.initialMenu();
		//uses the obj and call the second menu method
		menu.secondMenu();
		//setting the public variables for the amount of teams,players per team, and number of users
		numOfTeams = menu.getAmountOfTeams();
		numOfUsers = menu.getNumberOfUsers();
		
		//Gets the draft picks of the Users
		playerDraftOrder = menu.DraftOrder(numOfUsers);
		
		//prints all the teams and their players 
		MenuAndSettings.printTeams();
	
		//***STARTING THE DRAFT***
		
		//uses the obj and calls the draft initializer menu
		menu.DraftInitializer();
		
		//creates an obj of the Draft class
		Draft initializer = new Draft();
		
		//uses the obj and calls the draft
		initializer.StandardDraft();
		
		//uses the schedule generator method and assigns it to the scheduel list based off all the teams
	    schedule = ScheduleGenerator.generateSchedule(numOfTeams);	
		
		//***STARTING REGULAR SEASON***
	    
	    //creates obj for the weekly scores class
		WeeklyScores scores = new WeeklyScores();
		
		//starts the regular season
		scores.regularSeason();
	
		//***STARTING POST SEASON***

		//uses the flag int to start the post season, 2 rounds in the post season, flag=0 , so the post season method will run twice
		while(flag < 2) {
			WeeklyScores.winners.clear();
			WeeklyScores.losers.clear();
			scores.postSeason();
			flag++;
			
		}
		
	}
	
			

}
