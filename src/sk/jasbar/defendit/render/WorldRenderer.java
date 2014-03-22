package sk.jasbar.defendit.render;

import org.lwjgl.opengl.GL11;

import sk.jasbar.defendit.DefendItGame;
import sk.jasbar.defendit.engine.IRenderable;
import sk.jasbar.defendit.engine.render.Frustum;
import sk.jasbar.defendit.engine.render.ICameraCoordsProvider;
import sk.jasbar.defendit.engine.render.ICulling;
import sk.jasbar.defendit.game.Blocks;
import sk.jasbar.defendit.game.Player;
import sk.jasbar.defendit.game.World;
import sk.jasbar.defendit.resources.Resources;

public class WorldRenderer implements IRenderable {
    private final World world;
    // 1000 = far clipping plane. Dalej nema vyznam renderovat, kedze to OpenGL
    // aj tak odsekne...
    public static final int renderDistance = (int) (200);
    private BufferedBlocksRenderer renderer;
    public static boolean needsRender = true;
    private final float FOV = 30;
    private final ICulling culling;

    public WorldRenderer(World world) {
        this.world = world;
        renderer = new BufferedBlocksRenderer(GL11.GL_QUADS);
        for (int x = 0; x < World.SIZE_X; ++x) {
            for (int z = 0; z < World.SIZE_Z; ++z) {
                for (int y = 0; y < World.SIZE_Y; ++y) {
                    world.setVisible(x, y, z, blockRenders(null, x, y, z));
                }
            }
        }
        culling = new Frustum();
        culling.init(this);
    }

    private boolean blockRenders(ICameraCoordsProvider cam, int x, int y, int z) {
        if (cam == null) {
            return (Blocks.block(world.getBlockIdAt(x, y, z)).renders(world, x, y, z) && world.visibleTest(x, y, z));
        } else if (cam instanceof Player) {
            return (Blocks.block(world.getBlockIdAt(x, y, z)).renders(world, x, y, z) && world.visibleTest(x, y, z))
                    && isBlockInFrustrum((Player) cam, x, y, z);
        } else {
            return false;
        }
    }

    private boolean isBlockInFrustrum(Player plr, int xBlock, int yBlock, int zBlock) {
        // z = tg (ry) * x
        double z = plr.getZ()/BlockRenderer.BLOCK_SIZE + Math.tan(Math.toRadians(plr.getCamRY())) * zBlock;
        double x = plr.getX()/BlockRenderer.BLOCK_SIZE + Math.tan(Math.toRadians(plr.getCamRY())) * xBlock;
        return (z > zBlock) && (x > xBlock);
    }

    @Override
    public void render(DefendItGame game, ICameraCoordsProvider cam) {
        if (needsRender) {
            renderer.reset();
            needsRender = false;
            int xBegin = (int) Math.max(0, -cam.getCamX() / BlockRenderer.BLOCK_SIZE - renderDistance / 2);
            int yBegin = (int) Math.max(0, -cam.getCamY() / BlockRenderer.BLOCK_SIZE - renderDistance / 2);
            int zBegin = (int) Math.max(0, -cam.getCamZ() / BlockRenderer.BLOCK_SIZE - renderDistance / 2);
            int xEnd = (int) Math.min(xBegin + renderDistance, World.SIZE_X - 1);
            int yEnd = (int) Math.min(yBegin + renderDistance, World.SIZE_Y - 1);
            int zEnd = (int) Math.min(zBegin + renderDistance, World.SIZE_Z - 1);

            for (int x = xBegin; x < xEnd; ++x) {
                for (int z = zBegin; z < zEnd; ++z) {
                    for (int y = yBegin; y < yEnd; ++y) {
                        if (blockRenders(cam, x, y, z)) {
                            BlockRenderer blockRenderer = BlockRendererRegistry.instance.getRenderer(world.getBlockIdAt(x, y, z));
                            blockRenderer.renderBlock(renderer, world, x, y, z);
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
