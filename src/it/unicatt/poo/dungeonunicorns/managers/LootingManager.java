package it.unicatt.poo.dungeonunicorns.managers;

import it.unicatt.poo.dungeonunicorns.beans.armors.Armor;
import it.unicatt.poo.dungeonunicorns.beans.enums.LootChoose;

public class LootingManager {
	
	public static LootChoose lootChooseArmorResult(Armor playerArmor, Armor groundArmor) {
		LootChoose result = null;
		if(playerArmor == null) {
			result = LootChoose.TAKE;
		} else if(groundArmor.getStartingLife() > playerArmor.getStartingLife() && groundArmor.getArmorLife() > playerArmor.getArmorLife()) {
			result = LootChoose.TAKE;
		} else if(groundArmor.getStartingLife() > playerArmor.getStartingLife() && groundArmor.getArmorLife() < playerArmor.getArmorLife()) {
			result = LootChoose.LET_PLAYER_CHOOSE;
		} else if(groundArmor.getStartingLife() > playerArmor.getStartingLife() && groundArmor.getArmorLife() < playerArmor.getArmorLife()) {
			
		}
		return result;
	}
	
	public static String getLootTypeFromLootId(String lootId) {
		String result = null;
		if(lootId.contains(".")) {
			result = lootId.split(".")[0];
		}
		return result;
	}
}
