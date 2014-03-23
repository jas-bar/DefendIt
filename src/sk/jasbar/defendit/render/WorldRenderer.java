package sk.jasbar.defendit.render;

import java.lang.Thread.State;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import sk.jasbar.defendit.DefendItGame;
import sk.jasbar.defendit.engine.IRenderable;
import sk.jasbar.defendit.engine.render.Frustum;
import sk.jasbar.defendit.engine.render.ICameraCoordsProvider;
import sk.jasbar.defendit.engine.render.ICulling;
import sk.jasbar.defendit.game.Blocks;
import sk.jasbar.defendit.game.World;
import sk.jasbar.defendit.resources.Resources;

public class WorldRenderer implements IRenderable {
    private final World world;
    // 1000 = far clipping plane. Dalej nema vyznam renderovat, kedze to OpenGL
    // aj tak odsekne...
    public static final int renderDistance = (int) (200);
    public final BufferedBlocksRenderer renderTarget;
    public static boolean needsRender = true;
    private final float FOV = 30;
    private final int THREADS = 8;
    private final ICulling culling;
    private boolean done;
    private ArrayList<WorldRendererThread> threads = new ArrayList<WorldRendererThread>();

    public WorldRenderer(World world) {
        this.world = world;
        renderTarget = new BufferedBlocksRenderer(GL11.GL_QUADS);

        for (int x = 0; x < World.SIZE_X; ++x) {
            for (int z = 0; z < World.SIZE_Z; ++z) {
                for (int y = 0; y < World.SIZE_Y; ++y) {
                    world.setVisible(x, y, z, world.visibleTest(x, y, z));
                }
            }
        }

        culling = new Frustum();
        culling.init(this);
    }

    public boolean blockRenders(ICameraCoordsProvider cam, int x, int y, int z) {
        return world.isVisible(x, y, z) && (Blocks.block(world.getBlockIdAt(x, y, z)).renders(world, x, y, z) && isBlockInFrustrum(x, y, z));

    }

    private boolean isBlockInFrustrum(int xBlock, int yBlock, int zBlock) {
        return culling.blockRenders(xBlock, yBlock, zBlock);
    }

    @Override
    public void render(DefendItGame game, ICameraCoordsProvider cam) {
        if (needsRender) {
            culling.init(WorldRenderer.this);
            renderTarget.reset();
            needsRender = false;
            int xBegin = (int) Math.max(0, -cam.getCamX() / BlockRenderer.BLOCK_SIZE - renderDistance / 2);
            int yBegin = (int) Math.max(0, -cam.getCamY() / BlockRenderer.BLOCK_SIZE - renderDistance / 2);
            int zBegin = (int) Math.max(0, -cam.getCamZ() / BlockRenderer.BLOCK_SIZE - renderDistance / 2);
            int xEnd = (int) Math.min(xBegin + renderDistance, World.SIZE_X - 1);
            int yEnd = (int) Math.min(yBegin + renderDistance, World.SIZE_Y - 1);
            int zEnd = (int) Math.min(zBegin + renderDistance, World.SIZE_Z - 1);

            final int XSTEP = (xEnd - xBegin) / THREADS;
            final int ZSTEP = (zEnd - zBegin) / THREADS;
            for (int xB = xBegin; xB < xEnd; xB += XSTEP) {
                for (int zB = zBegin; zB < zEnd; zB += ZSTEP) {
                    WorldRendererThread thread = new WorldRendererThread(this, cam, xB, yBegin, zB, xB + XSTEP, yEnd, zB + ZSTEP);
                    threads.add(thread);
                    thread.start();
                }
            }
            done = false;
            while (!done) {
                done = true;
                for (WorldRendererThread thread : threads) {
                    done = done && thread.getState() == State.TERMINATED;
                }
            }
            threads.clear();
            renderTarget.endEdit();
        }
        Resources.getTexture("textures.textures").bind();
        renderTarget.draw();
    }

    public World getWorld() {
        return world;
    }

}
