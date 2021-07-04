package it.unicatt.poo.dungeonunicorns.graphics.entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import it.unicatt.poo.dungeonunicorns.core.Player;
import it.unicatt.poo.dungeonunicorns.managers.TextureSizeManager;

public class TiledPlayer extends TiledEntity {
	
	public TiledPlayer() {
		super();
		super.setEntity(new Player());
		readTexturesAndAssign();
	}
	
	public Player getPlayer() {
		return (Player) super.getEntity();
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
