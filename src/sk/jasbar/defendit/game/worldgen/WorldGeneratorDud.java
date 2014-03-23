package sk.jasbar.defendit.game.worldgen;

import java.util.Random;

import sk.jasbar.defendit.game.Blocks;
import sk.jasbar.defendit.game.World;

public class WorldGeneratorDud extends WorldGenerator {

    @Override
    public void generate(World world) {
        int use;
        Random rand = new Random();
        for (int x = 0; x < World.SIZE_X; ++x) {
            for (int z = 0; z < World.SIZE_Z; ++z) {
                for (int y = 0; y < 60; ++y) {
                    if (rand.nextInt(2) == 0) {
                        world.setBlockIdNoUpdate(x, y, z, Blocks.stone.blockID);
                    } else {
                        world.setBlockIdNoUpdate(x, y, z, Blocks.dirt.blockID);
                    }

                }
            }
        }
    }

}
