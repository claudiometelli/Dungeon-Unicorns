package it.unicatt.poo.dungeonunicorns.core;

import it.unicatt.poo.dungeonunicorns.beans.SquareRoom;

public class DemoTest {
	public static void main(String[] args) {
		SquareRoom room = new SquareRoom(15, 15, 2, 10);
		//CrossRoom room = new CrossRoom(10, 10, 4, 4);
		Player player = new Player();
		Monster monster = new Monster(player);
		player.placeEntity(room, 6, 7);
		monster.placeEntity(room, 8, 7);
		System.out.println(room.printRoom() + player.toString() + "\n" + monster.toString());
		monster.getIntelligence().nextMove();
		monster.getIntelligence().nextMove();
		monster.getIntelligence().nextMove();
		System.out.println(room.printRoom() + player.toString() + "\n" + monster.toString());
	}
}
