package sk.jasbar.defendit.engine;

import sk.jasbar.defendit.DefendItGame;

public class ShowFPS implements IUpdateable {
    private long lastCheck = 0;
    private int frames = 0;

    @Override
    public void update(DefendItGame game) {
        long time = System.currentTimeMillis();
        if (time - lastCheck >= 1000) {
            int fps = (int) (frames / ((time - lastCheck) / 1000));
            System.out.println("FPS:" + fps);
            lastCheck = System.currentTimeMillis();
            frames = 0;
        }
        frames++;
    }

}
