package sk.jasbar.defendit.resources;

import java.io.File;
import java.util.HashMap;
import java.util.regex.Pattern;

public class ShaderProgramProcessor extends PatternBasedProcessor {
    private final static Pattern pattern = Pattern.compile("\\w*\\.(vert|frag)");
    private final HashMap<String, File> shaders = new HashMap<String, File>();

    public ShaderProgramProcessor() {
        super(pattern);
    }

    @Override
    public RawResource process(File file, String key) throws Exception {
        if(shaders.containsKey(key)){
            File file2 = shaders.get(key);
            shaders.remove(key);
            ShaderProgramResource resource = new ShaderProgramResource(file,file2);
            System.out.println("Constructed shader: "+key);
            return resource;
        }else
            shaders.put(key , file);
        return null;
        
    }


}
