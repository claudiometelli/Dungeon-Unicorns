package it.unicatt.poo.dungeonunicorns.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public class IdManager {
	
	/**
	 * Minimum number of the possible id
	 */
	private static int MIN_NUM_ID = 0;
	/**
	 * Maximum number of the possible id
	 */
	private static int MAX_MUM_ID = 1000;
	
	/**
	 * A list with the id which has been already generated
	 */
	private static List<Integer> generatedIds = new ArrayList<Integer>();
	
	/**
	 * Create a new id
	 * @return the new id
	 */
	public static Integer getNewId() {
		Integer result = null;
		Random random = new Random();
	    do {
	    	result = random.nextInt(MAX_MUM_ID - MIN_NUM_ID) + MIN_NUM_ID;
	    } while(isIdAlreadyGenerated(result));
	    generatedIds.add(result);
		return result;
	}
	
	/**
	 * Check if the id is already generated
	 * @param id - the id to be check id it is already generated
	 * @return true if it is already generated, false otherwise
	 */
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
