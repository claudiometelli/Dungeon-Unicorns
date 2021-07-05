package it.unicatt.poo.dungeonunicorns.core;

import it.unicatt.poo.dungeonunicorns.beans.Coordinate;

public class EnemyIntelligence {
	
	private Entity enemy;
	private Player player;
	
	public EnemyIntelligence(Entity entity, Player player) {
		this.enemy = entity;
		this.player = player;
	}
	
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
	
	/**
	 * Checks if can move right in open spaces
	 * TODO make it work in every type of space
	 * @return true if it is going to move, false if not
	 */
	private boolean isGoingToMoveRight() {
		return player.getCurrentPosition().getX() > enemy.getCurrentPosition().getX() && enemy.canMoveRight();
	}
	
	/**
	 * Checks if can move left in open spaces
	 * TODO make it work in every type of space
	 * @return true if it is going to move, false if not
	 */
	private boolean isGoingToMoveLeft() {
		return player.getCurrentPosition().getX() < enemy.getCurrentPosition().getX() && enemy.canMoveLeft();
	}
	
	/**
	 * Checks if can move up in open spaces
	 * TODO make it work in every type of space
	 * @return true if it is going to move, false if not
	 */
	private boolean isGoingToMoveUp() {
		return player.getCurrentPosition().getY() > enemy.getCurrentPosition().getY() && enemy.canMoveUp();
	}
	
	/**
	 * Checks if can move down in open spaces
	 * TODO make it work in every type of space
	 * @return true if it is going to move, false if not
	 */
	private boolean isGoingToMoveDown() {
		return player.getCurrentPosition().getY() < enemy.getCurrentPosition().getY() && enemy.canMoveDown();
	}
	
	/**
	 * Work only in rectangles open squares
	 * TODO Work for better intelligence
	 * 
	 * @return true if enemy is moving, false if not
	 */
	public boolean nextMove() {
		boolean result = false;
		// Moves into player direction
		EntityDirection direction = isGoingToMove();
		if(direction.equals(EntityDirection.RIGHT)) {
			result = enemy.moveRight();
		} else if(direction.equals(EntityDirection.LEFT)) {
			result = enemy.moveLeft();
		} else if(direction.equals(EntityDirection.UP)) {
			result = enemy.moveUp();
		} else if(direction.equals(EntityDirection.DOWN)) {
			result = enemy.moveDown();
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
		for(Coordinate bordering : enemy.getCurrentRoom().getBorderingCoordinates(enemy.getCurrentPosition())) {
			if(bordering.getEntity() != null && bordering.getEntity().equals(player)) {
				enemy.attack(player);
				System.out.println("MONSTER ATTACK: PlayerLife: " + player.getLife() + "EnemyLife: " + enemy.getLife());
			}
		}
		
		return result;
	}
	
}
