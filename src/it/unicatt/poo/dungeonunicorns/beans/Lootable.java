package it.unicatt.poo.dungeonunicorns.beans;

import it.unicatt.poo.dungeonunicorns.core.Entity;

public interface Lootable {
	
	public String getLootId();
	
	public boolean isGoingToLoot(Entity entity);
	
	public Lootable loot(Entity entity);
	
}
