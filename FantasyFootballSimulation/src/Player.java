
public class Player {

	public String position;
	public int ID;
	public String name;
	public String team;
	
	public Player() {
		ID = 0;
		name = "";
		team = "";
	}
	
	public Player (int ID, String name, String team) {
		this.ID = ID;
		this.name = name;
		this.team = team;
	}
	
	public void printPlayer(){
		System.out.print("\n"+ ID+ " | " + name + " | " + team );
	}
	
	public void printPlayerName() {
		System.out.println(name);
	}
	
	public int getID() {
		return ID;
	}
	
	public String getPosition() {
		return position;
	}
	
	public String getName() {
		return name;
	}
}

