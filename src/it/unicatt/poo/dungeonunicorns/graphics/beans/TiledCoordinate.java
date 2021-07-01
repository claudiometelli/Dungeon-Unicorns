package it.unicatt.poo.dungeonunicorns.graphics.beans;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import it.unicatt.poo.dungeonunicorns.beans.Coordinate;

public class TiledCoordinate {
	
	/**
	 * se null è una cella vuota
	 */
	private Cell cell;
	private Coordinate coordinate;
	
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
	
}
