package it.unicatt.poo.dungeonunicorns.beans;

public class Teleporter {
	
	private Coordinate teleportingPoint1;
	private Coordinate teleportingPoint2;
	
	private Teleporter(Coordinate teleportingPoint1, Coordinate teleportingPoint2) {
		this.teleportingPoint1 = teleportingPoint1;
		this.teleportingPoint2 = teleportingPoint2;
		
	}
	
	public static void createTeleporter(Coordinate teleportingPoint1, Coordinate teleportingPoint2) {
		Teleporter teleporter = new Teleporter(teleportingPoint1, teleportingPoint2);
		teleporter.getTeleportingPoint1().setTeleporter(teleporter);
		teleporter.getTeleportingPoint2().setTeleporter(teleporter);
	}
	
	public Coordinate getTeleportingPoint1() {
		return teleportingPoint1;
	}
	
	public void setTeleportingPoint1(Coordinate teleportingPoint1) {
		this.teleportingPoint1 = teleportingPoint1;
	}
	
	public Coordinate getTeleportingPoint2() {
		return teleportingPoint2;
	}
	
	public void setTeleportingPoint2(Coordinate teleportingPoint2) {
		this.teleportingPoint2 = teleportingPoint2;
	}
	
//	private boolean teleport(Entity entity) {
//		boolean result = false;
//		if(entity.getCurrentPosition().equals(teleportingPoint1)) {
//			result = entity.moveIntoPosition(teleportingPoint2);
//		} else if (entity.getCurrentPosition().equals(teleportingPoint2)) {
//			result = entity.moveIntoPosition(teleportingPoint1);
//		}
//		return result;
//	}
	
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
