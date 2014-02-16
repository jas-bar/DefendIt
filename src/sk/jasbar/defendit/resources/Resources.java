package sk.jasbar.defendit.resources;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;

import sk.jasbar.defendit.engine.render.ProgramARB;

public class Resources {
    private HashMap<String, RawResource> resourcesMap = new HashMap<String, RawResource>();
    private static final Resources res = new Resources();
    private ArrayList<ResourceProcessor> processors = new ArrayList<ResourceProcessor>();

    private Resources() {
        processors.add(new ShaderProgramProcessor());
        processors.add(new TextureProcessor());
    }

    public static Resources get() {
        return res;
    }

    public void load() throws Exception {
        File resDir = new File("res");
        scanDir(resDir);
    }

    @SuppressWarnings("unchecked")
    public <T extends RawResource> T getResource(String key) {
        if (!resourcesMap.containsKey(key)) {
            System.out.println("Resource not found: " + key);
        }
        return (T) resourcesMap.get(key);
    }

    private void scanDir(File dir) throws Exception {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                scanDir(file);
            } else {
                addFile(file);
            }
        }
    }

    private void addFile(File file) throws Exception {
        String key = createResourceKeyForFile(file);

        RawResource res = process(file, key);
        if (res != null)
            resourcesMap.put(key, res);
    }

    private RawResource process(File file, String key) throws Exception {
        RawResource result = null;
        for (ResourceProcessor rp : processors) {
            if (rp.processes(file)) {
                result = rp.process(file, key);
            }
        }
        return result;
    }

    private String createResourceKeyForFile(File file) {
        String filePath = file.getPath();
        filePath = filePath.replace("*" + File.separator + "res" + File.separator, "");
        if (filePath.contains(".")) {
            String[] split = filePath.split("\\\\.");
            String ext = split[split.length - 1];
            filePath = filePath.replace(".".concat(ext), "");
        }
        filePath = filePath.replace(File.separatorChar, '.');
        System.out.println(String.format("File '%s' ==> '%s'", file.getPath(), filePath));
        return filePath;
    }

    public static ProgramARB getProgram(String key) {
        try {
            return ((ShaderProgramResource) res.getResource(key)).getShaderProgram();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Texture getTexture(String key) {
        try {
            return ((TextureResource) res.getResource(key)).getTexture();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
