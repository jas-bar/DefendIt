package sk.jasbar.defendit.resources;

import java.io.File;

public class RawResource {
    protected final File location;

    public RawResource(File file) {
        this.location = file;
    }

    public File getFile() {
        return location;
    }

}
