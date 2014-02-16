package sk.jasbar.defendit.resources;

import java.io.File;
import java.io.FileInputStream;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class TextureResource extends RawResource {
    private final Texture texture;

    public TextureResource(File file) throws Exception {
        super(file);
        texture = TextureLoader.getTexture("png", new FileInputStream(file));
    }

    public Texture getTexture() {
        return texture;
    }

}
