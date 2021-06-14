package it.unicatt.poo.dungeonunicorns.beans.rooms;

import it.unicatt.poo.dungeonunicorns.beans.Coordinate;

public class RectangleRoom extends Room {
	
	public RectangleRoom(int width, int height) {
		super(width, height);
		for(int i = 0; i< width; i++) {
			super.getCoordinateByPosition(i, 0).setDownBorder(true);
			super.getCoordinateByPosition(i, height - 1).setUpBorder(true);
		}
		for(int i = 0; i < height; i++) {
			super.getCoordinateByPosition(0, i).setLeftBorder(true);
			super.getCoordinateByPosition(width - 1, i).setRightBorder(true);
		}
		for(Coordinate coordinate: super.getCoordinates()) {
			coordinate.setWalkable(true);
		}
	}
}
