package it.unicatt.poo.dungeonunicorns.graphics.beans;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import it.unicatt.poo.dungeonunicorns.beans.Coordinate;
import it.unicatt.poo.dungeonunicorns.graphics.entities.TiledEntity;

/**
 * A class which define the coordinate as we see it in the game
 * 
 * @author elisamangiavacca
 * @version 1.0.0
 *
 */
public class TiledCoordinate {
	
	/**
	 * se null è una cella vuota
	 */
	private Cell cell;
	private Coordinate coordinate;
	private TiledEntity entity;
	
	public TiledCoordinate(Cell cell, Coordinate coordinate) {
		this.cell = cell;
		this.coordinate = coordinate;
	}
	
	public Cell getCell() {
		return cell;
	}
	
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public TiledEntity getEntity() {
		return entity;
	}
	
	public void setEntity(TiledEntity entity) {
		this.entity = entity;
	}
	
}
