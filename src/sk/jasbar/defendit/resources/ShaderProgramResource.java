package sk.jasbar.defendit.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import sk.jasbar.defendit.engine.render.EShaderType;
import sk.jasbar.defendit.engine.render.ProgramARB;
import sk.jasbar.defendit.engine.render.Shader;

public class ShaderProgramResource extends RawResource {
    private final File file1, file2;

    public ShaderProgramResource(File file, File file2) {
        super(null);
        this.file1 = file;
        this.file2 = file2;
    }

    public ProgramARB getShaderProgram() throws Exception {
        File vertFile = null, fragFile = null;
        if (file1.getName().endsWith(".vert")) {
            vertFile = file1;
        }
        if (file2.getName().endsWith(".vert")) {
            vertFile = file2;
        }
        if (file1.getName().endsWith(".frag")) {
            fragFile = file1;
        }
        if (file2.getName().endsWith(".frag")) {
            fragFile = file2;
        }
        if (vertFile == null || fragFile == null)
            throw new FileNotFoundException("Shader needs both .frag and .vert files!");
        Shader vert, frag;
        vert = new Shader(EShaderType.VERTEX);
        frag = new Shader(EShaderType.FRAGMENT);
        vert.create(readFile(vertFile));
        frag.create(readFile(fragFile));
        ProgramARB program = new ProgramARB(vert, frag);
        return program;
    }

    private String readFile(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            sb = sb.append(line).append('\n');
        }
        return sb.toString();
    }
}
