package it.unicatt.poo.dungeonunicorns.graphics.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import it.unicatt.poo.dungeonunicorns.core.Entity;
import it.unicatt.poo.dungeonunicorns.core.Player;
import it.unicatt.poo.dungeonunicorns.graphics.MainGame;
import it.unicatt.poo.dungeonunicorns.graphics.beans.TiledRoom;

public abstract class TiledEntity {
	
	Entity entity;
	Texture texture;
	Rectangle entityArea;
	TiledRoom room;
	
	public TiledEntity(Texture texture) {
		this.texture = texture;
		this.entityArea = new Rectangle();
		this.entity = new Player();
		entityArea.width = texture.getWidth();
		entityArea.height = texture.getHeight();
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
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
	
	public void placeEntity(TiledRoom room, int x, int y) {
		float coordinateSize = MainGame.getInstance().getGameScreen().getCoordinateSize();
		setPositionEntityArea(x * coordinateSize, y * coordinateSize);
		entity.placeEntity(room.getRoom(), x, y);
	}
	
	public boolean moveRight() {
		boolean result = entity.moveRight();
		if(result) {
			entityArea.x += MainGame.getInstance().getGameScreen().getCoordinateSize();
		}
		return result;
	}
	
	public boolean moveLeft() {
		boolean result = entity.moveLeft();
		if(result) {
			entityArea.x -= MainGame.getInstance().getGameScreen().getCoordinateSize();
		}
		return result;
	}
	
	public boolean moveUp() {
		boolean result = entity.moveUp();
		if(result) {
			entityArea.y += MainGame.getInstance().getGameScreen().getCoordinateSize();
		}
		return result;
	}
	
	public boolean moveDown() {
		boolean result = entity.moveDown();
		if(result) {
			entityArea.y -= MainGame.getInstance().getGameScreen().getCoordinateSize();
		}
		return result;
	}
}
