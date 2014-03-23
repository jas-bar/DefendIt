package sk.jasbar.defendit.render;

import sk.jasbar.defendit.engine.render.ICameraCoordsProvider;

public class WorldRendererThread extends Thread {
    private final WorldRenderer renderer;
    private final ICameraCoordsProvider cam;
    private final int xBegin, yBegin, zBegin, xEnd, yEnd, zEnd;

    public WorldRendererThread(WorldRenderer wr, ICameraCoordsProvider cam, int xBegin, int yBegin, int zBegin, int xEnd, int yEnd, int zEnd) {
        renderer = wr;
        this.xBegin = xBegin;
        this.yBegin = yBegin;
        this.zBegin = zBegin;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.zEnd = zEnd;
        this.cam = cam;
    }

    @Override
    public void run() {
        for (int x = xBegin; x < xEnd; x++) {
            for (int y = yBegin; y < yEnd; y++) {
                for (int z = zBegin; z < zEnd; z++) {
                    if (renderer.blockRenders(cam, x, y, z)) {
                        BlockRenderer blockRenderer = BlockRendererRegistry.instance.getRenderer(renderer.getWorld().getBlockIdAt(x, y, z));
                        blockRenderer.renderBlock(renderer.renderTarget, renderer.getWorld(), x, y, z);
                    }
                    
                }
            }
        }
    }
}
