package sk.jasbar.defendit.game.blocks;

import sk.jasbar.defendit.game.Block;
import sk.jasbar.defendit.game.Blocks;
import sk.jasbar.defendit.render.BlockRendererRegistry;
import sk.jasbar.defendit.render.TexturedBlockRenderer;

public class BlockStone extends Block {
    public BlockStone(int btx, int bty) {
        super();
        BlockRendererRegistry.instance.registerRenderer(blockID, new TexturedBlockRenderer(this, Blocks.textures, btx, bty));
    }
}
