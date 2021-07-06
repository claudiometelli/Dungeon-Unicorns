package it.unicatt.poo.dungeonunicorns.core;

/**
 * A class which represent the player
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public class Player extends Entity {
	
	/**
	 * the starting amount of life points of the player
	 */
	private final static int PLAYER_LIFE_STARTING_VALUE = 100;
	
	/**
	 * the damage done with a melee attack
	 */
	private final static int PLAYER_MELEE_DAMAGE_VALUE = 35;
	
	/**
	 * Constructor for Player which sets the entity's life to PLAYER_LIFE_STARTING_VALUE and entity Id
	 */
	public Player() {
		super(PLAYER_LIFE_STARTING_VALUE);
		entityId = "Player";
	}
	
	@Override
	public boolean attack(Entity enemy) {
		boolean result = false;
		if(getEntitiesBordering().contains(enemy)) {
			if(super.getWeapon() != null) {
				enemy.getDamage(super.getWeapon().getWeaponDamage());
			} else {
				enemy.getDamage(PLAYER_MELEE_DAMAGE_VALUE);
			}
			result = true;
		}
		return result;
	}
	
}
