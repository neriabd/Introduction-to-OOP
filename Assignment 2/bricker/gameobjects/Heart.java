package bricker.gameobjects;

import danogl.GameObject;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * This class represents the heart object which is a graphical representation for the life on the game
 * display.
 * @see GameObject
 * @author Neriya Ben David
 */
public class Heart extends GameObject {

    /**
     * The constructor of the Heart object.
     * @param topLeftCorner the position of the top left corner of the heart in the window.
     * @param dimensions the dimensions of the heart.
     * @param renderer the image object of the heart.
     */
    public Heart(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderer) {
        super(topLeftCorner, dimensions, renderer);
    }
}
