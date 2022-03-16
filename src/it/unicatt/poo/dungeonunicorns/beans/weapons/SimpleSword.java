package it.unicatt.poo.dungeonunicorns.beans.weapons;

import it.unicatt.poo.dungeonunicorns.beans.armors.BlueArmor;
import it.unicatt.poo.dungeonunicorns.exceptions.AttributeNotSpecifiedException;
import it.unicatt.poo.dungeonunicorns.utils.IOUtils;

/**
 * A class which represent a simple sword
 * A sword which makes the damage of SIMPLE_SWORD_DAMAGE
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public class SimpleSword extends Weapon {
	
	/**
	 * the amount of damage done by this sword
	 */
	private static int SIMPLE_SWORD_DAMAGE;
	
	/**
	 * Id for recognize the type of loot element
	 */
	private static String LOOT_ID;
	
	static {
		try {
			SIMPLE_SWORD_DAMAGE = IOUtils.getIntegerAttribute("GeneralConfig.txt", "SimpleSword.Damage");
			LOOT_ID = IOUtils.getAttribute("GeneralConfig.txt", "SimpleSword.LootId");
		} catch(AttributeNotSpecifiedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Constructor for SimpleSword
	 * Sets the weaponDamage to SIMPLE_SWORD_DAMAGE
	 */
	private SimpleSword() {
		super(SIMPLE_SWORD_DAMAGE);
	}
	
	/**
	 * Method to get a Simple Sword
	 * 
	 * @return the instance of the sword
	 */
	public static SimpleSword getNewSimpleSword() {
		return new SimpleSword();
	}

	@Override
	public String getLootId() {
		return LOOT_ID;
	}

}
