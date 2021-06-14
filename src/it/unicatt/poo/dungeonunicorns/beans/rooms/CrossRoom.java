package it.unicatt.poo.dungeonunicorns.beans.rooms;

import it.unicatt.poo.dungeonunicorns.beans.Coordinate;

public class CrossRoom extends Room {
	
	public CrossRoom(int width, int height, int crossWidth, int crossHeight) {
		super(width, height);
		int xStartingPoint = width / 2 - crossWidth / 2;
		int yStartingPoint = height / 2 - crossHeight / 2;
		for(int i = 0; i < crossWidth; i++) {
			for(int j = 0; j < height; j++) {
				super.getCoordinateByPosition(xStartingPoint + i, j).setWalkable(true);
			}
		}
		for(int i = 0; i < crossHeight; i++) {
			for(int j = 0; j < width; j++) {
				super.getCoordinateByPosition(j, yStartingPoint + i).setWalkable(true);
			}
		}
		for(Coordinate coordinate: super.getCoordinates()) {
			if(coordinate.isWalkable()) {
				if(coordinate.getX() == 0 || !super.getCoordinateByPosition(coordinate.getX() - 1, coordinate.getY()).isWalkable()) {
					coordinate.setLeftBorder(true);
				}
				if(coordinate.getX() == width - 1 || !super.getCoordinateByPosition(coordinate.getX() + 1, coordinate.getY()).isWalkable()) {
					coordinate.setRightBorder(true);
				}
				if(coordinate.getY() == 0 || !super.getCoordinateByPosition(coordinate.getX(), coordinate.getY() - 1).isWalkable()) {
					coordinate.setDownBorder(true);
				}
				if(coordinate.getY() == height - 1 || !super.getCoordinateByPosition(coordinate.getX(), coordinate.getY() + 1).isWalkable()) {
					coordinate.setUpBorder(true);
				}
			}
		}
	}
	
}
