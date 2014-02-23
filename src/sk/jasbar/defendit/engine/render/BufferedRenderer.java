package sk.jasbar.defendit.engine.render;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import sk.jasbar.defendit.render.WorldRenderer;

public class BufferedRenderer {
    private static final int BUFFER_SIZE = WorldRenderer.renderDistance * WorldRenderer.renderDistance * 32 / 2 * 6 * 4;
    private static final int VERTEX_SIZE = 3;
    
    private final FloatBuffer vertices;
    private final int vertexPointer;
    private final int vertexesMode;
    private int count;

    public BufferedRenderer(int vertexesMode) {
        vertices = BufferUtils.createFloatBuffer(BUFFER_SIZE * 3);
        vertexPointer = glGenBuffers();
        this.vertexesMode = vertexesMode;
    }

    public void cleanup() {
        glDeleteBuffers(vertexPointer);
    }

    public void addVertex(float x, float y, float z) {
        vertices.put(x).put(y).put(z);
        ++count;
    }

    public void endEdit() {
        vertices.flip();
        glBindBuffer(GL_ARRAY_BUFFER, vertexPointer);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void reset() {
        vertices.clear();
        count = 0;
    }

    public void draw() {
        glBindBuffer(GL_ARRAY_BUFFER, vertexPointer);
        glVertexPointer(VERTEX_SIZE, GL_FLOAT, 0, 0);

        glEnableClientState(GL_VERTEX_ARRAY);
        glDrawArrays(vertexesMode, 0, count * 3);
        glDisableClientState(GL_VERTEX_ARRAY);
        
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
}
