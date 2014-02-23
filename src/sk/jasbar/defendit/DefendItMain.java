package sk.jasbar.defendit;

import java.io.File;
import java.nio.FloatBuffer;

import javax.swing.JOptionPane;

import sk.jasbar.defendit.engine.GameLoader;

public class DefendItMain {

    public static void main(String[] args) {
        DefendItSettings settings = new DefendItSettings(new File("settings.properties"));
        try {
            DefendItGame game = new DefendItGame(settings);
            settings.load();
            GameLoader.loadGame(game, settings);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Engine failure", JOptionPane.ERROR_MESSAGE);
        }
    }
}
