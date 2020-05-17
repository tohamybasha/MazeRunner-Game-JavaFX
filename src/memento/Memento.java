package memento;

public class Memento {

	private GameStateSave state;

	public Memento(GameStateSave state) {
		this.state = state;
	}

	public GameStateSave getState() {
		return state;
	}
}
