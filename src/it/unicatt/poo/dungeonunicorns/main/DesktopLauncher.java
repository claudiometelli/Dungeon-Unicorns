package it.unicatt.poo.dungeonunicorns.main;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unicatt.poo.dungeonunicorns.core.support.Constants;
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
	//private final static String SCREEN_CONFIG_PATH = "configfiles/ScreenConfig.txt";
	
	/**
	 * Starting point of the graphic application
	 * 
	 */
	public static void main (String[] arg) {
		new LwjglApplication(MainGame.getInstance(), configLauncher());
	}
	
	/**
	 * 
	 * @return the LwjglApplicationConfiguration configured with the sedttings of SCREEN_CONFIG_PATH
	 */
	private static LwjglApplicationConfiguration configLauncher() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// reading file configuration from configuration  file
		try {
			config.title = IOUtils.getAttribute(Constants.CONFIG_DIRECTORY+File.separator+Constants.SCREEN_CONFIG_FILE_NAME, "Title");;
		    config.width = IOUtils.getIntegerAttribute(Constants.CONFIG_DIRECTORY+File.separator+Constants.SCREEN_CONFIG_FILE_NAME, "Width");
			config.height = IOUtils.getIntegerAttribute(Constants.CONFIG_DIRECTORY+File.separator+Constants.SCREEN_CONFIG_FILE_NAME, "Height");
		} catch (AttributeNotSpecifiedException ansException) {
			System.err.println(ansException.getMessage());
		}
	    return config;
	}
}
