package sk.jasbar.defendit.game.blocks;

import sk.jasbar.defendit.game.Block;
import sk.jasbar.defendit.game.Blocks;
import sk.jasbar.defendit.game.World;

public class BlockAir extends Block {
    public BlockAir() {
        super();
    }

    @Override
    public boolean renders(World world, int x, int y, int z) {
        return false;
    }

}
