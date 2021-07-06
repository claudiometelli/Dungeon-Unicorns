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
	
	private int roomId;
	/**
	 * The set of coordinates used to fill the room
	 */
	private Coordinate[] coordinates;
	
	private int width;
	private int height;
	
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
	
	public int getRoomId() {
		return roomId;
	}
	
	public Coordinate[] getCoordinates() {
		return coordinates;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Coordinate getCoordinateByPosition(int x, int y) {
		Coordinate result = null;
		for(Coordinate coordinate : coordinates) {
			if(coordinate.getX() == x && coordinate.getY() == y) {
				result = coordinate;
			}
		}
		return result;
	}
	
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
	 * ritorna le coordinate vicine su cui si può camminare
	 * @param coordinate
	 * @return 
	 */
	public List<Coordinate> getWalkableBorderingCoordinates(Coordinate coordinate) {
		List<Coordinate> bordering = getBorderingCoordinates(coordinate);
		List<Coordinate> result = new ArrayList<Coordinate>();
		for(Coordinate borderCoordinate : bordering) {
			if(borderCoordinate.isWalkable()) {
				result.add(borderCoordinate);
			}
		}
		/*
		List<Coordinate> result = new ArrayList<Coordinate>();
		if(!(coordinate.getX() == 0 || !getCoordinateByPosition(coordinate.getX() - 1, coordinate.getY()).isWalkable())) {
			result.add(getCoordinateByPosition(coordinate.getX() - 1, coordinate.getY()));
		}
		if(!(coordinate.getX() == width - 1 || !getCoordinateByPosition(coordinate.getX() + 1, coordinate.getY()).isWalkable())) {
			result.add(getCoordinateByPosition(coordinate.getX() + 1, coordinate.getY()));
		}
		if(!(coordinate.getY() == 0 || !getCoordinateByPosition(coordinate.getX(), coordinate.getY() - 1).isWalkable())) {
			result.add(getCoordinateByPosition(coordinate.getX(), coordinate.getY() - 1));
		}
		if(!(coordinate.getY() == height - 1 || !getCoordinateByPosition(coordinate.getX(), coordinate.getY() + 1).isWalkable())) {
			result.add(getCoordinateByPosition(coordinate.getX(), coordinate.getY() + 1));
		}
		*/
		return result;
	}
	
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
	 * ritorna le coordinate vicine
	 * @param coordinate
	 * @return
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
	
}
