package it.unicatt.poo.dungeonunicorns.core;

import it.unicatt.poo.dungeonunicorns.beans.armors.Armor;
import it.unicatt.poo.dungeonunicorns.beans.weapons.Weapon;

public class Player extends Entity {
	
	private final static int PLAYER_LIFE_STARTING_VALUE = 100;
	private final static int PLAYER_MELEE_DAMAGE_VALUE = 10;
	
	private Armor armor;
	private Weapon weapon;
	
	public Player() {
		super(PLAYER_LIFE_STARTING_VALUE);
		entityId = "Player";
	}
	
	public Armor getArmor() {
		return armor;
	}
	
	public void setArmor(Armor armor) {
		this.armor = armor;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}
	
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	public boolean attack(Entity enemy) {
		boolean result = false;
		if(getEntitiesBordering().contains(enemy)) {
			if(weapon != null) {
				enemy.getDamage(weapon.getWeaponDamage());
			} else {
				enemy.getDamage(PLAYER_MELEE_DAMAGE_VALUE);
			}
			result = true;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}
