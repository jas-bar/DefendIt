package sk.jasbar.defendit.engine;

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
