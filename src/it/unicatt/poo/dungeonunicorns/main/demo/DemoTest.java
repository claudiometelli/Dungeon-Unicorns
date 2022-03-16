package it.unicatt.poo.dungeonunicorns.main.demo;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * A simple textual demo to check if business logic tier is working
 * 
 * @author claudiometelli
 *
 */
public class DemoTest {
	
	public static void main(String[] args) {
		new LwjglApplication(new DemoGame(), configLauncher());
	}
	
	private static LwjglApplicationConfiguration configLauncher() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    config.width = 0;
		config.height = 0;
	    return config;
	}
	
}
