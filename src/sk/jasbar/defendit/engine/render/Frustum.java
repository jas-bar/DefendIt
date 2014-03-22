package sk.jasbar.defendit.engine.render;

import sk.jasbar.defendit.render.WorldRenderer;

public class Frustum implements ICulling {

    @Override
    public boolean blockRenders(ICameraCoordsProvider cam, int blockX, int blockY, int blockZ) {
        return false;
    }

    @Override
    public void init(WorldRenderer renderer) {
    }

}
