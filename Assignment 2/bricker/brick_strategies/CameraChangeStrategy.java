package bricker.brick_strategies;

import bricker.gameobjects.Ball;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.gui.WindowController;
import danogl.gui.rendering.Camera;
import danogl.util.Counter;
import danogl.util.Vector2;

import static bricker.utilities.GameConstants.*;


/**
 * This class is responsible for changing the camera when the ball collides with a specific object.
 * @see CollisionStrategy
 * @author Neriya Ben David
 */

public class CameraChangeStrategy extends CollisionStrategy {
    // Constants
    private static final float DIMENSION_FACTOR = 1.2f;

    // Fields
    private final GameManager gameManager;
    private final WindowController windowController;
    private final Ball ball;
    private final Counter collisionCounter;

    /**
     * The constructor of the CameraChangeStrategy object.
     * @param gameObjects An object which holds all game objects of the game running.
     * @param gameManager the game manager object.
     * @param windowController the window controller object.
     * @param ball the ball object the camera will follow.
     * @param collisionsCounter the counter of the collisions the ball has had.
     * @param identifier the identifier integer of the strategy.
     */
    public CameraChangeStrategy(GameObjectCollection gameObjects, GameManager gameManager,
                                WindowController windowController, Ball ball,
                                Counter collisionsCounter, int identifier) {
        super(gameObjects, identifier);
        this.gameManager = gameManager;
        this.windowController = windowController;
        this.collisionCounter = collisionsCounter;
        this.ball = ball;

    }

    /**
     * This method is called when the ball collides with an object.
     * If the object is the expected object, the camera will be changed to follow the ball.
     * @param collidedObject the object that was collided with.
     * @param colliderObject the object that collided with the collided object.
     */
    @Override
    public void onCollision(GameObject collidedObject, GameObject colliderObject) {
        super.onCollision(collidedObject, colliderObject);

        // If the camera is already set, don't change it
        if (gameManager.camera() != null) {
            return;
        }

        // If the collider object is not the bricker.main ball, don't change the camera
        if (!(colliderObject.getTag().equals(MAIN_BALL_TAG))) {
            return;
        }

        // Set the camera to follow the ball
        Camera camera = new Camera(ball,
                Vector2.ZERO,
                windowController.getWindowDimensions().mult(DIMENSION_FACTOR),
                windowController.getWindowDimensions());
        gameManager.setCamera(camera);

        // Reset the collision counter and increase it by the ball's collision counter
        collisionCounter.reset();
        collisionCounter.increaseBy(ball.getCollisionCounter());
    }
}
