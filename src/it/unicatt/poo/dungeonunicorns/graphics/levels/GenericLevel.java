package it.unicatt.poo.dungeonunicorns.graphics.levels;

import java.util.ArrayList;
import java.util.List;

import it.unicatt.poo.dungeonunicorns.graphics.beans.TiledRoom;
import it.unicatt.poo.dungeonunicorns.graphics.entities.TiledMonster;

/**
 * A class which define a generic level
 * 
 * @author elisamangiavacca
 * @version 1.0.0
 *
 */
public abstract class GenericLevel {
	
	private List<TiledRoom> rooms;
	private List<TiledMonster> monsters;
	
	public GenericLevel() {
		this.rooms = loadMaps();
		this.monsters = loadEnemies();
		
	}
	
	public List<TiledMonster> getMonsters() {
		return monsters;
	}
	
	public List<TiledRoom> getRooms() {
		return rooms;
	}
	
	public void addRoom(TiledRoom room) {
		rooms.add(room);
	}
	
	public void addMonster(TiledMonster monster) {
		monsters.add(monster);
	}
	
	public abstract List<TiledRoom> loadMaps();
	
	public abstract List<TiledMonster> loadEnemies();
}
