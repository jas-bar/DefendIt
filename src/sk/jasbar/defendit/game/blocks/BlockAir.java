package sk.jasbar.defendit.game.blocks;

import sk.jasbar.defendit.game.Block;
import sk.jasbar.defendit.game.Blocks;
import sk.jasbar.defendit.game.World;
import sk.jasbar.defendit.render.BlockRenderer;
import sk.jasbar.defendit.render.BlockRendererRegistry;
import sk.jasbar.defendit.render.IBlockRenderTarget;
import sk.jasbar.defendit.render.TexturedBlockRenderer;

public class BlockAir extends Block {
    public BlockAir() {
        super();
        BlockRendererRegistry.instance.registerRenderer(blockID, new BlockRenderer(this){@Override
		public void renderBlock(IBlockRenderTarget target, World world, int x,
				int y, int z) {
			// TODO Auto-generated method stub
			
		}});
    }

    @Override
    public boolean renders(World world, int x, int y, int z) {
        return false;
    }

}
