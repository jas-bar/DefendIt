package sk.jasbar.defendit.render;

import sk.jasbar.defendit.DefendItGame;
import sk.jasbar.defendit.engine.IRenderable;
import sk.jasbar.defendit.engine.render.ICameraCoordsProvider;
import sk.jasbar.defendit.game.Blocks;
import sk.jasbar.defendit.game.World;
import sk.tomsik68.gamedev.engine3d.Renderer;

public class WorldRenderer implements IRenderable {
    private final World world;
    // 1000 = far clipping plane. Dalej nema vyznam renderovat, kedze to OpenGL
    // aj tak odsekne...
    public static final int renderDistance = (int) (20);

    public WorldRenderer(World world) {
        this.world = world;

        for (int x = 0; x < World.SIZE_X; ++x) {
            for (int z = 0; z < World.SIZE_Z; ++z) {
                for (int y = 0; y < World.SIZE_Y; ++y) {
                    world.setVisible(x, y, z, blockRenders(x, y, z));
                }
            }
        }

    }

    private boolean blockRenders(int x, int y, int z) {
        return (Blocks.block(world.getBlockIdAt(x, y, z)).renders(world, x, y, z) && world.visibleTest(x, y, z));
    }

    @Override
    public void render(DefendItGame game, ICameraCoordsProvider cam) {

        int xBegin = (int) Math.max(0, -cam.getCamX() / BlockRenderer.BLOCK_SIZE - renderDistance / 2);
        int yBegin = (int) Math.max(0, -cam.getCamY() / BlockRenderer.BLOCK_SIZE - renderDistance / 2);
        int zBegin = (int) Math.max(0, -cam.getCamZ() / BlockRenderer.BLOCK_SIZE - renderDistance / 2);

        int xEnd = (int) Math.min(xBegin + renderDistance, World.SIZE_X - 1);
        int yEnd = (int) Math.min(yBegin + renderDistance, World.SIZE_Y - 1);
        int zEnd = (int) Math.min(zBegin + renderDistance, World.SIZE_Z - 1);
        
        
        for (int x = xBegin; x < xEnd; ++x) {
            for (int z = zBegin; z < zEnd; ++z) {
                for (int y = yBegin; y < yEnd; ++y) {
                    if (world.isVisible(x, y, z)) {
                        BlockRenderer renderer = BlockRendererRegistry.instance.getRenderer(world.getBlockIdAt(x, y, z));
                        renderer.renderBlock(world, x, y, z);
                    }
                }
            }
        }
        Renderer.draw();
    }

}
