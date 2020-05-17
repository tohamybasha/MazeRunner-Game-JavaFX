package model;

public class Tree implements Cell{

	private final String type = "Tree";
	private final String path ="pics/tree.gif";
	
	@Override
	public String getType() {
		return type;
	}
	@Override
	public String getPath() {
		return path;
	}

}
