package it.unicatt.poo.dungeonunicorns.graphics;

import java.nio.file.Paths;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import it.unicatt.poo.dungeonunicorns.exceptions.AttributeNotSpecifiedException;
import it.unicatt.poo.dungeonunicorns.graphics.beans.TiledRoom;
import it.unicatt.poo.dungeonunicorns.graphics.entities.EntityDirection;
import it.unicatt.poo.dungeonunicorns.graphics.entities.TiledPlayer;
import it.unicatt.poo.dungeonunicorns.managers.TextureSizeManager;
import it.unicatt.poo.dungeonunicorns.utils.IOUtils;

public class GameScreen implements Screen {

	private final static String SCALE_CONFIG_PATH = "configfiles/ScaleConfig.txt";

	private final MainGame game;

	private Float unitScale;
	private Float coordinateSize;

	private OrthographicCamera camera;
	private OrthogonalTiledMapRenderer mapRenderer;
	private TiledRoom room;
	private TiledPlayer player;

	private float stateTime;

	public GameScreen(final MainGame game) {
		this.game = game;
		this.stateTime = 0f;
		game.setGameScreen(this);
		try {
			unitScale = IOUtils.getFloatAttributeFromConfigFile(Paths.get(SCALE_CONFIG_PATH), "MAIN_UNIT_SCALE");
			coordinateSize = IOUtils.getFloatAttributeFromConfigFile(Paths.get(SCALE_CONFIG_PATH), "BASE_TILE_UNIT")
					* unitScale;
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
		assetManager.load("assets/TestMap.tmx", TiledMap.class);
		assetManager.finishLoading();
		room = new TiledRoom(assetManager.get("assets/TestMap.tmx"));
		player = new TiledPlayer();
		placeEntities();
	}

	private void placeEntities() {
		player.placeEntity(room, 6, 6);
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
				player.getYPositionEntityArea());
		game.getBatch().end();

		if (Gdx.input.isKeyJustPressed(Keys.RIGHT) && !player.isMoving()) {
			if (player.moveRight()) {
				player.setActualAnimation(player.getRightMovementAnimation());
			}
		} else if (player.isMoving() && player.getActualDirection().equals(EntityDirection.RIGHT)) {
			player.moveRight();
			if (!player.isMoving()) {
				player.setActualAnimation(player.getStoppedAnimation());
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.LEFT) && !player.isMoving()) {
			if (player.moveLeft()) {
				player.setActualAnimation(player.getLeftMovementAnimation());
			}
		} else if (player.isMoving() && player.getActualDirection().equals(EntityDirection.LEFT)) {
			player.moveLeft();
			if (!player.isMoving()) {
				player.setActualAnimation(player.getStoppedAnimation());
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.UP) && !player.isMoving()) {
			if (player.moveUp()) {
				player.setActualAnimation(player.getUpMovementAnimation());
			}
		} else if (player.isMoving() && player.getActualDirection().equals(EntityDirection.UP)) {
			player.moveUp();
			if (!player.isMoving()) {
				player.setActualAnimation(player.getStoppedAnimation());
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.DOWN) && !player.isMoving()) {
			if (player.moveDown()) {
				player.setActualAnimation(player.getDownMovementAnimation());
			}
		} else if (player.isMoving() && player.getActualDirection().equals(EntityDirection.DOWN)) {
			player.moveDown();
			if (!player.isMoving()) {
				player.setActualAnimation(player.getStoppedAnimation());
			}
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
