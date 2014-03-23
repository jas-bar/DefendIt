package sk.jasbar.defendit.game;

import sk.jasbar.defendit.engine.render.TextureManager;
import sk.jasbar.defendit.game.blocks.*;

public class Blocks {
    public static final int MAX_BLOCKS = 256;
    public static BlockAir air = new BlockAir();
    public static BlockStone stone;
    public static BlockDirt dirt;
    public static TextureManager textures;

    public static void init(TextureManager tm) {
        textures = tm;
        dirt = new BlockDirt(0, 0);
        stone = new BlockStone(1, 0);
    }

    public static Block block(byte id) {
        return BlockRegistry.instance.getBlockById(id);
    }
}
