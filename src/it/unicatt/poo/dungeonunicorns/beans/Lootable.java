package it.unicatt.poo.dungeonunicorns.beans;

public interface Lootable {
	
	public String getLootId();
	
	public default boolean loot() {
		boolean result = false;
		return result;
	}

}
