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

public class LevelTwo extends GenericLevel {
	
	@Override
	public List<TiledRoom> loadMaps() {
		List<TiledRoom> result = new ArrayList<TiledRoom>();
		AssetManager assetManager = new AssetManager();
		assetManager.setLoader(TiledMap.class, new TmxMapLoader());
		assetManager.load("assets/maps/TestMap.tmx", TiledMap.class);
		assetManager.finishLoading();
		TiledRoom room = new TiledRoom(assetManager.get("assets/maps/TestMap.tmx"));
		result.add(room);
		room.setSpawningPoint(room.getCoordinateByPosition(4, 4), MainGame.getInstance().getGameScreen().getPlayer());
		Teleporter.createTeleporter(room.getRoom().getCoordinateByPosition(5, 4), room.getRoom().getCoordinateByPosition(9, 9));
		return result;
	}

	@Override
	public List<TiledMonster> loadEnemies() {
		List<TiledMonster> result = new ArrayList<TiledMonster>();
		TiledMonster monster = new TiledMonster(MainGame.getInstance().getGameScreen().getPlayer());
		super.getRooms().get(0).setSpawningPoint(super.getRooms().get(0).getCoordinateByPosition(6, 6), monster);
		result.add(monster);
		return result;
	}
}
