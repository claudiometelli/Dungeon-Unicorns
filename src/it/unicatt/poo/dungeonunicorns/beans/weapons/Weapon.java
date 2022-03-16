package it.unicatt.poo.dungeonunicorns.beans.weapons;

import it.unicatt.poo.dungeonunicorns.beans.Lootable;

/**
 * A class which represent a generic weapon
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public abstract class Weapon implements Lootable {
	
	/**
	 * the damage done when you attack with the weapon
	 */
	private int weaponDamage;
	
	/**
	 * 
	 * @param weaponDamage - the amount of damage done by the weapon
	 */
	public Weapon(int weaponDamage) {
		this.weaponDamage = weaponDamage;
	}
	
	/**
	 * 
	 * @return  the amount of damage done by the weapon
	 */
	public int getWeaponDamage() {
		return weaponDamage;
	}
	
}
