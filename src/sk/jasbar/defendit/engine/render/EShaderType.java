package sk.jasbar.defendit.engine.render;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBVertexShader;

public enum EShaderType {
    FRAGMENT(ARBFragmentShader.GL_FRAGMENT_SHADER_ARB), VERTEX(ARBVertexShader.GL_VERTEX_SHADER_ARB);
    private final int glType;

    private EShaderType(int glType) {
        this.glType = glType;
    }

    public int toGLType() {
        return glType;
    }
}
