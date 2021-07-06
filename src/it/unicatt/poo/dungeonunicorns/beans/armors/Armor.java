package it.unicatt.poo.dungeonunicorns.beans.armors;

/**
 * A class which represent an armor for a general entity in the game
 * 
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public abstract class Armor {
	
	/**
	 * Life points for the armor
	 */
	private int armorLife;
	
	/**
	 * Constructor for class Armor
	 * 
	 * @param armorLife - the initial amount of life points for the armor
	 */
	public Armor(int armorLife) {
		this.armorLife = armorLife;
	}
	
	/**
	 * 
	 * @return the actual life points for the armor
	 */
	public int getArmorLife() {
		return armorLife;
	}
	
	/**
	 * 
	 * @param armorLife - the amount of life points to set the armorLife
	 */
	public void setArmorLife(int armorLife) {
		this.armorLife = armorLife;
	}
}
