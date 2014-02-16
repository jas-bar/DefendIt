package sk.jasbar.defendit.game.worldgen;

import java.util.Random;

import sk.jasbar.defendit.game.Blocks;
import sk.jasbar.defendit.game.World;

public class WorldGenJas extends WorldGenerator {

    @Override
    public void generate(World world) {
        Random rand = new Random();
        int count = 20 + rand.nextInt(200);
        for (int c = 0; c < count; ++c) {
            int xx = rand.nextInt(World.SIZE_X-6);
            int yy = 24;
            int zz = rand.nextInt(World.SIZE_Z-6);
            for(int x = xx;x<xx+5;++x){
                for(int y = yy;y<yy+5;++y){
                    for(int z = zz;z<zz+5;++z){
                        world.setBlockIdNoUpdate(x, y, z, Blocks.stone.blockID);
                    }
                }
            }
        }
    }

}
