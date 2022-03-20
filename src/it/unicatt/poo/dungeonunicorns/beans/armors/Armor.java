package it.unicatt.poo.dungeonunicorns.beans.armors;

import it.unicatt.poo.dungeonunicorns.beans.Lootable;
import it.unicatt.poo.dungeonunicorns.managers.IdManager;

/**
 * A class which represent an armor for a general entity in the game
 * 
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public abstract class Armor implements Lootable{
	
	/**
	 * Id used to recognize the armor
	 */
	private int id;
	/**
	 * Life points for the armor
	 */
	private int armorLife;
	
	
	/**
	 * Constructor for class Armor
	 * 
	 * @param armorLife - the initial amount of life points for the armor
	 */
	public Armor(int armorLife) {
		this.armorLife = armorLife;
		this.id = IdManager.getNewId();
	}
	
	/*
	 * @return the id of the armor
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @return the actual life points for the armor
	 */
	public int getArmorLife() {
		return armorLife;
	}
	
	/**
	 * 
	 * @param armorLife - the amount of life points to set the armorLife
	 */
	public void setArmorLife(int armorLife) {
		this.armorLife = armorLife;
	}
	
	/**
	 * 
	 * @return true if the armor has all its life, false otherwise
	 */
	public boolean isFull() {
		return getStartingLife() == armorLife;
	}
	
	/**
	 * 
	 * @return the starting life of the armor
	 */
	public abstract int getStartingLife();
	
	@Override
	public String toString() {
		return "Armor Id: " + id + "; Armor Life: " + armorLife;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Armor) {
			Armor armor = (Armor) obj;
			if(armor.getId() == id) {
				result = true;
			}
		}
		return result;
	}
}
