package it.unicatt.poo.dungeonunicorns.graphics.beans;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import it.unicatt.poo.dungeonunicorns.beans.Coordinate;
import it.unicatt.poo.dungeonunicorns.beans.Room;
import it.unicatt.poo.dungeonunicorns.graphics.MainGame;

public class TiledRoom {

	private TiledMap map;
	private TiledCoordinate[] coordinates;
	private Room room;

	public TiledRoom(TiledMap map) {
		this.map = map;
		this.room = new Room(getMapWidth(), getMapHeight());
		this.coordinates = new TiledCoordinate[getMapWidth() * getMapHeight()];
		createRoom();
	}

	private void createRoom() {
		// riempio livello con le coordinate su cui si può camminare
		TiledMapTileLayer tiledLayer0 = (TiledMapTileLayer) map.getLayers().get(0);
		int counter = 0;
		for (int x = 0; x < tiledLayer0.getWidth(); x++) {
			for (int y = 0; y < tiledLayer0.getHeight(); y++) {
				coordinates[counter] = new TiledCoordinate(tiledLayer0.getCell(x, y),
						room.getCoordinateByPosition(x, y));
				if (coordinates[counter].getCell() != null) {
					coordinates[counter].getCoordinate().setWalkable(true);
				}
				counter++;
			}
		}
		// setto not walkable i tile del livello 1
		TiledMapTileLayer tiledLayer1 = (TiledMapTileLayer) map.getLayers().get(1);
		for (int x = 0; x < tiledLayer1.getWidth(); x++) {
			for (int y = 0; y < tiledLayer1.getHeight(); y++) {
				if (tiledLayer1.getCell(x, y) != null) {
					room.getCoordinateByPosition(x, y).setWalkable(false);
				}
			}
		}
		// cerco i bordi
		for (TiledCoordinate coordinate : coordinates) {
			if (coordinate.getCoordinate().isWalkable()) {
				int x = coordinate.getCoordinate().getX();
				int y = coordinate.getCoordinate().getY();
				for (Coordinate border : room.getNotWalkableBorderingCoordinates(coordinate.getCoordinate())) {
					if (border.getX() == x && border.getY() == y - 1) {
						coordinate.getCoordinate().setDownBorder(true);
					}
					if (border.getX() == x && border.getY() == y + 1) {
						coordinate.getCoordinate().setUpBorder(true);
					}
					if (border.getX() == x + 1 && border.getY() == y) {
						coordinate.getCoordinate().setRightBorder(true);
					}
					if (border.getX() == x - 1 && border.getY() == y) {
						coordinate.getCoordinate().setLeftBorder(true);
					}
				}
			}
		}
	}

	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}

	public int getMapWidth() {
		TiledMapTileLayer tiledLayer = (TiledMapTileLayer) map.getLayers().get(0);
		return tiledLayer.getWidth();
	}

	public int getMapHeight() {
		TiledMapTileLayer tiledLayer = (TiledMapTileLayer) map.getLayers().get(0);
		return tiledLayer.getHeight();
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public TiledCoordinate getCoordinateByPosition(int x, int y) {
		TiledCoordinate result = null;
		for (TiledCoordinate coordinate : coordinates) {
			if (coordinate.getCoordinate().getX() == x && coordinate.getCoordinate().getY() == y) {
				result = coordinate;
			}
		}
		return result;
	}

	public TiledCoordinate getCoordinateByPosition(Coordinate coordinate) {
		TiledCoordinate result = null;
		for (TiledCoordinate tiledCoordinate : coordinates) {
			if (tiledCoordinate.getCoordinate().equals(coordinate)) {
				result = tiledCoordinate;
			}
		}
		return result;
	}

	public TiledCoordinate getCoordinateByScreenCoordinates(float x, float y) {
		TiledCoordinate result = null;
		int xTile = Math.round(x / MainGame.getInstance().getGameScreen().getCoordinateSize());
		int yTile = Math.round((MainGame.getInstance().getGameScreen().getCoordinateSize() * room.getHeight() - y)
				/ MainGame.getInstance().getGameScreen().getCoordinateSize());
		result = getCoordinateByPosition(xTile, yTile);
		return result;
	}

}
