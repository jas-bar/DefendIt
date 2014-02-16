package sk.jasbar.defendit.engine;

import sk.jasbar.defendit.DefendItGame;
import sk.jasbar.defendit.engine.render.ICameraCoordsProvider;

public interface IRenderable {

    void render(DefendItGame defendItGame, ICameraCoordsProvider camCoords);

}
