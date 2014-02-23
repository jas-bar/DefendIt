package sk.jasbar.defendit.render;

import sk.jasbar.defendit.engine.render.BufferedRenderer;
import sk.jasbar.defendit.game.World;

public class BufferedBlocksRenderer extends BufferedRenderer implements IBlockRenderTarget {
    private static final float B = BlockRenderer.BLOCK_SIZE;

    public BufferedBlocksRenderer(int vertexesMode) {
        super(vertexesMode);
    }

    @Override
    public void renderBackFace(World world, float x, float y, float z) {
        addVertex(x,y+B,z);
        addVertex(x+B, y+B, z);
        addVertex(x+B,y,z);
        addVertex(x, y, z);
    }
    // TODO: skrecok
    @Override
    public void renderFrontFace(World world, float x, float y, float z) {
        /*vertices.put(x).put(y).put(z + d);
        vertices.put(x + w).put(y).put(z + d);
        vertices.put(x + w).put(y + h).put(z + d);
        vertices.put(x).put(y + h).put(z + d);*/
    }

    @Override
    public void renderLeftFace(World world, float x, float y, float z) {
        /*vertices.put(x).put(y).put(z);
        vertices.put(x).put(y).put(z + d);
        vertices.put(x).put(y + h).put(z + d);
        vertices.put(x).put(y + h).put(z);*/
    }

    @Override
    public void renderRightFace(World world, float x, float y, float z) {
        /*vertices.put(x + w).put(y + h).put(z);
        vertices.put(x + w).put(y + h).put(z + d);
        vertices.put(x + w).put(y).put(z + d);
        vertices.put(x + w).put(y).put(z);*/
    }

    @Override
    public void renderUpFace(World world, float x, float y, float z) {
        /*vertices.put(x).put(y + h).put(z);
        vertices.put(x).put(y + h).put(z + d);
        vertices.put(x + w).put(y + h).put(z + d);
        vertices.put(x + w).put(y + h).put(z);*/
        addVertex(x, y+B, z);
        addVertex(x, y+B, z+B);
        addVertex(x+B, y+B, z+B);
        addVertex(x+B, y+B, z);
    }

    @Override
    public void renderDownFace(World world, float x, float y, float z) {
        /*vertices.put(x + w).put(y).put(z);
        vertices.put(x + w).put(y).put(z + d);
        vertices.put(x).put(y).put(z + d);
        vertices.put(x).put(y).put(z);*/
    }

}
