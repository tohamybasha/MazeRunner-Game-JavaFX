package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class LosePage extends Application{
//	public static void main(String[] args) {
//	Application.launch(args);
//	}
	private String text="",scoreRes="";
	public LosePage (String t ,String s) {
		this.text = t;
		this.scoreRes = s;
	}
	public LosePage() {}
	@Override
	public void start(Stage stage)  {
		Label score = new Label(text);//score.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		score.getStylesheets().add("css/TextStyles.css");
		Label scoreResult = new Label("Your score: "+scoreRes);//score.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		scoreResult.getStylesheets().add("css/TextStyles.css");
		VBox box = new VBox();
		Button retry = new Button("RETRY");retry.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		Button close = new Button("CLOSE");close.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		retry.getStylesheets().add("css/Styles2.css");close.getStylesheets().add("css/Styles2.css");
		VBox.setMargin(close, new Insets(10,0,0,15));
		
		//Actions
		close.setOnAction(e->{stage.close();});
		retry.setOnAction(e->{MainPage fp = MainPage.getInstance();
		try {fp.start(stage);} catch (Exception e1) {
			//e1.printStackTrace();
			}});
		
		Pane pane = new Pane();
		Scene lozz = new Scene(pane,700,600);
		
		box.getChildren().addAll(score,scoreResult,retry,close);
		box.setLayoutX((lozz.getWidth()/2)-60);box.setLayoutY((lozz.getHeight()/2)-20);
		VBox.setMargin(score, new Insets(0,0,5,0));
		VBox.setMargin(retry, new Insets(0,0,0,15));
		pane.getChildren().addAll(box);
		//BackGround :D
		Image loseBG = new Image("pics/background2.jpg");
		BackgroundSize bgSize = new BackgroundSize(lozz.getWidth(), lozz.getHeight(), false, false,false,false);
		BackgroundImage bgImg = new BackgroundImage(loseBG, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.CENTER, bgSize);
		pane.setBackground(new Background(bgImg));
		
		stage.setScene(lozz);
		stage.show();
		stage.setTitle("You Lost it");
		stage.setResizable(false);
	}

}
