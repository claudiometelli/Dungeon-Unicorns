package it.unicatt.poo.dungeonunicorns.beans;

import java.util.List;

import it.unicatt.poo.dungeonunicorns.beans.armors.Armor;
import it.unicatt.poo.dungeonunicorns.core.Entity;
import it.unicatt.poo.dungeonunicorns.managers.LootingManager;

public class LootBox {
	
	private List<Lootable> lootElements;
	private Coordinate position;
	
	private LootBox(List<Lootable> lootElements, Coordinate position) {
		this.lootElements = lootElements;
		this.position = position;
	}
	
	public boolean dropLoot(Lootable loot, Entity entity) {
		boolean result = false;
		String lootType = LootingManager.getLootTypeFromLootId(loot.getLootId());
		System.out.println(lootType);
		if(lootType != null) {
			if(lootType.equals("Armor")) {
				LootingManager.lootChooseArmorResult(entity.getArmor(), (Armor) loot);
			}
		}
//		for(Lootable lootElement : lootElements) {
//			String lootType = getLootTypeFromLootId(lootElement.getLootId());
//			System.out.println(lootType);
//			if(lootType != null) {
//				if(lootType.equals("Armor")) {
//					LootChooseManager.lootChooseArmorResult(entity.getArmor(), (Armor) lootElement);
//				}
//			}
//		}
		return result;
	}
	
	
	
	public Coordinate getPosition() {
		return position;
	}
	
}
