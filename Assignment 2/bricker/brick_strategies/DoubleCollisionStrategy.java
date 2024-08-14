package bricker.brick_strategies;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;

/**
 * DoubleCollisionStrategy class is a subclass of CollisionStrategy class.
 * This strategy is used to from two different collision strategies on the collided object up to
 * 3 times.
 * @see CollisionStrategy
 * @author Neriya Ben David
 */
public class DoubleCollisionStrategy extends CollisionStrategy {

    // Constants
    private static final int WITH_DOUBLE = 5;
    private static final int WITHOUT_DOUBLE = 4;
    private static final int MAX_STRATEGIES = 3;

    // Fields
    private final CollisionStrategy[] strategies;
    private final CollisionStrategyFactory randomCollisions;
    private int strategyCount = 0;

    /**
     * The constructor of the DoubleCollisionStrategy object.
     * @param gameObjects An object which holds all game objects of the game running.
     * @param randomCollisions the collision strategy factory object to pick strategy.
     * @param identifier the identifier integer of this strategy.
     */
    public DoubleCollisionStrategy(GameObjectCollection gameObjects,
                                   CollisionStrategyFactory randomCollisions, int identifier) {
        super(gameObjects, identifier);
        this.strategies = new CollisionStrategy[MAX_STRATEGIES];
        this.randomCollisions = randomCollisions;
        initializeStrategies();
    }

    /**
     * This method is called when the ball collides with a brick object.
     * This method calls the onCollision method which implements strategies of all strategies in the
     * strategies array.
     * @param collidedObject the object that was collided with.
     * @param colliderObject the object that collided with the collided object.
     */
    @Override
    public void onCollision(GameObject collidedObject, GameObject colliderObject) {
        super.onCollision(collidedObject, colliderObject);
        for (int i = 0; i < strategyCount; i++) {
            strategies[i].onCollision(collidedObject, colliderObject);
        }
    }

    /*
     * This method initializes the strategies array up to maximum of x strategies based on the
     * value of the MAX_STRATEGIES.
     */
    private void initializeStrategies() {
        CollisionStrategy firstStrategy;
        boolean firstIsDoubleStrategy;

        CollisionStrategy secondStrategy;
        boolean secondIsDoubleStrategy;

        boolean noDoubleStrategyRemains = false;

        // This loop will run until the strategies array is full or there are no more  double strategies
        while (strategyCount < MAX_STRATEGIES - 1) {
            firstStrategy = randomCollisions.getRandomCollisionStrategy(WITH_DOUBLE);
            firstIsDoubleStrategy = firstStrategy.identifier == identifier;

            // If the first strategy has a double collision, the second strategy cannot be a double
            if (firstIsDoubleStrategy) {
                secondStrategy = randomCollisions.getRandomCollisionStrategy(WITHOUT_DOUBLE);
            } else {
                secondStrategy = randomCollisions.getRandomCollisionStrategy(WITH_DOUBLE);
                addStrategy(firstStrategy);
            }

            // if the second strategy is a double
            secondIsDoubleStrategy = secondStrategy.identifier == identifier;
            if (!secondIsDoubleStrategy) {
                addStrategy(secondStrategy);
            }

            // If there are no more double strategies, there is no point in continuing the loop
            if (!firstIsDoubleStrategy & !secondIsDoubleStrategy) {
                noDoubleStrategyRemains = true;
                break;
            }
        }

        // If there are no more double strategies, add a random special strategy to strategies array
        if (!noDoubleStrategyRemains) {
            addStrategy(randomCollisions.getRandomCollisionStrategy(WITHOUT_DOUBLE));
        }
    }

    // This method adds a strategy to the strategies array and updates the strategy count.
    private void addStrategy(CollisionStrategy strategy) {
        strategies[strategyCount++] = strategy;
    }
}

