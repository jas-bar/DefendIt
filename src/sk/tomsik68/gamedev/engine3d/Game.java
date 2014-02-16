package sk.tomsik68.gamedev.engine3d;

public abstract class Game {
    public Game() {
    }

    public abstract void init();

    public abstract void render();

    public abstract void update();

    public boolean shouldExit() {
        return false;
    }

    public void exit() {

    }

}
