package it.unicatt.poo.dungeonunicorns.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdManager {
	
	private static int MIN_NUM_ID = 0;
	private static int MAX_MUM_ID = 1000;
	
	private static List<Integer> generatedIds = new ArrayList<Integer>();
	
	public static Integer getNewId() {
		Integer result = null;
		Random random = new Random();
	    do {
	    	result = random.nextInt(MAX_MUM_ID - MIN_NUM_ID) + MIN_NUM_ID;
	    } while(isIdAlreadyGenerated(result));
	    generatedIds.add(result);
		return result;
	}
	
	private static boolean isIdAlreadyGenerated(Integer id) {
		boolean result = false;
		for(Integer generatedId : generatedIds) {
			if(generatedId.equals(id)) {
				result = true;
			}
		}
		return result;
	}
	
}
