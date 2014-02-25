package sk.jasbar.defendit.render;

import sk.jasbar.defendit.engine.render.Rectangle2D;
import sk.jasbar.defendit.game.World;

public interface IBlockRenderTarget {

    public void renderBackFace(World world, Rectangle2D texture, float x, float y, float z);

    public void renderFrontFace(World world, Rectangle2D texture, float x, float y, float z);

    public void renderLeftFace(World world, Rectangle2D texture, float x, float y, float z);

    public void renderRightFace(World world, Rectangle2D texture, float x, float y, float z);

    public void renderUpFace(World world, Rectangle2D texture, float x, float y, float z);

    public void renderDownFace(World world, Rectangle2D texture, float x, float y, float z);
}
