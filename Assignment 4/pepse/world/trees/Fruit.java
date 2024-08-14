package pepse.world.trees;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.components.ScheduledTask;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;
import pepse.util.Observer;

import static pepse.util.GameConstants.*;

/**
 * This class is responsible for the fruit game objects in the game.
 * @see GameObject
 * @see Observer
 * @author Neriya Ben David
 */
public class Fruit extends GameObject implements Observer {

    // Fields
    private int currentColorIndex = -1;

    /**
     * Creates a new Fruit object.
     * @param topLeftCorner the top left corner position of the fruit.
     * @param dimensions the dimensions of the fruit.
     */
    public Fruit(Vector2 topLeftCorner, Vector2 dimensions) {
        super(topLeftCorner, dimensions, null);
        changeColor();
        setTag(TAG_FRUIT);
    }

    /**
     * This method is called when a collision occurs with a fruit game object.
     * @param other The GameObject with which a collision occurred.
     * @param collision Information regarding this collision.
     *                  A reasonable elastic behavior can be achieved with:
     *                  setVelocity(getVelocity().flipped(collision.getNormal()));
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (other.getTag().equals(TAG_AVATAR)) {
            hideFruit();
        }
    }

    /*
     * The observer function that is called when the fruit game object is notified by the subject.
     */
    @Override
    public void performAction() {
        changeColor();
    }

    /*
     * Hides the fruit game object.
     */
    private void hideFruit() {
        setDimensions(Vector2.ZERO);
        new ScheduledTask(this, CYCLE_LENGTH, false,
                () -> setDimensions(FRUIT_DIMENSIONS));
    }

    /*
     * Changes the color of the fruit game object.
     */
    private void changeColor() {
        currentColorIndex = (currentColorIndex + 1) % FRUIT_COLORS.size();
        renderer().setRenderable(new OvalRenderable(FRUIT_COLORS.get(currentColorIndex)));
    }
}
