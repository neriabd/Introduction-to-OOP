package bricker.gameobjects;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import bricker.brick_strategies.CollisionStrategy;

/**
 * This class represents a brick object in the game.
 * @see GameObject game
 * @see CollisionStrategy
 * @author Neriya Ben David
 */
public class Brick extends GameObject {
    private final CollisionStrategy strategy;
    private static final Counter brickCounter = new Counter(0);

    /**
     * The constructor of the brick object.
     * @param topLeftCorner The position of the top left corner of the brick in the window.
     * @param dimensions The dimensions of the brick.
     * @param renderer The image object of the brick.
     * @param strategy The collision strategy of the brick.
     */
    public Brick(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderer,
                 CollisionStrategy strategy) {
        super(topLeftCorner, dimensions, renderer);
        this.strategy = strategy;
        Brick.brickCounter.increment();
    }

    /**
     * This method is called when the ball collides with a brick object.
     * @param other The object that the ball collided with.
     * @param collision The collision parameters and attributes.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        strategy.onCollision(this, other);
    }

    /**
     * This method returns the number of bricks in the game.
     * @return The number of bricks in the game.
     */
    public static int getBrickCounter() {
        return brickCounter.value();
    }

    /**
     * This method decrements the number of bricks in the game by 1.
     */
    public static void decrementBrickCounter() {
        brickCounter.decrement();
    }
}
