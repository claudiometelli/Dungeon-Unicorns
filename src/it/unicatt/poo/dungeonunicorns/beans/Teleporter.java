package it.unicatt.poo.dungeonunicorns.beans;

/**
 * A class which represent a single teleport in the game
 * 
 * @author claudiometelli
 * @version 1.0.0
 */
public class Teleporter {
	
	/**
	 * The coordinate on which the first teleporting point is placed
	 */
	private Coordinate teleportingPoint1;
	
	/**
	 * The coordinate on which the second teleporting point is placed
	 */
	private Coordinate teleportingPoint2;
	
	/**
	 * Constructor for Teleporter which sets the two teleporting points
	 * 
	 * @param teleportingPoint1 - the coordinate on which the first teleporting point is placed
	 * @param teleportingPoint2 - the coordinate on which the second teleporting point is placed
	 */
	private Teleporter(Coordinate teleportingPoint1, Coordinate teleportingPoint2) {
		this.teleportingPoint1 = teleportingPoint1;
		this.teleportingPoint2 = teleportingPoint2;
		
	}
	
	/**
	 * Method used to create a teleporter
	 * Use this and not the constructor because you just need to set the teleporting points to teleport entities instead of create an instance of teleport
	 * 
	 * @param teleportingPoint1
	 * @param teleportingPoint2
	 */
	public static void createTeleporter(Coordinate teleportingPoint1, Coordinate teleportingPoint2) {
		Teleporter teleporter = new Teleporter(teleportingPoint1, teleportingPoint2);
		teleporter.getTeleportingPoint1().setTeleporter(teleporter);
		teleporter.getTeleportingPoint2().setTeleporter(teleporter);
	}
	
	/**
	 * 
	 * @return the coordinate on which the first teleporting point is placed
	 */
	public Coordinate getTeleportingPoint1() {
		return teleportingPoint1;
	}
	
	/**
	 * 
	 * @param teleportingPoint1 the coordinate on which the first teleporting point must be placed
	 */
	public void setTeleportingPoint1(Coordinate teleportingPoint1) {
		this.teleportingPoint1 = teleportingPoint1;
	}
	
	/**
	 * 
	 * @return the coordinate on which the second teleporting point is placed
	 */
	public Coordinate getTeleportingPoint2() {
		return teleportingPoint2;
	}
	
	/**
	 * 
	 * @param teleportingPoint2 the coordinate on which the second teleporting point must be placed
	 */
	public void setTeleportingPoint2(Coordinate teleportingPoint2) {
		this.teleportingPoint2 = teleportingPoint2;
	}
	
	/**
	 * teleport the entity on one of the teleporting point to the other
	 * 
	 * @return true if you can teleport, false otherwise
	 */
	public boolean teleport() {
		boolean result = false;
		if(teleportingPoint1.getEntity() != null && teleportingPoint1.getEntity().getCurrentPosition().equals(teleportingPoint1)) {
			result = teleportingPoint1.getEntity().moveIntoPosition(teleportingPoint2);
		} else if (teleportingPoint2.getEntity() != null && teleportingPoint2.getEntity().getCurrentPosition().equals(teleportingPoint2)) {
			result = teleportingPoint2.getEntity().moveIntoPosition(teleportingPoint1);
		}
		return result;
	}
	
}
