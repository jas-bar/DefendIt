package sk.jasbar.defendit.engine.render;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.glu.GLU;

public class AbstractCamera {
    private float aspectRatio, fov, nearClip, farClip;
    private final ICameraCoordsProvider coords;

    public AbstractCamera(ICameraCoordsProvider c, float aspect, float fOv, float clipNear, float clipFar) {
        coords = c;
        aspectRatio = aspect;
        fov = fOv;
        nearClip = clipNear;
        farClip = clipFar;
    }

    public void init() {
        glMatrixMode(GL_PROJECTION);
        initLight();
        glLoadIdentity();
        GLU.gluPerspective(fov, aspectRatio, nearClip, farClip);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glTexParameterf( GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glEnable(GL_DEPTH_TEST);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_BLEND);
    }
    
    private void initLight(){
    	
    	FloatBuffer ambientLightColor = BufferUtils.createFloatBuffer(4);
    	ambientLightColor.put(new float[]{0.f,0.f,0.f,1.f});
    	ambientLightColor.flip();
    	
    	FloatBuffer specularLight = BufferUtils.createFloatBuffer(4);
    	specularLight.put(new float[]{1.f, 1.f, 1.f, 1.f});
    	specularLight.flip();
    	
    	FloatBuffer lightColor0 = BufferUtils.createFloatBuffer(4);
    	lightColor0.put(new float[]{1.f, 1.f, 1.f, 1.f});
    	lightColor0.flip();
    	
    	glShadeModel(GL_SMOOTH);
    	glEnable(GL_NORMALIZE);
    	glLightModeli(GL_LIGHT_MODEL_AMBIENT, GL_TRUE);
    	glEnable(GL_LIGHTING);
    	glEnable(GL_LIGHT0);
    	glFrontFace(GL_CCW) ;
    	glEnable(GL_CULL_FACE);
    	glCullFace(GL_BACK);
    	glEnable(GL_COLOR_MATERIAL);
    	//glLightModelfv(GL_LIGHT_MODEL_AMBIENT, ambientLightColor);
    	glLight(GL_LIGHT0, GL_AMBIENT, ambientLightColor);
    	glLight(GL_LIGHT0, GL_SPECULAR, specularLight);
    	glLight(GL_LIGHT0, GL_DIFFUSE, lightColor0);
    	//glLight(GL_LIGHT0, GL_POSITION, lightPos0);
    	glColorMaterial(GL_FRONT, GL_DIFFUSE);
    	
    	
    }

    public void useView() {
        glRotatef(coords.getCamRX(), 1, 0, 0);
        glRotatef(coords.getCamRY(), 0, 1, 0);
        glRotatef(coords.getCamRZ(), 0, 0, 1);
        glTranslatef(coords.getCamX(), coords.getCamY(), coords.getCamZ());
    }
}
