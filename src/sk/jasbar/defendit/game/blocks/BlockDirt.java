package sk.jasbar.defendit.game.blocks;

import sk.jasbar.defendit.game.Block;
import sk.jasbar.defendit.render.BlockRendererRegistry;
import sk.jasbar.defendit.render.ColorBlockRenderer;
import sk.jasbar.defendit.render.TexturedBlockRenderer;

public class BlockDirt extends Block {
	public BlockDirt() {
        super();
        BlockRendererRegistry.instance.registerRenderer(blockID, new TexturedBlockRenderer(this, "textures.dirt"));
    }

}
