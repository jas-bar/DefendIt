package sk.jasbar.defendit.engine;


import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import sk.jasbar.defendit.DefendItGame;
import sk.jasbar.defendit.game.Player;

public class PlayerMovementUpdateable implements IUpdateable {

    private static final float CAMERA_STEP = 10f;
    private final Player player;

    public PlayerMovementUpdateable(Player player) {
        this.player = player;
    }

    @Override
    public void update(DefendItGame game) {
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            player.addX((float) -Math.sin(Math.toRadians(player.getCamRY() + 90)) * CAMERA_STEP);
            player.addZ((float) Math.cos(Math.toRadians(player.getCamRY() + 90)) * CAMERA_STEP);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            player.addX((float) Math.sin(Math.toRadians(player.getCamRY() + 90)) * CAMERA_STEP);
            player.addZ((float) -Math.cos(Math.toRadians(player.getCamRY() + 90)) * CAMERA_STEP);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            player.addX((float) Math.sin(Math.toRadians(player.getCamRY())) * CAMERA_STEP);
            player.addZ((float) -Math.cos(Math.toRadians(player.getCamRY())) * CAMERA_STEP);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            player.addX((float) -Math.sin(Math.toRadians(player.getCamRY())) * CAMERA_STEP);
            player.addZ((float) Math.cos(Math.toRadians(player.getCamRY())) * CAMERA_STEP);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            player.addY(-CAMERA_STEP);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
            player.addY(CAMERA_STEP);
        }

        player.setRY(player.getCamRY() + Mouse.getDX() / 5f);
        player.setRX(player.getCamRX() - Mouse.getDY() / 5f);

        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !Keyboard.isRepeatEvent()) {
            Mouse.setGrabbed(!Mouse.isGrabbed());
        }
    }

}
