package model;

public class Bomb2 implements Cell{

	private final String type = "Bomb2";
	private final String path ="pics/Bomb2.jpg";
	
	@Override
	public String getType() {
		return type;
	}
	@Override
	public String getPath() {
		return path;
	}

}
