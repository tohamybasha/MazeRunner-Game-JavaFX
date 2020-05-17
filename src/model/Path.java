package model;

public class Path implements Cell{
	private final String type = "Path";
	private final String path ="pics/path.jpg";
	
	@Override
	public String getType() {
		return type;
	}
	@Override
	public String getPath() {
		return path;
	}


}
