package it.unicatt.poo.dungeonunicorns.graphics;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import it.unicatt.poo.dungeonunicorns.core.EntityDirection;
import it.unicatt.poo.dungeonunicorns.exceptions.AttributeNotSpecifiedException;
import it.unicatt.poo.dungeonunicorns.graphics.beans.TiledRoom;
import it.unicatt.poo.dungeonunicorns.graphics.entities.TiledMonster;
import it.unicatt.poo.dungeonunicorns.graphics.entities.TiledPlayer;
import it.unicatt.poo.dungeonunicorns.managers.TurnManager;
import it.unicatt.poo.dungeonunicorns.utils.IOUtils;

public class GameScreen implements Screen {

	private final static String SCALE_CONFIG_PATH = "configfiles/ScaleConfig.txt";
	private final static String SCREEN_CONFIG_PATH = "configfiles/ScreenConfig.txt";

	private final MainGame game;

	private Integer screenWidth;
	private Integer screenHeight;
	private Float unitScale;
	private Float coordinateSize;

	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer mapRenderer;
	private TiledRoom room;
	private TiledPlayer player;
	private TiledMonster monster;

	private float stateTime;

	public GameScreen(final MainGame game) {
		this.game = game;
		this.stateTime = 0f;
		game.setGameScreen(this);
		try {
			Path screenConfig = Paths.get(SCREEN_CONFIG_PATH);
			Path scaleConfig = Paths.get(SCALE_CONFIG_PATH);
			screenWidth = IOUtils.getIntegerAttributeFromConfigFile(screenConfig, "Width");
			screenHeight = IOUtils.getIntegerAttributeFromConfigFile(screenConfig, "Height");
			unitScale = IOUtils.getFloatAttributeFromConfigFile(scaleConfig, "MAIN_UNIT_SCALE");
			coordinateSize = IOUtils.getFloatAttributeFromConfigFile(scaleConfig, "BASE_TILE_UNIT") * unitScale;
		} catch (AttributeNotSpecifiedException e) {
			System.err.println(e.getMessage());
		}
		initGameObjects();
		mapRenderer = new OrthogonalTiledMapRenderer(room.getMap(), unitScale);
		mapRenderer.setView(camera);
	}

	public Float getCoordinateSize() {
		return coordinateSize;
	}

	/**
	 * Method to load assets using the asset manager and assign them to the
	 * properties of the class
	 */
	private void initGameObjects() {
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false);
		AssetManager assetManager = new AssetManager();
		assetManager.setLoader(TiledMap.class, new TmxMapLoader());
		assetManager.load("assets/maps/ExtraLargeMap.tmx", TiledMap.class);
		assetManager.finishLoading();
		room = new TiledRoom(assetManager.get("assets/maps/ExtraLargeMap.tmx"));
		player = new TiledPlayer();
		monster = new TiledMonster(player);
		placeEntities();
		TurnManager.setTurnTo(player);
	}

	private void placeEntities() {
		player.placeEntity(room, 6, 6);
		monster.placeEntity(room, 8, 8);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		stateTime += Gdx.graphics.getDeltaTime();
		camera.update();
		mapRenderer.render();
		game.getBatch().begin();
		game.getBatch().draw(player.getActualAnimation().getKeyFrame(stateTime, true), player.getXPositionEntityArea(),
				screenHeight - player.getYPositionEntityArea());
		// https://jvm-gaming.org/t/libgdx-animation-problem/52386
		if (monster.getActualAnimation().getKeyFrame(stateTime, true) != null) {
			game.getBatch().draw(monster.getActualAnimation().getKeyFrame(stateTime, true),
					monster.getXPositionEntityArea(), screenHeight - monster.getYPositionEntityArea());
		}
		game.getBatch().end();
		checkPlayerMove();
		checkMonsterMove();
	}

	private void checkPlayerMove() {
		if (Gdx.input.justTouched() && TurnManager.isEntityTurn(player)) {
			if(monster.getEntityArea().contains(Gdx.input.getX(), Gdx.input.getY() + coordinateSize)) {
				if(player.attack(monster)) {
					System.out.println("ATTACCATO");
					TurnManager.setTurnTo(monster);
				}
			}
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.D) && !player.isMoving() && TurnManager.isEntityTurn(player)) {
			if (player.moveRight()) {
				player.setActualAnimation(player.getRightMovementAnimation());
			}
		} else if (player.isMoving() && player.getActualDirection().equals(EntityDirection.RIGHT)) {
			player.moveRight();
			if (!player.isMoving()) {
				player.setActualAnimation(player.getStoppedAnimation());
				TurnManager.setTurnTo(monster);
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.A) && !player.isMoving() && TurnManager.isEntityTurn(player)) {
			if (player.moveLeft()) {
				player.setActualAnimation(player.getLeftMovementAnimation());
			}
		} else if (player.isMoving() && player.getActualDirection().equals(EntityDirection.LEFT)) {
			player.moveLeft();
			if (!player.isMoving()) {
				player.setActualAnimation(player.getStoppedAnimation());
				TurnManager.setTurnTo(monster);
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.W) && !player.isMoving() && TurnManager.isEntityTurn(player)) {
			if (player.moveUp()) {
				player.setActualAnimation(player.getUpMovementAnimation());
			}
		} else if (player.isMoving() && player.getActualDirection().equals(EntityDirection.UP)) {
			player.moveUp();
			if (!player.isMoving()) {
				player.setActualAnimation(player.getStoppedAnimation());
				TurnManager.setTurnTo(monster);
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.S) && !player.isMoving() && TurnManager.isEntityTurn(player)) {
			if (player.moveDown()) {
				player.setActualAnimation(player.getDownMovementAnimation());
			}
		} else if (player.isMoving() && player.getActualDirection().equals(EntityDirection.DOWN)) {
			player.moveDown();
			if (!player.isMoving()) {
				player.setActualAnimation(player.getStoppedAnimation());
				TurnManager.setTurnTo(monster);
			}
		}
	}

	private void checkMonsterMove() {
		if (TurnManager.isEntityTurn(monster) && monster.isNextMoveMoving() && !monster.isMoving()) {
			EntityDirection direction = monster.nextMove();
			if (direction.equals(EntityDirection.RIGHT)) {
				monster.setActualAnimation(monster.getRightMovementAnimation());
			} else if(direction.equals(EntityDirection.LEFT)) {
				monster.setActualAnimation(monster.getLeftMovementAnimation());
			} else if(direction.equals(EntityDirection.UP)) {
				monster.setActualAnimation(monster.getUpMovementAnimation());
			} else if(direction.equals(EntityDirection.DOWN)) {
				monster.setActualAnimation(monster.getDownMovementAnimation());
			}
		} else if (TurnManager.isEntityTurn(monster) && monster.isMoving()) {
			monster.nextMove();
			if (!monster.isMoving()) {
				monster.setActualAnimation(monster.getStoppedAnimation());
				TurnManager.setTurnTo(player);
			}
		}
		if (TurnManager.isEntityTurn(monster) && !monster.isNextMoveMoving()) {
			monster.nextMove();
			TurnManager.setTurnTo(player);
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		mapRenderer.dispose();
	}

}
