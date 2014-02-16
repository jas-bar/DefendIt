package sk.jasbar.defendit.game.blocks;

import sk.jasbar.defendit.game.Block;
import sk.jasbar.defendit.render.BlockRendererRegistry;
import sk.jasbar.defendit.render.ColorBlockRenderer;

public class BlockStone extends Block {
    public BlockStone() {
        super();
        BlockRendererRegistry.instance.registerRenderer(blockID, new ColorBlockRenderer(this, 0.6f, 0.6f, 0.6f));
    }
}
