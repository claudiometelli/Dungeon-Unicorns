package it.unicatt.poo.dungeonunicorns.core;

import it.unicatt.poo.dungeonunicorns.beans.SquareRoom;
import it.unicatt.poo.dungeonunicorns.beans.Teleporter;

public class DemoTest {
	public static void main(String[] args) {
		SquareRoom room = new SquareRoom(15, 15, 2, 10);
		SquareRoom room2 = new SquareRoom(15, 15, 3, 6);
		Teleporter.createTeleporter(room.getCoordinateByPosition(3, 3), room2.getCoordinateByPosition(5, 5));
		//CrossRoom room = new CrossRoom(10, 10, 4, 4);
		Player player = new Player();
		Monster monster = new Monster(player);
		player.placeEntity(room, 4, 3);
		monster.placeEntity(room, 8, 7);
		System.out.println(room.printRoom() + player.toString() + "\n" + monster.toString());
		player.moveLeft();
		if(player.getCurrentPosition().getTeleporter() != null) {
			player.getCurrentPosition().getTeleporter().teleport();
		}
		System.out.println(room.printRoom() + player.toString() + "\n" + monster.toString());
		System.out.println(room2.printRoom() + player.toString() + "\n" + monster.toString());
	}
}
