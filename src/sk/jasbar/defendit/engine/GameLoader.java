package sk.jasbar.defendit.engine;

public class GameLoader {
    public static void loadGame(Game g, ISettings settings) {

        final LWJGL3DGame game = new LWJGL3DGame(settings, g);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    game.init();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                while (!game.shouldExit()) {
                    game.render();
                    game.update();
                }
                game.exit();
            }
        };
        thread.start();
    }
}
