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
 *
 */
public class TextureSizeManager {
	
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
	
	public static Texture resizePlayer(Texture texture) {
		Float multiplier = null;
		try {
			multiplier = IOUtils.getFloatAttributeFromConfigFile(Paths.get("configfiles/ScaleConfig.txt"), "PLAYER_MULTIPLIER");
		} catch (AttributeNotSpecifiedException e) {
			System.err.println(e.getMessage());
		}
		return resizeTexture(texture, multiplier);
	}
	
}
