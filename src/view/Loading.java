
//Copied into main page ^_^ 




package view;

import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Loading extends Application{

//	public static void main(String[] args) {
//        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
//     Application.launch(args);
//    }
private int loading = 0;
	@Override
	public void start(Stage Pstage){
		// TODO Auto-generated method stub
		//Loading screen
		StackPane pane2 = new StackPane();
		Image bg1 = new Image("pics/loading.jpg");
		ImageView view = new ImageView(bg1);
		pane2.getChildren().add(view);
		Scene load = new Scene(pane2);
		Pstage.setTitle("loading...");Pstage.setScene(load);Pstage.show();Pstage.centerOnScreen();
		
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		pause.play();
		
		pause.setOnFinished(e->{
			Pstage.close();
			
		});
		}
	

}
