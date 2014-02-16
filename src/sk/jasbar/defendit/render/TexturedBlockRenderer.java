package sk.jasbar.defendit.render;

import org.newdawn.slick.opengl.Texture;

import sk.jasbar.defendit.game.Block;
import sk.jasbar.defendit.game.Blocks;
import sk.jasbar.defendit.game.World;
import sk.jasbar.defendit.resources.Resources;
import sk.tomsik68.gamedev.engine3d.Renderer;

public class TexturedBlockRenderer extends BlockRenderer{

	private final Texture texture;
	
	public TexturedBlockRenderer(Block block,String textures) {
		super(block);
		texture = Resources.getTexture(textures);
		
	}

	@Override
	public void renderBlock(World world, int x, int y, int z) {
		float renderX = x * BLOCK_SIZE;
        float renderY = y * BLOCK_SIZE;
        float renderZ = z * BLOCK_SIZE;

        if (z > world.SIZE_Z - 1 || !Blocks.block(world.getBlockIdAt(x, y, z + 1)).renders(world, x, y, z + 1))
            Renderer.renderFrontFace(texture,renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        if (z < 1 || !Blocks.block(world.getBlockIdAt(x, y, z - 1)).renders(world, x, y, z - 1))
            Renderer.renderBackFace(texture,renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);

        if (x > world.SIZE_X - 1 || !Blocks.block(world.getBlockIdAt(x + 1, y, z)).renders(world, x + 1, y, z))
            Renderer.renderRightFace(texture,renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        if (x < 1 || !Blocks.block(world.getBlockIdAt(x - 1, y, z)).renders(world, x, y, z - 1))
            Renderer.renderLeftFace(texture,renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);

        if (y > world.SIZE_Y - 1 || !Blocks.block(world.getBlockIdAt(x, y + 1, z)).renders(world, x, y + 1, z))
            Renderer.renderUpFace(texture,renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        if (y < 1 || !Blocks.block(world.getBlockIdAt(x, y - 1, z)).renders(world, x, y - 1, z))
            Renderer.renderDownFace(texture,renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
		
	}

}
