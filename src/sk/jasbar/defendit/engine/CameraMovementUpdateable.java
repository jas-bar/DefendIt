package sk.jasbar.defendit.engine;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import sk.jasbar.defendit.DefendItGame;
import sk.jasbar.defendit.engine.render.Camera;

public class CameraMovementUpdateable implements IUpdateable {
    private static final float CAMERA_STEP = 10f;
    private final Camera cam;

    public CameraMovementUpdateable(Camera c) {
        cam = c;
    }

    @Override
    public void update(DefendItGame game) {
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            cam.addZ((float) Math.cos(Math.toRadians(cam.getRy() - 90)) * CAMERA_STEP);
            cam.addX((float) Math.sin(Math.toRadians(cam.getRy() - 90)) * -CAMERA_STEP);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            cam.addZ((float) Math.cos(Math.toRadians(cam.getRy() + 90)) * CAMERA_STEP);
            cam.addX((float) Math.sin(Math.toRadians(cam.getRy() + 90)) * -CAMERA_STEP);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            cam.addZ((float) Math.cos(Math.toRadians(cam.getRy())) * CAMERA_STEP);
            cam.addX((float) Math.sin(Math.toRadians(cam.getRy())) * -CAMERA_STEP);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            cam.addZ((float) Math.cos(Math.toRadians(cam.getRy())) * -CAMERA_STEP);
            cam.addX((float) Math.sin(Math.toRadians(cam.getRy())) * CAMERA_STEP);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            cam.addY(-CAMERA_STEP);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
            cam.addY(CAMERA_STEP);
        }

        cam.setRY(cam.getRy() + Mouse.getDX() / 5f);
        cam.setRX(cam.getRx() - Mouse.getDY() / 5f);

        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !Keyboard.isRepeatEvent()) {
        	System.exit(0);
            Mouse.setGrabbed(!Mouse.isGrabbed());
        }
    }

}
