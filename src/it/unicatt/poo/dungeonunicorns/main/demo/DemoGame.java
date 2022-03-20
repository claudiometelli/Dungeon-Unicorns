package it.unicatt.poo.dungeonunicorns.main.demo;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import it.unicatt.poo.dungeonunicorns.beans.LootBox;
import it.unicatt.poo.dungeonunicorns.beans.Lootable;
import it.unicatt.poo.dungeonunicorns.beans.SquareRoom;
import it.unicatt.poo.dungeonunicorns.beans.armors.BlueArmor;
import it.unicatt.poo.dungeonunicorns.beans.armors.WhiteArmor;
import it.unicatt.poo.dungeonunicorns.beans.enums.EntityDirection;
import it.unicatt.poo.dungeonunicorns.beans.teleporters.OneWayTeleporter;
import it.unicatt.poo.dungeonunicorns.beans.weapons.SimpleSword;
import it.unicatt.poo.dungeonunicorns.core.Monster;
import it.unicatt.poo.dungeonunicorns.core.Player;

public class DemoGame extends Game {
	
	private Player player;
	private Monster monster;
	private SquareRoom room;
	private SquareRoom room2;
	private LootBox lootBox;
	
	@Override
	public void create() {
		room = new SquareRoom(15, 15, 2, 10);
		room2 = new SquareRoom(15, 15, 3, 6);
		OneWayTeleporter.createTeleporter(room.getCoordinateByPosition(3, 3), room2.getCoordinateByPosition(5, 5));
		OneWayTeleporter.createTeleporter(room2.getCoordinateByPosition(5, 7), room.getCoordinateByPosition(3, 5));
		player = new Player();
		player.setArmor(WhiteArmor.getNewWhiteArmor());
		monster = new Monster(player);
		player.placeEntity(room, 4, 3);
		monster.placeEntity(room, 8, 5);
		List<Lootable> loot = new ArrayList<Lootable>();
		loot.add(BlueArmor.getNewBlueArmor());
		loot.add(SimpleSword.getNewSimpleSword());
		lootBox = LootBox.placeLootBox(loot, room, 5,4);
	}
	
	@Override
	public void render() {
		boolean hasMoved = false;
		boolean hasAttacked = false;
		if(Gdx.input.isKeyJustPressed(Keys.A) || Gdx.input.isKeyJustPressed(Keys.LEFT)){
			if(player.canMoveLeft()) {
				player.moveLeft();
				hasMoved = true;
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.D) || Gdx.input.isKeyJustPressed(Keys.RIGHT)){
			if(player.canMoveRight()) {
				player.moveRight();
				hasMoved = true;
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.W) || Gdx.input.isKeyJustPressed(Keys.UP)){
			if(player.canMoveUp()) {
				player.moveUp();
				hasMoved = true;
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.S) || Gdx.input.isKeyJustPressed(Keys.DOWN)){
			if(player.canMoveDown()) {
				player.moveDown();
				hasMoved = true;
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT)){
			player.attack(EntityDirection.RIGHT);
			hasAttacked = true;
		}
		if(Gdx.input.isKeyJustPressed(Keys.LEFT)){
			player.attack(EntityDirection.LEFT);
			hasAttacked = true;
		}
		if(Gdx.input.isKeyJustPressed(Keys.UP)){
			player.attack(EntityDirection.UP);
			hasAttacked = true;
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
			player.attack(EntityDirection.DOWN);
			hasAttacked = true;
		}
		if(Gdx.input.isKeyJustPressed(Keys.Q)){
			player.LootFromBorderingLootBoxes();
		}
		if(Gdx.input.isKeyJustPressed(Keys.E)){
			System.out.println(player.getCurrentRoom().printRoom() + player.toString() + "\n" + monster.toString() + "\n" + lootBox.toString());
		}
		if(hasMoved) {
			if(player.isOnTeleporter()) {
				player.useTeleport();
			}
			monster.nextMove();
		}
		if(hasAttacked) {
			monster.nextMove();
		}
		
	}

}
