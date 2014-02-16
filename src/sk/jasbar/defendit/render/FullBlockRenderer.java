package sk.jasbar.defendit.render;

import sk.jasbar.defendit.game.Block;
import sk.jasbar.defendit.game.Blocks;
import sk.jasbar.defendit.game.World;
import sk.tomsik68.gamedev.engine3d.Renderer;

public class FullBlockRenderer extends BlockRenderer {

    public FullBlockRenderer(Block block) {
        super(block);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void renderBlock(World world, int x, int y, int z) {
        /*float renderX = x * BLOCK_SIZE;
        float renderY = y * BLOCK_SIZE;
        float renderZ = z * BLOCK_SIZE;

        // Renderer.renderBox(renderX, renderY,
        // renderZ,BLOCK_SIZE,BLOCK_SIZE,BLOCK_SIZE);

        if (z > world.SIZE_Z - 1 || !Blocks.block(world.getBlockIdAt(x, y, z + 1)).renders(world, x, y, z + 1))
            Renderer.renderFrontFace(renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        if (z < 1 || !Blocks.block(world.getBlockIdAt(x, y, z - 1)).renders(world, x, y, z - 1))
            Renderer.renderBackFace(renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);

        if (x > world.SIZE_X - 1 || !Blocks.block(world.getBlockIdAt(x + 1, y, z)).renders(world, x + 1, y, z))
            Renderer.renderRightFace(renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        if (x < 1 || !Blocks.block(world.getBlockIdAt(x - 1, y, z)).renders(world, x, y, z - 1))
            Renderer.renderLeftFace(renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);

        if (y > world.SIZE_Y - 1 || !Blocks.block(world.getBlockIdAt(x, y + 1, z)).renders(world, x, y + 1, z))
            Renderer.renderUpFace(renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        if (y < 1 || !Blocks.block(world.getBlockIdAt(x, y - 1, z)).renders(world, x, y - 1, z))
            Renderer.renderDownFace(renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);*/
    }

}
