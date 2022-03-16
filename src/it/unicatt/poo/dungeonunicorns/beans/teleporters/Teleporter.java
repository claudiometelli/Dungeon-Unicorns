package it.unicatt.poo.dungeonunicorns.beans.teleporters;

import it.unicatt.poo.dungeonunicorns.beans.Coordinate;

/**
 * A class which represent a single teleport in the game
 * 
 * @author claudiometelli
 * @version 1.0.0
 */
public abstract class Teleporter {
	
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
	public Teleporter(Coordinate teleportingPoint1, Coordinate teleportingPoint2) {
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
//	public static void createTeleporter(Coordinate teleportingPoint1, Coordinate teleportingPoint2) {
//		Teleporter teleporter = new Teleporter(teleportingPoint1, teleportingPoint2);
//		teleporter.getTeleportingPoint1().setTeleporter(teleporter);
//		teleporter.getTeleportingPoint2().setTeleporter(teleporter);
//	}
	
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
	 * teleport the entity instead of what type of teleporter you take
	 * 
	 * @return true if you can teleport, false otherwise
	 */
	public abstract boolean teleport();
	
}
