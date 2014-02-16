package sk.jasbar.defendit.game.blocks;

import sk.jasbar.defendit.game.Block;
import sk.jasbar.defendit.render.BlockRendererRegistry;
import sk.jasbar.defendit.render.ColorBlockRenderer;

public class BlockDirt extends Block {
	public BlockDirt() {
        super();
        BlockRendererRegistry.instance.registerRenderer(blockID, new ColorBlockRenderer(this, 0.f, 0.6f, 0.f));
    }

}
