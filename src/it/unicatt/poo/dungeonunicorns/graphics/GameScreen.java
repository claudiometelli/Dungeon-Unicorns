package it.unicatt.poo.dungeonunicorns.graphics;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import it.unicatt.poo.dungeonunicorns.core.EntityDirection;
import it.unicatt.poo.dungeonunicorns.exceptions.AttributeNotSpecifiedException;
import it.unicatt.poo.dungeonunicorns.graphics.beans.TiledRoom;
import it.unicatt.poo.dungeonunicorns.graphics.entities.TiledMonster;
import it.unicatt.poo.dungeonunicorns.graphics.entities.TiledPlayer;
import it.unicatt.poo.dungeonunicorns.graphics.levels.GenericLevel;
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
	
	private GenericLevel level;
	
	private TiledRoom room;
	private TiledPlayer player;
	private List<TiledMonster> monsters;
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
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false);
		player = new TiledPlayer();
		monsters = new ArrayList<TiledMonster>();
	}

	public void setLevel(GenericLevel level) {
		room = level.getRooms().get(0);
		for(TiledMonster m : level.getMonsters()) {
			monsters.add(m);
		}
		monster = level.getMonsters().get(0);
		mapRenderer = new OrthogonalTiledMapRenderer(room.getMap(), unitScale);
		mapRenderer.setView(camera);
		placeEntities();
		TurnManager.setTurnTo(player);
	}
	
	private void placeEntities() {
		player.placeEntity(room, room.getSpawningPoint(player));
		for(TiledMonster m : monsters) {
			if(m.isInRoom(room)) {
				m.placeEntity(room, room.getSpawningPoint(m));
			}
		}
	}
	
	public Float getCoordinateSize() {
		return coordinateSize;
	}
	
	public Integer getScreenWidth() {
		return screenWidth;
	}
	
	public Integer getScreenHeight() {
		return screenHeight;
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
		game.getFont().draw(game.getBatch(), "Player Life: " + player.getPlayer().getLife(), 1000, 500);
		game.getFont().draw(game.getBatch(), "Monster Life: " + monster.getMonster().getLife(), 1000, 470);
		game.getBatch().draw(player.getActualAnimation().getKeyFrame(stateTime, true), player.getXPositionEntityArea(),
				screenHeight - player.getYPositionEntityArea());
		for(TiledMonster m : monsters) {
			// https://jvm-gaming.org/t/libgdx-animation-problem/52386
			
			if (m.isInRoom(room) && m.getActualAnimation().getKeyFrame(stateTime, true) != null) {
				game.getBatch().draw(m.getActualAnimation().getKeyFrame(stateTime, true),
						m.getXPositionEntityArea(), screenHeight - m.getYPositionEntityArea());
			}
		}
		game.getBatch().end();
		checkPlayerMove();
		checkTeleport();
		checkMonsterMove();
	}

	private void checkPlayerMove() {
		if (Gdx.input.justTouched() && TurnManager.isEntityTurn(player)) {
			if(monster.getEntityArea().contains(Gdx.input.getX(), Gdx.input.getY() + coordinateSize)) {
				if(player.attack(monster)) {
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
				player.setJustTeleport(false);
				monster = TurnManager.setTurnToNextMonster(monsters);
				if(monster == null) {
					monster = monsters.get(0);
					TurnManager.setTurnTo(player);
				}
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
				player.setJustTeleport(false);
				monster = TurnManager.setTurnToNextMonster(monsters);
				if(monster == null) {
					monster = monsters.get(0);
					TurnManager.setTurnTo(player);
				}
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
				player.setJustTeleport(false);
				monster = TurnManager.setTurnToNextMonster(monsters);
				if(monster == null) {
					monster = monsters.get(0);
					TurnManager.setTurnTo(player);
				}
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
				player.setJustTeleport(false);
				monster = TurnManager.setTurnToNextMonster(monsters);
				if(monster == null) {
					monster = monsters.get(0);
					TurnManager.setTurnTo(player);
				}
			}
		}
	}
	
	private void checkTeleport() {
		if(player.getPlayer().getCurrentPosition().getTeleporter() != null && !player.isJustTeleport()) {
			if(player.getPlayer().getCurrentPosition().getTeleporter().getTeleportingPoint1().getRoom().equals(
					player.getPlayer().getCurrentPosition().getTeleporter().getTeleportingPoint2().getRoom())){
				player.teleport();
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
				monster = TurnManager.setTurnToNextMonster(monsters);
				if(monster == null) {
					monster = monsters.get(0);
					TurnManager.setTurnTo(player);
				}
			}
		}
		if (TurnManager.isEntityTurn(monster) && !monster.isNextMoveMoving()) {
			monster.nextMove();
			monster = TurnManager.setTurnToNextMonster(monsters);
			if(monster == null) {
				monster = monsters.get(0);
				TurnManager.setTurnTo(player);
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

	public TiledPlayer getPlayer() {
		return player;
	}
	
}
