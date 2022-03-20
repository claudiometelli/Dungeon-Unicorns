package it.unicatt.poo.dungeonunicorns.beans.weapons;

import it.unicatt.poo.dungeonunicorns.beans.Lootable;
import it.unicatt.poo.dungeonunicorns.managers.IdManager;

/**
 * A class which represent a generic weapon
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public abstract class Weapon implements Lootable {
	
	/**
	 * Id used to recognize the weapon
	 */
	private int id;
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
		this.id = IdManager.getNewId();
	}
	
	/*
	 * @return the id of the weapon
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @return  the amount of damage done by the weapon
	 */
	public int getWeaponDamage() {
		return weaponDamage;
	}
	
	public abstract Integer attack();
	
	@Override
	public String toString() {
		return "Weapon Id: " + id + "; Weapon Damage: " + weaponDamage;
	}
	
}
