package sk.jasbar.defendit.game.worldgen;

import sk.jasbar.defendit.game.Blocks;
import sk.jasbar.defendit.game.World;
import sk.jasbar.defendit.util.PerlinNoiseGenerator;

public class WorldGenNoise extends WorldGenerator {
    private final PerlinNoiseGenerator noiseGen;

    public WorldGenNoise() {
        noiseGen = new PerlinNoiseGenerator();
    }

    @Override
    public void generate(World world) {
        for (int x = 0; x < World.SIZE_X; ++x) {
            for (int z = 0; z < World.SIZE_Z; ++z) {
                double noise = 10 + 50 * noiseGen.noise2((x), (z));
                for (int y = 0; y < noise; ++y) {
                    if (y < 10)
                        world.setBlockIdAt(x, y, z, Blocks.stone.blockID);
                    else if (y >= 10 && y < 24) {
                        world.setBlockIdAt(x, y, z, Blocks.dirt.blockID);
                    } else
                        world.setBlockIdAt(x, y, z, Blocks.air.blockID);
                }
            }
        }
    }

}
