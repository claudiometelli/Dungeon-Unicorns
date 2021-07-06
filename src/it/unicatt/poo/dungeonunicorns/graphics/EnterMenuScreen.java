package it.unicatt.poo.dungeonunicorns.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import it.unicatt.poo.dungeonunicorns.graphics.levels.LevelOne;
import it.unicatt.poo.dungeonunicorns.graphics.levels.LevelTwo;

/**
 * A class that launches the game's enter menu screen on the screen
 * 
 * @author elisamangiavacca
 * @version 1.0.0
 *
 */
public class EnterMenuScreen implements Screen {
	
	private final MainGame game;
	
	private OrthographicCamera camera;
	private Texture backgroundTexture;
	
	/* The image is loaded as a screen*/
	public EnterMenuScreen(final MainGame game) {
		this.game = game;
		game.setEnterMenuScreen(this);
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false);
		backgroundTexture = new Texture("images/EnterScreenImage.png");
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	/* In this method we define the menu screen,
	 * when the menu opens there is a choice to be made: play level 1 or level 2 */
	@Override
	public void render(float delta) {
		camera.update();
		ScreenUtils.clear(0.5f, 0, 0.2f, 1);
		game.getBatch().setProjectionMatrix(camera.combined);
		game.getBatch().begin();
		game.getBatch().draw(backgroundTexture, 0, 0);
		// TODO cambiare il font e il colore della scritta
		game.getFont().draw(game.getBatch(), "Press 1 for level 1\nPress 2 for level 2", (Gdx.graphics.getWidth() / 5) * 4, 50);
		game.getBatch().end();
		
		// if the choice is 1:
		if(Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
			GameScreen gameScreen = new GameScreen(game);
			gameScreen.setLevel(new LevelOne());
			game.setScreen(gameScreen);
			dispose();
		}
		
		// if the choice is 2:
		if(Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
			GameScreen gameScreen = new GameScreen(game);
			gameScreen.setLevel(new LevelTwo());
			game.setScreen(gameScreen);
			dispose();
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
		
	}

}
