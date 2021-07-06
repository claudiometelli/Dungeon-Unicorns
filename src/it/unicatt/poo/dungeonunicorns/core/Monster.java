package it.unicatt.poo.dungeonunicorns.core;

import it.unicatt.poo.dungeonunicorns.beans.armors.WhiteArmor;

/**
 * A class which represent a monster
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public class Monster extends Entity {
	
	/**
	 * the starting amount of life points of the monster
	 */
	private final static int MONSTER_LIFE_STARTING_VALUE = 100;
	/**
	 * the damage done with a melee attack
	 */
	private final static int MONSTER_MELEE_DAMAGE_VALUE = 10;
	
	/**
	 * The intelligence of the player
	 */
	private EnemyIntelligence intelligence;
	
	/**
	 * The player of which the monster is enemy
	 */
	private Player player;
	
	/**
	 * Constructor for Monster which sets the entity's life to MONSTER_LIFE_STARTING_VALUE and entity Id and create a new intelligence
	 * 
	 * @param player - the player of which the monster is enemy
	 */
	public Monster(Player player) {
		super(MONSTER_LIFE_STARTING_VALUE);
		this.entityId = "Monster";
		this.intelligence = new EnemyIntelligence(this, player);
		super.setArmor(new WhiteArmor());
	}
	
	/**
	 * 
	 * @return the intelligence of the player
	 */
	public EnemyIntelligence getIntelligence() {
		return intelligence;
	}
	
	/**
	 * 
	 * @return the player of which the monster is enemy
	 */
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public boolean attack(Entity enemy) {
		boolean result = false;
		if(getEntitiesBordering().contains(enemy)) {
			if(super.getWeapon() != null) {
				enemy.getDamage(super.getWeapon().getWeaponDamage());
			} else {
				enemy.getDamage(MONSTER_MELEE_DAMAGE_VALUE);
			}
			result = true;
		}
		return result;
	}
	
}
