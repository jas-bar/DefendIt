package sk.jasbar.defendit.game.worldgen;

import sk.jasbar.defendit.game.Blocks;
import sk.jasbar.defendit.game.World;
import sk.jasbar.defendit.util.PerlinNoiseGenerator;

public class WorldGenNoise extends WorldGenerator {
    private final PerlinNoiseGenerator noiseGen;

    public WorldGenNoise() {
        noiseGen = new PerlinNoiseGenerator();
    }

    public void generate(World world) {
        for (int x = 0; x < World.SIZE_X; ++x) {
            for (int z = 0; z < World.SIZE_Z; ++z) {
                double noise = 10 + 80 * noiseGen.noise2((16.f * x) / World.SIZE_X, (16.0f * z) / World.SIZE_Z);
                if (noise < 3)
                    noise = 3;
                if (noise > World.SIZE_Y)
                    noise = 32.f;
                for (int y = 0; y < noise; ++y) {
                    if (y < 10)
                        world.setBlockIdNoUpdate(x, y, z, Blocks.stone.blockID);
                    else if (y >= 10 && y < 60) {
                        world.setBlockIdNoUpdate(x, y, z, Blocks.dirt.blockID);
                    } else
                        world.setBlockIdNoUpdate(x, y, z, Blocks.air.blockID);
                }
            }
        }
    }

}
