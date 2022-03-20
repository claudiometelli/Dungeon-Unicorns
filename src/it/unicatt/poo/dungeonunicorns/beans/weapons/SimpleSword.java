package it.unicatt.poo.dungeonunicorns.beans.weapons;

import it.unicatt.poo.dungeonunicorns.beans.Lootable;
import it.unicatt.poo.dungeonunicorns.beans.armors.Armor;
import it.unicatt.poo.dungeonunicorns.beans.armors.BlueArmor;
import it.unicatt.poo.dungeonunicorns.core.Entity;
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
	
	private Integer swordAttacksLeft;
	
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
		try {
			swordAttacksLeft = IOUtils.getIntegerAttribute("GeneralConfig.txt", "SimpleSword.Attacks");
		} catch(AttributeNotSpecifiedException e) {
			System.err.println(e.getMessage());
		}
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
	public Integer attack() {
		Integer result = null;
		if(swordAttacksLeft > 0) {
			swordAttacksLeft--;
			result = SIMPLE_SWORD_DAMAGE;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return super.toString() + "; AttacksLeft: " + swordAttacksLeft;
	}
	
	@Override
	public String getLootId() {
		return LOOT_ID;
	}
	
	@Override
	public boolean isGoingToLoot(Entity entity) {
		//TODO
		boolean result = true;
		return result;
	}
	
	@Override
	public Lootable loot(Entity entity) {
		Lootable result = null;
		if(isGoingToLoot(entity)) {
			result = entity.getWeapon();
			entity.setWeapon(this);
		} else {
			result = this;
		}
		return result;
	}

}
