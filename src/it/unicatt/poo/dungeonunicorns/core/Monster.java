package it.unicatt.poo.dungeonunicorns.core;

public class Monster extends Entity {
	
	private final static int MONSTER_LIFE_STARTING_VALUE = 100;
	
	public Monster() {
		super(MONSTER_LIFE_STARTING_VALUE);
		entityId = "Monster";
	}
}
