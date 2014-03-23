package sk.jasbar.defendit.render;

import sk.jasbar.defendit.engine.render.BufferedRenderer;
import sk.jasbar.defendit.engine.render.Rectangle2D;
import sk.jasbar.defendit.game.World;

public class BufferedBlocksRenderer extends BufferedRenderer implements IBlockRenderTarget {
    private static final float B = BlockRenderer.BLOCK_SIZE;
    private final Object lock = new Object();
    
    public BufferedBlocksRenderer(int vertexesMode) {
        super(vertexesMode);
    }

    @Override
    public synchronized void renderBackFace(World world, Rectangle2D texture, float x, float y, float z) {
            addNormal(0,0,-1);addTextureCoord(texture.x, texture.y+texture.h);addVertex(x,y+B,z);
            addNormal(0,0,-1);addTextureCoord(texture.x+texture.h, texture.y+texture.h);addVertex(x+B, y+B, z);
            addNormal(0,0,-1);addTextureCoord(texture.x+texture.w, texture.y);addVertex(x+B,y,z);
            addNormal(0,0,-1);addTextureCoord(texture.x, texture.y);addVertex(x, y, z);
    }

    @Override
    public synchronized void renderFrontFace(World world,Rectangle2D texture, float x, float y, float z) {
            addNormal(0,0,1);addTextureCoord(texture.x, texture.y);addVertex(x,y,z+B);
            addNormal(0,0,1);addTextureCoord(texture.x+texture.w, texture.y);addVertex(x+B, y, z+B);
            addNormal(0,0,1);addTextureCoord(texture.x+texture.w, texture.y+texture.h);addVertex(x+B,y+B,z+B);
            addNormal(0,0,1);addTextureCoord(texture.x, texture.y+texture.h);addVertex(x, y+B, z+B);
    }

    @Override
    public synchronized void renderLeftFace(World world,Rectangle2D texture, float x, float y, float z) {
            addNormal(-1,0,0);addTextureCoord(texture.x, texture.y);addVertex(x,y,z);
            addNormal(-1,0,0);addTextureCoord(texture.x, texture.y+texture.h);addVertex(x, y, z+B);
            addNormal(-1,0,0);addTextureCoord(texture.x+texture.w, texture.y+texture.h);addVertex(x,y+B,z+B);
            addNormal(-1,0,0);addTextureCoord(texture.x+texture.w, texture.y);addVertex(x, y+B, z);
    }

    @Override
    public synchronized void renderRightFace(World world,Rectangle2D texture, float x, float y, float z) {
            addNormal(1,0,0);addTextureCoord(texture.x, texture.y+texture.h);addVertex(x+B,y+B,z);
            addNormal(1,0,0);addTextureCoord(texture.x+texture.w, texture.y+texture.h);addVertex(x+B, y+B, z+B);
            addNormal(1,0,0);addTextureCoord(texture.x+texture.w, texture.y);addVertex(x+B,y,z+B);
            addNormal(1,0,0);addTextureCoord(texture.x, texture.y);addVertex(x+B, y, z);
    }

    @Override
    public synchronized void renderUpFace(World world,Rectangle2D texture, float x, float y, float z) {
            addNormal(0,1,0);addTextureCoord(texture.x, texture.y);addVertex(x, y+B, z);
            addNormal(0,1,0);addTextureCoord(texture.x, texture.y+texture.h);addVertex(x, y+B, z+B);
            addNormal(0,1,0);addTextureCoord(texture.x+texture.w, texture.y+texture.h);addVertex(x+B, y+B, z+B);
            addNormal(0,1,0);addTextureCoord(texture.x+texture.w, texture.y);addVertex(x+B, y+B, z);
    }

    @Override
    public synchronized void renderDownFace(World world,Rectangle2D texture, float x, float y, float z) {
            addNormal(0,-1,0);addTextureCoord(texture.x+texture.w, texture.y);addVertex(x+B, y, z);
            addNormal(0,-1,0);addTextureCoord(texture.x+texture.w, texture.y+texture.h);addVertex(x+B, y, z+B);
            addNormal(0,-1,0);addTextureCoord(texture.x, texture.y+texture.h);addVertex(x, y, z+B);
            addNormal(0,-1,0);addTextureCoord(texture.x, texture.y);addVertex(x, y, z);
    }

}
