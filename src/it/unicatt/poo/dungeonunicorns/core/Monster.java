package it.unicatt.poo.dungeonunicorns.core;

import it.unicatt.poo.dungeonunicorns.beans.Coordinate;
import it.unicatt.poo.dungeonunicorns.beans.armors.BlueArmor;
import it.unicatt.poo.dungeonunicorns.beans.enums.EntityDirection;

/**
 * A class which represent a monster
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public class Monster extends Entity implements EnemyStrategy {
	
	/**
	 * the starting amount of life points of the monster
	 */
	private final static int MONSTER_LIFE_STARTING_VALUE = 100;
	/**
	 * the damage done with a melee attack
	 */
	private final static int MONSTER_MELEE_DAMAGE_VALUE = 10;
	
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
		this.player = player;
		this.entityId = "Monster";
		super.setArmor(BlueArmor.getNewBlueArmor());
	}
	
	/**
	 * 
	 * @return the player of which the monster is enemy
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Checks if can move right in open spaces
	 * TODO make it work in every type of space
	 * @return true if it is going to move, false if not
	 */
	private boolean isGoingToMoveRight() {
		return player.getCurrentPosition().getX() > super.getCurrentPosition().getX() && canMoveRight();
	}
	
	/**
	 * Checks if can move left in open spaces
	 * TODO make it work in every type of space
	 * @return true if it is going to move, false if not
	 */
	private boolean isGoingToMoveLeft() {
		return player.getCurrentPosition().getX() < super.getCurrentPosition().getX() && canMoveLeft();
	}
	
	/**
	 * Checks if can move up in open spaces
	 * TODO make it work in every type of space
	 * @return true if it is going to move, false if not
	 */
	private boolean isGoingToMoveUp() {
		return player.getCurrentPosition().getY() < super.getCurrentPosition().getY() && canMoveUp();
	}
	
	/**
	 * Checks if can move down in open spaces
	 * TODO make it work in every type of space
	 * @return true if it is going to move, false if not
	 */
	private boolean isGoingToMoveDown() {
		return player.getCurrentPosition().getY() > super.getCurrentPosition().getY() && canMoveDown();
	}
	
	/**
	 * check if the entity is going to move
	 * 
	 * @return the direction where the entity is going to move
	 */
	public EntityDirection isGoingToMove() {
		EntityDirection result = EntityDirection.NO_DIRECTION;
		if(isGoingToMoveRight()) {
			result = EntityDirection.RIGHT;
		} else if(isGoingToMoveLeft()) {
			result = EntityDirection.LEFT;
		} else if(isGoingToMoveUp()) {
			result = EntityDirection.UP;
		} else if(isGoingToMoveDown()) {
			result = EntityDirection.DOWN;
		}
		return result;
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
	
	/**
	 * Method attack applied to the player
	 * 
	 * @param p the player
	 * @return true if attacks the player, false otherwise
	 */
//	public boolean attackPlayer() {
//		return attack(player);
//	}

	/**
	 * Decides next move to do for the enemy and attack if possible
	 * Work only in rectangles areas
	 * TODO Work for better intelligence
	 * 
	 * @return true if enemy is moving, false if not
	 */
	@Override
	public boolean nextMove() {
		boolean result = false;
		// Moves into player direction
		EntityDirection direction = isGoingToMove();
		if(direction.equals(EntityDirection.RIGHT)) {
			result = super.moveRight();
		} else if(direction.equals(EntityDirection.LEFT)) {
			result = super.moveLeft();
		} else if(direction.equals(EntityDirection.UP)) {
			result = super.moveUp();
		} else if(direction.equals(EntityDirection.DOWN)) {
			result = super.moveDown();
		}
//		else if(direction.equals(EntityDirection.NO_DIRECTION)) {
//			// Not Moving, checks for attack
//			for(Coordinate bordering : enemy.getCurrentRoom().getBorderingCoordinates(enemy.getCurrentPosition())) {
//				if(bordering.getEntity() != null && bordering.getEntity().equals(player)) {
//					enemy.attack(player);
//					System.out.println("MONSTER ATTACK: PlayerLife: " + player.getLife() + "EnemyLife: " + enemy.getLife());
//				}
//			}
//		}
		// attacks if possible
		for(Coordinate bordering : super.getCurrentRoom().getBorderingCoordinates(super.getCurrentPosition())) {
			if(bordering.getEntity() != null && bordering.getEntity().equals(player)) {
				attack(player);
			}
		}
		return result;
	}
	
}
