import java.util.*;

public class Team {

	private int teamNum;
	private int QB;
	private int RB1;
	private int RB2;
	private int WR1;
	private int WR2;
	private int TE;

	private int currentTotalWeeklyScore;
	private int totalScore;
	
	private int currentQBScore;
	private int currentRB1Score;
	private int currentRB2Score;
	private int currentWR1Score;
	private int currentWR2Score;
	private int currentTEScore;
	
	private int wins;
	private int losses;
	
	private int championship;
	
	private int currentWeekMatchup;
	


	
	public Team() {
		int teamNum;
		int QB;
		int RB1;
		int RB2;
		int WR1;
		int WR2;
		int TE;
		int wins;
		int losses;
		int totalScore;
		int championship;
	}
	
	public Team (int teamNum, int QB, int RB1, int RB2, int WR1, int WR2, int TE) {
		this.teamNum = teamNum;
		this.QB = QB;
		this.RB1 = RB1;
		this.RB2 = RB2;
		this.WR1 = WR1;
		this.WR2 = WR2;
		this.TE = TE;
		this.wins = 0;
		this.losses = 0;
		this.totalScore = 0;
		this.championship = 0;

	}
	
	public void printTeam(){
		System.out.print("\n \nTeam "+ teamNum+ " \nQB: " + findPlayerName(Main.players,QB));
		System.out.print("\nRB1: "+findPlayerName(Main.players,RB1));
		System.out.print("\nRB2: "+findPlayerName(Main.players,RB2));
		System.out.print("\nWR1: "+findPlayerName(Main.players,WR1));
		System.out.print("\nWR2: "+findPlayerName(Main.players,WR2));
		System.out.print("\nTE: "+findPlayerName(Main.players,TE));
	}
	
	
	public static String findPlayerName(ArrayList<Player> players, int ID) {
		for(int i = 0; i < players.size() ; i++) {
			if(players.get(i).getID() == ID){
				return players.get(i).getName();
			}
		}
		return "UNKOWN";
	}
	
	public int getTeamNum() {
		return teamNum;
	}
	public int getQB(){
		return QB;
	}
	public int getRB1(){
		return RB1;
	}
	public int getRB2(){
		return RB2;
	}
	public int getWR1(){
		return WR1;
	}
	public int getWR2(){
		return WR2;
	}
	public int getTE(){
		return TE;
	}
	
	
	public void setCurrentTotalWeeklyScore(int a) {
		this.currentTotalWeeklyScore = a;
		this.totalScore += a;
	}
	
	public void setCurrentQBScore(int a) {
		this.currentQBScore = a;
	}
	
	public void setCurrentRB1Score(int a) {
		this.currentRB1Score = a;
	}
	public void setCurrentRB2Score(int a) {
		this.currentRB2Score = a;
	}
	public void setCurrentWR1Score(int a) {
		this.currentWR1Score = a;
	}
	public void setCurrentWR2Score(int a) {
		this.currentWR2Score = a;
	}
	public void setCurrentTEScore(int a) {
		this.currentTEScore = a;
	}
	public void setCurrentWeekMatchup(int a) {
		this.currentWeekMatchup = a;
	}
	public void addWins() {
		wins++;
	}
	public void addLosses() {
		losses++;
	}
	public void addChampionship() {
		championship++;
	}
	
	
	public int getCurrentTotalWeeklyScore() {
		return currentTotalWeeklyScore;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public int getCurrentQBScore() {
		return currentQBScore;
	}
	public int getCurrentRB1Score() {
		return currentRB1Score;
	}
	public int getCurrentRB2Score() {
		return currentRB2Score;
	}
	public int getCurrentWR1Score() {
		return currentWR1Score;
	}
	public int getCurrentWR2Score() {
		return currentWR2Score;
	}
	public int getCurrentTEScore() {
		return currentTEScore;
	}
	public int getCurrentWeekMatchup() {
		return currentWeekMatchup;
	}
	public int getWins() {
		return wins;
	}
	public int getLosses() {
		return losses;
	}
	public int getChampionship() {
		return championship;
	}
	
	
}
