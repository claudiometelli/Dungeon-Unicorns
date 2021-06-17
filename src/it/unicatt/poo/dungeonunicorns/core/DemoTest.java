package it.unicatt.poo.dungeonunicorns.core;

import it.unicatt.poo.dungeonunicorns.beans.rooms.CrossRoom;

public class DemoTest {
	public static void main(String[] args) {
		CrossRoom room = new CrossRoom(10, 10, 4, 4);
		Player player = new Player();
		Monster monster = new Monster();
		player.placeEntity(room, 3, 2);
		monster.placeEntity(room, 4, 2);
		System.out.println(room.printRoom() + player.toString() + "\n" + monster.toString());
		player.moveUp();
		System.out.println(room.printRoom() + player.toString() + "\n" + monster.toString());
		player.moveDown();
		player.attack(monster);
		System.out.println(room.printRoom() + player.toString() + "\n" + monster.toString());
	}
}
