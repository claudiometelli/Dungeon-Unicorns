package it.unicatt.poo.dungeonunicorns.beans;

import java.util.ArrayList;
import java.util.List;

import it.unicatt.poo.dungeonunicorns.managers.IdManager;

/**
 * A class which represent a room of a dungeon in the game
 * A room is always represented like a Rectangle with width and height
 * 
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public class Room {
	/**
	 * the room id to be unique
	 */
	private int roomId;
	
	/**
	 * The set of coordinates used to fill the room
	 */
	private Coordinate[] coordinates;
	
	/**
	 * The width of the room in coordinates
	 */
	private int width;
	/**
	 * The height of the room in coordinates
	 */
	private int height;
	
	/**
	 * Constructor for the room which assign a new roomId
	 * and initializes all the coordinates of the room
	 * 
	 * @param width - The width of the room in coordinates
	 * @param height - The height of the room in coordinates
	 */
	public Room(int width, int height) {
		this.roomId = IdManager.getNewId();
		this.width = width;
		this.height = height;
		this.coordinates = new Coordinate[width * height];
		int counter = 0;
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				coordinates[counter] = new Coordinate(this, i, j);
				counter++;
			}
		}
	}
	
	/**
	 * 
	 * @return the roomId
	 */
	public int getRoomId() {
		return roomId;
	}
	
	/**
	 * 
	 * @return all the coordinates of the room
	 */
	public Coordinate[] getCoordinates() {
		return coordinates;
	}
	
	/**
	 * 
	 * @return The width of the room in Coordinates
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * 
	 * @return The height of the room in Coordinates
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * give the coordinate in the room at the specified position
	 * 
	 * @param x - the position on the x ass of the room
	 * @param y - the position on the y ass of the room
	 * @return the coordinate in the position (x, y)
	 */
	public Coordinate getCoordinateByPosition(int x, int y) {
		Coordinate result = null;
		for(Coordinate coordinate : coordinates) {
			if(coordinate.getX() == x && coordinate.getY() == y) {
				result = coordinate;
			}
		}
		return result;
	}
	
	/**
	 * Prints all the coordinates of the room
	 * 
	 * @return a String with the final result of all the coordinates
	 */
	public String printRoom() {
		StringBuilder result = new StringBuilder();
		for(int i = height - 1; i >= 0; i--) {
			for(int j = 0; j < width; j++) {
				result.append(getCoordinateByPosition(j, i).printCoordinate());
			}
			result.append("\n");
		}
		return result.toString();
	}
	
	/**
	 * 
	 * @param coordinate - the coordinate on which you want the near coordinates on which you can walk
	 * @return the coordinates on which you can walk near the coordinate parameter
	 */
	public List<Coordinate> getWalkableBorderingCoordinates(Coordinate coordinate) {
		List<Coordinate> bordering = getBorderingCoordinates(coordinate);
		List<Coordinate> result = new ArrayList<Coordinate>();
		for(Coordinate borderCoordinate : bordering) {
			if(borderCoordinate.isWalkable()) {
				result.add(borderCoordinate);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param coordinate - the coordinate on which you want the near coordinates on which you can't walk
	 * @return the coordinates on which you can't walk near the coordinate parameter
	 */
	public List<Coordinate> getNotWalkableBorderingCoordinates(Coordinate coordinate) {
		List<Coordinate> bordering = getBorderingCoordinates(coordinate);
		List<Coordinate> result = new ArrayList<Coordinate>();
		for(Coordinate borderCoordinate : bordering) {
			if(!borderCoordinate.isWalkable()) {
				result.add(borderCoordinate);
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param coordinate - the coordinate on which you want the near coordinates
	 * @return the coordinates near the coordinate parameter
	 */
	public List<Coordinate> getBorderingCoordinates(Coordinate coordinate){
		List<Coordinate> result = new ArrayList<Coordinate>();
		int x = coordinate.getX();
		int y = coordinate.getY();
		if(x != 0) {
			result.add(getCoordinateByPosition(x - 1, y));
		}
		if(x != width - 1) {
			result.add(getCoordinateByPosition(x + 1, y));
		}
		if(y != 0) {
			result.add(getCoordinateByPosition(x, y - 1));
		}
		if(y != height - 1) {
			result.add(getCoordinateByPosition(x, y + 1));
		}
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Room && obj != null) {
			Room object = (Room) obj;
			if(object.getRoomId() == roomId) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "Room: " + roomId;
	}
	
}
