package sk.jasbar.defendit.game;

import java.util.ArrayList;
import java.util.List;

import sk.jasbar.defendit.DefendItGame;
import sk.jasbar.defendit.engine.IUpdateable;

public class World implements IUpdateable {
    private ArrayList<Entity> entities = new ArrayList<Entity>();
    public static final int SIZE_X = 1024, SIZE_Y = 32, SIZE_Z = 1024;

    private final ChunkManager chunks = new ChunkManager();

    //private byte[][][] blocks = new byte[SIZE_X][SIZE_Y][SIZE_Z];
    private boolean[][][] visible = new boolean[SIZE_X][SIZE_Y][SIZE_Z];

    public void coordsCheck(int x, int y, int z) {
        if (x < 0 || x >= SIZE_X || y < 0 || y >= SIZE_Y || z < 0 || z >= SIZE_Z) {
            throw new RuntimeException("Coordinates are out of bounds [" + x + ";" + y + ";" + z + "]");
        }
    }

    public byte getBlockIdAt(int x, int y, int z) {
        return chunks.getChunkAt(x,z).getBlockID(x % Chunk.SIZE_X, y, z % Chunk.SIZE_Z);
        // coordsCheck(x, y, z);
        // return blocks[x][y][z];
    }

    public void setBlockIdNoUpdate(int x, int y, int z, byte id) {
        // coordsCheck(x, y, z);
        // blocks[x][y][z] = (byte) (id & 0xFF);
        chunks.getChunk(x / Chunk.SIZE_X, z / Chunk.SIZE_Z).setBlockID(x % Chunk.SIZE_X, y, z % Chunk.SIZE_Z, id);
    }

    public boolean isVisible(int x, int y, int z) {
        // coordsCheck(x, y, z);
        return visible[x][y][z];
    }

    public void setVisible(int x, int y, int z, boolean vis) {
        // coordsCheck(x, y, z);
        visible[x][y][z] = vis;
    }

    public void tick() {

    }

    public void setBlockId(int x, int y, int z, byte blockID) {
        setBlockIdNoUpdate(x, y, z, blockID);
        updateBlock(x, y, z);
        updateBlock(Math.min(x + 1, World.SIZE_X - 1), y, z);
        updateBlock(Math.max(x - 1, 0), y, z);
        updateBlock(x, Math.min(y + 1, World.SIZE_Y - 1), z);
        updateBlock(x, Math.max(y - 1, 0), z);
        updateBlock(x, y, Math.min(z + 1, World.SIZE_Z - 1));
        updateBlock(x, y, Math.max(z - 1, 0));

    }

    private void updateBlock(int x, int y, int z) {
        visible[x][y][z] = blockRenders(x, y, z);
    }

    private boolean blockRenders(int x, int y, int z) {
        return Blocks.block(getBlockIdAt(x, y, z)).renders(this, x, y, z);
    }

    public boolean visibleTest(int x, int y, int z) {
        if (x > 1 && x < World.SIZE_X - 1 && y > 1 && y < World.SIZE_Y - 1 && z > 1 && z < World.SIZE_Z - 1) {
            return (!Blocks.block(getBlockIdAt(x + 1, y, z)).renders(this, x + 1, y, z)
                    || !Blocks.block(getBlockIdAt(x - 1, y, z)).renders(this, x - 1, y, z)
                    || !Blocks.block(getBlockIdAt(x, y + 1, z)).renders(this, x, y + 1, z)
                    || !Blocks.block(getBlockIdAt(x, y - 1, z)).renders(this, x, y - 1, z)
                    || !Blocks.block(getBlockIdAt(x, y, z - 1)).renders(this, x, y, z - 1) || !Blocks.block(getBlockIdAt(x, y, z + 1)).renders(this,
                    x, y, z + 1));
        } else
            return false;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    @Override
    public void update(DefendItGame game) {
        for (Entity entity : entities) {
            entity.tick();
        }
    }
}
