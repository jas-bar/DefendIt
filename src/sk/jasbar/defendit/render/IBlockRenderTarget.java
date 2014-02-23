package sk.jasbar.defendit.render;

import sk.jasbar.defendit.game.World;

public interface IBlockRenderTarget {
   
    public void renderBackFace(World world, float x, float y, float z);

    public void renderFrontFace(World world, float x, float y, float z);

    public void renderLeftFace(World world, float x, float y, float z);

    public void renderRightFace(World world, float x, float y, float z);

    public void renderUpFace(World world, float x, float y, float z);

    public void renderDownFace(World world, float x, float y, float z);
}
