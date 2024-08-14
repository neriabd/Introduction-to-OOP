package pepse.world;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;

import static pepse.util.GameConstants.*;

/**
 * This class is responsible for the sky game object in the game.
 * @author Neriya Ben David
 */
public class Sky {

    /**
     * Creates a new sky game object.
     * @param windowsDimensions the dimensions of the game window.
     * @return the sky game object.
     */
    public static GameObject create(Vector2 windowsDimensions) {
        GameObject sky = new GameObject(Vector2.ZERO, windowsDimensions,
                new RectangleRenderable(BASIC_SKY_COLOR));
        sky.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sky.setTag(TAG_SKY);
        return sky;
    }
}
