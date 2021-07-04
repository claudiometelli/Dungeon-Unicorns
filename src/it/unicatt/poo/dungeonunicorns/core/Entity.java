package it.unicatt.poo.dungeonunicorns.core;

import java.util.ArrayList;
import java.util.List;

import it.unicatt.poo.dungeonunicorns.beans.Coordinate;
import it.unicatt.poo.dungeonunicorns.beans.Room;
import it.unicatt.poo.dungeonunicorns.beans.armors.Armor;
import it.unicatt.poo.dungeonunicorns.beans.weapons.Weapon;
import it.unicatt.poo.dungeonunicorns.managers.EntitiesIdManager;

public abstract class Entity {
	
	String entityId;
	private int instanceId;
	private int life;
	
	private Armor armor;
	private Weapon weapon;
	
	private Coordinate currentPosition;
	private Room currentRoom;
	
	public Entity(int life) {
		this.instanceId = EntitiesIdManager.getNewId();
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
		return entityId + "; Life: " + life + "; Position: (" + currentPosition.toString() + ")";
	}
	
	public void placeEntity(Room room, int x, int y) {
		currentRoom = room;
		currentPosition = currentRoom.getCoordinateByPosition(x, y);
		currentPosition.setEntity(this);
	}
	
	public boolean moveRight() {
		return genericMove(1, 0);
	}
	
	public boolean canMoveRight() {
		return isMovementAllowed(1, 0);
	}
	
	public boolean moveLeft() {
		return genericMove(-1, 0);
	}
	
	public boolean canMoveLeft() {
		return isMovementAllowed(-1, 0);
	}
	
	public boolean moveUp() {
		return genericMove(0, 1);
	}
	
	public boolean canMoveUp() {
		return isMovementAllowed(0, 1);
	}
	
	public boolean moveDown() {
		return genericMove(0, -1);
	}
	
	public boolean canMoveDown() {
		return isMovementAllowed(0, -1);
	}
	
	public boolean isAnotherEntityBordering() {
		boolean result = false;
		if(!getEntitiesBordering().isEmpty()) {
			result = true;
		}
		return result;
	}
	
	public List<Entity> getEntitiesBordering() {
		List<Entity> entities = new ArrayList<Entity>();
		for(Coordinate coordinate : currentRoom.getWalkableBorderingCoordinates(currentPosition)) {
			if(coordinate.getEntity() != null) {
				entities.add(coordinate.getEntity());
			}
		}
		return entities;
	}
	
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
	
	public void getDamage(int damage) {
		life -= damage;
	}
	
	private boolean genericMove(int horizontal, int vertical) {
		boolean result = isMovementAllowed(horizontal, vertical);
		if(result) {
			int currentX = currentPosition.getX();
			int currentY = currentPosition.getY();
			currentPosition.setEntity(null);
			currentRoom.getCoordinateByPosition(currentX + horizontal, currentY + vertical).setEntity(this);
			currentPosition = currentRoom.getCoordinateByPosition(currentX + horizontal, currentY + vertical);
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
	
	public abstract boolean attack(Entity enemy);
	
}
