package sk.jasbar.defendit.engine.render;

import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;

public class ProgramARB {
    private Shader vertexShader, fragmentShader;
    private int programID = 0;

    public ProgramARB(Shader vertShader, Shader fragShader) {
        vertexShader = vertShader;
        fragmentShader = fragShader;
    }

    public void create() {
        if (vertexShader == null)
            throw new NullPointerException("Vertex shader is null!");
        if (fragmentShader == null)
            throw new NullPointerException("Fragment shader is null!");
        try {
            programID = ARBShaderObjects.glCreateProgramObjectARB();
            if (programID == 0) {
                throw new IllegalStateException("OpenGL Failure: Failed to create program object.");
            }

            ARBShaderObjects.glAttachObjectARB(programID, vertexShader.getID());
            ARBShaderObjects.glAttachObjectARB(programID, fragmentShader.getID());

            ARBShaderObjects.glLinkProgramARB(programID);
            if (ARBShaderObjects.glGetObjectParameteriARB(programID, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) {
                throw new IllegalStateException("OpenGL Failure: Program linking failed!");
            }
            ARBShaderObjects.glValidateProgramARB(programID);
            if (ARBShaderObjects.glGetObjectParameteriARB(programID, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) {
                throw new IllegalStateException("OpenGL Failure: Program is invalid!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Shader getVertexShader() {
        return vertexShader;
    }

    public Shader getFragmentShader() {
        return fragmentShader;
    }

    public void use() {
        if (programID == 0) {
            throw new IllegalStateException("ProgramARB needs to be created first!");
        }
        ARBShaderObjects.glUseProgramObjectARB(programID);
    }

    public void release() {
        ARBShaderObjects.glUseProgramObjectARB(0);
    }
}
