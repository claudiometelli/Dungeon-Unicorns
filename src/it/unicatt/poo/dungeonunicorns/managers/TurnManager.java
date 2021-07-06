package it.unicatt.poo.dungeonunicorns.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unicatt.poo.dungeonunicorns.graphics.entities.TiledEntity;
import it.unicatt.poo.dungeonunicorns.graphics.entities.TiledMonster;

public class TurnManager {
	
	private static List<TiledEntity> entities = new ArrayList<TiledEntity>();
	private static List<TiledMonster> monsters = new ArrayList<TiledMonster>();
	private static Map<TiledMonster, Boolean> turnMonstersPlayed = new HashMap<TiledMonster, Boolean>();
	private static TiledEntity actualTurnEntity;
	
	public static void addEntity(TiledEntity entity) {
		entities.add(entity);
	}
	
	public static void setTurnTo(TiledEntity entity) {
		if(!entities.contains(entity)) {
			entities.add(entity);
		}
		actualTurnEntity = entity;
	}
	
	/**
	 * 
	 * @param monsters
	 * @return monster che ha il turno se no null
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
	
	public static void clearForNextGame() {
		entities.clear();
		monsters.clear();
	};
	
	public static boolean isEntityTurn(TiledEntity entity) {
		boolean result = false;
		if(entity.equals(actualTurnEntity)) {
			result = true;
		}
		return result;
	}
}
