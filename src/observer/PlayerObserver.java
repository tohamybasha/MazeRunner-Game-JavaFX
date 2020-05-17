package observer;

import control.MovementControl;
import control.TestMaze;
import view.TheMaze;

public class PlayerObserver implements Observer{
	//Observed properties of the player
	private int armor ,health,ammo,score;
	// Static used as a counter
	private static int observerIDTracker = 0;
	//Used to track observers
	private int observerID;
	private int highestScore = 0;
	private Subject player;
	
	public PlayerObserver(Subject player){
		        // Store the reference to the player object so
		        // I can make calls to its methods
		        this.player = player;
		        // Assign an observer ID and increment the static counter
		        this.observerID = ++observerIDTracker;
		        // Message notifies user of new observer
		        System.out.println("New Observer " + this.observerID);
		        // Add the observer to the Subjects ArrayList
		        player.addObserver(this);
		    }
	@Override
	public void update(int score, int health, int armor, int ammo) {
		this.ammo = ammo;
		this.score = score;
		this.health = health;
		this.armor = armor;
		if(this.score > highestScore) {
			highestScore = this.score;
			MovementControl.saveCheckPoint();
			TestMaze.save();
		}
		TheMaze.refreshPanel(score, health, armor, ammo);
//		printThem();
	}
	public void printThem() {
		System.out.println("\nScore: " + score + "\nHealth: " +  health + 
				"\nAmmo: " + ammo  + "\nArmor: " + armor + "\n");
		
	}
}
