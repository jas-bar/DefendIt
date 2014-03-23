package sk.jasbar.defendit.game;

public class ChunkManager {

    private final Chunk[][] chunks = new Chunk[World.SIZE_X / Chunk.SIZE_X][World.SIZE_Z / Chunk.SIZE_Z];

    public ChunkManager() {

    }

    public Chunk getChunkAt(int bx, int bz) {
        return getChunk(bx / Chunk.SIZE_X, bz / Chunk.SIZE_Z);
    }

    public Chunk getChunk(int cx, int cz) {
        if (chunks[cx][cz] == null) {
            chunks[cx][cz] = new Chunk(cx,cz);
        }
        return chunks[cx][cz];
    }
}
