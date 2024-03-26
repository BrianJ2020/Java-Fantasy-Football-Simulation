
public class TightEnd extends Player{

	private String position;
	
	public TightEnd() {
		super();
		position = "";
	}
	
	
	public TightEnd(int OVRrank, String name, String team, String position) {
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
