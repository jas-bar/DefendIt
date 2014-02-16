package sk.jasbar.defendit.engine.render;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.ARBShaderObjects;

public class Shader {
    private int id, type;

    public Shader(EShaderType type) {
        this.type = type.toGLType();
    }

    public void create(String shaderString) throws Exception {
        if (shaderString == null || shaderString.length() == 0)
            throw new IllegalArgumentException("Got an empty or null string!");
        try {
            id = ARBShaderObjects.glCreateShaderObjectARB(type);
            if (id == 0)
                throw new IllegalStateException("OpenGL Failure: Failed to create shader object.");

            ARBShaderObjects.glShaderSourceARB(id, shaderString);
            ARBShaderObjects.glCompileShaderARB(id);
            if (ARBShaderObjects.glGetObjectParameteriARB(id, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL_FALSE) {
                throw new IllegalStateException("OpenGL Failure: Failed to compile shader.");
            }
        } catch (Exception e) {
            ARBShaderObjects.glDeleteObjectARB(id);
            throw e;
        }
    }

    public int getID() {
        return id;
    }

}
