package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import control.MovementControl;
import control.TestMaze;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Player;
import state.Context;
import state.RunningState;
import state.StoppedState;

public class TheMaze extends Application{

//	public static void main(String[] args) {
//		Application.launch(args);
//	}
public static int maxRows = MovementControl.maxRows , maxCol=MovementControl.maxCol , cellWidth = MovementControl.cellWidth , cellHeight = MovementControl.cellHeight;
private static GridPane mazeGrid ;
private String playerFace ="right";
//State 
public final static Context context = new Context();
public final static RunningState startState = new RunningState();
public final static StoppedState stopState = new StoppedState();
private boolean pauseManager = false;
//Memento
//private GameStateSave save = new GameStateSave();
//private Originator originator = new Originator();
//private CareTaker careTaker = new CareTaker();
private static IntegerProperty myInt;
private static PauseTransition time;
private MovementControl movement ;
private static Label scoreNum , healthNum , armorNum,ammoNum;
private static Stage stage;
private static BorderPane pane;
//public TheMaze(Stage pStage) throws Exception {
//	movement = new MovementControl();
////	start(pStage);
//}
//public TheMaze() { 	movement = new MovementControl(pStage); }
public TheMaze(Stage pStage , MovementControl loadedControl){
	movement = loadedControl;
}
public static Stage getStage() {
	return stage;
}
//public void setMovement(MovementControl movement) {
//	this.movement = movement;
//}
	@Override
	public void start(Stage pStage)  {
		stage = pStage;
//		//Loading screen
		
//		Stage stage2 = new Stage();
//		StackPane pane2 = new StackPane();
//		Image bg1 = new Image("pics/blue_loading.gif");
//		ImageView view = new ImageView(bg1);
//		pane2.getChildren().add(view);
//		Scene load = new Scene(pane2);
//		stage2.setTitle("loading...");stage2.setScene(load);stage2.show();
//		PauseTransition pause = new PauseTransition(Duration.seconds(1));pause.play();
//		pause.setOnFinished(e->{
//			stage2.close();
//		});
//		if(movement.equals(null))//System.out.println("NULLLLL");
		startState.doAction(context);
		
		//Timer set to count to 30
		myInt = new SimpleIntegerProperty();myInt.set(0);
		Label timerHolder = new Label("Time: ");
		Label timer = new Label();timer.getStylesheets().add("css/Styles2.css");
		timerHolder.setGraphic(timer);timerHolder.setContentDisplay(ContentDisplay.RIGHT);timerHolder.getStylesheets().add("css/TextStyles.css");
		timer.textProperty().bind(myInt.asString());
		
				time = new PauseTransition(Duration.seconds(1));
				time.setOnFinished(e2->{
					if(myInt.get() <= 120)
					{
						myInt.set(myInt.get()+1);
						time.playFromStart();
					}
					else {
						myInt.set(0);
						LosePage lp = new LosePage("Time is Over",Player.getScore()+"");
						try {lp.start(pStage);} catch (Exception e1) {
							//e1.printStackTrace();
							}
					}
				});
				time.play();
				
		//Test for memento
//		save.setCurrentColumn(sengab.getCurrX());
//		save.setCurrentRow(sengab.getCurrY());
//		save.setMaze(mazee);
//		save.setPlayerHealth(sengab.getHealth());
//		save.setScore(sengab.getScore());
//		save.setPlayerAmmo(sengab.getAmmo());
//		save.setPlayerArmor(sengab.getArmor());
//		originator.setState(save);
//		careTaker.add(originator.save());
//		
		//Panel
		VBox panel = new VBox();
		Label score = new Label("Score: ");score.getStylesheets().add("css/TextStyles.css");
		scoreNum = new Label("0");scoreNum.getStylesheets().add("css/Styles2.css");
		score.setGraphic(scoreNum);score.setContentDisplay(ContentDisplay.RIGHT);
		Label health = new Label("Health: ");health.getStylesheets().add("css/TextStyles.css");
		healthNum = new Label("100");healthNum.getStylesheets().add("css/Styles2.css");
		health.setGraphic(healthNum);health.setContentDisplay(ContentDisplay.RIGHT);
		Label armor = new Label("Armor: ");armor.getStylesheets().add("css/TextStyles.css");
		armorNum = new Label("0");armorNum.getStylesheets().add("css/Styles2.css");
		armor.setGraphic(armorNum);armor.setContentDisplay(ContentDisplay.RIGHT);
		Label ammo = new Label("Ammo: ");ammo.getStylesheets().add("css/TextStyles.css");
		ammoNum = new Label("6");ammoNum.getStylesheets().add("css/Styles2.css");
		ammo.setGraphic(ammoNum);ammo.setContentDisplay(ContentDisplay.RIGHT);
		Button stuck = new Button("Stuck?,Re-build here");//stuck.getStylesheets().add("css/TextStyles.css");
		VBox.setMargin(stuck, new Insets(20,0,0,20));stuck.setFocusTraversable(false);
		Button pausebt = new Button("Pause");//stuck.getStylesheets().add("css/TextStyles.css");
		VBox.setMargin(pausebt, new Insets(20,0,0,20));pausebt.setFocusTraversable(false);
		
		panel.setPadding(new Insets(10,30,0,0));
		panel.getChildren().addAll(timerHolder,score, health , armor , ammo ,stuck,pausebt);
		
		stuck.setOnMouseClicked(e->{
			stopState.doAction(context);
			timeReset();
			pStage.close();
			TestMaze maze = new TestMaze();
			try {
				maze.start(pStage);
			} catch (Exception e1) {
				//e1.printStackTrace();
			}
		});
		//Draw Objects
//		mazeGrid = movement.getMazeGrid();
		
//		mazeGrid.add(panel, maxCol+1, 0);mazeGrid.add(timer, maxCol+1, 1);
		
		
		pane = new BorderPane();pane.setCenter(mazeGrid);pane.setRight(panel);
		
		
		//Background
		Image bg = new Image("pics/background1.jpg");
		pane.setBackground(new Background(new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		mazeGrid.setPrefSize(500, 500);
		//mazeGrid.setGridLinesVisible(true);
		//Actions
		pausebt.setOnAction(e->{
			if(context.getState().equals(startState))
				pause();
			else
				{resume();pStage.show();mazeGrid.requestFocus();}
		});
				mazeGrid.setOnKeyPressed(e->{
					switch(e.getCode()) {
					case DOWN:	movement.move("DOWN");	playerFace="down";		break;
					case UP:	movement.move("UP");	playerFace="up";	break;
					case RIGHT:	movement.move("RIGHT");	playerFace="right";		break;
					case LEFT:	movement.move("LEFT");	playerFace="left";		break;
					case SPACE: //Weapon hit
						if(movement.getSengab().getAmmo() > 0)
							movement.destroyTrees(movement.getSengab().getCurrX(), movement.getSengab().getCurrY(),playerFace);
						//else outOfAmmo
						break;
					}	
				});
		Scene scene = new Scene(pane,835,590);//Width , Height
		pStage.setScene(scene);
		pStage.show();
		pStage.setResizable(false);
		pStage.setTitle("Maze");
		mazeGrid.requestFocus();
	}
	public static void reFocus() {
		stage.show();mazeGrid.requestFocus();
		mazeGrid = MovementControl.getMazeGrid();
		pane.setCenter(mazeGrid);
	}
	public void setMazeGrid(GridPane mazeGrid) {
		this.mazeGrid = mazeGrid;
	}
	public static IntegerProperty getMyInt() {
		return myInt;
	}

	public static void setMyInt(IntegerProperty myInt) {
		TheMaze.myInt = myInt;
	}

	public static void refreshPanel(int score , int health , int armor , int ammo ) {
		scoreNum.setText(score+"");ammoNum.setText(""+ammo);armorNum.setText(""+armor);
		healthNum.setText(""+health);
		
	}
	public void pause() {
		stopState.doAction(context);
		time.pause();
		Image paus = new Image("pics/pause.jpg");
		ImageView pause = new ImageView(paus);
		pause.setFitHeight(pane.getHeight());pause.setFitWidth(mazeGrid.getWidth());
		pane.setCenter(pause);
	}
	public void resume() {
		startState.doAction(context);
		time.play();
		pane.setCenter(mazeGrid);
	}
	public static void timeReset() {
		time.stop();
		myInt.set(0);
	}

	public static PauseTransition getTime() {
		return time;
	}
	
}
