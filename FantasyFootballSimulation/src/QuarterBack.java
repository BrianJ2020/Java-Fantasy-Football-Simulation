
public class QuarterBack extends Player{

	private String position;
	
	
	public QuarterBack() {
		super();
		position = "";
	}
	
	
	public QuarterBack(int OVRrank, String name, String team, String position) {
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
