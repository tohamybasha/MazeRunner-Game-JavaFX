package view;

import control.MovementControl;
import control.TestMaze;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.MazeStructure;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Screen;

public class MainPage extends Application{
//	public static void main(String[] args) {
//		Application.launch(args);
//	}
private Button load;
private int loading = 0;
private MovementControl mv;
private TheMaze maze;
private boolean gameCreated = false;
private static MainPage page = new MainPage();
private MainPage() {
	mv = new MovementControl();
	gameCreated = true;
}
public static MainPage getInstance() {
	return page;
}
public  void disableLoad() {
	load.setDisable(true);
}
public  void enableLoad() {
	load.setDisable(false);
}
	@Override
	public void start(Stage pStage)  {	
		Label diff = new Label("Welcome to MazeRunner Game");diff.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		diff.getStylesheets().add("css/TextStyles.css");
		ToggleGroup group = new ToggleGroup();
		RadioButton easy = new RadioButton("Easy");easy.setToggleGroup(group);easy.getStylesheets().add("css/TextStyles.css");
		RadioButton med = new RadioButton("Medium");med.setToggleGroup(group);med.getStylesheets().add("css/TextStyles.css");
		RadioButton hard = new RadioButton("Hard");hard.setToggleGroup(group);hard.getStylesheets().add("css/TextStyles.css");
		
		VBox box = new VBox();
		Button newGame = new Button("NewGame");newGame.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		load = new Button("Load Checkpoint");load.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		Button exit = new Button("Exit");exit.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		box.getChildren().addAll(newGame,load,exit);
		if(MovementControl.saves == 0)load.setDisable(true);
		else load.setDisable(false);
		//Styles
		newGame.getStylesheets().add("css/TextStyles.css");
		load.getStylesheets().add("css/TextStyles.css");
		exit.getStylesheets().add("css/TextStyles.css");
		VBox.setMargin(load, new Insets(15,0,15,0));
//		VBox.setMargin(exit, new Insets(15));
		
		Pane pane = new Pane();pane.getChildren().addAll(diff,box);
		Image bg = new Image("pics/background2.jpg");
		
		pane.setBackground(new Background(new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		Scene scene = new Scene(pane,705,630); //Width , Height
		box.setLayoutX((scene.getWidth()/2)-90);box.setLayoutY(scene.getHeight()/2);
		VBox.setMargin(newGame, new Insets(0,0,0,30));VBox.setMargin(exit, new Insets(0,0,0,60));
		diff.setLayoutX(scene.getWidth()/3);diff.setLayoutY((scene.getHeight()/2)-70);
		//Actions
		newGame.setOnAction(e->{
		
				//Loading screen
				StackPane pane2 = new StackPane();
				Image bg1 = new Image("pics/loading.jpg");
				ImageView view = new ImageView(bg1);
				pane2.getChildren().add(view);
				Scene loads = new Scene(pane2);
				pStage.setTitle("loading...");pStage.setScene(loads);pStage.show();pStage.centerOnScreen();
				PauseTransition pause = new PauseTransition(Duration.seconds(0.6));
//				if(gameCreated) 
					pause.play();
				pause.setOnFinished(e2->{
					if (loading < 1) {
					loading++;
					System.out.println("Loading...");
					pause.playFromStart();
					}
					else {
						System.out.println("Game Loaded");
						pStage.close();
						load.setDisable(false);
					if(!gameCreated) {
						Loading lo = new Loading();try {lo.start(pStage);} catch (Exception e1) {//e1.printStackTrace();
						}
						mv = new MovementControl();
					}
					else gameCreated = false;
					try {
						maze = new TheMaze(pStage,mv);
						maze.setMazeGrid(mv.getMazeGrid());
						maze.start(pStage);
					} catch (Exception e1) {//e1.printStackTrace(); 
					}
					}
				});});
				
			
//				pStage.show();
//				mv = new MovementControl();
//				MovementControl.newMovement();
//				MovementControl mv = new MovementControl();
//				MovementControl mv = MovementControl.getMovement();

		load.setOnAction(e->{
			try {
				TestMaze.load();
				
				maze = new TheMaze(pStage,mv);
				maze.setMazeGrid(mv.getMazeGrid());
				maze.start(pStage);
				TheMaze.refreshPanel(mv.getSengab().getScore(), mv.getSengab().getHealth(), mv.getSengab().getArmor(), mv.getSengab().getAmmo());

//				maze.reFocus();
//				maze.setMazeGrid(MovementControl.getMovement().getMazeGrid());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
		});
		exit.setOnAction(e->{pStage.close();});

		pStage.setScene(scene);
		pStage.show();
		pStage.setResizable(false);
		pStage.setTitle("Maze");
	
	}

}
