package sk.jasbar.defendit.engine.render;

import sk.jasbar.defendit.render.WorldRenderer;

public interface ICulling {
    public void init(WorldRenderer renderer);

    public boolean blockRenders(ICameraCoordsProvider cam, int blockX, int blockY, int blockZ);

}
