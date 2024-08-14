package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import static bricker.utilities.GameConstants.*;

/**
 * This class represents the heart object which is a graphical representation for the life on the game
 * display.
 * @see GameObject
 * @see Heart
 * @author Neriya Ben David
 */
public class MovingHeart extends Heart{

    // Fields
    private final Counter livesCounter;
    private boolean hasCollided = false;

    /**
     * The constructor of the MovingHeart object.
     * @param topLeftCorner the position of the top left corner of the heart in the window.
     * @param dimensions the dimensions of the heart.
     * @param renderable the image object of the heart.
     * @param livesCounter the counter of the current number of life the user has.
     */
    public MovingHeart(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                       Counter livesCounter) {
        super(topLeftCorner, dimensions, renderable);
        this.livesCounter = livesCounter;
    }


    /**
     * This method returns true if the moving heart object is allowed to collide with the object.
     * @param other the object that the ball collided with.
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        return other.getTag().equals(MAIN_PADDLE_TAG);
    }

    /**
     * This method is called when the ball collides with the moving heart object.
     * @param other the object that the ball collided with.
     * @param collision the collision parameters and attributes.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        livesCounter.increment();
        hasCollided = true;
    }

    /**
     * This method returns true if the moving heart object has collided with the object.
     * @return true if the moving heart object has collided with the object.
     */
    public boolean hasCollided(){
        return hasCollided;
    }
}
