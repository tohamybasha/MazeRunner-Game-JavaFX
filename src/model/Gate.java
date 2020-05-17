package model;



public class Gate implements Cell{
	private final String type = "Gate";
	private final String path ="pics/gate.jpg";
	
	public Gate() {
		
	}
	@Override
	public String getType() {
		return type;
	}
	@Override
	public String getPath() {
		return path;
	}

	
	
}
