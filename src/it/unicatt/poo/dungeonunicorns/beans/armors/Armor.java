package it.unicatt.poo.dungeonunicorns.beans.armors;

/**
 * A class which represent an armor for the USER? in the game
 * 
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public abstract class Armor {
/* DA RICONTROLLARE I COMMENTI*/	
	/**
	 * The strength of the armor
	 */
	private int armorLife;
	
	public Armor(int armorLife) {
		this.armorLife = armorLife;
	}
	
	/**
	 * @return the strength of the armor
	 */
	public int getArmorLife() {
		return armorLife;
	}
	
	public void setArmorLife(int armorLife) {
		this.armorLife = armorLife;
	}
}
