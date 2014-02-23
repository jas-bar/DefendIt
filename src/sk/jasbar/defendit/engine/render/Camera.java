package sk.jasbar.defendit.engine.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

import org.lwjgl.util.glu.GLU;

public class Camera {
    private float x, y, z;
    private float rx, ry, rz;
    private float aspectRatio, fov, nearClip, farClip;

    public Camera(float aspect, float fOv, float clipNear, float clipFar) {
        setX(0);
        setY(0);
        setZ(0);
        setRX(0);
        setRY(0);
        setRZ(0);
        aspectRatio = aspect;
        fov = fOv;
        nearClip = clipNear;
        farClip = clipFar;
    }

    public void init() {
    	float[] ambientLightColor = new float[] { 0.2f, 0.2f, 0.2f, 1.0f};
    	float[] specularLight = new float[] {1.f, 1.f, 1.f, 1.f};
    	float[] lightColor0 = new float[] { 0.5f, 0.5f, 0.5f, 1.f };
    	float[] lightPos0 = new float[] { 0.f, 0.f, 0.f, 1.0f };
    	
    	FloatBuffer ambient = FloatBuffer.wrap(ambientLightColor);
    	
        glMatrixMode(GL_PROJECTION);
        
    	//glShadeModel(GL_SMOOTH);
    	//glEnable(GL_NORMALIZE);
    	//glLightModeli(GL_LIGHT_MODEL_AMBIENT, GL_TRUE);
    	//glEnable(GL_LIGHTING);
    	//glEnable(GL_LIGHT0);
    	//glEnable(GL_CULL_FACE);
    	//glCullFace(GL_BACK);
    	//glEnable(GL_COLOR_MATERIAL);
    	//glLightModelfv(GL_LIGHT_MODEL_AMBIENT, ambientLightColor);
    	//glLight(GL_LIGHT0, GL_AMBIENT, ambient);
    	//glLight(GL_LIGHT0, GL_SPECULAR, specularLight);
    	//glLight(GL_LIGHT0, GL_DIFFUSE, lightColor0);
    	//glLight(GL_LIGHT0, GL_POSITION, lightPos0);
    	//glColorMaterial(GL_FRONT, GL_DIFFUSE);
        
        glLoadIdentity();
        GLU.gluPerspective(fov, aspectRatio, nearClip, farClip);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_DEPTH_TEST);
    }

    public void useView() {
        glRotatef(rx, 1, 0, 0);
        glRotatef(ry, 0, 1, 0);
        glRotatef(rz, 0, 0, 1);
        glTranslatef(x, y, z);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRx() {
        return rx;
    }

    public void setRX(float rx) {
        this.rx = rx;
    }

    public float getRy() {
        return ry;
    }

    public void setRY(float ry) {
        this.ry = ry;
    }

    public float getRz() {
        return rz;
    }

    public void setRZ(float rz) {
        this.rz = rz;
    }

    public void addX(float add) {
        x += add;
    }

    public void addY(float add) {
        y += add;
    }

    public void addZ(float add) {
        z += add;
    }
}
