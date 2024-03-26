
public class RunningBack extends Player {

	private String position;
	
	public RunningBack() {
		super();
		position = "";
	}
	
	public RunningBack(int OVRrank, String name, String team, String position) {
		super(OVRrank,name,team);
		this.position = position;
	}
	
	@Override
	public void printPlayer(){
		super.printPlayer();
		System.out.print(" | " +position + "\n");
		System.out.println("-------------------------------------");

	}
	
	public String getPosition() {
		return position;
	}
	
	
	
}
