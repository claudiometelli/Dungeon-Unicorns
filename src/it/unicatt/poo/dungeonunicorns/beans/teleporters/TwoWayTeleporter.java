package it.unicatt.poo.dungeonunicorns.beans.teleporters;

import it.unicatt.poo.dungeonunicorns.beans.Coordinate;

public class TwoWayTeleporter  extends Teleporter {
	
	/**
	 * Constructor for Teleporter which sets the two teleporting points
	 * 
	 * @param teleportingPoint1 - the coordinate on which the start teleporting point is placed
	 * @param teleportingPoint2 - the coordinate on which the destination teleporting point is placed
	 */
	private TwoWayTeleporter(Coordinate teleportingPoint1, Coordinate teleportingPoint2) {
		super(teleportingPoint1, teleportingPoint2);
	}
	
	/**
	 * Method used to create a teleporter
	 * Use this and not the constructor because you just need to set the teleporting points to teleport entities instead of create an instance of teleport
	 * 
	 * @param teleportingPoint1
	 * @param teleportingPoint2
	 * 
	 * @return true if you can generate the teleporter, false otherwise
	 */
	public static boolean createTeleporter(Coordinate teleportingPoint1, Coordinate teleportingPoint2) {
		boolean result = false;
		if(teleportingPoint1.isWalkable() && teleportingPoint2.isWalkable()) {
			TwoWayTeleporter twoWayTeleporter = new TwoWayTeleporter(teleportingPoint1, teleportingPoint2);
			twoWayTeleporter.getTeleportingPoint1().setTeleporter(twoWayTeleporter);
			twoWayTeleporter.getTeleportingPoint2().setTeleporter(twoWayTeleporter);
			result = true;
		}
		return result;
	}
	
	/**
	 * teleport the entity from one teleporting point into the other
	 * 
	 * @return true if you can teleport, false otherwise
	 */
	@Override
	public boolean teleport() {
		boolean result = false;
		if(super.getTeleportingPoint1().getEntity() != null && super.getTeleportingPoint1().getEntity().getCurrentPosition().equals(super.getTeleportingPoint1())) {
			result = super.getTeleportingPoint1().getEntity().moveIntoPosition(super.getTeleportingPoint2());
		} else if (super.getTeleportingPoint2().getEntity() != null && super.getTeleportingPoint2().getEntity().getCurrentPosition().equals(super.getTeleportingPoint2())) {
			result = super.getTeleportingPoint2().getEntity().moveIntoPosition(super.getTeleportingPoint1());
		}
		return result;
	}
}
