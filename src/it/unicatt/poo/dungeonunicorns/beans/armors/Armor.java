package it.unicatt.poo.dungeonunicorns.beans.armors;

public abstract class Armor {
	
	private int armorLife;
	
	public Armor(int armorLife) {
		this.armorLife = armorLife;
	}
	
	public int getArmorLife() {
		return armorLife;
	}
	
	public void setArmorLife(int armorLife) {
		this.armorLife = armorLife;
	}
}
