package it.unicatt.poo.dungeonunicorns.graphics.entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import it.unicatt.poo.dungeonunicorns.core.EntityDirection;
import it.unicatt.poo.dungeonunicorns.core.Player;
import it.unicatt.poo.dungeonunicorns.graphics.MainGame;
import it.unicatt.poo.dungeonunicorns.managers.TextureSizeManager;

/**
 * A class which extend TiledEntity
 * define the unicorn as we see it in the game
 * 
 * @author elisamangiavacca
 * @version 1.0.0
 *
 */
public class TiledPlayer extends TiledEntity {
	
	private boolean justTeleport;
	
	public TiledPlayer() {
		super();
		super.setEntity(new Player());
		readTexturesAndAssign();
	}
	
	public Player getPlayer() {
		return (Player) super.getEntity();
	}
	
	public boolean attack(TiledEntity entity) {
		return getPlayer().attack(entity.getEntity());
	}
	
	public boolean isJustTeleport() {
		return justTeleport;
	}
	
	public void setJustTeleport(boolean justTeleport) {
		this.justTeleport = justTeleport;
	}
	
	public boolean teleport() {
		boolean result = false;
		if(!justTeleport) {
			super.getCoordinate().setEntity(null);
			result = getPlayer().getCurrentPosition().getTeleporter().teleport();
			if(result) {
				super.setXPositionEntityArea(getPlayer().getCurrentPosition().getX() * MainGame.getInstance().getGameScreen().getCoordinateSize());
				super.setYPositionEntityArea(MainGame.getInstance().getGameScreen().getScreenHeight() - 
						getPlayer().getCurrentPosition().getY() * MainGame.getInstance().getGameScreen().getCoordinateSize());
				super.setCoordinate(super.getRoom().getCoordinateByPosition(super.getEntity().getCurrentPosition()));
				super.getCoordinate().setEntity(this);
				justTeleport = true;
			} else {
				super.getCoordinate().setEntity(this);
			}
			
		}
		return result;
	}

	public boolean moveRight() {
		boolean result = getPlayer().canMoveRight();
		if(!super.isMoving() && result) {
			super.setArrivingPoint(super.getXPositionEntityArea() + MainGame.getInstance().getGameScreen().getCoordinateSize());
			super.setActualDirection(EntityDirection.RIGHT);
			super.setMoving(true);
		}
		if(result) {
			super.setXPositionEntityArea(super.getXPositionEntityArea() + MainGame.getInstance().getGameScreen().getCoordinateSize() / NUMBER_OF_ANIMATIONS_DURING_MOVEMENT);
			if(super.getXPositionEntityArea() >= super.getArrivingPoint()) {
				super.setXPositionEntityArea(super.getArrivingPoint());
				super.getCoordinate().setEntity(null);
				super.getEntity().moveRight();
				super.setCoordinate(super.getRoom().getCoordinateByPosition(super.getEntity().getCurrentPosition()));
				super.getCoordinate().setEntity(this);
				super.setActualDirection(EntityDirection.NO_DIRECTION);
				super.setMoving(false);
			}
		}
		return result;
	}
	
	public boolean moveLeft() {
		boolean result = getPlayer().canMoveLeft();
		if(!super.isMoving() && result) {
			super.setArrivingPoint(super.getXPositionEntityArea() - MainGame.getInstance().getGameScreen().getCoordinateSize());
			super.setActualDirection(EntityDirection.LEFT);
			super.setMoving(true);
		}
		if(result) {
			super.setXPositionEntityArea(super.getXPositionEntityArea() - MainGame.getInstance().getGameScreen().getCoordinateSize() / NUMBER_OF_ANIMATIONS_DURING_MOVEMENT);
			if(super.getXPositionEntityArea() <= super.getArrivingPoint()) {
				super.setXPositionEntityArea(super.getArrivingPoint());
				super.getCoordinate().setEntity(null);
				super.getEntity().moveLeft();
				super.setCoordinate(super.getRoom().getCoordinateByPosition(super.getEntity().getCurrentPosition()));
				super.getCoordinate().setEntity(this);
				super.setActualDirection(EntityDirection.NO_DIRECTION);
				super.setMoving(false);
			}
		}
		return result;
	}
	
	public boolean moveUp() {
		boolean result = getPlayer().canMoveUp();
		if(!super.isMoving() && result) {
			super.setArrivingPoint(super.getYPositionEntityArea() - MainGame.getInstance().getGameScreen().getCoordinateSize());
			super.setActualDirection(EntityDirection.UP);
			super.setMoving(true);
		}
		if(result) {
			super.setYPositionEntityArea(super.getYPositionEntityArea() - MainGame.getInstance().getGameScreen().getCoordinateSize() / NUMBER_OF_ANIMATIONS_DURING_MOVEMENT);
			if(super.getYPositionEntityArea() <= super.getArrivingPoint()) {
				super.setYPositionEntityArea(super.getArrivingPoint());
				super.getCoordinate().setEntity(null);
				super.getEntity().moveUp();
				super.setCoordinate(super.getRoom().getCoordinateByPosition(super.getEntity().getCurrentPosition()));
				super.getCoordinate().setEntity(this);
				super.setActualDirection(EntityDirection.NO_DIRECTION);
				super.setMoving(false);
			}
		}
		return result;
	}
	
	public boolean moveDown() {
		boolean result = getPlayer().canMoveDown();
		if(!super.isMoving() && result) {
			super.setArrivingPoint(super.getYPositionEntityArea() + MainGame.getInstance().getGameScreen().getCoordinateSize());
			super.setActualDirection(EntityDirection.DOWN);
			super.setMoving(true);
		}
		if(result) {
			super.setYPositionEntityArea(super.getYPositionEntityArea() + MainGame.getInstance().getGameScreen().getCoordinateSize() / NUMBER_OF_ANIMATIONS_DURING_MOVEMENT);
			if(super.getYPositionEntityArea() >= super.getArrivingPoint()) {
				super.setYPositionEntityArea(super.getArrivingPoint());
				super.getCoordinate().setEntity(null);
				super.getEntity().moveDown();
				super.setCoordinate(super.getRoom().getCoordinateByPosition(super.getEntity().getCurrentPosition()));
				super.getCoordinate().setEntity(this);
				super.setActualDirection(EntityDirection.NO_DIRECTION);
				super.setMoving(false);
			}
		}
		return result;
	}
	
	@Override
	protected void readTexturesAndAssign() {
		AssetManager assetManager = new AssetManager();
		assetManager.load("assets/player_assets/player_not_moving.png", Texture.class);
		assetManager.load("assets/player_assets/right_movement_00.png", Texture.class);
		assetManager.load("assets/player_assets/right_movement_01.png", Texture.class);
		assetManager.load("assets/player_assets/right_movement_02.png", Texture.class);
		assetManager.load("assets/player_assets/left_movement_00.png", Texture.class);
		assetManager.load("assets/player_assets/left_movement_01.png", Texture.class);
		assetManager.load("assets/player_assets/left_movement_02.png", Texture.class);
		assetManager.load("assets/player_assets/up_movement_00.png", Texture.class);
		assetManager.load("assets/player_assets/up_movement_01.png", Texture.class);
		assetManager.load("assets/player_assets/up_movement_02.png", Texture.class);
		assetManager.load("assets/player_assets/down_movement_00.png", Texture.class);
		assetManager.load("assets/player_assets/down_movement_01.png", Texture.class);
		assetManager.load("assets/player_assets/down_movement_02.png", Texture.class);
		assetManager.finishLoading();
		Texture mainPlayerTexture = TextureSizeManager.resizePlayer(assetManager.get("assets/player_assets/player_not_moving.png"));
		Texture rightMovementTexture0 = TextureSizeManager.resizePlayer(assetManager.get("assets/player_assets/right_movement_00.png"));
		Texture rightMovementTexture1 = TextureSizeManager.resizePlayer(assetManager.get("assets/player_assets/right_movement_01.png"));
		Texture rightMovementTexture2 = TextureSizeManager.resizePlayer(assetManager.get("assets/player_assets/right_movement_02.png"));
		Texture leftMovementTexture0 = TextureSizeManager.resizePlayer(assetManager.get("assets/player_assets/left_movement_00.png"));
		Texture leftMovementTexture1 = TextureSizeManager.resizePlayer(assetManager.get("assets/player_assets/left_movement_01.png"));
		Texture leftMovementTexture2 = TextureSizeManager.resizePlayer(assetManager.get("assets/player_assets/left_movement_02.png"));
		Texture upMovementTexture0 = TextureSizeManager.resizePlayer(assetManager.get("assets/player_assets/up_movement_00.png"));
		Texture upMovementTexture1 = TextureSizeManager.resizePlayer(assetManager.get("assets/player_assets/up_movement_01.png"));
		Texture upMovementTexture2 = TextureSizeManager.resizePlayer(assetManager.get("assets/player_assets/up_movement_02.png"));
		Texture downMovementTexture0 = TextureSizeManager.resizePlayer(assetManager.get("assets/player_assets/down_movement_00.png"));
		Texture downMovementTexture1 = TextureSizeManager.resizePlayer(assetManager.get("assets/player_assets/down_movement_01.png"));
		Texture downMovementTexture2 = TextureSizeManager.resizePlayer(assetManager.get("assets/player_assets/down_movement_02.png"));
		TextureRegion[] rightWalk = new TextureRegion[3];
		rightWalk[0] = new TextureRegion(rightMovementTexture0);
		rightWalk[1] = new TextureRegion(rightMovementTexture1);
		rightWalk[2] = new TextureRegion(rightMovementTexture2);
		TextureRegion[] leftWalk = new TextureRegion[3];
		leftWalk[0] = new TextureRegion(leftMovementTexture0);
		leftWalk[1] = new TextureRegion(leftMovementTexture1);
		leftWalk[2] = new TextureRegion(leftMovementTexture2);
		TextureRegion[] upWalk = new TextureRegion[3];
		upWalk[0] = new TextureRegion(upMovementTexture0);
		upWalk[1] = new TextureRegion(upMovementTexture1);
		upWalk[2] = new TextureRegion(upMovementTexture2);
		TextureRegion[] downWalk = new TextureRegion[3];
		downWalk[0] = new TextureRegion(downMovementTexture0);
		downWalk[1] = new TextureRegion(downMovementTexture1);
		downWalk[2] = new TextureRegion(downMovementTexture2);
		super.setTexture(mainPlayerTexture);
		super.setRightMovementAnimation(rightWalk);
		super.setLeftMovementAnimation(leftWalk);
		super.setUpMovementAnimation(upWalk);
		super.setDownMovementAnimation(downWalk);
	}
	
}
