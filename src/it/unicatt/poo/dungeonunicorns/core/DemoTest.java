package it.unicatt.poo.dungeonunicorns.core;

import it.unicatt.poo.dungeonunicorns.beans.SquareRoom;

public class DemoTest {
	public static void main(String[] args) {
		SquareRoom room = new SquareRoom(15, 15, 4, 4);
		//CrossRoom room = new CrossRoom(10, 10, 4, 4);
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
