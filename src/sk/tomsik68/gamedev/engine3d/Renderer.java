package sk.tomsik68.gamedev.engine3d;

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

	public static void renderBackFace(float x, float y, float z, float w,
			float h, float d) {
		glBegin(GL_QUADS);
		{
			glNormal3d(0.f, 0.f, -1.f);//TODO: normaly
			glVertex3d(x, y + h, z);
			glVertex3d(x + w, y + h, z);
			glVertex3d(x + w, y, z);
			glVertex3d(x, y, z);
		}
		glEnd();
	}

	public static void renderFrontFace(float x, float y, float z, float w,
			float h, float d) {
		glBegin(GL_QUADS);
		{
			glNormal3d(0.f, 0.f, 1.f);
			glVertex3d(x, y, z + d);
			glVertex3d(x + w, y, z + d);
			glVertex3d(x + w, y + h, z + d);
			glVertex3d(x, y + h, z + d);
		}
		glEnd();
	}

	public static void renderRightFace(float x, float y, float z, float w,
			float h, float d) {
		glBegin(GL_QUADS);
		{
			glNormal3d(1.f, 0.f, 0.f);
			glVertex3d(x + w, y + h, z);
			glVertex3d(x + w, y + h, z + d);
			glVertex3d(x + w, y, z + d);
			glVertex3d(x + w, y, z);
		}
		glEnd();
	}

	public static void renderLeftFace(float x, float y, float z, float w,
			float h, float d) {
		glBegin(GL_QUADS);
		{
			glNormal3d(-1.f, 0.f, 0.f);
			glVertex3d(x, y, z);
			glVertex3d(x, y, z + d);
			glVertex3d(x, y + h, z + d);
			glVertex3d(x, y + h, z);
		}
		glEnd();
	}

	public static void renderUpFace(float x, float y, float z, float w,
			float h, float d) {
		glBegin(GL_QUADS);
		{
			glNormal3d(0.f, 1.f, 0.f);
			glVertex3d(x, y + h, z);
			glVertex3d(x, y + h, z + d);
			glVertex3d(x + w, y + h, z + d);
			glVertex3d(x + w, y + h, z);
		}
		glEnd();
	}

	public static void renderDownFace(float x, float y, float z, float w,
			float h, float d) {
		glBegin(GL_QUADS);
		{
			glNormal3d(0.f, -1.f, 0.f);
			glVertex3d(x + w, y, z);
			glVertex3d(x + w, y, z + d);
			glVertex3d(x, y, z + d);
			glVertex3d(x, y, z);
		}
		glEnd();
	}

	public static void renderBox(float x, float y, float z, float w, float h,
			float d) {
		w /= 2;
		h /= 2;
		d /= 2;

		glBegin(GL_QUADS);
		{
			// front face
			glVertex3d(x, y, z);
			glVertex3d(x + w, y, z);
			glVertex3d(x + w, y + h, z);
			glVertex3d(x, y + h, z);
			// glNormal3d(0.f, 0.f, -1.f);
		}
		glEnd();
		// glVertex3d(x, y, z);
		glBegin(GL_QUADS);
		{
			// right face
			glVertex3d(x + w, y, z);
			glVertex3d(x + w, y, z + d);
			glVertex3d(x + w, y + h, z + d);
			glVertex3d(x + w, y + h, z);
			// glNormal3d(-1.f, 0.f, 0.f);
			// glVertex3d(x + w, y, z);
		}
		glEnd();
		glBegin(GL_QUADS);
		{

			// down face
			glVertex3d(x, y + h, z);
			glVertex3d(x, y + h, z + d);
			glVertex3d(x + w, y + h, z + d);
			glVertex3d(x + w, y + h, z);
			// glVertex3d(x, y + h, z);
		}
		glEnd();
		glBegin(GL_QUADS);
		{
			// back face
			glVertex3d(x, y, z + d);
			glVertex3d(x + w, y, z + d);
			glVertex3d(x + w, y + h, z + d);
			glVertex3d(x, y + h, z + d);
			// glVertex3d(x, y, z + d);
		}
		glEnd();
		glBegin(GL_QUADS);
		{
			// left face
			glVertex3d(x, y, z);
			glVertex3d(x, y, z + d);
			glVertex3d(x, y + h, z + d);
			glVertex3d(x, y + h, z);
			// glVertex3d(x, y, z);
		}
		glEnd();
		glBegin(GL_QUADS);
		{

			// up face
			glVertex3d(x, y, z);
			glVertex3d(x, y, z + d);
			glVertex3d(x + w, y, z + d);
			glVertex3d(x + w, y, z);
			// glVertex3d(x, y, z);
		}
		glEnd();

	}

	public static void renderTexturedBox(float x, float y, float z, float w,
			float h, float d, Texture... textures) {
		int tex = 0;
		glBegin(GL_QUADS);
		{
			textures[(tex++ % textures.length) % 6].bind();
			// front face
			glTexCoord2f(0, 0);
			glVertex3d(x, y, z);
			glTexCoord2f(1, 0);
			glVertex3d(x + w, y, z);
			glTexCoord2f(1, 1);
			glVertex3d(x + w, y + h, z);
			glTexCoord2f(0, 1);
			glVertex3d(x, y + h, z);
			// glVertex3d(x, y, z);

			textures[tex++ % 6].bind();
			// right face
			glTexCoord2f(0, 0);
			glVertex3d(x + w, y, z);
			glTexCoord2f(1, 0);
			glVertex3d(x + w, y, z + d);
			glTexCoord2f(1, 1);
			glVertex3d(x + w, y + h, z + d);
			glTexCoord2f(0, 1);
			glVertex3d(x + w, y + h, z);
			// glVertex3d(x + w, y, z);

			textures[tex++ % 6].bind();
			// down face
			glTexCoord2f(0, 0);
			glVertex3d(x, y + h, z);
			glTexCoord2f(1, 0);
			glVertex3d(x, y + h, z + d);
			glTexCoord2f(1, 1);
			glVertex3d(x + w, y + h, z + d);
			glTexCoord2f(0, 1);
			glVertex3d(x, y + h, z + d);
			// glVertex3d(x, y + h, z);

			textures[tex++ % 6].bind();
			// back face
			glTexCoord2f(0, 0);
			glVertex3d(x, y, z + d);
			glTexCoord2f(1, 0);
			glVertex3d(x + w, y, z + d);
			glTexCoord2f(1, 1);
			glVertex3d(x + w, y + h, z + d);
			glTexCoord2f(0, 1);
			glVertex3d(x, y + h, z + d);
			// glVertex3d(x, y, z + d);

			textures[tex++ % 6].bind();
			// left face
			glTexCoord2f(0, 0);
			glVertex3d(x, y, z);
			glTexCoord2f(1, 0);
			glVertex3d(x, y, z + d);
			glTexCoord2f(1, 1);
			glVertex3d(x, y + h, z + d);
			glTexCoord2f(0, 1);
			glVertex3d(x, y + h, z);
			// glVertex3d(x, y, z);

			textures[tex++ % 6].bind();
			// up face
			glTexCoord2f(0, 0);
			glVertex3d(x, y, z);
			glTexCoord2f(1, 0);
			glVertex3d(x, y, z + d);
			glTexCoord2f(1, 1);
			glVertex3d(x + w, y, z + d);
			glTexCoord2f(0, 1);
			glVertex3d(x, y, z + d);
			// glVertex3d(x, y, z);
		}
		glEnd();
	}

	public static void rotate(int i, double angle) {
		GL11.glRotated(angle, i & ROTATE_X, i & ROTATE_Y, i & ROTATE_Z);
	}
}
