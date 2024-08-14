package bricker.brick_strategies;

import bricker.gameobjects.Brick;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;

/**
 * CollisionStrategy class is a superclass for all collision strategies.
 * This class is used to define the basic structure of all collision strategies
 * and remove the collided object from the game.
 * @see BasicCollisionStrategy
 * @see ExtraPaddleStrategy
 * @see ExtraPucksStrategy
 * @see ExtraLifeStrategy
 * @see CameraChangeStrategy
 * @see DoubleCollisionStrategy
 * @author Neriya Ben David
 */
public class CollisionStrategy {

    /**
     * game objects collection object which holds all game objects of the game running.
     */
    protected final GameObjectCollection gameObjects;

    /**
     * The identifier integer of the strategy.
     */
    protected final int identifier;

    /**
     * The constructor of the CollisionStrategy object.
     * @param gameObjects An object which holds all game objects of the game running.
     * @param identifier the identifier integer of the strategy.
     */
    public CollisionStrategy(GameObjectCollection gameObjects, int identifier) {
        this.gameObjects = gameObjects;
        this.identifier = identifier;
    }

    /**
     * This method is called when the ball collides with an object.
     * If the object is a brick, it will be removed from the game.
     * @param collidedObject the object that was collided with.
     * @param colliderObject the object that collided with the collided object.
     */
    public void onCollision(GameObject collidedObject, GameObject colliderObject) {
        // if the game object is in queue for removal, don't remove it twice so the counter
        // won't be decremented twice or more.
        if (this.gameObjects.removeGameObject(collidedObject, Layer.STATIC_OBJECTS)) {
            Brick.decrementBrickCounter();
        }
    }
}
