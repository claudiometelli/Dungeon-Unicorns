package it.unicatt.poo.dungeonunicorns.core;

import it.unicatt.poo.dungeonunicorns.beans.enums.EntityDirection;

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
	
	public boolean attack(EntityDirection direction) {
		boolean result = false;
		switch(direction) {
			case RIGHT:
				if(super.checkEntityRightBordering()) {
					result = attack(super.getEntityRightBordering());
				}
				break;
			case LEFT:
				if(super.checkEntityLeftBordering()) {
					result = attack(super.getEntityLeftBordering());
				}
				break;
			case UP:
				if(super.checkEntityUpBordering()) {
					result = attack(super.getEntityUpBordering());
				}
				break;
			case DOWN:
				if(super.checkEntityDownBordering()) {
					result = attack(super.getEntityDownBordering());
				}
				break;
		}
		return result;
	}
	
	public boolean useTeleport() {
		boolean result = false;
		if(getCurrentPosition().getTeleporter() != null) {
			result = getCurrentPosition().getTeleporter().teleport();
		}
		return result;
	}
	
}
