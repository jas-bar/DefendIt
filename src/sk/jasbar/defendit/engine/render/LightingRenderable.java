package sk.jasbar.defendit.engine.render;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import sk.jasbar.defendit.DefendItGame;
import sk.jasbar.defendit.engine.IRenderable;
import sk.jasbar.defendit.game.World;
import sk.jasbar.defendit.render.BlockRenderer;
import static org.lwjgl.opengl.GL11.*;

public class LightingRenderable implements IRenderable {
	private FloatBuffer lightPos0;

	public LightingRenderable() {
		lightPos0 = BufferUtils.createFloatBuffer(4);
		lightPos0.put(new float[] { 20 * BlockRenderer.BLOCK_SIZE, World.SIZE_Y * BlockRenderer.BLOCK_SIZE, 20 * BlockRenderer.BLOCK_SIZE, 1.f });
		lightPos0.flip();
	}

	@Override
	public void render(DefendItGame defendItGame,
			ICameraCoordsProvider camCoords) {
		lightPos0.clear();
		lightPos0.put(new float[] { -camCoords.getCamX(), -camCoords.getCamY()+100, -camCoords.getCamZ(), 1.f });
		lightPos0.flip();
		
		glLight(GL_LIGHT0, GL_POSITION, lightPos0);

	}

}
