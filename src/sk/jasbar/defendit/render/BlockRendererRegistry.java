package sk.jasbar.defendit.render;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class BlockRendererRegistry {
    public static BlockRendererRegistry instance = new BlockRendererRegistry();
    private final HashMap<Byte, BlockRenderer> renderers = new HashMap<Byte, BlockRenderer>();

    private BlockRendererRegistry() {

    }

    public void registerRenderer(byte blockID, BlockRenderer blockRenderer) {
        renderers.put(blockID, blockRenderer);
    }

    public BlockRenderer getRenderer(byte blockID) {
        if (!renderers.containsKey(blockID))
            throw new NoSuchElementException("Renderer for #" + blockID + " isn't registered");
        return renderers.get(blockID);
    }
}
