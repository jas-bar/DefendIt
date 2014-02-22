package sk.jasbar.defendit.game;

public class Entity {
    protected float x, y, z;
    protected float rx, ry, rz;
    protected final World world;

    public Entity(World w) {
        world = w;
    }

    public void addZ(float f) {
        z += f;
    }

    public void addX(float f) {
        x += f;
    }

    public void addY(float f) {
        y += f;
    }

    public void setRY(float f) {
        ry = f;
    }

    public void setRX(float f) {
        rx = f;
    }

    public void setX(float f) {
        x = f;
    }

    public void setY(float f) {
        y = f;
    }

    public void setZ(float f) {
        z = f;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void tick() {

    }
}
