package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.util.Random;
import static bricker.utilities.GameConstants.*;


/**
 * This class represents the ball object in the game that can collide with other objects.
 * @see GameObject
 * @author Neriya Ben David.
 */
public class Ball extends GameObject{

    // fields
    private final Counter collisionCounter = new Counter(0);
    private final Sound collisionSound;
    private final Random rand = new Random();

    /**
     * The constructor of the ball object.
     * @param topLeftCorner position of the top left corner of the ball in the window.
     * @param dimensions the dimensions of the ball
     * @param renderer the image object of the ball
     * @param collisionSound the sound file object of the ball's collision.
     */
    public Ball(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderer,
                Sound collisionSound) {
        super(topLeftCorner, dimensions, renderer);
        this.collisionSound = collisionSound;
    }

    /**
     * This method randomizes the velocity of the ball to a random diagonal direction.
     * @param other the object that the ball collided with.
     * @param collision the collision parameters and attributes.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        Vector2 newVel = getVelocity().flipped(collision.getNormal());
        setVelocity(newVel);
        collisionSound.play();
        collisionCounter.increment();
    }

    /**
     * This method initializes the ball's velocity to a random direction.
     */
    public void initializeRandomVelocity() {
        // randomize the velocity of the ball to a random diagonal direction
        float ballVelocityX = BALL_SPEED;
        float ballVelocityY = BALL_SPEED;
        if (rand.nextBoolean()) {
            ballVelocityX = -ballVelocityX;
        }
        if (rand.nextBoolean()) {
            ballVelocityY = -ballVelocityY;
        }

        // set the velocity of the ball to the random direction
        setVelocity(new Vector2(ballVelocityX, ballVelocityY));
    }

    /**
     * This method returns the number of collisions the ball has had.
     * @return the number of collisions the ball has had.
     */
    public int getCollisionCounter() {
        return collisionCounter.value();
    }


}
