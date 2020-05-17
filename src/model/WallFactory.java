package model;

public class WallFactory {
	public static Cell create(String wall) {
		if (wall.equalsIgnoreCase("brick")) {
			return new Wall();
		} else if (wall.equalsIgnoreCase("tree")) {
			return new Tree();
		}
		return null;
	}
}
