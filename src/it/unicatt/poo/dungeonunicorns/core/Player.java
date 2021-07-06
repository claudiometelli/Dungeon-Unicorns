package it.unicatt.poo.dungeonunicorns.core;

public class Player extends Entity {
	
	private final static int PLAYER_LIFE_STARTING_VALUE = 100;
	private final static int PLAYER_MELEE_DAMAGE_VALUE = 50;
	
	public Player() {
		super(PLAYER_LIFE_STARTING_VALUE);
		entityId = "Player";
	}
	
	@Override
	public boolean attack(Entity enemy) {
		boolean result = false;
		if(getEntitiesBordering().contains(enemy)) {
			if(super.getWeapon() != null) {
				enemy.getDamage(super.getWeapon().getWeaponDamage());
			} else {
				enemy.getDamage(PLAYER_MELEE_DAMAGE_VALUE);
			}
			result = true;
		}
		return result;
	}
	
}
