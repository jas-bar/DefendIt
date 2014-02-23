package sk.tomsik68.gamedev.engine3d;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import sk.jasbar.defendit.game.World;
import sk.jasbar.defendit.render.WorldRenderer;

public class Renderer {
    public static final byte ROTATE_X = 1;
    public static final byte ROTATE_Y = 2;
    public static final byte ROTATE_Z = 4;
    public static final int BUFFER_SIZE = 0x200000;
    private static FloatBuffer vertices;

    public static void setColor(float r, float g, float b) {
        glColor3f(r, g, b);
    }

    public static void renderBackFace(Texture texture, float x, float y, float z, float w, float h, float d) {
        glNormal3d(0.f, 0.f, -1.f);
        glTexCoord2f(0, 1);
        
        glVertex3d(x, y + h, z);
        
        glTexCoord2f(1, 1);
        glVertex3d(x + w, y + h, z);
        glTexCoord2f(1, 0);
        glVertex3d(x + w, y, z);
        glTexCoord2f(0, 0);
        glVertex3d(x, y, z);

    }

    public static void renderFrontFace(Texture texture, float x, float y, float z, float w, float h, float d) {

        glNormal3d(0.f, 0.f, 1.f);
        glTexCoord2f(0, 0);
        glVertex3d(x, y, z + d);
        glTexCoord2f(1, 0);
        glVertex3d(x + w, y, z + d);
        glTexCoord2f(1, 1);
        glVertex3d(x + w, y + h, z + d);
        glTexCoord2f(0, 1);
        glVertex3d(x, y + h, z + d);

    }

    public static void renderRightFace(Texture texture, float x, float y, float z, float w, float h, float d) {

        glNormal3d(1.f, 0.f, 0.f);
        glTexCoord2f(0, 1);
        glVertex3d(x + w, y + h, z);
        glTexCoord2f(1, 1);
        glVertex3d(x + w, y + h, z + d);
        glTexCoord2f(1, 0);
        glVertex3d(x + w, y, z + d);
        glTexCoord2f(0, 0);
        glVertex3d(x + w, y, z);

    }

    public static void renderLeftFace(Texture texture, float x, float y, float z, float w, float h, float d) {

        glNormal3d(-1.f, 0.f, 0.f);
        glTexCoord2f(0, 0);
        glVertex3d(x, y, z);
        glTexCoord2f(0, 1);
        glVertex3d(x, y, z + d);
        glTexCoord2f(1, 1);
        glVertex3d(x, y + h, z + d);
        glTexCoord2f(1, 0);
        glVertex3d(x, y + h, z);

    }

    public static void renderUpFace(Texture texture, float x, float y, float z, float w, float h, float d) {

        glNormal3d(0.f, 1.f, 0.f);
        glTexCoord2f(0, 0);
        glVertex3d(x, y + h, z);
        glTexCoord2f(0, 1);
        glVertex3d(x, y + h, z + d);
        glTexCoord2f(1, 1);
        glVertex3d(x + w, y + h, z + d);
        glTexCoord2f(1, 0);
        glVertex3d(x + w, y + h, z);

    }

    public static void renderDownFace(Texture texture, float x, float y, float z, float w, float h, float d) {
        glNormal3d(0.f, -1.f, 0.f);
        glTexCoord2f(1, 0);
        glVertex3d(x + w, y, z);
        glTexCoord2f(1, 1);
        glVertex3d(x + w, y, z + d);
        glTexCoord2f(0, 1);
        glVertex3d(x, y, z + d);
        glTexCoord2f(0, 0);
        glVertex3d(x, y, z);
    }

    public static void drawTexturedRectangle(Texture texture, float x, float y, float w, float h) {
        drawColorizedTexturedRectangle(texture, x, y, w, h, 1, 1, 1);
    }

    public static void drawColorizedTexturedRectangle(Texture texture, float x, float y, float w, float h, float r, float g, float b) {
        texture.bind();
        glColor3f(r, g, b);
        glBegin(GL_QUADS);
        {
            glTexCoord2f(1, 0);
            glVertex2f(x + w, y);

            glTexCoord2f(0, 0);
            glVertex2f(x, y);

            glTexCoord2f(0, 1);
            glVertex2f(x, y + h);

            glTexCoord2f(1, 1);
            glVertex2f(x + w, y + h);
        }
        glEnd();
    }

    public static void rotate(int i, double angle) {
        GL11.glRotated(angle, i & ROTATE_X, i & ROTATE_Y, i & ROTATE_Z);
    }

    public static void init() throws Exception {
        vertices = BufferUtils.createFloatBuffer(BUFFER_SIZE);

    }
}
