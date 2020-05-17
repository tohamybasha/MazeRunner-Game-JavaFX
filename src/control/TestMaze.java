package control;

import javafx.application.Application;
import javafx.stage.Stage;
import memento.CareTaker;
import memento.GameStateSave;
import memento.Memento;
import memento.Originator;
import view.MainPage;

public class TestMaze extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}
	//Memento
	private static GameStateSave save ;
	private static Originator originator = new Originator();
	private static CareTaker careTaker = new CareTaker();
	
	@Override
	public void start(Stage pStage){
		
		MainPage game = MainPage.getInstance();
		try {
			game.start(pStage);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	public static void save() {
		save = MovementControl.saveCheckPoint();
		originator.setState(save);
		careTaker.add(originator.save());
		System.out.println("CHECKPOINT SAVED");
		MovementControl.saves++;
	}
	public static void load() {
		GameStateSave load = originator.restore(careTaker.get(MovementControl.saves-1));
		MovementControl.loadCheckPoint(load);
	}

}
