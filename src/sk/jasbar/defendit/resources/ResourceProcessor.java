package sk.jasbar.defendit.resources;

import java.io.File;

public abstract class ResourceProcessor {
    public abstract boolean processes(File file);

    public abstract RawResource process(File file, String key) throws Exception;
}
