package sk.jasbar.defendit.engine.render;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class Renderer {
    public static final byte ROTATE_X = 1;
    public static final byte ROTATE_Y = 2;
    public static final byte ROTATE_Z = 4;

    public static void setColor(float r, float g, float b) {
        glColor3f(r, g, b);
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

}
