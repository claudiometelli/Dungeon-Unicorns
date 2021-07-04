package it.unicatt.poo.dungeonunicorns.managers;

import java.util.ArrayList;
import java.util.List;

import it.unicatt.poo.dungeonunicorns.graphics.entities.TiledEntity;

public class TurnManager {
	
	private static List<TiledEntity> entities = new ArrayList<TiledEntity>();
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
	
	public static boolean isEntityTurn(TiledEntity entity) {
		boolean result = false;
		if(entity.equals(actualTurnEntity)) {
			result = true;
		}
		return result;
	}
}
