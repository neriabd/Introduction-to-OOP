package bricker.brick_strategies;

import bricker.gameobjects.ExtraPaddle;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import static bricker.utilities.GameConstants.*;

/**
 * This class is responsible for creating the collision strategy that adds
 * an extra paddle to the game.
 * @see CollisionStrategy
 * @author Neriya Ben David
 */
public class ExtraPaddleStrategy extends CollisionStrategy {
    // Constants
    private static final int maxCollisions = 4;
    private static final float HALF_FACTOR = 0.5f;

    // Fields
    private final Renderable renderer;
    private final UserInputListener inputListener;
    private final Vector2 windowDimensions;
    private static boolean isStrategyOff = true;

    /**
     * The constructor of the ExtraPaddleStrategy object.
     * @param gameObjects An object which holds all game objects of the game running.
     * @param renderer the renderer object for the extra paddle.
     * @param inputListener the input listener object for the paddle.
     * @param windowDimensions the dimensions of the window displaying the game.
     * @param identifier the identifier integer of the strategy.
     */
    public ExtraPaddleStrategy(GameObjectCollection gameObjects, Renderable renderer,
                               UserInputListener inputListener, Vector2 windowDimensions,int identifier) {
        super(gameObjects, identifier);
        this.renderer = renderer;
        this.inputListener = inputListener;
        this.windowDimensions = windowDimensions;
    }

    /**
     * This method is called when the ball collides with a brick object.
     * If the object is the expected object and there is not already an extra paddle
     * an extra paddle will be added to the game.
     * @param targetObject the object that was collided with.
     * @param collidingObject the object that collided with the collided object.
     */
    @Override
    public void onCollision(GameObject targetObject, GameObject collidingObject) {
        super.onCollision(targetObject, collidingObject);
        // create a new extra paddle object if the there is no extra paddle in the game
        if (isStrategyOff) {
            isStrategyOff = false;

            Vector2 dimensions = new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT);
            ExtraPaddle extraPaddle = new ExtraPaddle(Vector2.ZERO, dimensions, renderer,
                    inputListener, windowDimensions, maxCollisions);

            extraPaddle.setCenter(windowDimensions.mult(HALF_FACTOR));
            extraPaddle.setTag(EXTRA_PADDLE_TAG);

            this.gameObjects.addGameObject(extraPaddle, Layer.DEFAULT);
        }
    }

    /**
     * This method is called when the extra paddle is removed from the game.
     * It turns off the strategy in order to notify that the extra paddle is removed and
     * can be added again if needed.
     */
    public static void turnOffStrategy() {
        isStrategyOff = true;
    }
}
