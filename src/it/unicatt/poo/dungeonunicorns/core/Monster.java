package it.unicatt.poo.dungeonunicorns.core;

public class Monster extends Entity {
	
	private final static int MONSTER_LIFE_STARTING_VALUE = 100;
	private final static int MONSTER_MELEE_DAMAGE_VALUE = 10;
	
	private EnemyIntelligence intelligence;
	
	private Player player;
	
	public Monster(Player player) {
		super(MONSTER_LIFE_STARTING_VALUE);
		this.entityId = "Monster";
		this.intelligence = new EnemyIntelligence(this, player);
	}
	
	public EnemyIntelligence getIntelligence() {
		return intelligence;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public boolean attack(Entity enemy) {
		boolean result = false;
		if(getEntitiesBordering().contains(enemy)) {
			if(super.getWeapon() != null) {
				enemy.getDamage(super.getWeapon().getWeaponDamage());
			} else {
				enemy.getDamage(MONSTER_MELEE_DAMAGE_VALUE);
			}
			result = true;
		}
		return result;
	}
	
}
