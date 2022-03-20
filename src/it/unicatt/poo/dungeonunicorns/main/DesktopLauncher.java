package it.unicatt.poo.dungeonunicorns.main;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unicatt.poo.dungeonunicorns.exceptions.AttributeNotSpecifiedException;
import it.unicatt.poo.dungeonunicorns.graphics.MainGame;
import it.unicatt.poo.dungeonunicorns.utils.IOUtils;

/**
 * A class which represent the starting point of the application
 * It uses libGDX methods to make the desktop application starting
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public class DesktopLauncher {
	
	/**
	 * The path for where the screen configuration file is placed
	 */
	private final static String SCREEN_CONFIG_PATH = "configfiles/ScreenConfig.txt";
	
	/**
	 * Starting point of the graphic application
	 * 
	 */
	public static void main (String[] arg) {
		new LwjglApplication(MainGame.getInstance(), configLauncher());
	}
	
	/**
	 * 
	 * @return the LwjglApplicationConfiguration configured with the settings of SCREEN_CONFIG_PATH
	 */
	private static LwjglApplicationConfiguration configLauncher() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// reading file configuration from configuration  file
		try {
			config.title = IOUtils.getAttribute(SCREEN_CONFIG_PATH, "Title");;
		    config.width = IOUtils.getIntegerAttribute(SCREEN_CONFIG_PATH, "Width");
			config.height = IOUtils.getIntegerAttribute(SCREEN_CONFIG_PATH, "Height");
		} catch (AttributeNotSpecifiedException ansException) {
			System.err.println(ansException.getMessage());
		}
	    return config;
	}
}
