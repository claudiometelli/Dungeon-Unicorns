package it.unicatt.poo.dungeonunicorns.graphics;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends Game {
	
	private static MainGame instance;
	
	private SpriteBatch batch;
	private BitmapFont font;
	
	private GameScreen gameScreen;
	private EnterMenuScreen enterMenuScreen;
	
	@Override
	public void create() {
		this.batch = new SpriteBatch();
		this.font = new BitmapFont();
		this.setScreen(new EnterMenuScreen(this));
	}
	
	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}
	
	public static MainGame getInstance() {
		if(instance == null) {
			instance = new MainGame();
		}
		return instance;
	}
	
	public SpriteBatch getBatch() {
		return batch;
	}
	
	public BitmapFont getFont() {
		return font;
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	
	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}
	
	public EnterMenuScreen getEnterMenuScreen() {
		return enterMenuScreen;
	}
	
	public void setEnterMenuScreen(EnterMenuScreen enterMenuScreen) {
		this.enterMenuScreen = enterMenuScreen;
	}
	
}
