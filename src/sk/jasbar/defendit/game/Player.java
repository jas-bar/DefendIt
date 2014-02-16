package sk.jasbar.defendit.game;

import sk.jasbar.defendit.engine.render.ICameraCoordsProvider;

public class Player implements ICameraCoordsProvider {
    private float x, y, z;
    private float rx, ry, rz;

    @Override
    public float getCamRX() {
        return rx;
    }

    @Override
    public float getCamRY() {
        return ry;
    }

    @Override
    public float getCamRZ() {
        return rz;
    }

    @Override
    public float getCamX() {
        return -x;
    }

    @Override
    public float getCamY() {
        return -y;
    }

    @Override
    public float getCamZ() {
        return -z;
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

}
