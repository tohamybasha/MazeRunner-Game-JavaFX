package memento;

public class Originator {
	
    private GameStateSave state;

    public void setState(GameStateSave state) {
        this.state = state;
    }

    public Memento save() {
        return new Memento(state);
    }
   
    public GameStateSave restore(Memento memento) {
        state = memento.getState();
        return state;
    }
}
