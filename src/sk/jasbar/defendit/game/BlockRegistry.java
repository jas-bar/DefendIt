package sk.jasbar.defendit.game;

public class BlockRegistry {
    public static final BlockRegistry instance = new BlockRegistry();

    //private final HashMap<Byte, Block> blockLookup = new HashMap<Byte, Block>();
    private final Block[] blocks = new Block[Blocks.MAX_BLOCKS];
    private byte lastBlockID = -1;

    private BlockRegistry() {
    }

    public byte registerBlock(Block block) {
        ++lastBlockID;
        //blockLookup.put(lastBlockID, block);
        blocks[lastBlockID] = block;
        System.out.println("Register #" + lastBlockID+" |"+block);
        return lastBlockID;
    }

    public Block getBlockById(byte id) {
        return blocks[id];
        //return blockLookup.get(id);
    }

}
