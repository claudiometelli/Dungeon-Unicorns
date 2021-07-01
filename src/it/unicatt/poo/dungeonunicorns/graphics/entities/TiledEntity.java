package it.unicatt.poo.dungeonunicorns.graphics.entities;

import java.nio.file.Paths;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import it.unicatt.poo.dungeonunicorns.core.Entity;
import it.unicatt.poo.dungeonunicorns.core.Player;
import it.unicatt.poo.dungeonunicorns.exceptions.AttributeNotSpecifiedException;
import it.unicatt.poo.dungeonunicorns.graphics.MainGame;
import it.unicatt.poo.dungeonunicorns.graphics.beans.TiledRoom;
import it.unicatt.poo.dungeonunicorns.utils.IOUtils;

public abstract class TiledEntity {
	
	private final static int NUMBER_OF_ANIMATIONS_DURING_MOVEMENT = 16;
	private final static float ANIMATION_TIME = IOUtils.getInstantFloatAttributeFromConfigFile(Paths.get("configfiles/ScaleConfig.txt"), "ANIMATION_TIME");
	
	
	private Entity entity;
	private Texture texture;
	private Animation<TextureRegion> rightMovementAnimation;
	private Animation<TextureRegion> leftMovementAnimation;
	private Animation<TextureRegion> upMovementAnimation;
	private Animation<TextureRegion> downMovementAnimation;
	private Animation<TextureRegion> stoppedAnimation;
	private Animation<TextureRegion> actualAnimation;
	private EntityDirection actualDirection;
	private Rectangle entityArea;
	private TiledRoom room;
	
	private boolean moving;
	private float arrivingPoint;
	
	public TiledEntity() {
		this.entityArea = new Rectangle();
		this.actualDirection = EntityDirection.NO_DIRECTION;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
		entityArea.width = texture.getWidth();
		entityArea.height = texture.getHeight();
		TextureRegion[] animation = new TextureRegion[1];
		animation[0] = new TextureRegion(texture);
		this.stoppedAnimation = new Animation<TextureRegion>(ANIMATION_TIME, animation);
		this.actualAnimation = stoppedAnimation;
	}
	
	public Animation<TextureRegion> getRightMovementAnimation() {
		return rightMovementAnimation;
	}
	
	public void setRightMovementAnimation(Animation<TextureRegion> rightMovementAnimation) {
		this.rightMovementAnimation = rightMovementAnimation;
	}
	
	public void setRightMovementAnimation(TextureRegion[] rightMovementAnimation) {
		this.rightMovementAnimation = new Animation<TextureRegion>(ANIMATION_TIME, rightMovementAnimation);
	}
	
	public Animation<TextureRegion> getLeftMovementAnimation() {
		return leftMovementAnimation;
	}
	
	public void setLeftMovementAnimation(Animation<TextureRegion> leftMovementAnimation) {
		this.leftMovementAnimation = leftMovementAnimation;
	}
	
	public void setLeftMovementAnimation(TextureRegion[] leftMovementAnimation) {
		this.leftMovementAnimation = new Animation<TextureRegion>(ANIMATION_TIME, leftMovementAnimation);
	}
	
	public Animation<TextureRegion> getUpMovementAnimation() {
		return upMovementAnimation;
	}
	
	public void setUpMovementAnimation(Animation<TextureRegion> upMovementAnimation) {
		this.upMovementAnimation = upMovementAnimation;
	}
	
	public void setUpMovementAnimation(TextureRegion[] upMovementAnimation) {
		this.upMovementAnimation = new Animation<TextureRegion>(ANIMATION_TIME, upMovementAnimation);
	}
	
	public Animation<TextureRegion> getDownMovementAnimation() {
		return downMovementAnimation;
	}
	
	public void setDownMovementAnimation(Animation<TextureRegion> downMovementAnimation) {
		this.downMovementAnimation = downMovementAnimation;
	}
	
	public void setDownMovementAnimation(TextureRegion[] downMovementAnimation) {
		this.downMovementAnimation = new Animation<TextureRegion>(ANIMATION_TIME, downMovementAnimation);
	}
	
	public Animation<TextureRegion> getStoppedAnimation() {
		return stoppedAnimation;
	}
	
	public void setStoppedAnimation(Animation<TextureRegion> stoppedTexture) {
		this.stoppedAnimation = stoppedTexture;
	}
	
	public void setStoppedAnimation(TextureRegion[] stoppedTexture) {
		this.stoppedAnimation = new Animation<TextureRegion>(ANIMATION_TIME, stoppedTexture);
	}
	
	public Animation<TextureRegion> getActualAnimation() {
		return actualAnimation;
	}
	
	public void setActualAnimation(Animation<TextureRegion> actualAnimation) {
		this.actualAnimation = actualAnimation;
	}
	
	public EntityDirection getActualDirection() {
		return actualDirection;
	}
	
	public void setActualDirection(EntityDirection actualDirection) {
		this.actualDirection = actualDirection;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public Rectangle getEntityArea() {
		return entityArea;
	}
	
	public float getXPositionEntityArea() {
		return entityArea.x;
	}
	
	public void setXPositionEntityArea(float x) {
		entityArea.x = x;
	}
	
	public float getYPositionEntityArea() {
		return entityArea.y;
	}
	
	public void setYPositionEntityArea(float y) {
		entityArea.y = y;
	}
	
	public void setPositionEntityArea(float x, float y) {
		entityArea.x = x;
		entityArea.y = y;
	}
	
	public float getEntityAreaWidth() {
		return entityArea.width;
	}
	
	public void setEntityAreaWidth(float width) {
		entityArea.width = width;
	}
	
	public float getEntityAreaHeight() {
		return entityArea.height;
	}
	
	public void setEntityAreaHeight(float height) {
		entityArea.height = height;
	}
	
	public TiledRoom getRoom() {
		return room;
	}
	
	public void setRoom(TiledRoom room) {
		this.room = room;
	}
	
	public boolean isMoving() {
		return moving;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public void placeEntity(TiledRoom room, int x, int y) {
		this.room = room;
		float coordinateSize = MainGame.getInstance().getGameScreen().getCoordinateSize();
		setPositionEntityArea(x * coordinateSize, y * coordinateSize);
		entity.placeEntity(room.getRoom(), x, y);
	}
	
	public boolean moveRight() {
		boolean result = entity.canMoveRight();
		if(!moving && result) {
			arrivingPoint = entityArea.x + MainGame.getInstance().getGameScreen().getCoordinateSize();
			actualDirection = EntityDirection.RIGHT;
			moving = true;
		}
		if(result) {
			entityArea.x += (MainGame.getInstance().getGameScreen().getCoordinateSize() / NUMBER_OF_ANIMATIONS_DURING_MOVEMENT);
			if(entityArea.x >= arrivingPoint) {
				entityArea.x = arrivingPoint;
				entity.moveRight();
				actualDirection = EntityDirection.NO_DIRECTION;
				moving = false;
			}
		}
		return result;
	}
	
	public boolean moveLeft() {
		boolean result = entity.canMoveLeft();
		if(!moving && result) {
			arrivingPoint = entityArea.x - MainGame.getInstance().getGameScreen().getCoordinateSize();
			actualDirection = EntityDirection.LEFT;
			moving = true;
		}
		if(result) {
			entityArea.x -= (MainGame.getInstance().getGameScreen().getCoordinateSize() / NUMBER_OF_ANIMATIONS_DURING_MOVEMENT);
			if(entityArea.x <= arrivingPoint) {
				entityArea.x = arrivingPoint;
				entity.moveLeft();
				actualDirection = EntityDirection.NO_DIRECTION;
				moving = false;
			}
		}
		return result;
	}
	
	public boolean moveUp() {
		boolean result = entity.canMoveUp();
		if(!moving && result) {
			arrivingPoint = entityArea.y + MainGame.getInstance().getGameScreen().getCoordinateSize();
			actualDirection = EntityDirection.UP;
			moving = true;
		}
		if(result) {
			entityArea.y += (MainGame.getInstance().getGameScreen().getCoordinateSize() / NUMBER_OF_ANIMATIONS_DURING_MOVEMENT);
			if(entityArea.y >= arrivingPoint) {
				entityArea.y = arrivingPoint;
				entity.moveUp();
				actualDirection = EntityDirection.NO_DIRECTION;
				moving = false;
			}
		}
		return result;
	}
	
	public boolean moveDown() {
		boolean result = entity.canMoveDown();
		if(!moving && result) {
			arrivingPoint = entityArea.y - MainGame.getInstance().getGameScreen().getCoordinateSize();
			actualDirection = EntityDirection.DOWN;
			moving = true;
		}
		if(result) {
			entityArea.y -= (MainGame.getInstance().getGameScreen().getCoordinateSize() / NUMBER_OF_ANIMATIONS_DURING_MOVEMENT);
			if(entityArea.y <= arrivingPoint) {
				entityArea.y = arrivingPoint;
				entity.moveDown();
				actualDirection = EntityDirection.NO_DIRECTION;
				moving = false;
			}
		}
		return result;
	}
}
