package bricker.brick_strategies;

import bricker.gameobjects.Heart;
import bricker.gameobjects.MovingHeart;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import static bricker.utilities.GameConstants.*;

/**
 * This class is responsible for creating the collision strategy that adds
 * an extra life recovery object to the game.
 * adds an extra life recovery object to the game if user has less than the maximum life capacity.
 * @see CollisionStrategy
 * @author Neriya Ben David
 */
public class ExtraLifeStrategy extends CollisionStrategy {

    private static final int Y_AXIS_VELOCITY = 100;

    // Fields
    private final Vector2 dimensions;
    private final Renderable renderable;
    private final Counter lifeCounter;

    /**
     * The constructor of the LifeRecoveryStrategy object.
     * @param gameObjects An object which holds all game objects of the game running.
     * @param renderable the renderer for the heart object.
     * @param dimensions the dimensions of the heart object.
     * @param lifeCounter the counter of the current lives the user has.
     * add the life recovery object.
     * @param identifier the identifier integer of the strategy.
     */
    public ExtraLifeStrategy(GameObjectCollection gameObjects, Renderable renderable,
                             Vector2 dimensions, Counter lifeCounter, int identifier) {
        super(gameObjects, identifier);
        this.dimensions = dimensions;
        this.renderable = renderable;
        this.lifeCounter = lifeCounter;
    }

    /**
     * This method is called when the ball collides with brick object.
     * If the object is the expected object, an extra life recovery object will be added to the game.
     * @param collidedObject the object that was collided with.
     * @param colliderObject the object that collided with the collided object.
     */
    @Override
    public void onCollision(GameObject colliderObject, GameObject collidedObject) {
        super.onCollision(colliderObject, collidedObject);

        // create a new life recovery object
        Heart heart = new MovingHeart(Vector2.ZERO, dimensions, renderable, lifeCounter);
        heart.setCenter(collidedObject.getCenter());
        heart.setTag(MOVING_HEART_TAG);

        // set velocity to be horizontal to the bottom
        heart.setVelocity(new Vector2(0, Y_AXIS_VELOCITY));

        this.gameObjects.addGameObject(heart, Layer.DEFAULT);
    }
}
