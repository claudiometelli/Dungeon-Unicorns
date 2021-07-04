package it.unicatt.poo.dungeonunicorns.graphics.entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import it.unicatt.poo.dungeonunicorns.core.EntityDirection;
import it.unicatt.poo.dungeonunicorns.core.Monster;
import it.unicatt.poo.dungeonunicorns.core.Player;
import it.unicatt.poo.dungeonunicorns.graphics.MainGame;
import it.unicatt.poo.dungeonunicorns.managers.TextureSizeManager;

public class TiledMonster extends TiledEntity {
	
	public TiledMonster(TiledPlayer player) {
		super();
		super.setEntity(new Monster(player.getPlayer()));
		readTexturesAndAssign();
	}
	
	public Monster getMonster() {
		return (Monster) super.getEntity();
	}
	
	public boolean isNextMoveMoving() {
		boolean result = false;
		if(!getMonster().getIntelligence().isGoingToMove().equals(EntityDirection.NO_DIRECTION)){
			result = true;
		}
		return result;
	}
	
	public EntityDirection nextMove() {
		EntityDirection result = getMonster().getIntelligence().isGoingToMove();
		if(!super.isMoving() && !result.equals(EntityDirection.NO_DIRECTION)) {
			if(result.equals(EntityDirection.RIGHT)) {
				super.setArrivingPoint(super.getXPositionEntityArea() + MainGame.getInstance().getGameScreen().getCoordinateSize());
			} else if(result.equals(EntityDirection.LEFT)) {
				super.setArrivingPoint(super.getXPositionEntityArea() - MainGame.getInstance().getGameScreen().getCoordinateSize());
			} else if(result.equals(EntityDirection.UP)) {
				super.setArrivingPoint(super.getYPositionEntityArea() + MainGame.getInstance().getGameScreen().getCoordinateSize());
			} else if(result.equals(EntityDirection.DOWN)) {
				super.setArrivingPoint(super.getYPositionEntityArea() - MainGame.getInstance().getGameScreen().getCoordinateSize());
			}
			super.setMoving(true);
		} else if(!super.isMoving() && result.equals(EntityDirection.NO_DIRECTION)){
			getMonster().getIntelligence().nextMove();
		}
		if(!result.equals(EntityDirection.NO_DIRECTION)) {
			if(result.equals(EntityDirection.RIGHT)) {
				super.setXPositionEntityArea(super.getXPositionEntityArea() + MainGame.getInstance().getGameScreen().getCoordinateSize() / NUMBER_OF_ANIMATIONS_DURING_MOVEMENT);
				if(super.getXPositionEntityArea() >= super.getArrivingPoint()) {
					super.setArrivingPoint(super.getXPositionEntityArea());
					getMonster().getIntelligence().nextMove();
					super.setActualDirection(EntityDirection.NO_DIRECTION);
					super.setMoving(false);
				}
			} else if(result.equals(EntityDirection.LEFT)) {
				super.setXPositionEntityArea(super.getXPositionEntityArea() - MainGame.getInstance().getGameScreen().getCoordinateSize() / NUMBER_OF_ANIMATIONS_DURING_MOVEMENT);
				if(super.getXPositionEntityArea() <= super.getArrivingPoint()) {
					super.setArrivingPoint(super.getXPositionEntityArea());
					//super.getEntity().moveLeft();
					getMonster().getIntelligence().nextMove();
					super.setActualDirection(EntityDirection.NO_DIRECTION);
					super.setMoving(false);
				}
			} else if(result.equals(EntityDirection.UP)) {
				super.setYPositionEntityArea(super.getYPositionEntityArea() + MainGame.getInstance().getGameScreen().getCoordinateSize() / NUMBER_OF_ANIMATIONS_DURING_MOVEMENT);
				if(super.getYPositionEntityArea() >= super.getArrivingPoint()) {
					super.setArrivingPoint(super.getYPositionEntityArea());
					getMonster().getIntelligence().nextMove();
					//super.getEntity().moveUp();
					super.setActualDirection(EntityDirection.NO_DIRECTION);
					super.setMoving(false);
				}
			} else if(result.equals(EntityDirection.DOWN)) {
				super.setYPositionEntityArea(super.getYPositionEntityArea() - MainGame.getInstance().getGameScreen().getCoordinateSize() / NUMBER_OF_ANIMATIONS_DURING_MOVEMENT);
				if(super.getYPositionEntityArea() <= super.getArrivingPoint()) {
					super.setArrivingPoint(super.getYPositionEntityArea());
					getMonster().getIntelligence().nextMove();
					//super.getEntity().moveDown();
					super.setActualDirection(EntityDirection.NO_DIRECTION);
					super.setMoving(false);
				}
			}
		}
		return result;
	}

	@Override
	protected void readTexturesAndAssign() {
		AssetManager assetManager = new AssetManager();
		assetManager.load("assets/monster_assets/monster_not_moving.png", Texture.class);
		assetManager.load("assets/monster_assets/right_movement_00.png", Texture.class);
		assetManager.load("assets/monster_assets/right_movement_01.png", Texture.class);
		assetManager.load("assets/monster_assets/right_movement_02.png", Texture.class);
		assetManager.load("assets/monster_assets/right_movement_03.png", Texture.class);
		assetManager.load("assets/monster_assets/up_movement_00.png", Texture.class);
		assetManager.load("assets/monster_assets/up_movement_01.png", Texture.class);
		assetManager.load("assets/monster_assets/up_movement_02.png", Texture.class);
		assetManager.load("assets/monster_assets/up_movement_03.png", Texture.class);
		assetManager.load("assets/monster_assets/down_movement_00.png", Texture.class);
		assetManager.load("assets/monster_assets/down_movement_01.png", Texture.class);
		assetManager.load("assets/monster_assets/down_movement_02.png", Texture.class);
		assetManager.load("assets/monster_assets/down_movement_03.png", Texture.class);
		assetManager.finishLoading();
		Texture mainMonsterTexture = TextureSizeManager.resizeMonster(assetManager.get("assets/monster_assets/monster_not_moving.png"));
		Texture rightMovementTexture0 = TextureSizeManager.resizeMonster(assetManager.get("assets/monster_assets/right_movement_00.png"));
		Texture rightMovementTexture1 = TextureSizeManager.resizeMonster(assetManager.get("assets/monster_assets/right_movement_01.png"));
		Texture rightMovementTexture2 = TextureSizeManager.resizeMonster(assetManager.get("assets/monster_assets/right_movement_02.png"));
		Texture rightMovementTexture3 = TextureSizeManager.resizeMonster(assetManager.get("assets/monster_assets/right_movement_03.png"));
		Texture upMovementTexture0 = TextureSizeManager.resizeMonster(assetManager.get("assets/monster_assets/up_movement_00.png"));
		Texture upMovementTexture1 = TextureSizeManager.resizeMonster(assetManager.get("assets/monster_assets/up_movement_01.png"));
		Texture upMovementTexture2 = TextureSizeManager.resizeMonster(assetManager.get("assets/monster_assets/up_movement_02.png"));
		Texture upMovementTexture3 = TextureSizeManager.resizeMonster(assetManager.get("assets/monster_assets/up_movement_03.png"));
		Texture downMovementTexture0 = TextureSizeManager.resizeMonster(assetManager.get("assets/monster_assets/down_movement_00.png"));
		Texture downMovementTexture1 = TextureSizeManager.resizeMonster(assetManager.get("assets/monster_assets/down_movement_01.png"));
		Texture downMovementTexture2 = TextureSizeManager.resizeMonster(assetManager.get("assets/monster_assets/down_movement_02.png"));
		Texture downMovementTexture3 = TextureSizeManager.resizeMonster(assetManager.get("assets/monster_assets/down_movement_03.png"));
		TextureRegion[] rightWalk = new TextureRegion[4];
		rightWalk[0] = new TextureRegion(rightMovementTexture0);
		rightWalk[1] = new TextureRegion(rightMovementTexture1);
		rightWalk[2] = new TextureRegion(rightMovementTexture2);
		rightWalk[3] = new TextureRegion(rightMovementTexture3);
		TextureRegion[] leftWalk = new TextureRegion[4];
		leftWalk[0] = new TextureRegion(rightWalk[0]);
		leftWalk[1] = new TextureRegion(rightWalk[1]);
		leftWalk[2] = new TextureRegion(rightWalk[2]);
		leftWalk[3] = new TextureRegion(rightWalk[3]);
		for(TextureRegion textureRegion : leftWalk) {
			textureRegion.flip(true, false);
		}
		TextureRegion[] upWalk = new TextureRegion[4];
		upWalk[0] = new TextureRegion(upMovementTexture0);
		upWalk[1] = new TextureRegion(upMovementTexture1);
		upWalk[2] = new TextureRegion(upMovementTexture2);
		upWalk[2] = new TextureRegion(upMovementTexture3);
		TextureRegion[] downWalk = new TextureRegion[4];
		downWalk[0] = new TextureRegion(downMovementTexture0);
		downWalk[1] = new TextureRegion(downMovementTexture1);
		downWalk[2] = new TextureRegion(downMovementTexture2);
		downWalk[3] = new TextureRegion(downMovementTexture3);
		super.setTexture(mainMonsterTexture);
		super.setRightMovementAnimation(rightWalk);
		super.setLeftMovementAnimation(leftWalk);
		super.setUpMovementAnimation(upWalk);
		super.setDownMovementAnimation(downWalk);
	}
	
	
}
