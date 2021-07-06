package it.unicatt.poo.dungeonunicorns.managers;

import java.nio.file.Paths;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import it.unicatt.poo.dungeonunicorns.exceptions.AttributeNotSpecifiedException;
import it.unicatt.poo.dungeonunicorns.utils.IOUtils;

/**
 * A class used to manage the resizing of textures across the whole application
 * 
 * @author claudiometelli
 * @version 1.0.0
 *
 */
public class TextureSizeManager {
	
	/**
	 * A method used to resize textures
	 * 
	 * @param texture - the texture to be resized
	 * @param multiplier - the multiplier parameter used to resize the texture
	 * @return the resized texture
	 */
	public static Texture resizeTexture(Texture texture, Float multiplier) {
		Texture result = null;
		if(!texture.getTextureData().isPrepared()) {
			texture.getTextureData().prepare();
		}
		Pixmap texturePixmap = texture.getTextureData().consumePixmap();
		Pixmap resultPixmap = new Pixmap((int)(texture.getWidth() * multiplier), (int)(texture.getHeight() * multiplier), texturePixmap.getFormat());
		resultPixmap.drawPixmap(texturePixmap,
		        0, 0, texturePixmap.getWidth(), texturePixmap.getHeight(),
		        0, 0, resultPixmap.getWidth(), resultPixmap.getHeight()
		);
		result = new Texture(resultPixmap);
		texturePixmap.dispose();
		resultPixmap.dispose();
		return result;
	}
	
	/**
	 * A method used to resize player texture reading multiplier from file
	 * 
	 * @param texture - the player texture to be resized
	 * @return the resized player texture
	 */
	public static Texture resizePlayer(Texture texture) {
		Float multiplier = null;
		try {
			multiplier = IOUtils.getFloatAttributeFromConfigFile(Paths.get("configfiles/ScaleConfig.txt"), "PLAYER_MULTIPLIER");
		} catch (AttributeNotSpecifiedException e) {
			System.err.println(e.getMessage());
		}
		return resizeTexture(texture, multiplier);
	}
	
	/**
	 * A method used to resize monster texture reading multiplier from file
	 * 
	 * @param texture - the monster texture to be resized
	 * @return the resized monster texture
	 */
	public static Texture resizeMonster(Texture texture) {
		Float multiplier = null;
		try {
			multiplier = IOUtils.getFloatAttributeFromConfigFile(Paths.get("configfiles/ScaleConfig.txt"), "MONSTER_MULTIPLIER");
		} catch (AttributeNotSpecifiedException e) {
			System.err.println(e.getMessage());
		}
		return resizeTexture(texture, multiplier);
	}
	
}
