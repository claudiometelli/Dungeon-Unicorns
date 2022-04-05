package it.unicatt.poo.dungeonunicorns.beans.armors;

import java.io.File;

import it.unicatt.poo.dungeonunicorns.beans.Lootable;
import it.unicatt.poo.dungeonunicorns.core.Entity;
import it.unicatt.poo.dungeonunicorns.core.support.Constants;
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
			WHITE_ARMOR_STARTING_LIFE_VALUE = IOUtils.getIntegerAttribute(Constants.CONFIG_DIRECTORY+File.separator+Constants.GENERAL_CONFIG_FILE_NAME, "WhiteArmor.Life");
			LOOT_ID = IOUtils.getAttribute(Constants.CONFIG_DIRECTORY+File.separator+Constants.GENERAL_CONFIG_FILE_NAME, "WhiteArmor.LootId");
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
		return LOOT_ID + "-" + getId();
	}
	
	@Override
	public boolean isGoingToLoot(Entity entity) {
		boolean result = false;
		Armor entityArmor = entity.getArmor();
		
		if(entityArmor == null) {
			result = true;
		} else if(getArmorLife() > entityArmor.getArmorLife()) {
			result = true;
		} else if(getArmorLife() < entityArmor.getArmorLife()) {
			result = false;
		} else if(getArmorLife() == entityArmor.getArmorLife() && getStartingLife() > entityArmor.getStartingLife()) {
			result = true;
		} else if(getArmorLife() == entityArmor.getArmorLife() && getStartingLife() < entityArmor.getStartingLife()) {
			result = false;
		}
		return result;
	}
	
	@Override
	public Lootable loot(Entity entity) {
		Lootable result = null;
		if(isGoingToLoot(entity)) {
			result = entity.getArmor();
			entity.setArmor(this);
		} else {
			result = this;
		}
		return result;
	}

	
}
