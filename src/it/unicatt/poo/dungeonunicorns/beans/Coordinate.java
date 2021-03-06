package it.unicatt.poo.dungeonunicorns.beans;

import it.unicatt.poo.dungeonunicorns.beans.teleporters.Teleporter;
import it.unicatt.poo.dungeonunicorns.core.Entity;

/**
 * A class which represent a coordinate, the minimum unity in the game This
 * class is used to make rooms Every unity in a room is a coordinate
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public class Coordinate {
	/**
	 * The room where the coordinate is
	 */
	private Room room;
	/**
	 * The x position of the coordinate
	 */
	private int x;
	/**
	 * The y position of the coordinate
	 */
	private int y;
	/**
	 * A boolean used to check if the coordinate is on the border line of the room
	 */
	private boolean border;
	private boolean rightBorder;
	private boolean leftBorder;
	private boolean upBorder;
	private boolean downBorder;
	/**
	 * A boolean used to check if you can walk on the coordinate
	 */
	private boolean walkable;
	/**
	 * The teleporter on this coordinate, it is null if no teleporter is on the coordinate
	 */
	private Teleporter teleporter;
	/**
	 * The lootbox on this coordinate, it is null if no lootbox is on the coordinate
	 */
	private LootBox lootBox;
	/**
	 * The entity on the coordinate, it is null if no entity is on the coordinate
	 */
	private Entity entity;

	public Coordinate(Room room, int x, int y) {
		this.room = room;
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @return the room where the coordinate is
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * 
	 * @return the position of the coordinate on the x ass
	 */
	public int getX() {
		return x;
	}

	/**
	 * 
	 * @return the position of the coordinate on the y ass
	 */
	public int getY() {
		return y;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public Teleporter getTeleporter() {
		return teleporter;
	}
	
	public void setTeleporter(Teleporter teleporter) {
		this.teleporter = teleporter;
	}
	
	public LootBox getLootBox() {
		return lootBox;
	}
	
	public void setLootBox(LootBox lootBox) {
		this.lootBox = lootBox;
	}

	public boolean isBorder() {
		return border;
	}

	public void setBorder(boolean border) {
		this.border = border;
	}

	public boolean isRightBorder() {
		return rightBorder;
	}

	public void setRightBorder(boolean rightBorder) {
		if (rightBorder && !border) {
			this.border = true;
		}
		this.rightBorder = rightBorder;
	}

	public boolean isLeftBorder() {
		return leftBorder;
	}

	public void setLeftBorder(boolean leftBorder) {
		if (leftBorder && !border) {
			this.border = true;
		}
		this.leftBorder = leftBorder;
	}

	public boolean isUpBorder() {
		return upBorder;
	}

	public void setUpBorder(boolean upBorder) {
		if (upBorder && !border) {
			this.border = true;
		}
		this.upBorder = upBorder;
	}

	public boolean isDownBorder() {
		return downBorder;
	}

	public void setDownBorder(boolean downBorder) {
		if (downBorder && !border) {
			this.border = true;
		}
		this.downBorder = downBorder;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	
	/**
	 * 
	 * 
	 * @return the character which represent the coordinate to be printed
	 */
	public char printCoordinate() {
		char result = '-';
		if(entity != null) {
			result = entity.getEntityId().charAt(0);
		} else if(teleporter != null) {
			result = 'T';
		} else if(lootBox != null) {
			result = 'L';
		} else if(!walkable) {
			result = '?';
		} else if(border) {
			result = '*';
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Coordinate && obj != null) {
			Coordinate object = (Coordinate) obj;
			if (object.getRoom().equals(room) && object.getX() == x && object.getY() == y) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "(X:" + x + ";Y:" + y + ")";
	}

}
