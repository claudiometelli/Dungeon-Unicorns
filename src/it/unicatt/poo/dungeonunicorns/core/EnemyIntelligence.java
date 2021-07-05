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
		if(player.getCurrentPosition().getX() > enemy.getCurrentPosition().getX() && enemy.canMoveRight()) {
			result = EntityDirection.RIGHT;
		} else if(player.getCurrentPosition().getX() < enemy.getCurrentPosition().getX() && enemy.canMoveLeft()) {
			result = EntityDirection.LEFT;
		} else if(player.getCurrentPosition().getY() > enemy.getCurrentPosition().getY() && enemy.canMoveUp()) {
			result = EntityDirection.UP;
		} else if(player.getCurrentPosition().getY() < enemy.getCurrentPosition().getY() && enemy.canMoveDown()) {
			result = EntityDirection.DOWN;
		}
		return result;
	}
	
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
		// Checks for attack
		for(Coordinate bordering : enemy.getCurrentRoom().getBorderingCoordinates(enemy.getCurrentPosition())) {
			if(bordering.getEntity() != null && bordering.getEntity().equals(player)) {
				enemy.attack(player);
			}
		}
		return result;
	}
	
}
