package model;

import control.CreateGate;
import view.TheMaze;

public class MazeStructure {
	private static Cell[][] maze = new Cell[TheMaze.maxRows][TheMaze.maxCol];
	private final static int coin =1;
	private static final int tree =2;
	private final static int wall = 3;
	private final static int ammo = 4;
	private final static int health = 5;
	private final static int bombs =6 , bombs2 =7;
	private final static int shield = 8;
	//Singleton
	private static MazeStructure ourInstance = new MazeStructure();
	private MazeStructure() {
		fillRandomCells();
	}
	
	public static MazeStructure getInstance() {
		return ourInstance;
	}
	
	
	public static void reset() {
		fillRandomCells();
	}
	private static void fillRandomCells() {
		for(int i =0;i<TheMaze.maxRows;i++)
			for(int j=0;j<TheMaze.maxCol;j++) {
				if(!((i==Player.getPosY() && j==Player.getPosX()) || i==CreateGate.getPosY() && j==CreateGate.getPosX())) {
				int random = (int)(Math.random()*18);
				switch (random) {
				case tree: 	maze[i][j] = WallFactory.create("tree"); break;
				case wall: 	maze[i][j] = WallFactory.create("brick"); break;
				case ammo: 	maze[i][j] = GiftFactory.create("ammo"); break;
				case health:maze[i][j] = GiftFactory.create("health"); break;
				case coin: 	maze[i][j] = GiftFactory.create("coin"); break;
				case shield:maze[i][j] = GiftFactory.create("shield"); break;
				case bombs: maze[i][j] = new Bomb(); break;
				case bombs2: maze[i][j] = new Bomb2(); break;
				default : 	
					if(random >7 && random <11)
						maze[i][j] = new Wall();
					else 
					maze[i][j] = new Path(); break;
				}
			}
			}
	}


	public Cell[][] getMaze() {
		return maze;
	}
	
}
