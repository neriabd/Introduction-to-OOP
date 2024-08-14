package bricker.brick_strategies;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;

/**
 * BasicCollisionStrategy class is a subclass of CollisionStrategy class.
 * This strategy is only used to remove the collided (brick) object from the game.
 * @see CollisionStrategy
 * @author Neriya Ben David
 */
public class BasicCollisionStrategy extends CollisionStrategy {

    /**
     * The constructor of the BasicCollisionStrategy object.
     * @param gameObject An object which holds all game objects of the game running.
     * @param identifier the identifier integer of the strategy.
     */
    public BasicCollisionStrategy(GameObjectCollection gameObject, int identifier) {
        super(gameObject, identifier);
    }

    /**
     * This method is called when the ball collides with an object.
     * If the object is a brick, it will be removed from the game through
     * the super class method.
     * @param collidedObject the object that was collided with.
     * @param colliderObject the object that collided with the collided object.
     */
    @Override
    public void onCollision(GameObject collidedObject, GameObject colliderObject) {
        super.onCollision(collidedObject, colliderObject);
    }
}
