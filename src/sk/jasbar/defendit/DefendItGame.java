package sk.jasbar.defendit;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import sk.jasbar.defendit.engine.IRenderable;
import sk.jasbar.defendit.engine.IUpdateable;
import sk.jasbar.defendit.engine.PlayerMovementUpdateable;
import sk.jasbar.defendit.engine.ShowFPS;
import sk.jasbar.defendit.engine.render.AbstractCamera;
import sk.jasbar.defendit.engine.render.LightingRenderable;
import sk.jasbar.defendit.game.Player;
import sk.jasbar.defendit.game.World;
import sk.jasbar.defendit.game.worldgen.WorldGenJas;
import sk.jasbar.defendit.game.worldgen.WorldGenNoise;
import sk.jasbar.defendit.game.worldgen.WorldGeneratorMain;
import sk.jasbar.defendit.render.BlockRenderer;
import sk.jasbar.defendit.render.WorldRenderer;
import sk.jasbar.defendit.resources.Resources;
import sk.tomsik68.gamedev.engine3d.Game;
import sk.tomsik68.gamedev.engine3d.Renderer;

public class DefendItGame extends Game {
    private final DefendItSettings settings;

    private Player player;
    private AbstractCamera cam;
    private World world;
    private ArrayList<IUpdateable> updateables = new ArrayList<IUpdateable>();
    private ArrayList<IRenderable> renderables = new ArrayList<IRenderable>();
    private Resources resources = Resources.get();

    public DefendItGame(DefendItSettings settings) {
        this.settings = settings;
    }

    private void generateWorld() {
        WorldGeneratorMain gen = new WorldGeneratorMain();
        //gen.addModule(new WorldGeneratorDud());
        gen.addModule(new WorldGenJas());
        gen.addModule(new WorldGenNoise());
        gen.generate(world);
    }

    @Override
    public void init() {
        try {
            Renderer.init();
            resources.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        world = new World();
        player = new Player(world);
        cam = new AbstractCamera(player, settings.getDisplayWidth() / settings.getDisplayHeight(), 70, 0.3f, 5000f);
        updateables.add(new PlayerMovementUpdateable(player));
        if (settings.isShowFPS())
            updateables.add(new ShowFPS());
        generateWorld();
        WorldRenderer worldRenderer = new WorldRenderer(world);
        renderables.add(new LightingRenderable());
        renderables.add(worldRenderer);
        cam.init();
        player.setX(20 * BlockRenderer.BLOCK_SIZE);
        player.setY(World.SIZE_Y * BlockRenderer.BLOCK_SIZE);
        player.setZ(20 * BlockRenderer.BLOCK_SIZE);
        Mouse.setGrabbed(true);

    }

    @Override
    public void render() {
        
        cam.useView();
        GL11.glPushMatrix();
        {
            for (IRenderable renderable : renderables) {
                renderable.render(this, player);
            }
        }
        GL11.glPopMatrix();
    }

    @Override
    public void update() {
        for (IUpdateable updateable : updateables) {
            updateable.update(this);
        }
    }
    @Override
    public void exit() {
        Renderer.cleanup();
        super.exit();
    }
}
