package it.unicatt.poo.dungeonunicorns.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unicatt.poo.dungeonunicorns.graphics.entities.TiledEntity;
import it.unicatt.poo.dungeonunicorns.graphics.entities.TiledMonster;

/**
 * A class used to manage the turns during the game
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public class TurnManager {
	
	/**
	 * The entities to manage the turns
	 */
	private static List<TiledEntity> entities = new ArrayList<TiledEntity>();
	/**
	 * The monsters to manage the turns
	 */
	private static List<TiledMonster> monsters = new ArrayList<TiledMonster>();
	/**
	 * A map used to manage the turns of the various monster in the game
	 */
	private static Map<TiledMonster, Boolean> turnMonstersPlayed = new HashMap<TiledMonster, Boolean>();
	/**
	 * The entity which is the turn to play
	 */
	private static TiledEntity actualTurnEntity;
	
	/**
	 * 
	 * @param entity - the entity to be added to the list entities
	 */
	public static void addEntity(TiledEntity entity) {
		entities.add(entity);
	}
	
	/**
	 * A method which sets the turn to an entity using actualTurnEntity
	 * 
	 * @param entity - the entity to set the turn to
	 */
	public static void setTurnTo(TiledEntity entity) {
		if(!entities.contains(entity)) {
			entities.add(entity);
		}
		actualTurnEntity = entity;
	}
	
	/**
	 * A method to decide which monster has the turn
	 * 
	 * @param monsters - the monsters of the level to decide which one has the turn to play
	 * @return the monster which the turn must be switched to if is the turn of a monster, null otherwise
	 */
	public static TiledMonster setTurnToNextMonster(List<TiledMonster> monsters) {
		TiledMonster result = null;
		for(TiledMonster monster : monsters) {
			if(!entities.contains(monster)) {
				entities.add(monster);
			}
			if(!turnMonstersPlayed.containsKey(monster)) {
				turnMonstersPlayed.put(monster, false);
			}
		}
		boolean haveAllMonsterPlayed = true;
		for(TiledMonster monster : turnMonstersPlayed.keySet()) {
			if(!turnMonstersPlayed.get(monster)) {
				haveAllMonsterPlayed = false;
				break;
			}
		}
		if(!haveAllMonsterPlayed) {
			for(TiledMonster monster : turnMonstersPlayed.keySet()) {
				if(!turnMonstersPlayed.get(monster)) {
					turnMonstersPlayed.put(monster, true);
					actualTurnEntity = monster;
					result = monster;
					break;
				}
			}
		} else {
			for(TiledMonster monster : turnMonstersPlayed.keySet()) {
				turnMonstersPlayed.put(monster, false);
			}
		}
		return result;
	}
	
	/**
	 * A method which clear the monster from turnMonstersPlayed, entities and monsters if it has been eliminated
	 * 
	 * @param monster - the monster which has been eliminated
	 */
	public static void deleteMonster(TiledMonster monster) {
		if(entities.contains(monster)) {
			entities.remove(monster);
		}
		if(monsters.contains(monster)) {
			monsters.remove(monster);
		}
		if(turnMonstersPlayed.containsKey(monster)) {
			turnMonstersPlayed.remove(monster);
		}
	}
	
	/**
	 * Check if is the entity turn
	 * 
	 * @param entity - to entity to check if is its turn
	 * @return true if is the entity turn, false otherwise
	 */
	public static boolean isEntityTurn(TiledEntity entity) {
		boolean result = false;
		if(entity.equals(actualTurnEntity)) {
			result = true;
		}
		return result;
	}
}
