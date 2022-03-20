package it.unicatt.poo.dungeonunicorns.beans;

import java.util.ArrayList;
import java.util.List;

import it.unicatt.poo.dungeonunicorns.core.Entity;

public class LootBox {
	
	private List<Lootable> lootElements;
	private Coordinate position;
	
	private LootBox(List<Lootable> lootElements, Coordinate position) {
		this.lootElements = new ArrayList<Lootable>(lootElements);
		this.position = position;
	}
	
	public static LootBox placeLootBox(List<Lootable> lootElements, Room room, int x, int y) {
		return placeLootBox(lootElements, room.getCoordinateByPosition(x, y));
	}
	
	public static LootBox placeLootBox(List<Lootable> lootElements, Coordinate position) {
		LootBox result = new LootBox(lootElements, position);
		position.setLootBox(result);
		return result;
	}
	
	public boolean dropLoot(Entity entity) {
		boolean result = false;
		for(Lootable lootElement : new ArrayList<Lootable>(lootElements)) {
			Lootable l = lootElement.loot(entity);
			if(l == null) {
				lootElements.remove(lootElement);
			} else if(!l.getLootId().equals(lootElement.getLootId())) {
				//The entity has looted
				lootElements.add(lootElements.indexOf(lootElement), l);
				lootElements.remove(lootElement);
				if(!result) {
					result = true;
				}
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		String result = null;
		StringBuilder sb = new StringBuilder("LootBox elements:\n");
		for(Lootable lootElement : lootElements) {
			sb.append(lootElement.toString() + "\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		result = sb.toString();
		return result;
	}
	
	public Coordinate getPosition() {
		return position;
	}
	
}
