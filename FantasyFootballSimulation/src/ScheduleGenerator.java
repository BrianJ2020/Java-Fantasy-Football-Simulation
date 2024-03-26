import java.util.ArrayList;
import java.util.List;

public class ScheduleGenerator {
    public static List<List<Integer>> schedule = new ArrayList<>();


	public static List<List<Integer>> generateSchedule(int numTeams) {
      
        List<Integer> teams = new ArrayList<>();
        for (int i = 1; i <= numTeams; i++) {
            teams.add(i);
        }

        for (int week = 0; week < 16; week++) {
            List<Integer> matches = new ArrayList<>();
            for (int i = 0; i < numTeams / 2; i++) {
                matches.add(teams.get(i)); // Adding team numbers
                matches.add(teams.get(numTeams - i - 1)); // Adding team numbers in reverse order
            }
            schedule.add(matches);

            // Rotate teams for next week
            int lastTeam = teams.remove(teams.size() - 1);
            teams.add(1, lastTeam);
        }
        return schedule;
    }
	
	public static void makeCurrentWeekMatchup(List<List<Integer>> schedule, int currentWeek) {
		
		for(int i = 0; i < Main.numOfTeams;i++) {
			
			if(i % 2 == 0) {
				int focusedTeam = schedule.get(currentWeek - 1).get(i);
				int focusedTeamOpponent = schedule.get(currentWeek - 1).get(i+1);

				Main.teams.get(focusedTeam-1).setCurrentWeekMatchup(focusedTeamOpponent);
				Main.teams.get(focusedTeamOpponent-1).setCurrentWeekMatchup(focusedTeam);

			}	
			
		}
		
	}
	
	public static void printCurrentWeekMatchups() {
		for(int i = 1; i <= (Main.numOfTeams+1)/2; i++)
		System.out.println("Team: " + i +" VS Team: " + Main.teams.get(i-1).getCurrentWeekMatchup());

	}
	
	
	
	
}