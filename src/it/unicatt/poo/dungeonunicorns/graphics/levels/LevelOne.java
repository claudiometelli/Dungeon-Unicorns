package it.unicatt.poo.dungeonunicorns.graphics.levels;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import it.unicatt.poo.dungeonunicorns.beans.Teleporter;
import it.unicatt.poo.dungeonunicorns.graphics.MainGame;
import it.unicatt.poo.dungeonunicorns.graphics.beans.TiledRoom;
import it.unicatt.poo.dungeonunicorns.graphics.entities.TiledMonster;

public class LevelOne extends GenericLevel {

	@Override
	public List<TiledRoom> loadMaps() {
		List<TiledRoom> result = new ArrayList<TiledRoom>(2);
		AssetManager assetManager = new AssetManager();
		assetManager.setLoader(TiledMap.class, new TmxMapLoader());
		assetManager.load("assets/maps/ExtraLargeMap.tmx", TiledMap.class);
		assetManager.load("assets/maps/TestMap.tmx", TiledMap.class);
		assetManager.finishLoading();
		TiledRoom room1 = new TiledRoom(assetManager.get("assets/maps/ExtraLargeMap.tmx"));
		TiledRoom room2 = new TiledRoom(assetManager.get("assets/maps/TestMap.tmx"));
		result.add(room1);
		result.add(room2);
		if(MainGame.getInstance().getGameScreen() == null) {
			System.out.println("gay");
		}
		room1.setSpawningPoint(room1.getCoordinateByPosition(2, 10), MainGame.getInstance().getGameScreen().getPlayer());
		Teleporter.createTeleporter(room1.getRoom().getCoordinateByPosition(4, 5), room1.getRoom().getCoordinateByPosition(15, 10));
		return result;
	}

	@Override
	public List<TiledMonster> loadEnemies() {
		List<TiledMonster> result = new ArrayList<TiledMonster>();
		TiledMonster monster1 = new TiledMonster(MainGame.getInstance().getGameScreen().getPlayer());
		TiledMonster monster2 = new TiledMonster(MainGame.getInstance().getGameScreen().getPlayer());
		super.getRooms().get(0).setSpawningPoint(super.getRooms().get(0).getCoordinateByPosition(9, 9), monster1);
		super.getRooms().get(0).setSpawningPoint(super.getRooms().get(0).getCoordinateByPosition(7, 8), monster2);
		result.add(monster1);
		result.add(monster2);
		return result;
	}
	
	
}
