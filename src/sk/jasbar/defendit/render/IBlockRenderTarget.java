package sk.jasbar.defendit.render;

import sk.jasbar.defendit.game.World;

public interface IBlockRenderTarget {

    public void renderBackFace(World world, int btx, int bty, float x, float y, float z);

    public void renderFrontFace(World world, int btx, int bty, float x, float y, float z);

    public void renderLeftFace(World world, int btx, int bty, float x, float y, float z);

    public void renderRightFace(World world, int btx, int bty, float x, float y, float z);

    public void renderUpFace(World world, int btx, int bty, float x, float y, float z);

    public void renderDownFace(World world, int btx, int bty, float x, float y, float z);
}
