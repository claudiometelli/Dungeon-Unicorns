package it.unicatt.poo.dungeonunicorns.beans.armors;

import it.unicatt.poo.dungeonunicorns.beans.Lootable;
import it.unicatt.poo.dungeonunicorns.core.Entity;
import it.unicatt.poo.dungeonunicorns.exceptions.AttributeNotSpecifiedException;
import it.unicatt.poo.dungeonunicorns.utils.IOUtils;

/**
 * A class which represent a white armor 
 * A simple armor with a starting armorLife of WHITE_ARMOR_STARTING_LIFE_VALUE
 * 
 * @author claudiometelli
 *
 */

public class BlueArmor extends Armor {
	
	/**
	 * initial value for armorLife
	 */
	private static int BLUE_ARMOR_STARTING_LIFE_VALUE;
	
	/**
	 * Id for recognize the type of loot element
	 */
	private static String LOOT_ID;
	
	static {
		try {
			BLUE_ARMOR_STARTING_LIFE_VALUE = IOUtils.getIntegerAttribute("GeneralConfig.txt", "BlueArmor.Life");
			LOOT_ID = IOUtils.getAttribute("GeneralConfig.txt", "BlueArmor.LootId");
		} catch(AttributeNotSpecifiedException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Constructor for Blue Armor
	 * Sets the starting armorLife to BLUE_ARMOR_STARTING_LIFE_VALUE
	 */
	private BlueArmor() {
		super(BLUE_ARMOR_STARTING_LIFE_VALUE);
	}
	
	/**
	 * Method to get a Blue Armor
	 * 
	 * @return the instance of the armor
	 */
	public static BlueArmor getNewBlueArmor() {
		return new BlueArmor();
	}
	
	@Override
	public int getStartingLife() {
		return BLUE_ARMOR_STARTING_LIFE_VALUE;
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
