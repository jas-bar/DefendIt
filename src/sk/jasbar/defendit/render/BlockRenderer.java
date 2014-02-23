package sk.jasbar.defendit.render;

import sk.jasbar.defendit.game.Block;
import sk.jasbar.defendit.game.World;

public abstract class BlockRenderer {

    public static final float BLOCK_SIZE = 64;

    public BlockRenderer(Block block) {
        BlockRendererRegistry.instance.registerRenderer(block.blockID, this);
    }

    public abstract void renderBlock(IBlockRenderTarget target,World world, int x, int y, int z);
}
