package it.unicatt.poo.dungeonunicorns.graphics.entities;

import com.badlogic.gdx.graphics.Texture;

import it.unicatt.poo.dungeonunicorns.core.Player;

public class TiledPlayer extends TiledEntity {
	
	public TiledPlayer(Texture texture) {
		super(texture);
		super.setEntity(new Player());
	}
	
}
