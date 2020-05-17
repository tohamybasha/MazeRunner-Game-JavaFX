package model;

import java.util.ArrayList;

import decorator._Character;
import observer.Observer;
import observer.Subject;
import view.TheMaze;

public class Player implements Cell ,Subject,_Character{
	//Properties
	private ArrayList<Observer> observers;
	private int armor ;
	private int health,ammo;
	private static int score;
	private static int posX = 0,posY = TheMaze.maxRows-1;;
	private  int currX , currY;
	private String path;
	private final String shieldedPath = "pics/ShieldedPlayer.png";
	private final String normalPath = "pics/player1.png";
	private final String type = "player";
	
//Singleton
//	private static Player ourInstance = new Player();
//	public static Player getInstance() {
//		return ourInstance;
//	}

	public Player() {
		observers = new ArrayList<Observer>();
		path = normalPath;
		this.score = 0;
		this.ammo = 6;
		this.health =100;
		this.armor = 0;
		this.currX = posX; // Columns
		this.currY =  posY;  // Rows
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}
	public void setPath(String type) {
		switch(type) {
		case "shield":
			this.path = shieldedPath;
			break;
		case "normal":
			this.path = normalPath;break;
			
		}
	}

	public static int getScore() {
		return score;
	}

	public void incScore(int plus) {
		score+=plus;
		notifyObservers();
	}
	public void decScore(int minus) {
		score-=minus;
		if(score < 0 )
		score = 0;
		notifyObservers();
	}
	public void decAmmo() {
		ammo--;
		notifyObservers();
	}
	public void decArmor() {
		armor-=50;
		notifyObservers();
	}
	public void incArmor() {
		if(armor <=50)
		armor+=50;
		else armor = 100;
		notifyObservers();
	}
	public void decHealth() {
		health-=50;
		notifyObservers();
	}
	public void incAmmo() {
		if(ammo <5)
		ammo+=2;
		else if (ammo > 4)ammo = 6;
		notifyObservers();
	}
	public void incHealth() {
		if(health <=50)
		health+=50;
		else if (health >50)health = 100;
		notifyObservers();
	}
	public int getCurrX() {
		return currX;
	}

	public void setCurrX(int currX) {
		this.currX = currX;
	}

	public int getCurrY() {
		return currY;
	}

	public void setCurrY(int currY) {
		this.currY = currY;
	}

	public String getPath() {
		return path;
	}
	
	public int getArmor() {
		return armor;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
		notifyObservers();
	}
	public int getAmmo() {
		return ammo;
	}
	public void setAmmo(int ammo) {
		this.ammo = ammo;
		notifyObservers();
	}
	public static int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public static int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	@Override
	public String getType() {
		return type;
	}

	@Override
	public void addObserver(Observer newObserver) {
		// Adds a new observer to the ArrayList
		 observers.add(newObserver);
	}

	@Override
	public void removeObserver(Observer ob) {
		observers.remove(ob);
	}

	@Override
	public void notifyObservers() {
		// Cycle through all observers and notifies them of
		        // price changes
		        for(Observer observer : observers)
		            observer.update(score ,health ,armor ,ammo);
}

	
	
	
}
