package sk.jasbar.defendit.render;

import sk.jasbar.defendit.game.Block;
import sk.jasbar.defendit.game.World;
import sk.tomsik68.gamedev.engine3d.Renderer;

public class ColorBlockRenderer extends FullBlockRenderer {
    private final float r, g, b;

    public ColorBlockRenderer(Block block, float r, float g, float b) {
        super(block);
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public void renderBlock(World world, int x, int y, int z) {
        Renderer.setColor(r, g, b);
        super.renderBlock(world, x, y, z);
    }

}
