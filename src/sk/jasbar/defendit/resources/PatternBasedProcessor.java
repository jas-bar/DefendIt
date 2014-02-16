package sk.jasbar.defendit.resources;

import java.io.File;
import java.util.regex.Pattern;

public abstract class PatternBasedProcessor extends ResourceProcessor {
    private final Pattern pattern;

    public PatternBasedProcessor(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean processes(File file) {
        return pattern.matcher(file.getName()).matches();
    }

}
