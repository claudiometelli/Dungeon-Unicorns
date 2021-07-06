package it.unicatt.poo.dungeonunicorns.beans.armors;

/**
 * A class which represent a white armor 
 * A simple armor with a starting armorLife of WHITE_ARMOR_STARTING_LIFE_VALUE
 * 
 * @author claudiometelli
 *
 */

public class WhiteArmor extends Armor {
	
	/**
	 * initial value for armorLife
	 */
	private static final int WHITE_ARMOR_STARTING_LIFE_VALUE = 50; 

	/**
	 * Constructor for White Armor
	 * Sets the starting armorLife to WHITE_ARMOR_STARTING_LIFE_VALUE
	 */
	public WhiteArmor() {
		super(WHITE_ARMOR_STARTING_LIFE_VALUE);
	}
	
}
