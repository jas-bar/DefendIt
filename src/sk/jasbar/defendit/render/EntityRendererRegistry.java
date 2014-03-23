package sk.jasbar.defendit.render;

import java.util.HashMap;

public class EntityRendererRegistry {
    public static final EntityRendererRegistry instance = new EntityRendererRegistry();

    private final HashMap<Byte, EntityRenderer> blockLookup = new HashMap<Byte, EntityRenderer>();
    private byte lastBlockID = -1;
}
