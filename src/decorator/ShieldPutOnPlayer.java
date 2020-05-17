package decorator;

import model.Cell;

public class ShieldPutOnPlayer extends ShieldDecorator implements Cell{

	public ShieldPutOnPlayer(_Character player) {
		super(player);
		
	}
	
	//Puts the shield on the normal player by changing armor from 0 to 100 and the image path ^__^
	public void putOnShield() {
		shielded.setPath("shield");
		shielded.setArmor(100);
	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public void setArmor(int armor) {
		// TODO Auto-generated method stub
		
	}

}
