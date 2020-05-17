package control;

import decorator.ShieldPutOnPlayer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Cell;
import model.MazeStructure;
import model.Player;

public class InitializeMaze {
	private CreateGate gate = CreateGate.getInstance();
	private Player sengab = new Player();
	private GridPane mazeGrid = new GridPane();
	public static int maxRows = MovementControl.maxRows , maxCol=MovementControl.maxCol , cellWidth = MovementControl.cellWidth , cellHeight = MovementControl.cellHeight;
	private Image [][] images = new Image[maxRows][maxCol];
	private ImageView [][] imagesViews = new ImageView[maxRows][maxCol];
	private Cell [][]mazee;
	
	public InitializeMaze() {
		//2D Array holds the objects
		MazeStructure maze = MazeStructure.getInstance();//MazeStructure.getInstance();
		mazee = maze.getMaze();
		
		//Player start position
		int playerX = Player.getPosX() , playerY = Player.getPosY();
		mazee[playerY][playerX] = sengab;
		images[playerY][playerX] = new Image(sengab.getPath());
		imagesViews[playerY][playerX] = new ImageView(images[playerY][playerX]);
		imagesViews[playerY][playerX].setFitHeight(cellHeight);imagesViews[playerY][playerX].setFitWidth(cellWidth);
		mazeGrid.add(imagesViews[playerY][playerX], playerX, playerY);
		//Gate position upper right
		imagesViews[CreateGate.getPosY()][CreateGate.getPosX()] = gate.getImgV();
		mazee[CreateGate.getPosY()][CreateGate.getPosX()] = gate.getGate();
		mazeGrid.add(imagesViews[CreateGate.getPosY()][CreateGate.getPosX()], CreateGate.getPosX(), CreateGate.getPosY());

		//Draw random maze
		draw();
	}
	
	public Cell[][] getMazee() {
		return mazee;
	}

	public Player getSengab() {
		return sengab;
	}

	public Image[][] getImages() {
		return images;
	}

	public void draw() {
		for(int i =0;i<maxRows;i++)
			for(int j=0;j<maxCol;j++) {
//				if(!((i==sengab.getPosY() && j==sengab.getPosX()) || i==gate.getPosY() && j==gate.getPosX())) {
				images[i][j] = new Image(mazee[i][j].getPath());//mazee[i][j].getPath()
				imagesViews[i][j] = new ImageView(images[i][j]);
				imagesViews[i][j].setFitHeight(cellHeight);imagesViews[i][j].setFitWidth(cellWidth);
				mazeGrid.add(imagesViews[i][j], j, i);
				}
			}
	public GridPane getMazeGrid() {
		return mazeGrid;
	}


	public ImageView[][] getImagesViews() {
		return imagesViews;
	}
}
