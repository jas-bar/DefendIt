package sk.jasbar.defendit.game;

import java.util.HashMap;

public class BlockRegistry {
	Block[] blocks = new Block[256];
	
    public static final BlockRegistry instance = new BlockRegistry();

    private final HashMap<Byte, Block> blockLookup = new HashMap<Byte, Block>();
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
        //return blockLookup.get(id);
    	return blocks[id];
    }

}
