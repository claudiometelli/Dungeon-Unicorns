package it.unicatt.poo.dungeonunicorns.beans.armors;

import it.unicatt.poo.dungeonunicorns.exceptions.AttributeNotSpecifiedException;
import it.unicatt.poo.dungeonunicorns.utils.IOUtils;

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
	private static int WHITE_ARMOR_STARTING_LIFE_VALUE; 
	
	/**
	 * Id for recognize the type of loot element
	 */
	private static String LOOT_ID;
	
	static {
		try {
			WHITE_ARMOR_STARTING_LIFE_VALUE = IOUtils.getIntegerAttribute("GeneralConfig.txt", "WhiteArmor.Life");
			LOOT_ID = IOUtils.getAttribute("GeneralConfig.txt", "WhiteArmor.LootId");
		} catch(AttributeNotSpecifiedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Constructor for White Armor
	 * Sets the starting armorLife to WHITE_ARMOR_STARTING_LIFE_VALUE
	 */
	private WhiteArmor() {
		super(WHITE_ARMOR_STARTING_LIFE_VALUE);
	}
	
	public static WhiteArmor getNewWhiteArmor() {
		return new WhiteArmor();
	}
	
	@Override
	public int getStartingLife() {
		return WHITE_ARMOR_STARTING_LIFE_VALUE;
	}

	@Override
	public String getLootId() {
		return LOOT_ID;
	}

	
}
