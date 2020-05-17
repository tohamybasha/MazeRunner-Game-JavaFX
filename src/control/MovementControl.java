package control;

import decorator.ShieldPutOnPlayer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import memento.CareTaker;
import memento.GameStateSave;
import memento.Originator;
import model.Cell;
import model.MazeStructure;
import model.Path;
import model.Player;
import model.Wall;
import observer.PlayerObserver;
import state.Context;
import state.RunningState;
import state.StoppedState;
import view.LosePage;
import view.MainPage;
import view.TheMaze;
import view.WinPage;

public class MovementControl {
	public static int saves =0;
	private int[] lastMove = new int[6];
	private static Cell [][]mazee;
	private static GridPane mazeGrid ;
	public  static int maxRows = 15 , maxCol=17 , cellWidth = 40 , cellHeight = 39;
	private static Image [][] images ;
	private static ImageView [][] imagesViews;
	private static Player sengab;
	private ShieldPutOnPlayer shield; //The decorator 
	private Cell[] surroundObjects;
	private Stage pStage1 ;
	private InitializeMaze startMaze;
	private PlayerObserver observer;
//	private static MovementControl movement = new MovementControl();
//	public static void newMovement() {
//		movement = new MovementControl();
//	}
//	public static MovementControl getMovement() {
//		return movement;
//	}
//	private MovementControl game = new MovementControl();
	public MovementControl() {
				MazeStructure.reset();
				startMaze = new InitializeMaze();
				sengab = startMaze.getSengab();
				observer = new PlayerObserver(sengab);
				shield = new ShieldPutOnPlayer(sengab); 
				mazee = startMaze.getMazee();
				mazeGrid = startMaze.getMazeGrid();
				imagesViews = startMaze.getImagesViews();
				images = startMaze.getImages();
				surroundObjects = getSurround(sengab.getCurrX(),sengab.getCurrY());
	}
	
	public static GameStateSave saveCheckPoint() {
		GameStateSave checkPoint = new GameStateSave();
		checkPoint.setMazeGrid(mazeGrid);
//		checkPoint.setCurrentColumn(sengab.getCurrX());
//		checkPoint.setCurrentRow(sengab.getCurrY());
		checkPoint.setMaze(mazee);
		checkPoint.setPlayerHealth(sengab.getHealth());
		checkPoint.setScore(sengab.getScore());
		checkPoint.setPlayerAmmo(sengab.getAmmo());
		checkPoint.setPlayerArmor(sengab.getArmor());
		return checkPoint;
		
	}
	public static void loadCheckPoint(GameStateSave checkPoint) {
//		sengab.setCurrX(checkPoint.getCurrentColumn());
//		sengab.setCurrY(checkPoint.getCurrentRow());
		sengab.setHealth(checkPoint.getPlayerHealth());
		sengab.setAmmo(checkPoint.getPlayerAmmo());
		sengab.setArmor(checkPoint.getPlayerArmor());
		sengab.setScore(checkPoint.getScore());
		mazee = checkPoint.getMaze();
		mazeGrid = checkPoint.getMazeGrid();
				System.out.println("LOADED");
	}
//	private static void draw() {
//		for(int i =0;i<maxRows;i++)
//			for(int j=0;j<maxCol;j++) {
////				if(!((i==sengab.getPosY() && j==sengab.getPosX()) || i==gate.getPosY() && j==gate.getPosX())) {
//				images[i][j] = new Image(mazee[i][j].getPath());//mazee[i][j].getPath()
//				imagesViews[i][j] = new ImageView(images[i][j]);
//				imagesViews[i][j].setFitHeight(cellHeight);imagesViews[i][j].setFitWidth(cellWidth);
//				mazeGrid.add(imagesViews[i][j], j, i);
//				}
//			}

	public void setMazeGrid(GridPane mazeGrid) {
		this.mazeGrid = mazeGrid;
	}
	public static GridPane getMazeGrid() {
		return mazeGrid;
	}
	public Player getSengab() {
		return sengab;
	}
	
	public void move(String direction) {
		switch(direction) {
		case "UP":
			if(sengab.getCurrY() >= 0 && !surroundObjects[0].getType().equalsIgnoreCase("WALL")
					&& !surroundObjects[0].getType().equalsIgnoreCase("TREE")) {
				effects(sengab.getCurrY(),sengab.getCurrX(),sengab.getCurrY()-1,sengab.getCurrX());
			sengab.setCurrY(sengab.getCurrY()-1);
			surroundObjects = getSurround(sengab.getCurrX(), sengab.getCurrY());
			}
			break;
		case "DOWN":
			if(sengab.getCurrY()<TheMaze.maxRows && !surroundObjects[2].getType().equalsIgnoreCase("WALL")
					&& !surroundObjects[2].getType().equalsIgnoreCase("TREE")) {
				effects(sengab.getCurrY(),sengab.getCurrX(),sengab.getCurrY()+1,sengab.getCurrX());
			sengab.setCurrY(sengab.getCurrY()+1);
			surroundObjects = getSurround(sengab.getCurrX(), sengab.getCurrY());
			}
			break;
		case "RIGHT":
			if(sengab.getCurrX()<TheMaze.maxCol && !surroundObjects[1].getType().equalsIgnoreCase("WALL")
					&& !surroundObjects[1].getType().equalsIgnoreCase("TREE")) {
				effects(sengab.getCurrY(),sengab.getCurrX(),sengab.getCurrY(),sengab.getCurrX()+1);
			sengab.setCurrX(sengab.getCurrX()+1);
			surroundObjects = getSurround(sengab.getCurrX(), sengab.getCurrY());
			}
			break;
		case "LEFT":
			if(sengab.getCurrX()>=0 && !surroundObjects[3].getType().equalsIgnoreCase("WALL")
					&& !surroundObjects[3].getType().equalsIgnoreCase("TREE")) {
				effects(sengab.getCurrY(),sengab.getCurrX(),sengab.getCurrY(),sengab.getCurrX()-1);
			sengab.setCurrX(sengab.getCurrX()-1);
			surroundObjects = getSurround(sengab.getCurrX(), sengab.getCurrY());
			}
			break;
		}
		
	}
	public void effects(int i1 , int j1 , int i2 , int j2) {
		//This method checks what to do with our movement to next cell 
		//like going towards health , bomb ,ammo or normal path (Do nothing)	
//		Cell temp = mazee[i1][j1];
//		mazee[i1][j1] = mazee[i2][j2];
//		mazee[i2][j2] = temp;
		//Hit bomb reduce health 
//		Cell[][]tempMaze = mazee;
//		lastMove[4] = sengab.getCurrX();lastMove[5] = sengab.getCurrY();
		if(mazee[i2][j2].getType().equalsIgnoreCase("BOMB")) {
			if(sengab.getArmor()==0)
			{
				if(sengab.getScore() > 0) 
				sengab.decScore(20);
				
				sengab.decHealth();
			}
			else sengab.decArmor();
			mazee[i2][j2] = new Path();
			if(sengab.getHealth() <= 0)gameEnds(TheMaze.getStage(),"LOSE");
		}
		if(mazee[i2][j2].getType().equalsIgnoreCase("BOMB2")) {
			if(sengab.getArmor()==0)
			{
				if(sengab.getScore() > 0) 
				sengab.decScore(40);
				
				sengab.decHealth();
			}
			else {
				sengab.setArmor(0);
				sengab.decHealth();
			}
			mazee[i2][j2] = new Path();
			if(sengab.getHealth() <= 0)gameEnds(TheMaze.getStage(),"LOSE");
		}
		//Take health 
		if(mazee[i2][j2].getType().equalsIgnoreCase("HEALTH")) {
			sengab.incHealth();
			mazee[i2][j2] = new Path();
			
		}
		if(mazee[i2][j2].getType().equalsIgnoreCase("AMMO")) {
			sengab.incAmmo();
			mazee[i2][j2] = new Path();
			
		}
		if(mazee[i2][j2].getType().equalsIgnoreCase("GATE")) {
			gameEnds(TheMaze.getStage(),"Win");
			mazee[i2][j2] = new Path();
			
		}
		if(mazee[i2][j2].getType().equalsIgnoreCase("SHIELD")) {
			sengab.incArmor();
			mazee[i2][j2] = new Path();
			
		}
		if(mazee[i2][j2].getType().equalsIgnoreCase("COIN")) {
			sengab.incScore(20);
			mazee[i2][j2] = new Path();
//			mazee = tempMaze;
		}
		//Check if player got armor left
		if(sengab.getArmor()==0)sengab.setPath("normal");
		else sengab.setPath("shield");
		// Normal path just swap with our Player cell ^_^
			Cell temp = mazee[i1][j1];
			mazee[i1][j1] = mazee[i2][j2];
			mazee[i2][j2] = temp;


		refresh(i1,j1);
		refresh(i2,j2);
//		if(sengab.getScore()==40) goToCheckpoint();
	}
	//Refreshes specific Cell
	public void refresh(int i1 , int j1) {
		images[i1][j1] = new Image(mazee[i1][j1].getPath());
		imagesViews[i1][j1] = new  ImageView(images[i1][j1]);
		imagesViews[i1][j1].setFitHeight(cellHeight);imagesViews[i1][j1].setFitWidth(cellWidth);
		mazeGrid.add(imagesViews[i1][j1], j1, i1);
	}
	public void undoLastMove() {
		Cell temp = mazee[lastMove[0]][lastMove[1]];
		mazee[lastMove[0]][lastMove[1]] = mazee[lastMove[2]][lastMove[3]];
		mazee[lastMove[2]][lastMove[3]] = temp;
		refresh(lastMove[0],lastMove[1]);
		refresh(lastMove[2],lastMove[3]);
		sengab.setCurrX(lastMove[4]);sengab.setCurrY(lastMove[5]);
	}
	public void gameEnds(Stage stage,String state) {
		if(state.equalsIgnoreCase("LOSE")) {
			TheMaze.timeReset();
			TheMaze.stopState.doAction(TheMaze.context);
			LosePage lp = new LosePage("Your Health: 0",sengab.getScore()+"");
			
			try {
				startMaze = new InitializeMaze();
				lp.start(stage);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		else if (state.equalsIgnoreCase("WIN")) {
			saves = 0;
			TheMaze.timeReset();
			TheMaze.stopState.doAction(TheMaze.context);
			WinPage win = new WinPage(sengab.getScore()+"");
			try {
				win.start(stage);
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}
	public  Cell[] getSurround(int currX , int currY) {
		//Describes the surrounding 4 Cells starting from top and clockwise increasing index 
		Cell[] nearObjects = new Cell[4];
		if(currX-1 >= 0)
		{
			nearObjects[3] = mazee[currY][currX-1];
		}
		else nearObjects[3] = new Wall();
		
		if(currX+1 < TheMaze.maxCol)
		{
			nearObjects[1] = mazee[currY][currX+1];
		}
		else nearObjects[1] = new Wall();
		
		if(currY-1 >= 0)
		{
			nearObjects[0] = mazee[currY-1][currX];
		}
		else nearObjects[0] = new Wall();
		
		if(currY+1 < TheMaze.maxRows)
		{
			nearObjects[2] = mazee[currY+1][currX];
		}
		else nearObjects[2] = new Wall();

//		for(Cell e :nearObjects)System.out.println(e.getType());
		return nearObjects;
	}
	public void destroyTrees(int currX , int currY , String playerFace) {
		//Checks if there's any tree near the player and destroy it ( Change it with path object )
		if(!surroundObjects[0].getType().equalsIgnoreCase("WALL") && playerFace.equalsIgnoreCase("up")
				&&!surroundObjects[0].getType().equalsIgnoreCase("GATE")) { //TOP TREE
			destroyingScore(mazee[currY-1][currX].getType());
			mazee[currY-1][currX] = new Path();
			refresh(currY-1, currX);
		}
		if(!surroundObjects[1].getType().equalsIgnoreCase("WALL") && playerFace.equalsIgnoreCase("right")
				&&!surroundObjects[1].getType().equalsIgnoreCase("GATE")) { //Right TREE
			destroyingScore(mazee[currY][currX+1].getType());
			mazee[currY][currX+1] = new Path();
			refresh(currY, currX+1);
		}
		if(!surroundObjects[2].getType().equalsIgnoreCase("WALL") && playerFace.equalsIgnoreCase("down")
				&&!surroundObjects[2].getType().equalsIgnoreCase("GATE")) { //Down TREE
			destroyingScore(mazee[currY+1][currX].getType());
			mazee[currY+1][currX] = new Path();
			refresh(currY+1, currX);
		}
		if(!surroundObjects[3].getType().equalsIgnoreCase("WALL") && playerFace.equalsIgnoreCase("left")
				&&!surroundObjects[3].getType().equalsIgnoreCase("GATE")) { //Left TREE
			destroyingScore(mazee[currY][currX-1].getType());
			mazee[currY][currX-1] = new Path();
			refresh(currY, currX-1);
		}
		surroundObjects = getSurround(currX, currY); // Refresh surroundings after destroying trees
		sengab.decAmmo();
//		System.out.println(sengab.getAmmo());
	}
	private void destroyingScore(String type) {
		switch(type) {
		case "Tree": sengab.incScore(20);break;
		case "Health": sengab.decScore(10);break;
		case "Bomb": sengab.incScore(10);break;
		case "Shield": sengab.decScore(10);break;
		case "Ammo": sengab.decScore(10);break;
		case "Coin": sengab.decScore(10);break;
		case "Bomb2": sengab.incScore(20);break;
		}
	}
}
