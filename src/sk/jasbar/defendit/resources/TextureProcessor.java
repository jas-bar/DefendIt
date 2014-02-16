package sk.jasbar.defendit.resources;

import java.io.File;
import java.util.regex.Pattern;

public class TextureProcessor extends PatternBasedProcessor {
    private static final Pattern pattern = Pattern.compile("\\w*.png");

    public TextureProcessor() {
        super(pattern);
    }

    @Override
    public RawResource process(File file, String key) throws Exception {
        TextureResource resource = new TextureResource(file);
        return resource;
    }


}
