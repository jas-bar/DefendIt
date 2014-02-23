package sk.jasbar.defendit.engine.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import sk.jasbar.defendit.render.WorldRenderer;

public class BufferedRenderer {
	private static final int BUFFER_SIZE = 0x2000000;
    //private static final int BUFFER_SIZE = WorldRenderer.renderDistance * WorldRenderer.renderDistance * 32 / 2 * 6 * 4;
    private static final int VERTEX_SIZE = 3;

    private final FloatBuffer vertices;
    private final int vertexPointer;
    private final int vertexesMode;

    private final FloatBuffer normals;
    private final int normalPointer;

    private final FloatBuffer textures;
    private final int texturePointer;

    private int count;

    public BufferedRenderer(int vertexesMode) {
        vertices = BufferUtils.createFloatBuffer(BUFFER_SIZE * 3);
        vertexPointer = glGenBuffers();

        normals = BufferUtils.createFloatBuffer(BUFFER_SIZE * 3);
        normalPointer = glGenBuffers();

        textures = BufferUtils.createFloatBuffer(BUFFER_SIZE * 2);
        texturePointer = glGenBuffers();

        this.vertexesMode = vertexesMode;
    }

    public void cleanup() {
        glDeleteBuffers(vertexPointer);
    }

    public void addVertex(float x, float y, float z) {
        vertices.put(x).put(y).put(z);
        ++count;
    }

    public void addNormal(float x, float y, float z) {
        normals.put(x).put(y).put(z);
    }

    public void addTextureCoord(float x, float y) {
        textures.put(x).put(y);
    }

    public void endEdit() {
        vertices.flip();
        normals.flip();
        textures.flip();
        glBindBuffer(GL_ARRAY_BUFFER, vertexPointer);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, normalPointer);
        glBufferData(GL_ARRAY_BUFFER, normalPointer, GL_STATIC_DRAW);
        
        glBindBuffer(GL_ARRAY_BUFFER, texturePointer);
        glBufferData(GL_ARRAY_BUFFER, texturePointer, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void reset() {
        vertices.clear();
        normals.clear();
        textures.clear();
        count = 0;
    }

    public void draw() {
        glBindBuffer(GL_ARRAY_BUFFER, vertexPointer);
        glVertexPointer(VERTEX_SIZE, GL_FLOAT, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, texturePointer);
        glTexCoordPointer(2, GL_FLOAT, 0, 0);
        
        glBindBuffer(GL_ARRAY_BUFFER, normalPointer);
        glNormalPointer(GL_FLOAT, 0, 0);

        glEnableClientState(GL_VERTEX_ARRAY); 
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);
        glEnableClientState(GL_NORMAL_ARRAY);
        
        glDrawArrays(vertexesMode, 0, count * 3);

        glDisableClientState(GL_NORMAL_ARRAY);
        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
        glDisableClientState(GL_VERTEX_ARRAY);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
}
