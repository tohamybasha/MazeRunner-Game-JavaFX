package model;

import java.util.ArrayList;

public class GiftFactory {

	private static ArrayList<String> gifts;
	static {
		gifts = new ArrayList<>();
		gifts.add("ammo");
		gifts.add("health");
		gifts.add("coin");
		gifts.add("shield");
	}
	//Main method that creates objects
	public static Cell create(String gift) {
		if (gift.equalsIgnoreCase("ammo")) {
			return new Gift("ammo");
		} else if (gift.equalsIgnoreCase("health")) {
			return new Gift("health");
		} else if (gift.equalsIgnoreCase("shield")) {
			return new Gift("shield");
		} else if (gift.equalsIgnoreCase("coin")) {
			return new Gift("coin");
		}
		return null;
	}
	
	public static ArrayList<String> getSupportedGifts() {
		return new ArrayList<String>(gifts);
	}
}
