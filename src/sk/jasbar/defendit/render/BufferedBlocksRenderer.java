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
    	addNormal(0,0,-1);addVertex(x,y+B,z);
    	addNormal(0,0,-1);addVertex(x+B, y+B, z);
    	addNormal(0,0,-1);addVertex(x+B,y,z);
    	addNormal(0,0,-1);addVertex(x, y, z);
    }
    @Override
    public void renderFrontFace(World world, float x, float y, float z) {
    	addNormal(0,0,1);addVertex(x,y,z+B);
    	addNormal(0,0,1);addVertex(x+B, y, z+B);
    	addNormal(0,0,1);addVertex(x+B,y+B,z+B);
    	addNormal(0,0,1);addVertex(x, y+B, z+B);
    }

    @Override
    public void renderLeftFace(World world, float x, float y, float z) {
    	addNormal(-1,0,0);addVertex(x,y,z);
    	addNormal(-1,0,0);addVertex(x, y, z+B);
    	addNormal(-1,0,0);addVertex(x,y+B,z+B);
    	addNormal(-1,0,0);addVertex(x, y+B, z);
    }

    @Override
    public void renderRightFace(World world, float x, float y, float z) {  	
    	addNormal(1,0,0);addVertex(x+B,y+B,z);
    	addNormal(1,0,0);addVertex(x+B, y+B, z+B);
    	addNormal(1,0,0);addVertex(x+B,y,z+B);
    	addNormal(1,0,0);addVertex(x+B, y, z);
    }

    @Override
    public void renderUpFace(World world, float x, float y, float z) {
    	addNormal(0,1,0);addVertex(x, y+B, z);
    	addNormal(0,1,0);addVertex(x, y+B, z+B);
    	addNormal(0,1,0);addVertex(x+B, y+B, z+B);
    	addNormal(0,1,0);addVertex(x+B, y+B, z);
    }

    @Override
    public void renderDownFace(World world, float x, float y, float z) {
    	addNormal(0,-1,0);addVertex(x+B, y, z);
    	addNormal(0,-1,0);addVertex(x+B, y, z+B);
    	addNormal(0,-1,0);addVertex(x, y, z+B);
    	addNormal(0,-1,0);addVertex(x, y, z);
    }

}
