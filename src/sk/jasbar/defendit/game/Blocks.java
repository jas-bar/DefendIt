package sk.jasbar.defendit.game;

import sk.jasbar.defendit.game.blocks.*;

public class Blocks {
    public static final BlockAir air = new BlockAir();
    public static final BlockStone stone = new BlockStone();
    public static final BlockDirt dirt = new BlockDirt();

    public static Block block(byte id) {
        return BlockRegistry.instance.getBlockById(id);
    }
}
