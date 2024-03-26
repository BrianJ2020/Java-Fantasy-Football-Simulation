import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFileAndOrganize {


	//Method to read the input file and sort it into an array list.
	public static ArrayList readFile(File myFile) {
		
	ArrayList<Player> players = new ArrayList<>();

	//using a buffered reader with a try catch acception for the input file
	try (BufferedReader reader = new BufferedReader(new FileReader(myFile))) {
		        	
		//variable to store the current line that the reader is reading
		String line;

		//while loop that will take in the data from the file so long as the line it is reading isn't null
		while((line = reader.readLine()) != null) {
				
			//array of strings to store each piece of player data
			//when a comma is read by the reader, it will create a new piece and add it to the array
			String[] pieces = line.split(",");
				
	            //the following section will asign each variable a place in the pieces array
	            int OVRrank = Integer.parseInt(pieces[0]); 
	            String name = pieces[1]; 
	            String team = pieces[2]; 
	            String position = pieces[3];

	            
	            //The following if else statements will check which position the player belongs to, and create the object accordingly 
	            if (position.equals("RB")) {
	                RunningBack RB = new RunningBack(OVRrank, name, team, position); 
	                players.add(RB);   //This will add the SavingsAccount object to the ArrayList
	                
	            } else if (position.equals("WR")) {
	                WideReceiver WR = new WideReceiver(OVRrank, name, team, position); 
	                players.add(WR);   //This will add the SavingsAccount object to the ArrayList
	                
	            }
	            else if (position.equals("TE")) {
	                TightEnd TE = new TightEnd(OVRrank, name, team, position); 
	                players.add(TE);   //This will add the SavingsAccount object to the ArrayList
	                
	            }
	            else if (position.equals("QB")) {
	                QuarterBack QB = new QuarterBack(OVRrank, name, team, position); 
	                players.add(QB);   //This will add the SavingsAccount object to the ArrayList
	                
	            }
			}
			  }
			  //catch in case of an exception
			 catch (IOException e) {
		            System.err.println("Error reading the input file: " + e.getMessage());
		            //printStackTrace will print the throwable if an exception occurs
		            e.printStackTrace();
		            return players;
		            
		      }	
		return players;
	}
	
	
	public static void printArray(ArrayList<Player> players) {

    	for (Player p: players) {
    		p.printPlayer();
    		
    	}
    	
	}
	
	
	
	
	
	
	
}
