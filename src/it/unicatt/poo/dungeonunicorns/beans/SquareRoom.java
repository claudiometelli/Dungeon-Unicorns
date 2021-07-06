package it.unicatt.poo.dungeonunicorns.beans;

/**
 * A class which represent a particular room, used only in the demo tests
 * 
 * @author claudiometelli
 *
 */
public class SquareRoom extends Room {

	public SquareRoom(int width, int height, int roomStart, int roomWidth) {
		super(width, height);
		for(int i = roomStart; i < roomStart + roomWidth; i++) {
			for(int j = roomStart; j < roomStart + roomWidth; j++) {
				super.getCoordinateByPosition(i, j).setWalkable(true);
			}
		}
		for(int i = roomStart; i < roomStart + roomWidth; i++) {
			super.getCoordinateByPosition(i, roomStart).setDownBorder(true);
			super.getCoordinateByPosition(i, roomStart + roomWidth - 1).setUpBorder(true);
			super.getCoordinateByPosition(roomStart, i).setLeftBorder(true);
			super.getCoordinateByPosition(roomStart + roomWidth - 1, i).setRightBorder(true);
		}
	}
	
}
