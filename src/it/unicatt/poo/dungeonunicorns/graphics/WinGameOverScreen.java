package it.unicatt.poo.dungeonunicorns.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * A class that throws a different screen
 * depending on whether the user has won or lost
 * 
 * @author elisamangiavacca
 * @version 1.0.0
 *
 */
public class WinGameOverScreen  implements Screen {
	
	private final MainGame game;
	
	private OrthographicCamera camera;
	private Texture backgroundTexture;
	private Music music;
	
	private boolean win;
	
	public WinGameOverScreen (final MainGame game, boolean win) {
		this.game = game;
		this.win = win;
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false);
		AssetManager manager = new AssetManager();
		// if win: YOU WIN!
		if(win) {
			manager.load("images/Win.png", Texture.class);
			manager.load("assets/music/win_music.mp3", Music.class);
			manager.finishLoading();
			backgroundTexture = manager.get("images/Win.png");
			music = manager.get("assets/music/win_music.mp3");
		// else: GAME OVER!
		} else {
			manager.load("images/GameOver.png", Texture.class);
			manager.load("assets/music/game_over_music.mp3", Music.class);
			manager.finishLoading();
			backgroundTexture = manager.get("images/GameOver.png");
			music = manager.get("assets/music/game_over_music.mp3");
		}
		music.play();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	/*In this method the user can choose whether to quit or play again*/
	@Override
	public void render(float delta) {
		camera.update();
		ScreenUtils.clear(0, 0, 0, 1);
		game.getBatch().setProjectionMatrix(camera.combined);
		game.getBatch().begin();
		game.getBatch().draw(backgroundTexture, 0, 0);
		game.getFont().draw(game.getBatch(), "Press ENTER to start another game\nPress ESC to quit", (Gdx.graphics.getWidth() / 5) * 4, 50);
		game.getBatch().end();

		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			EnterMenuScreen gameScreen = new EnterMenuScreen(game);
			game.setScreen(gameScreen);
			dispose();
		}
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			dispose();
			Gdx.app.exit();
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
		backgroundTexture.dispose();
		music.dispose();
	}

}
