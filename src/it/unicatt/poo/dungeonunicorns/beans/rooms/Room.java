package it.unicatt.poo.dungeonunicorns.beans.rooms;

import java.util.ArrayList;
import java.util.List;

import it.unicatt.poo.dungeonunicorns.beans.Coordinate;

public abstract class Room {
	
	private Coordinate[] coordinates;
	private int width;
	private int height;
	
	public Room(int width, int height) {
		this.width = width;
		this.height = height;
		this.coordinates = new Coordinate[width * height];
		int counter = 0;
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				coordinates[counter] = new Coordinate(i, j);
				counter++;
			}
		}
	}
	
	public Coordinate[] getCoordinates() {
		return coordinates;
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
	
	public List<Coordinate> getWalkableBorderingCoordinates(Coordinate coordinate) {
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
		return result;
	}
}
