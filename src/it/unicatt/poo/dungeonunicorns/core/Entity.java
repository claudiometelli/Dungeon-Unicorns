package it.unicatt.poo.dungeonunicorns.core;

import java.util.ArrayList;
import java.util.List;

import it.unicatt.poo.dungeonunicorns.beans.Coordinate;
import it.unicatt.poo.dungeonunicorns.beans.LootBox;
import it.unicatt.poo.dungeonunicorns.beans.Room;
import it.unicatt.poo.dungeonunicorns.beans.armors.Armor;
import it.unicatt.poo.dungeonunicorns.beans.weapons.Weapon;
import it.unicatt.poo.dungeonunicorns.managers.IdManager;

/**
 * A class which represent a general entity in the game
 * For example the player or a monster
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public abstract class Entity {
	
	/**
	 * The Id of the entity
	 * Set by the sub-instances of the class
	 * Used By DemoTest to print coordinates on which there is an entity
	 */
	String entityId;
	/**
	 * The Id of the specific instance, used to identify a single entity
	 */
	private int instanceId;
	/**
	 * The amount of life of the entity
	 */
	private int life;
	
	/**
	 * The armor of the entity
	 */
	private Armor armor;
	/**
	 * The weapon used by the entity
	 */
	private Weapon weapon;
	
	/**
	 * The coordinate on which the entity is
	 */
	private Coordinate currentPosition;
	/**
	 * The room in which the entity is
	 */
	private Room currentRoom;
	
	/**
	 * 
	 * @param life - the initial amount of life points of the player
	 */
	public Entity(int life) {
		this.instanceId = IdManager.getNewId();
		this.life = life;
	}
	
	public String getEntityId() {
		return entityId;
	}
	
	public int getInstanceId() {
		return instanceId;
	}
	
	public int getLife() {
		return life;
	}
	
	public void setLife(int life) {
		this.life = life;
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
	
	public Coordinate getCurrentPosition() {
		return currentPosition;
	}
	
	public void setCurrentPosition(Coordinate currentPosition) {
		this.currentPosition = currentPosition;
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(entityId + "; Life: " + life + "; ");
		if(armor != null) {
			result.append(armor.toString() + "; ");
		}
		if(weapon != null) {
			result.append(weapon.toString() + "; ");
		}
		result.append("Position: (" + currentPosition.toString() + ")");
		return result.toString();
	}
	
	/**
	 * place the entity in a coordinate of a room
	 * 
	 * @param room - the room where the entity must be placed
	 * @param x - the position on the x ass of the coordinate in the room
	 * @param y - the position on the y ass of the coordinate in the room
	 */
	public void placeEntity(Room room, int x, int y) {
		currentRoom = room;
		currentPosition = currentRoom.getCoordinateByPosition(x, y);
		currentPosition.setEntity(this);
	}
	
	/**
	 * place the entity in a coordinate of a room
	 * 
	 * @param room - the room where the entity must be placed
	 * @param coordinate - the coordinate where the entity must be placed
	 */
	public void placeEntity(Room room, Coordinate coordinate) {
		currentRoom = room;
		currentPosition = coordinate;
		currentPosition.setEntity(this);
	}
	
	/**
	 * move on the right coordinate of the current position
	 * 
	 * @return true if the entity has moved, false otherwise
	 */
	public boolean moveRight() {
		return genericMove(1, 0);
	}
	
	/**
	 * check if can move on the right coordinate
	 * 
	 * @return true if the entity can move, false otherwise
	 */
	public boolean canMoveRight() {
		return isMovementAllowed(1, 0);
	}
	
	/**
	 * move on the left coordinate of the current position
	 * 
	 * @return true if the entity has moved, false otherwise
	 */
	public boolean moveLeft() {
		return genericMove(-1, 0);
	}
	
	/**
	 * check if can move on the left coordinate
	 * 
	 * @return true if the entity can move, false otherwise
	 */
	public boolean canMoveLeft() {
		return isMovementAllowed(-1, 0);
	}
	
	/**
	 * move on the up coordinate of the current position
	 * 
	 * @return true if the entity has moved, false otherwise
	 */
	public boolean moveUp() {
		return genericMove(0, -1);
	}
	
	/**
	 * check if can move on the up coordinate
	 * 
	 * @return true if the entity can move, false otherwise
	 */
	public boolean canMoveUp() {
		return isMovementAllowed(0, -1);
	}
	
	/**
	 * move on the down coordinate of the current position
	 * 
	 * @return true if the entity has moved, false otherwise
	 */
	public boolean moveDown() {
		return genericMove(0, 1);
	}
	
	/**
	 * check if can move on the down coordinate
	 * 
	 * @return true if the entity can move, false otherwise
	 */
	public boolean canMoveDown() {
		return isMovementAllowed(0, 1);
	}
	
	/**
	 * check if the player is on a teleporter
	 * 
	 * @return true if the entity is on a teleporter, false otherwise
	 */
	public boolean isOnTeleporter() {
		boolean result = false;
		if(currentPosition.getTeleporter() != null) {
			result = true;
		}
		return result;
	}
	
	/**
	 * check if there is an entity on the bordering coordinates of the current position
	 * 
	 * @return true if there is an entity bordering, false otherwise
	 */
	public boolean isAnotherEntityBordering() {
		boolean result = false;
		if(!getEntitiesBordering().isEmpty()) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 
	 * @return all the lootBox which are on the bordering coordinates of the current position
	 */
	public List<LootBox> getLootBoxsBordering() {
		List<LootBox> result = new ArrayList<LootBox>();
		for(Coordinate coordinate : currentRoom.getWalkableBorderingCoordinates(currentPosition)) {
			if(coordinate.getLootBox() != null) {
				result.add(coordinate.getLootBox());
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @return all the entities which are on the bordering coordinates of the current position
	 */
	public List<Entity> getEntitiesBordering() {
		List<Entity> result = new ArrayList<Entity>();
		for(Coordinate coordinate : currentRoom.getWalkableBorderingCoordinates(currentPosition)) {
			if(coordinate.getEntity() != null) {
				result.add(coordinate.getEntity());
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @return the entity on the right of this entity if there is an entity, null otherwise
	 */
	public Entity getEntityRightBordering() {
		Entity result = null;
		if(checkEntityRightBordering()) {
			result = getCurrentRoom().getCoordinateByPosition(getCurrentPosition().getX() + 1, getCurrentPosition().getY()).getEntity();
		}
		return result;
	}
	
	/**
	 * 
	 * @return the entity on the left of this entity if there is an entity, null otherwise
	 */
	public Entity getEntityLeftBordering() {
		Entity result = null;
		if(checkEntityLeftBordering()) {
			result = getCurrentRoom().getCoordinateByPosition(getCurrentPosition().getX() - 1, getCurrentPosition().getY()).getEntity();
		}
		return result;
	}
	
	/**
	 * 
	 * @return the entity above this entity if there is an entity, null otherwise
	 */
	public Entity getEntityUpBordering() {
		Entity result = null;
		if(checkEntityUpBordering()) {
			result = getCurrentRoom().getCoordinateByPosition(getCurrentPosition().getX(), getCurrentPosition().getY() - 1).getEntity();
		}
		return result;
	}
	
	/**
	 * 
	 * @return the entity under this entity if there is an entity, null otherwise
	 */
	public Entity getEntityDownBordering() {
		Entity result = null;
		if(checkEntityDownBordering()) {
			result = getCurrentRoom().getCoordinateByPosition(getCurrentPosition().getX(), getCurrentPosition().getY() + 1).getEntity();
		}
		return result;
	}
	
	/**
	 * check if another entity is on the right of this entity
	 * 
	 * @return true if there is an entity on the right, false otherwise
	 */
	public boolean checkEntityRightBordering() {
		boolean result = false;
		if(!getCurrentPosition().isRightBorder() && getCurrentRoom().getCoordinateByPosition(getCurrentPosition().getX() + 1, getCurrentPosition().getY()).getEntity() != null) {
			result = true;
		}
		return result;
	}
	
	/**
	 * check if another entity is on the left of this entity
	 * 
	 * @return true if there is an entity on the left, false otherwise
	 */
	public boolean checkEntityLeftBordering() {
		boolean result = false;
		if(!getCurrentPosition().isLeftBorder() && getCurrentRoom().getCoordinateByPosition(getCurrentPosition().getX() - 1, getCurrentPosition().getY()).getEntity() != null) {
			result = true;
		}
		return result;
	}
	
	/**
	 * check if another entity is above this entity
	 * 
	 * @return true if there is an entity above this one, false otherwise
	 */
	public boolean checkEntityUpBordering() {
		boolean result = false;
		if(!getCurrentPosition().isUpBorder() && getCurrentRoom().getCoordinateByPosition(getCurrentPosition().getX(), getCurrentPosition().getY() - 1).getEntity() != null) {
			result = true;
		}
		return result;
	}
	
	/**
	 * check if another entity is under this entity
	 * 
	 * @return true if there is an entity under this one, false otherwise
	 */
	public boolean checkEntityDownBordering() {
		boolean result = false;
		if(!getCurrentPosition().isDownBorder() && getCurrentRoom().getCoordinateByPosition(getCurrentPosition().getX(), getCurrentPosition().getY() + 1).getEntity() != null) {
			result = true;
		}
		return result;
	}
	
	/**
	 * Check if the movement is allowed at the which you want to move position
	 * 
	 * @param horizontal - the number of coordinates to move in the horizontal direction(positive numbers to move right, negative ones to move left) by the current position
	 * @param vertical - the number of coordinates to move in the vertical direction(positive numbers to move up, negative ones to move down) by the current position
	 * @return true if the entity can move, false otherwise
	 */
	private boolean isMovementAllowed(int horizontal, int vertical) {
		boolean result = true;
		if(horizontal == 1 && (currentPosition.isRightBorder() ||
				currentRoom.getCoordinateByPosition(currentPosition.getX() + horizontal, currentPosition.getY() + vertical).getEntity() != null)) {
			result = false;
		}
		if(horizontal == -1 && (currentPosition.isLeftBorder() ||
				currentRoom.getCoordinateByPosition(currentPosition.getX() + horizontal, currentPosition.getY() + vertical).getEntity() != null)) {
			result = false;
		}
		if(vertical == 1 && (currentPosition.isUpBorder() ||
				currentRoom.getCoordinateByPosition(currentPosition.getX() + horizontal, currentPosition.getY() + vertical).getEntity() != null)) {
			result = false;
		}
		if(vertical == -1 && (currentPosition.isDownBorder() ||
				currentRoom.getCoordinateByPosition(currentPosition.getX() + horizontal, currentPosition.getY() + vertical).getEntity() != null)) {
			result = false;
		}
		return result;
	}
	
	/**
	 * Method used to get damage by the entity
	 * 
	 * @param damage the amount of damage to be done to the entity
	 */
	public void getDamage(int damage) {
		int damageDone = 0;
		if(armor != null) {
			damageDone = getArmorDamage(damage);
			armor.setArmorLife(armor.getArmorLife() - damageDone);
		}
		if(damageDone < damage) {
			life -= getLifeDamage(damage - damageDone);
		}
	}
	
	/**
	 * Support method for getDamage
	 * 
	 * @param damage
	 * @return the amount of damage done to the armor
	 */
	private int getArmorDamage(int damage) {
		int result = damage;
		if(armor.getArmorLife() - damage < 0) {
			result = armor.getArmorLife();
		}
		return result;
	}
	
	/**
	 * Support method for getDamage
	 * 
	 * @param damage
	 * @return the amount of damage done to the life points of the entity
	 */
	private int getLifeDamage(int damage) {
		int result = damage;
		if(life - damage < 0) {
			result = life;
		}
		return result;
	}
	
	/**
	 * A support method
	 * 
	 * @param horizontal
	 * @param vertical
	 * @return
	 */
	private boolean genericMove(int horizontal, int vertical) {
		boolean result = isMovementAllowed(horizontal, vertical);
		if(result) {
			int currentX = currentPosition.getX();
			int currentY = currentPosition.getY();
			currentPosition.setEntity(null);
			currentPosition = currentRoom.getCoordinateByPosition(currentX + horizontal, currentY + vertical);
			currentPosition.setEntity(this);
		}
		return result;
	}
	
	/**
	 * Moves the entity into the specified coordinate
	 * 
	 * @param coordinate - the coordinate where the entity is going to move
	 * @return true if the entity has moved, false otherwise
	 */
	public boolean moveIntoPosition(Coordinate coordinate) {
		boolean result = false;
		if(coordinate.isWalkable()) {
			currentPosition.setEntity(null);
			currentRoom = coordinate.getRoom();
			currentPosition = coordinate;
			currentPosition.setEntity(this);
			result = true;
		}
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Entity && obj != null) {
			Entity object = (Entity) obj;
			if(entityId.equals(object.getEntityId())) {
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * Attack the entity
	 * 
	 * @param enemy - the enemy to be attacked
	 * @return true if the enemy has been attacked, false otherwise
	 */
	public abstract boolean attack(Entity enemy);
	
}
