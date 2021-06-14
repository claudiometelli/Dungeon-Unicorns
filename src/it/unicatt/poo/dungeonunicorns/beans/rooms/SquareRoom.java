package it.unicatt.poo.dungeonunicorns.beans.rooms;

import it.unicatt.poo.dungeonunicorns.beans.Coordinate;

public class SquareRoom extends Room {

	public SquareRoom(int width) {
		super(width, width);
		for(int i = 0; i < width; i++) {
			super.getCoordinateByPosition(i, 0).setDownBorder(true);
			super.getCoordinateByPosition(i, width - 1).setUpBorder(true);
			super.getCoordinateByPosition(0, i).setLeftBorder(true);
			super.getCoordinateByPosition(width - 1, i).setRightBorder(true);
		}
		for(Coordinate coordinate: super.getCoordinates()) {
			coordinate.setWalkable(true);
		}
	}
	
}
