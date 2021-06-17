package it.unicatt.poo.dungeonunicorns.core;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unicatt.poo.dungeonunicorns.exceptions.AttributeNotSpecifiedException;
import it.unicatt.poo.dungeonunicorns.utils.IOUtils;

public class DesktopLauncher {
	
	private final static String SCREEN_CONFIG_PATH = "configfiles/ScreenConfig.txt";
	
	public static void main (String[] arg) {
		new LwjglApplication(new Test(), configLauncher());
	}
	
	private static LwjglApplicationConfiguration configLauncher() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// reading file configuration from config file
		Path configFile = Paths.get(SCREEN_CONFIG_PATH);
		try {
			config.title = IOUtils.getAttributeFromConfigFile(configFile, "Title");;
		    config.width = IOUtils.getIntegerAttributeFromConfigFile(configFile, "Width");
			config.height = IOUtils.getIntegerAttributeFromConfigFile(configFile, "Height");
		} catch (AttributeNotSpecifiedException ansException) {
			System.err.println(ansException.getMessage());
		}
	    return config;
	}
}
