package memento;

import javafx.scene.layout.GridPane;
import model.Cell;

public class GameStateSave {

	private int score;
	private GridPane mazeGrid;
	private Cell[][] maze;
	private int currentRow;
	private int currentColumn;
	private int playerHealth;
	private int playerArmor;
	private int playerAmmo;
	private long time;
	
	
	
	public GridPane getMazeGrid() {
		return mazeGrid;
	}

	public void setMazeGrid(GridPane mazeGrid) {
		this.mazeGrid = mazeGrid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Cell[][] getMaze() {
		return maze;
	}

	public void setMaze(Cell[][] maze) {
		this.maze = maze;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public int getCurrentColumn() {
		return currentColumn;
	}

	public void setCurrentColumn(int currentColumn) {
		this.currentColumn = currentColumn;
	}

	public int getPlayerHealth() {
		return playerHealth;
	}

	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}

	public int getPlayerArmor() {
		return playerArmor;
	}

	public void setPlayerArmor(int playerArmor) {
		this.playerArmor = playerArmor;
	}

	public int getPlayerAmmo() {
		return playerAmmo;
	}

	public void setPlayerAmmo(int playerAmmo) {
		this.playerAmmo = playerAmmo;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}


}
