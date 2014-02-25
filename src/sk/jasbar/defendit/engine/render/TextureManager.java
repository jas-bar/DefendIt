package sk.jasbar.defendit.engine.render;

import org.newdawn.slick.opengl.Texture;

public class TextureManager {
    private final Texture blocksTexture;
    private final int blocksX, blocksY;

    public TextureManager(Texture blocks, int blocksX, int blocksY) {
        blocksTexture = blocks;
        this.blocksX = blocksX;
        this.blocksY = blocksY;
    }

    public Rectangle2D getBounds(int btx, int bty) {
        Rectangle2D rect = new Rectangle2D();
        rect.w = 1-1.0f / (1f * blocksTexture.getWidth() / blocksX);
        rect.x = btx * rect.w;
        rect.h = 1-1.0f / (1f * blocksTexture.getHeight() / blocksY);
        rect.y = bty * rect.h;
        return rect;
    }
}
