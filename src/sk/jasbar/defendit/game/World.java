package sk.jasbar.defendit.game;

public class World {
    public static final int SIZE_X = 1024, SIZE_Y = 32, SIZE_Z = 1024;
    private byte[][][] blocks = new byte[SIZE_X][SIZE_Y][SIZE_Z];
    private boolean[][][] visible = new boolean[SIZE_X][SIZE_Y][SIZE_Z];

    public void coordsCheck(int x, int y, int z) {
        if (x < 0 || x >= SIZE_X || y < 0 || y >= SIZE_Y || z < 0 || z >= SIZE_Z) {
            throw new RuntimeException("Coordinates are out of bounds [" + x + ";" + y + ";" + z + "]");
        }
    }

    public byte getBlockIdAt(int x, int y, int z) {
        coordsCheck(x, y, z);
        return blocks[x][y][z];
    }

    public void setBlockIdAt(int x, int y, int z, int id) {
        coordsCheck(x, y, z);
        blocks[x][y][z] = (byte) (id & 0xFF);
    }
    
    public void setVisibility(int x, int y,int z, boolean vis){
    	visible[x][y][z] = vis;
    }
    
    public boolean getVisibility(int x, int y,int z){
    	return visible[x][y][z];
    }

    public void tick() {

    }
}
