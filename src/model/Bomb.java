package model;

public class Bomb implements Cell{

	private final String type = "Bomb";
	private final String path ="pics/bomb.jpg";
	
	@Override
	public String getType() {
		return type;
	}
	@Override
	public String getPath() {
		return path;
	}

}
