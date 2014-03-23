package sk.jasbar.defendit.engine.render;

import static org.lwjgl.opengl.GL11.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import sk.jasbar.defendit.render.BlockRenderer;
import sk.jasbar.defendit.render.WorldRenderer;

public class Frustum implements ICulling {
	
	float[][] frustum = new float[6][4];
	
	private FloatBuffer proj = ByteBuffer.allocateDirect(16 << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
	private FloatBuffer modl = ByteBuffer.allocateDirect(16 << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
	private float[] clip = new float[16];
	
    @Override
    public boolean blockRenders(int blockX, int blockY, int blockZ) {
    	
    	float x = blockX* BlockRenderer.BLOCK_SIZE;
    	float y = blockY* BlockRenderer.BLOCK_SIZE;
    	float z = blockZ* BlockRenderer.BLOCK_SIZE;
    	
    	for(int i = 0; i < 6; i++){
    		
    		/*if( frustum[i][0] * (x) + frustum[i][1] * (y) + frustum[i][2] * (z) + frustum[i][3] > 0 )
   	         continue;*/
    		
    		if( frustum[i][0] * (x - BlockRenderer.BLOCK_SIZE) + frustum[i][1] * (y - BlockRenderer.BLOCK_SIZE) + frustum[i][2] * (z - BlockRenderer.BLOCK_SIZE) + frustum[i][3] > 0 )
    	         continue;
    	    if( frustum[i][0] * (x + BlockRenderer.BLOCK_SIZE) + frustum[i][1] * (y - BlockRenderer.BLOCK_SIZE) + frustum[i][2] * (z - BlockRenderer.BLOCK_SIZE) + frustum[i][3] > 0 )
    	         continue;
    	    if( frustum[i][0] * (x - BlockRenderer.BLOCK_SIZE) + frustum[i][1] * (y + BlockRenderer.BLOCK_SIZE) + frustum[i][2] * (z - BlockRenderer.BLOCK_SIZE) + frustum[i][3] > 0 )
    	         continue;
    	    if( frustum[i][0] * (x + BlockRenderer.BLOCK_SIZE) + frustum[i][1] * (y + BlockRenderer.BLOCK_SIZE) + frustum[i][2] * (z - BlockRenderer.BLOCK_SIZE) + frustum[i][3] > 0 )
    	         continue;
    	    if( frustum[i][0] * (x - BlockRenderer.BLOCK_SIZE) + frustum[i][1] * (y - BlockRenderer.BLOCK_SIZE) + frustum[i][2] * (z + BlockRenderer.BLOCK_SIZE) + frustum[i][3] > 0 )
    	         continue;
    	    if( frustum[i][0] * (x + BlockRenderer.BLOCK_SIZE) + frustum[i][1] * (y - BlockRenderer.BLOCK_SIZE) + frustum[i][2] * (z + BlockRenderer.BLOCK_SIZE) + frustum[i][3] > 0 )
    	         continue;
    	    if( frustum[i][0] * (x - BlockRenderer.BLOCK_SIZE) + frustum[i][1] * (y + BlockRenderer.BLOCK_SIZE) + frustum[i][2] * (z + BlockRenderer.BLOCK_SIZE) + frustum[i][3] > 0 )
    	         continue;
    	    if( frustum[i][0] * (x + BlockRenderer.BLOCK_SIZE) + frustum[i][1] * (y + BlockRenderer.BLOCK_SIZE) + frustum[i][2] * (z + BlockRenderer.BLOCK_SIZE) + frustum[i][3] > 0 )
    	         continue;
    	    return false;
    	}
    return true;
    }

    @Override
    public void init(WorldRenderer renderer) {
    		
    	proj.clear();
    	modl.clear();
    	
    	glGetFloat(GL_PROJECTION_MATRIX, proj);
    	proj.flip().limit(16);
    	glGetFloat(GL_MODELVIEW_MATRIX, modl);
    	modl.flip().limit(16);
        
    	clip[ 0] = ( modl.get( 0) * proj.get( 0) + modl.get( 1) * proj.get(4) + modl.get( 2) * proj.get( 8) + modl.get( 3) * proj.get(12));
    	clip[ 1] = ( modl.get( 0) * proj.get( 1) + modl.get( 1) * proj.get(5) + modl.get( 2) * proj.get( 9) + modl.get( 3) * proj.get(13));
    	clip[ 2] = ( modl.get( 0) * proj.get( 2) + modl.get( 1) * proj.get(6) + modl.get( 2) * proj.get(10) + modl.get( 3) * proj.get(14));
    	clip[ 3] = ( modl.get( 0) * proj.get( 3) + modl.get( 1) * proj.get(7) + modl.get( 2) * proj.get(11) + modl.get( 3) * proj.get(15));
    	                                                                                                                          
    	clip[ 4] = ( modl.get( 4) * proj.get( 0) + modl.get( 5) * proj.get(4) + modl.get( 6) * proj.get( 8) + modl.get( 7) * proj.get(12));
    	clip[ 5] = ( modl.get( 4) * proj.get( 1) + modl.get( 5) * proj.get(5) + modl.get( 6) * proj.get( 9) + modl.get( 7) * proj.get(13));
    	clip[ 6] = ( modl.get( 4) * proj.get( 2) + modl.get( 5) * proj.get(6) + modl.get( 6) * proj.get(10) + modl.get( 7) * proj.get(14));
    	clip[ 7] = ( modl.get( 4) * proj.get( 3) + modl.get( 5) * proj.get(7) + modl.get( 6) * proj.get(11) + modl.get( 7) * proj.get(15));
    	                                                                                                                          
    	clip[ 8] = ( modl.get( 8) * proj.get( 0) + modl.get( 9) * proj.get(4) + modl.get(10) * proj.get( 8) + modl.get(11) * proj.get(12));
    	clip[ 9] = ( modl.get( 8) * proj.get( 1) + modl.get( 9) * proj.get(5) + modl.get(10) * proj.get( 9) + modl.get(11) * proj.get(13));
    	clip[10] = ( modl.get( 8) * proj.get( 2) + modl.get( 9) * proj.get(6) + modl.get(10) * proj.get(10) + modl.get(11) * proj.get(14));
    	clip[11] = ( modl.get( 8) * proj.get( 3) + modl.get( 9) * proj.get(7) + modl.get(10) * proj.get(11) + modl.get(11) * proj.get(15));
    	                                                                                                                          
    	clip[12] = ( modl.get(12) * proj.get( 0) + modl.get(13) * proj.get(4) + modl.get(14) * proj.get( 8) + modl.get(15) * proj.get(12));
    	clip[13] = ( modl.get(12) * proj.get( 1) + modl.get(13) * proj.get(5) + modl.get(14) * proj.get( 9) + modl.get(15) * proj.get(13));
    	clip[14] = ( modl.get(12) * proj.get( 2) + modl.get(13) * proj.get(6) + modl.get(14) * proj.get(10) + modl.get(15) * proj.get(14));
    	clip[15] = ( modl.get(12) * proj.get( 3) + modl.get(13) * proj.get(7) + modl.get(14) * proj.get(11) + modl.get(15) * proj.get(15));
    	
    	   /* Extract the numbers for the RIGHT plane */
    	   frustum[0][0] = clip[ 3] - clip[ 0];
    	   frustum[0][1] = clip[ 7] - clip[ 4];
    	   frustum[0][2] = clip[11] - clip[ 8];
    	   frustum[0][3] = clip[15] - clip[12];
    	   normalize(0);

    	   /* Extract the numbers for the LEFT plane */
    	   frustum[1][0] = clip[ 3] + clip[ 0];
    	   frustum[1][1] = clip[ 7] + clip[ 4];
    	   frustum[1][2] = clip[11] + clip[ 8];
    	   frustum[1][3] = clip[15] + clip[12];
    	   normalize(1);

    	   /* Extract the BOTTOM plane */
    	   frustum[2][0] = clip[ 3] + clip[ 1];
    	   frustum[2][1] = clip[ 7] + clip[ 5];
    	   frustum[2][2] = clip[11] + clip[ 9];
    	   frustum[2][3] = clip[15] + clip[13];
    	   normalize(2);

    	   /* Extract the TOP plane */
    	   frustum[3][0] = clip[ 3] - clip[ 1];
    	   frustum[3][1] = clip[ 7] - clip[ 5];
    	   frustum[3][2] = clip[11] - clip[ 9];
    	   frustum[3][3] = clip[15] - clip[13];;
    	   normalize(3);

    	   /* Extract the FAR plane */
    	   frustum[4][0] = clip[ 3] - clip[ 2];
    	   frustum[4][1] = clip[ 7] - clip[ 6];
    	   frustum[4][2] = clip[11] - clip[10];
    	   frustum[4][3] = clip[15] - clip[14];
    	   normalize(4);

    	   /* Extract the NEAR plane */
    	   frustum[5][0] = clip[ 3] - clip[ 2];
    	   frustum[5][1] = clip[ 7] - clip[ 6];
    	   frustum[5][2] = clip[11] - clip[10];
    	   frustum[5][3] = clip[15] - clip[14];
    	   normalize(5);
    }
    
    private void normalize(int plane){
       float t =  (float) Math.sqrt( frustum[plane][0] * frustum[plane][0] + frustum[plane][1] * frustum[plane][1] + frustum[plane][2] * frustum[plane][2]);
 	   frustum[plane][0] /= t;
 	   frustum[plane][1] /= t;
 	   frustum[plane][2] /= t;
 	   frustum[plane][3] /= t;
    	
    }

}
