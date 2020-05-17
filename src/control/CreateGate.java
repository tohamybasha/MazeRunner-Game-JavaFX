package control;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Gate;
import model.MazeStructure;
import view.TheMaze;

public class CreateGate {
	private static int posX = TheMaze.maxCol-1;
	private static int posY = 0;
	private Gate gate = new Gate();
	private Image img = new Image(gate.getPath());
	private ImageView imgV = new ImageView(img);
	
	//Singleton
	private static CreateGate ourInstance = new CreateGate();
	private CreateGate() {
		imgV.setFitHeight(TheMaze.cellHeight);
		imgV.setFitWidth(TheMaze.cellWidth);
	}
	public static CreateGate getInstance() {
			return ourInstance;
		}

	public Gate getGate() {
		return gate;
	}

	public static int getPosX() {
		return posX;
	}
	public static int getPosY() {
		return posY;
	}
	public ImageView getImgV() {
		return imgV;
	}
	
}
