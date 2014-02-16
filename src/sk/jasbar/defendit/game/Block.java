package sk.jasbar.defendit.game;

public class Block {
    public final byte blockID;

    public Block() {
        blockID = BlockRegistry.instance.registerBlock(this);
        Blocks.block(blockID);
    }

    public boolean renders(World world, int x, int y, int z) {
        return true;
    }
}
