package pepse.world.trees;

import danogl.GameObject;
import danogl.components.GameObjectPhysics;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import pepse.util.ColorSupplier;
import pepse.util.Observer;

import static pepse.util.GameConstants.*;

/**
 * This class is responsible for the trunk game objects in the game.
 * @author Neriya Ben David
 * @see GameObject
 * @see Observer
 * @author Neriya Ben David
 */
public class Trunk extends GameObject implements Observer {

    /**
     * Creates a new Trunk object.
     * @param topLeftCorner the top left corner position of the trunk.
     * @param dimensions the dimensions of the trunk.
     */
    public Trunk(Vector2 topLeftCorner, Vector2 dimensions) {
        super(topLeftCorner, dimensions, null);
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
        setTag(TAG_TRUNK);
        changeColor();
    }

    /**
     * The observer function that is called when the trunk game object is notified by the subject.
     */
    @Override
    public void performAction() {
        changeColor();
    }

    /*
     * This method changes the color of the trunk to a new color variant of the original color.
     */
    private void changeColor() {
        renderer().setRenderable(
                new RectangleRenderable(ColorSupplier.approximateColor(TRUNK_COLOR, COLOR_DELTA_TRUNK)));
    }


}
