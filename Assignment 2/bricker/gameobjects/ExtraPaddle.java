package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import static bricker.utilities.GameConstants.*;

/**
 * This class represents the extra paddle object in the game that can collide with other objects.
 * @see Paddle
 * @author Neriya Ben David
 */
public class ExtraPaddle extends Paddle{

    // Fields
    private final Counter collisionCount;
    private final int maxCollisions;


    /**
     * The constructor of the ExtraPaddle object.
     * @param topLeftCorner the position of the top left corner of the extra paddle in the window.
     * @param dimensions the dimensions of the extra paddle.
     * @param renderer the image object of the extra paddle.
     * @param inputListener the input listener object for the paddle.
     * @param windowDimensions the dimensions of the window displaying the game.
     * @param maxCollisions the maximum number of collisions the extra paddle can have until it
     * disappears.
     */
    public ExtraPaddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderer,
                       UserInputListener inputListener, Vector2 windowDimensions,
                       int maxCollisions) {
        super(topLeftCorner, dimensions, renderer, inputListener, windowDimensions);
        this.maxCollisions = maxCollisions;
        this.collisionCount = new Counter(0);
    }

    /**
     * This method is called when the ball collides with the extra paddle object.
     * If the object is the expected object, the collision counter will be incremented.
     * @param other the object that the ball collided with.
     * @param collision the collision parameters and attributes.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        collisionCount.increment();
    }

    /**
     * This method returns the collision counter of the extra paddle object.
     * @other the object that the ball collided with.
     * @return true if the extra paddle is allowed to collide with object.
     */
    @Override
    public boolean shouldCollideWith(GameObject other) {
        return other.getTag().equals(MAIN_BALL_TAG) || other.getTag().equals(PUCK_TAG);
    }

    /**
     * This method check if the extra paddle has reached the maximum number of collisions.
     * @return true if the extra paddle has reached the maximum number of collisions else false.
     */
    public boolean isPaddleMaxCollision(){
        return collisionCount.value() == maxCollisions;
    }
}
