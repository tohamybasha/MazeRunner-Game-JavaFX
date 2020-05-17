package state;

public class StoppedState implements State {

	   public void doAction(Context context) {
	      System.out.println("Player is in stop state");
	      context.setState(this);	
	   }
	   public String toString(){
	      return "Stop State";
	   }
	}
