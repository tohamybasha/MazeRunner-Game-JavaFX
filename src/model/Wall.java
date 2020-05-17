package model;

public class Wall implements Cell{

	private final String type = "Wall";
	private final String path ="pics/Brickwall.png";
	
	@Override
	public String getType() {
		return type;
	}
	@Override
	public String getPath() {
		return path;
	}

}
