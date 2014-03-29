package sk.jasbar.defendit.render;

import org.lwjgl.opengl.GL11;

import sk.jasbar.defendit.DefendItGame;
import sk.jasbar.defendit.engine.IRenderable;
import sk.jasbar.defendit.engine.render.Frustum;
import sk.jasbar.defendit.engine.render.ICameraCoordsProvider;
import sk.jasbar.defendit.engine.render.ICulling;
import sk.jasbar.defendit.game.Blocks;
import sk.jasbar.defendit.game.Chunk;
import sk.jasbar.defendit.game.World;
import sk.jasbar.defendit.resources.Resources;
import sk.jasbar.defendit.util.MathHelper;

public class WorldRenderer implements IRenderable {
    private final World world;
    // 1000 = far clipping plane. Dalej nema vyznam renderovat, kedze to OpenGL
    // aj tak odsekne...
    public static final int renderDistance = (int) (200);
    private BufferedBlocksRenderer renderer;
    public static boolean needsRender = true;
    private final ICulling culling;

    public WorldRenderer(World world) {
        this.world = world;
        renderer = new BufferedBlocksRenderer(GL11.GL_QUADS);
        for (int x = 0; x < World.SIZE_X; ++x) {
            for (int z = 0; z < World.SIZE_Z; ++z) {
                for (int y = 0; y < World.SIZE_Y; ++y) {
                    world.setVisible(x, y, z, dud(x, y, z));
                }
            }
        }
        culling = new Frustum();
        culling.init(this);
    }

    private boolean dud(int x, int y, int z) {
        return Blocks.block(world.getBlockIdAt(x, y, z)).renders(world, x, y, z) && world.visibleTest(x, y, z);
    }

    private boolean blockRenders(int x, int y, int z) {
        return world.isVisible(x, y, z) && Blocks.block(world.getBlockIdAt(x, y, z)).renders(world, x, y, z) && culling.blockRenders(x, y, z);
    }

    @Override
    public void render(DefendItGame game, ICameraCoordsProvider cam) {
        if (needsRender || true) {
            culling.init(this);
            renderer.reset();
            needsRender = false;
            /*int xBegin = (int) Math.max(0, -cam.getCamX() / BlockRenderer.BLOCK_SIZE - renderDistance / 2);
            int yBegin = (int) Math.max(0, -cam.getCamY() / BlockRenderer.BLOCK_SIZE - renderDistance / 2);
            int zBegin = (int) Math.max(0, -cam.getCamZ() / BlockRenderer.BLOCK_SIZE - renderDistance / 2);
            int xEnd = (int) Math.min(xBegin + renderDistance, World.SIZE_X - 1);
            int yEnd = (int) Math.min(yBegin + renderDistance, World.SIZE_Y - 1);
            int zEnd = (int) Math.min(zBegin + renderDistance, World.SIZE_Z - 1);*/
            for (int cx = 0; cx < World.SIZE_X/Chunk.SIZE_X; cx++) {
                for (int cz = 0; cz < World.SIZE_Z / Chunk.SIZE_Z; cz++) {
                    if (MathHelper.distance2D(-cam.getCamX()/BlockRenderer.BLOCK_SIZE, -cam.getCamZ()/BlockRenderer.BLOCK_SIZE, cx*Chunk.SIZE_X, cz*Chunk.SIZE_Z) < renderDistance && culling.chunkRenders(cx, cz)){
                        for (int x = cx*Chunk.SIZE_X; x < cx*Chunk.SIZE_X+Chunk.SIZE_X; ++x) {
                            for (int z = cz*Chunk.SIZE_Z; z < cz*Chunk.SIZE_Z+Chunk.SIZE_Z; ++z) {
                                for (int y = 0; y < World.SIZE_Y; ++y) {
                                    if (blockRenders(x, y, z)) {
                                        BlockRenderer blockRenderer = BlockRendererRegistry.instance.getRenderer(world.getBlockIdAt(x, y, z));
                                        blockRenderer.renderBlock(renderer, world, x, y, z);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            renderer.endEdit();
        }
        Resources.getTexture("textures.textures").bind();
        renderer.draw();
    }

}
