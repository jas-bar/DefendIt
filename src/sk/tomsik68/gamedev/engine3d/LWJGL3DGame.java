package sk.tomsik68.gamedev.engine3d;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class LWJGL3DGame {
    public final ISettings settings;
    private final Game game;

    public LWJGL3DGame(ISettings settings, Game game) {
        this.settings = settings;
        this.game = game;
    }

    public void init() throws LWJGLException {
        createGameWindow();
        game.init();
    }

    private void createGameWindow() throws LWJGLException {
        Display.setVSyncEnabled(settings.isVSync());
        Display.setDisplayMode(new DisplayMode(settings.getDisplayWidth(), settings.getDisplayHeight()));
        Display.setTitle(settings.getWindowTitle());
        Display.create();
    }

    public void render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glLoadIdentity();

        game.render();

        Display.sync(settings.getTargetFPS());
        Display.update();
    }

    public void update() {
        game.update();
    }

    public boolean shouldExit() {
        return Display.isCloseRequested() || game.shouldExit();
    }

    public void exit() {
        game.exit();
        Display.destroy();
    }

}
