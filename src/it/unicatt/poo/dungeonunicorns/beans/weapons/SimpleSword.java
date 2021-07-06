package it.unicatt.poo.dungeonunicorns.beans.weapons;

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
	 * the amoount of damage done by this sword
	 */
	private static final int SIMPLE_SWORD_DAMAGE = 20;
	
	/**
	 * Constructor for SimpleSword
	 * Sets the weaponDamage to SIMPLE_SWORD_DAMAGE
	 */
	public SimpleSword() {
		super(SIMPLE_SWORD_DAMAGE);
	}

}
