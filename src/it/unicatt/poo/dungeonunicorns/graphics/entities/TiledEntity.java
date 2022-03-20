package it.unicatt.poo.dungeonunicorns.graphics.entities;

import java.nio.file.Paths;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import it.unicatt.poo.dungeonunicorns.beans.Coordinate;
import it.unicatt.poo.dungeonunicorns.beans.enums.EntityDirection;
import it.unicatt.poo.dungeonunicorns.core.Entity;
import it.unicatt.poo.dungeonunicorns.exceptions.AttributeNotSpecifiedException;
import it.unicatt.poo.dungeonunicorns.graphics.MainGame;
import it.unicatt.poo.dungeonunicorns.graphics.beans.TiledCoordinate;
import it.unicatt.poo.dungeonunicorns.graphics.beans.TiledRoom;
import it.unicatt.poo.dungeonunicorns.utils.IOUtils;

/**
 * A class which describe the game entities as we will see them in the game
 * 
 * @author elisamangiavacca
 * @version 1.0.0
 *
 */
public abstract class TiledEntity {
	
	final static int NUMBER_OF_ANIMATIONS_DURING_MOVEMENT = 16;
	static float ANIMATION_TIME = 0;
	static {
		try{
			IOUtils.getFloatAttribute("configfiles/ScaleConfig.txt", "ANIMATION_TIME");
		} catch(AttributeNotSpecifiedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	
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
	private TiledCoordinate coordinate;
	
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
	
	public TiledCoordinate getCoordinate() {
		return coordinate;
	}
	
	public void setCoordinate(TiledCoordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public boolean isMoving() {
		return moving;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public float getArrivingPoint() {
		return arrivingPoint;
	}
	
	public void setArrivingPoint(float arrivingPoint) {
		this.arrivingPoint = arrivingPoint;
	}
	
	public void placeEntity(TiledRoom room, int x, int y) {
		placeEntity(room, room.getCoordinateByPosition(x, y));
	}
	
	public void placeEntity(TiledRoom room, TiledCoordinate coordinate) {
		this.room = room;
		this.coordinate = coordinate;
		float coordinateSize = MainGame.getInstance().getGameScreen().getCoordinateSize();
		setPositionEntityArea(coordinate.getCoordinate().getX() * coordinateSize, (room.getRoom().getHeight() - coordinate.getCoordinate().getY()) * coordinateSize);
		entity.placeEntity(room.getRoom(), coordinate.getCoordinate());
	}
	
	public boolean isInRoom(TiledRoom room) {
		return this.room.getRoom().equals(room.getRoom());
	}
	
	public void deleteEntity() {
		coordinate.setEntity(null);
		entity.getCurrentPosition().setEntity(null);
		texture.dispose();
		entityArea = null;
	}
	
	protected abstract void readTexturesAndAssign();
}
