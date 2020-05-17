package model;

public class Gift implements Cell{
	// Ammo or Health or Score gift(Coin)
	private  String type ;
	private final String ammoPath ="pics/ammo.jpg";
	private final String coinPath ="pics/coin.jpg";
	private final String healthPath ="pics/health.gif";
	private final String shieldPath ="pics/shield.jpg";
	private String path ;
	public Gift(String type) {
		this.type = type;
		switch(type) {
		case "ammo":	this.path = ammoPath; this.type = "Ammo";break;
		case "health":	this.path = healthPath; this.type = "Health";break;
		case "coin":	this.path = coinPath; this.type = "Coin";break;
		case "shield":	this.path = shieldPath; this.type = "Shield";break;
		default: path ="";
		}
	}
	@Override
	public String getType() {
		return type;
	}
	@Override
	public String getPath() {
		return path;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
