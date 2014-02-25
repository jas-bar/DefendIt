package sk.jasbar.defendit.render;

import sk.jasbar.defendit.engine.render.Rectangle2D;
import sk.jasbar.defendit.engine.render.TextureManager;
import sk.jasbar.defendit.game.Block;
import sk.jasbar.defendit.game.Blocks;
import sk.jasbar.defendit.game.World;

public class TexturedBlockRenderer extends BlockRenderer {

    private final int btx, bty;
    private final TextureManager textureManager;

    public TexturedBlockRenderer(Block block, TextureManager textures, int btx, int bty) {
        super(block);
        this.btx = btx;
        this.bty = bty;
        textureManager = textures;
    }

    @Override
    public void renderBlock(IBlockRenderTarget target, World world, int x, int y, int z) {
        float renderX = x * BLOCK_SIZE;
        float renderY = y * BLOCK_SIZE;
        float renderZ = z * BLOCK_SIZE;  
        Rectangle2D texture = textureManager.getBounds(btx, bty);
        if (z > World.SIZE_Z - 1 || !Blocks.block(world.getBlockIdAt(x, y, z + 1)).renders(world, x, y, z + 1))
            target.renderFrontFace(world, texture, renderX, renderY, renderZ);
            //Renderer.renderFrontFace(texture, renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        if (z < 1 || !Blocks.block(world.getBlockIdAt(x, y, z - 1)).renders(world, x, y, z - 1))
            target.renderBackFace(world, texture, renderX, renderY, renderZ);
            //Renderer.renderBackFace(texture, renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);

        if (x > World.SIZE_X - 1 || !Blocks.block(world.getBlockIdAt(x + 1, y, z)).renders(world, x + 1, y, z))
            target.renderRightFace(world, texture, renderX, renderY, renderZ);
            //Renderer.renderRightFace(texture, renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        if (x < 1 || !Blocks.block(world.getBlockIdAt(x - 1, y, z)).renders(world, x, y, z - 1))
            target.renderLeftFace(world, texture, renderX, renderY, renderZ);
            //Renderer.renderLeftFace(texture, renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);

        if (y > World.SIZE_Y - 1 || !Blocks.block(world.getBlockIdAt(x, y + 1, z)).renders(world, x, y + 1, z))
            target.renderUpFace(world, texture, renderX, renderY, renderZ);
            //Renderer.renderUpFace(texture, renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
        if (y < 1 || !Blocks.block(world.getBlockIdAt(x, y - 1, z)).renders(world, x, y - 1, z))
            target.renderDownFace(world, texture, renderX, renderY, renderZ);
            //Renderer.renderDownFace(texture, renderX, renderY, renderZ, BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
    }
}
