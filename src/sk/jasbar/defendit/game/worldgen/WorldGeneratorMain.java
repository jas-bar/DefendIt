package sk.jasbar.defendit.game.worldgen;

import java.util.ArrayList;

import sk.jasbar.defendit.game.World;

public class WorldGeneratorMain {
    private ArrayList<WorldGenerator> generators = new ArrayList<WorldGenerator>();

    public WorldGeneratorMain() {

    }

    public void generate(World world) {
        for (WorldGenerator gen : generators) {
            gen.generate(world);
        }
    }

    public void addModule(WorldGenerator gen) {
        generators.add(gen);
    }
}
