package sk.jasbar.defendit;

import java.io.File;

import sk.jasbar.defendit.util.BaseProperties;
import sk.tomsik68.gamedev.engine3d.ISettings;

public class DefendItSettings extends BaseProperties implements ISettings {

    public DefendItSettings(File cfgFile) {
        super(cfgFile);
    }

    @Override
    public boolean isVSync() {
        return getBoolean("vsync", false);
    }

    @Override
    public int getDisplayWidth() {
        return getInt("screen-width", 800);
    }

    @Override
    public String getWindowTitle() {
        return getString("title", "DefendIt 0.1 by JasBar");
    }

    @Override
    public int getTargetFPS() {
        return getInt("fps", 60);
    }

    @Override
    public int getDisplayHeight() {
        return getInt("screen-height", 600);
    }

    public boolean isShowFPS() {
        return getBoolean("show-fps", true);
    }

}
