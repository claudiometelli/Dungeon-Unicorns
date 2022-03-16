package it.unicatt.poo.dungeonunicorns.graphics.levels;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import it.unicatt.poo.dungeonunicorns.beans.teleporters.TwoWayTeleporter;
import it.unicatt.poo.dungeonunicorns.graphics.MainGame;
import it.unicatt.poo.dungeonunicorns.graphics.beans.TiledRoom;
import it.unicatt.poo.dungeonunicorns.graphics.entities.TiledMonster;

/**
 * A class in which level 1 of the game is defined
 * 
 * @author elisamangiavacca
 * @version 1.0.0
 *
 */
public class LevelOne extends GenericLevel {

	@Override
	public List<TiledRoom> loadMaps() {
		List<TiledRoom> result = new ArrayList<TiledRoom>();
		AssetManager assetManager = new AssetManager();
		assetManager.setLoader(TiledMap.class, new TmxMapLoader());
		assetManager.load("assets/maps/ExtraLargeMap.tmx", TiledMap.class);
		assetManager.finishLoading();
		TiledRoom room = new TiledRoom(assetManager.get("assets/maps/ExtraLargeMap.tmx"));
		result.add(room);
		room.setSpawningPoint(room.getCoordinateByPosition(10, 3), MainGame.getInstance().getGameScreen().getPlayer());
		// teleport is defined in these coordinates
		TwoWayTeleporter.createTeleporter(room.getRoom().getCoordinateByPosition(4, 10), room.getRoom().getCoordinateByPosition(15, 5));
		return result;
	}

	@Override
	public List<TiledMonster> loadEnemies() {
		List<TiledMonster> result = new ArrayList<TiledMonster>();
		TiledMonster monster1 = new TiledMonster(MainGame.getInstance().getGameScreen().getPlayer());
		TiledMonster monster2 = new TiledMonster(MainGame.getInstance().getGameScreen().getPlayer());
		super.getRooms().get(0).setSpawningPoint(super.getRooms().get(0).getCoordinateByPosition(8, 3), monster1);
		super.getRooms().get(0).setSpawningPoint(super.getRooms().get(0).getCoordinateByPosition(7, 3), monster2);
		result.add(monster1);
		result.add(monster2);
		return result;
	}
	
	
}
