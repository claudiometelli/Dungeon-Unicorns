package it.unicatt.poo.dungeonunicorns.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import it.unicatt.poo.dungeonunicorns.core.Test;

public class EnterMenuScreen implements Screen {
	
	private final Test game;
	private OrthographicCamera camera;
	private Texture backgroundTexture;
	
	public EnterMenuScreen(Test game) {
		this.game = game;
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false);
		backgroundTexture = new Texture("images/EnterScreenImage.png");
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		camera.update();
		ScreenUtils.clear(0.5f, 0, 0.2f, 1);
		game.getBatch().setProjectionMatrix(camera.combined);
		game.getBatch().begin();
		game.getBatch().draw(backgroundTexture, 0, 0);
		// TODO cambiare il font e il colore della scritta
		game.getFont().draw(game.getBatch(), "Tap anywhere to begin!", (Gdx.graphics.getWidth() / 5) * 4, 50);
		game.getBatch().end();

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
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
		// TODO Auto-generated method stub
		
	}

}
