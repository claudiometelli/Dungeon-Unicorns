package it.unicatt.poo.dungeonunicorns.graphics.beans;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.ARBGetTextureSubImage;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import it.unicatt.poo.dungeonunicorns.beans.Coordinate;
import it.unicatt.poo.dungeonunicorns.beans.Room;
import it.unicatt.poo.dungeonunicorns.graphics.MainGame;
import it.unicatt.poo.dungeonunicorns.graphics.entities.TiledEntity;

/**
 * A class which define the room as we see it in the game
 * 
 * @author elisamangiavacca
 * @version 1.0.0
 *
 */
public class TiledRoom {

	private TiledMap map;
	private TiledCoordinate[] coordinates;
	private Room room;
	
	private Map<TiledEntity, TiledCoordinate> spawningPoints;

	public TiledRoom(TiledMap map) {
		this.map = map;
		this.room = new Room(getMapWidth(), getMapHeight());
		this.coordinates = new TiledCoordinate[getMapWidth() * getMapHeight()];
		spawningPoints = new HashMap<TiledEntity, TiledCoordinate>();
		createRoom();
	}

	private void createRoom() {
		// filling level with walkable coordinates
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
		// level1 tiles setted as no walkable
		TiledMapTileLayer tiledLayer1 = (TiledMapTileLayer) map.getLayers().get(1);
		for (int x = 0; x < tiledLayer1.getWidth(); x++) {
			for (int y = 0; y < tiledLayer1.getHeight(); y++) {
				if (tiledLayer1.getCell(x, y) != null) {
					room.getCoordinateByPosition(x, y).setWalkable(false);
				}
			}
		}
		// looking for borders
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
	
	public TiledCoordinate getSpawningPoint(TiledEntity entity) {
		return spawningPoints.get(entity);
	}
	
	public void setSpawningPoint(TiledCoordinate coordinate, TiledEntity entity) {
		spawningPoints.put(entity, coordinate);
		entity.setRoom(this);
	}

}
