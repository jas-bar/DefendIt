package sk.jasbar.defendit.util;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class BaseProperties {
    private final Properties props;
    private final File cfgFile;

    public BaseProperties(File cfgFile) {
        this.cfgFile = cfgFile;
        props = new Properties();
    }

    public void load() throws Exception {
        props.load(new FileReader(cfgFile));
    }

    public String getString(String key) {
        return props.getProperty(key);
    }

    public String getString(String key, String def) {
        return props.getProperty(key, def);
    }

    public Double getDouble(String key) {
        return Double.parseDouble(getString(key));
    }

    public Double getDouble(String key, double def) {
        return Double.parseDouble(getString(key, "" + def));
    }

    public Integer getInt(String key) {
        return Integer.parseInt(getString(key));
    }

    public Integer getInt(String key, int def) {
        return Integer.parseInt(getString(key, "" + def));
    }

    public Boolean getBoolean(String key) {
        return Boolean.parseBoolean(getString(key));
    }

    public Boolean getBoolean(String key, boolean def) {
        return Boolean.parseBoolean(getString(key, Boolean.toString(def)));
    }
}
