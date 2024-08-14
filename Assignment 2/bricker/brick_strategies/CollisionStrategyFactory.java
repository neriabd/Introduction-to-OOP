package bricker.brick_strategies;

import bricker.gameobjects.Ball;
import danogl.GameManager;
import danogl.collisions.GameObjectCollection;
import danogl.gui.Sound;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.util.Random;

import static bricker.utilities.GameConstants.*;

/**
 * This class is responsible for creating the collision strategies for the game and returning
 * according to random selection.
 * @see CollisionStrategy
 * @author Neriya Ben David
 */
public class CollisionStrategyFactory {

    // Constants for the indexes of the strategies
    private static final int INDEX_EXTRA_PUCKS_STRATEGY = 0;
    private static final int INDEX_EXTRA_PADDLE_STRATEGY = 1;
    private static final int INDEX_CAMERA_CHANGE_STRATEGY = 2;
    private static final int INDEX_LIFE_RECOVERY_STRATEGY = 3;
    private static final int INDEX_DOUBLE_BEHAVIOR_STRATEGY = 4;
    private static final int INDEX_BASIC_COLLISION_STRATEGY = 5;

    // Random Field
    private final Random rand = new Random();

    // Renderers Fields
    private final Renderable puckRenderer;
    private final Renderable extraPaddleRenderer;
    private final Renderable HeartRenderer;

    // Dimensions Fields
    private final Vector2 windowDimensions;


    // Sound Fields
    private final Sound collisionSound;

    // Game Objects Fields
    private final Ball ball;
    private final GameObjectCollection gameObjects;
    private final UserInputListener inputListener;
    private final WindowController windowController;
    private final GameManager gameManager;

    // Counters Fields
    private final Counter cameraChangeCollisionCounter;
    private final Counter lifeCounter;


    /**
     * Constructor of the CollisionStrategyFactory object.
     * @param puckRenderer the renderer of the puck object.
     * @param extraPaddleRenderer the renderer of the extra paddle object.
     * @param HeartRenderer the renderer of the heart object.
     * @param windowDimensions the dimensions of the window.
     * @param collisionSound the sound of the collision.
     * @param ball the ball object.
     * @param gameObjects the game objects collection.
     * @param inputListener the object to listen to user input.
     * @param windowController the window controller object.
     * @param gameManager the game manager object.
     * @param cameraChangeCollisionCounter the counter of the camera change number of collisions.
     * @param lifeCounter the counter of the life in the game.
     */
    public CollisionStrategyFactory(Renderable puckRenderer, Renderable extraPaddleRenderer,
                                    Renderable HeartRenderer, Vector2 windowDimensions,
                                    Sound collisionSound, Ball ball, GameObjectCollection gameObjects,
                                    UserInputListener inputListener, WindowController windowController,
                                    GameManager gameManager, Counter cameraChangeCollisionCounter,
                                    Counter lifeCounter) {

        this.puckRenderer = puckRenderer;
        this.extraPaddleRenderer = extraPaddleRenderer;
        this.HeartRenderer = HeartRenderer;

        this.windowDimensions = windowDimensions;

        this.collisionSound = collisionSound;

        this.ball = ball;
        this.gameObjects = gameObjects;
        this.inputListener = inputListener;
        this.windowController = windowController;
        this.gameManager = gameManager;

        this.cameraChangeCollisionCounter = cameraChangeCollisionCounter;
        this.lifeCounter = lifeCounter;
    }

    /**
     * This method returns a random collision strategy.
     * if max strategies is x > 5, the method will return one of the following strategies with the
     * following probabilities:
     * 1. ExtraPucksStrategy - 1 / x
     * 2. ExtraPaddleStrategy - 1 / x
     * 3. CameraChangeStrategy - 1 / x
     * 4. LifeRecoveryStrategy - 1 / x
     * 5. DoubleBehaviorStrategy - 1 / x
     * 6. BasicCollisionStrategy - (x - 5) / x
     * @param maxStrategies the number of strategies to choose from.
     * @return a random collision strategy.
     */
    public CollisionStrategy getRandomCollisionStrategy(int maxStrategies) {
        int randomStrategyIndex = rand.nextInt(maxStrategies);

        switch (randomStrategyIndex) {
            case INDEX_EXTRA_PUCKS_STRATEGY:
                return getExtraPucksStrategy();
            case INDEX_EXTRA_PADDLE_STRATEGY:
                return getExtraPaddleStrategy();
            case INDEX_CAMERA_CHANGE_STRATEGY:
                return getCameraChangeStrategy();
            case INDEX_LIFE_RECOVERY_STRATEGY:
                return getLifeRecoveryStrategy();
            case INDEX_DOUBLE_BEHAVIOR_STRATEGY:
                return getDoubleBehaviorStrategy();
        }
        return getBasicCollisionStrategy();
    }

    /*
     * This method returns the basic collision strategy.
     * @return the basic collision strategy.
     */
    private CollisionStrategy getBasicCollisionStrategy() {
        return new BasicCollisionStrategy(gameObjects, INDEX_BASIC_COLLISION_STRATEGY);
    }

    /*
     * This method returns the extra pucks strategy.
     * @return the extra pucks strategy.
     */
    private CollisionStrategy getExtraPucksStrategy() {
        return new ExtraPucksStrategy(gameObjects, puckRenderer, collisionSound, INDEX_EXTRA_PUCKS_STRATEGY);
    }

    /*
     * This method returns the extra paddle strategy.
     * @return the extra paddle strategy.
     */
    private CollisionStrategy getExtraPaddleStrategy() {
        return new ExtraPaddleStrategy(gameObjects, extraPaddleRenderer, inputListener, windowDimensions,
                INDEX_EXTRA_PADDLE_STRATEGY);
    }

    /*
     * This method returns the camera change strategy.
     * @return the camera change strategy.
     */
    private CollisionStrategy getCameraChangeStrategy() {
        return new CameraChangeStrategy(gameObjects, gameManager, windowController,
                ball, cameraChangeCollisionCounter, INDEX_CAMERA_CHANGE_STRATEGY);
    }

    /*
     * This method returns the life recovery strategy.
     * @return the life recovery strategy.
     */
    private CollisionStrategy getLifeRecoveryStrategy() {
        Vector2 heartDimensions = new Vector2(HEART_WIDTH, HEART_HEIGHT);
        return new ExtraLifeStrategy(gameObjects, HeartRenderer,
                heartDimensions, lifeCounter, INDEX_LIFE_RECOVERY_STRATEGY);
    }

    /*
     * This method returns the double behavior strategy.
     * @return the double behavior strategy.
     */
    private CollisionStrategy getDoubleBehaviorStrategy() {
        return new DoubleCollisionStrategy(gameObjects, this, INDEX_DOUBLE_BEHAVIOR_STRATEGY);
    }
}