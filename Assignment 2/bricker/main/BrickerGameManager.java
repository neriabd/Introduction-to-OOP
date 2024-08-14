package bricker.main;

import bricker.brick_strategies.*;
import bricker.gameobjects.*;
import bricker.utilities.BoundedCounter;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.*;
import danogl.gui.rendering.Renderable;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import java.awt.event.KeyEvent;

import static bricker.utilities.GameConstants.*;

/**
 * The BrickerGameManager class the  bricker.main class of the Bricker game who manages
 * the game and its logic.
 * @see GameManager
 * @author Neriya Ben David
 */
public class BrickerGameManager extends GameManager {

    // fields for the game
    private final int numberOfBricksInRow;
    private final int numberOfBricksInColumn;
    private ImageReader imageReader;
    private SoundReader soundReader;
    private UserInputListener inputListener;
    private WindowController windowController;
    private Vector2 windowDimensions;
    private Ball ball;
    private final Counter lifesCounter;
    private final Counter cameraChangeCollisionCounter = new BoundedCounter(0,
            maxCameraChangeCollisions);
    private CollisionStrategyFactory randomCollisions;

    /**
     * Constructor for the BrickerGameManager class that initializes the game with the
     * given parameters:
     * @param windowTitle the title of the window
     * @param windowDimensions the dimensions of the window
     * @param numberOfBricksInRow the number of bricks in a row
     * @param numberOfBricksInColumn the number of bricks in a column
     */
    public BrickerGameManager(String windowTitle, Vector2 windowDimensions, int numberOfBricksInRow,
                              int numberOfBricksInColumn) {
        super(windowTitle, windowDimensions);
        this.numberOfBricksInRow = numberOfBricksInRow;
        this.numberOfBricksInColumn = numberOfBricksInColumn;
        this.lifesCounter = new BoundedCounter(INITIAL_LIFE_VALUE, MAX_LIVES);
    }

    /**
     * create the game objects for the game and initializes them.
     * initializes the game with the given parameters:
     * @param imageReader image reader to read images from.
     * @param soundReader sound reader to read sounds from.
     * @param inputListener input listener to listen to user input
     * @param windowController the window controller to control the window of the game
     */
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
                               UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        this.imageReader = imageReader;
        this.soundReader = soundReader;
        this.inputListener = inputListener;
        this.windowController = windowController;
        this.windowDimensions = windowController.getWindowDimensions();

        // creating ball
        createBall();

        // create paddle
        createPaddle();

        // creating walls
        createWalls();

        // creating background
        createBackground();

        // create strategy factory
        createStrategyFactory();

        // creating bricks
        createBricks();

        // create lifeCounter graphics
        createGraphicLifeCounter();

        // create lifeCounter numeric text
        createNumericLifeCounter();
    }

    /*
     * Creates a new CollisionStrategyFactory object and initializes it with the game objects
     * and the game manager.
     */
    private void createStrategyFactory() {
        Renderable puckRenderer = imageReader.readImage(PUCK_IMAGE_PATH, true);
        Sound collisionSound = soundReader.readSound(BLOP_SOUND_PATH);

        Renderable PaddleRenderer = imageReader.readImage(PADDLE_IMAGE_PATH, true);

        Renderable heartRenderer = imageReader.readImage(HEART_IMAGE_PATH, true);

        this.randomCollisions = new CollisionStrategyFactory(puckRenderer, PaddleRenderer, heartRenderer,
                windowDimensions, collisionSound, ball, gameObjects(), inputListener,
                windowController, this, cameraChangeCollisionCounter, lifesCounter);
    }

    /*
     * Creates a new NumericLifeCounter object in the bot right corner of the screen.
     */
    private void createNumericLifeCounter() {
        TextRenderable textRenderer = new TextRenderable(Integer.toString(this.lifesCounter.value()));
        Vector2 topLeftCounter = new Vector2(windowDimensions.x() - LIFE_NUMERIC_RIGHT_MARGIN,
                windowDimensions.y() - LIFE_NUMERIC_BOTTOM_MARGIN);
        Vector2 dimensions = new Vector2(LIFE_NUMERIC_TEXT_WIDTH, LIFE_NUMERIC_TEXT_HEIGHT);

        NumericLife textLiveCounter = new NumericLife(topLeftCounter, dimensions, textRenderer,
                lifesCounter);

        gameObjects().addGameObject(textLiveCounter, Layer.UI);
    }

    /*
     * Creates a new GraphicLifeCounter object and initializes it with the game objects.
     * The GraphicLifeCounter object is responsible for displaying graphic representation for the lives
     * of the player.
     */
    private void createGraphicLifeCounter() {
        Renderable heartImage = imageReader.readImage(HEART_IMAGE_PATH, true);
        GraphicLife graphicLifeManager = new GraphicLife(heartImage, windowDimensions,
                lifesCounter, this.gameObjects());
        gameObjects().addGameObject(graphicLifeManager);
    }

    /*
     * Creates the bricks for the game and initializes them.
     */
    private void createBricks() {
        Renderable brickRenderer = imageReader.readImage(BRICK_IMAGE_PATH, false);

        // Calculate the valid width for bricks considering the margins and spacing
        float bricksTotalWidth = windowDimensions.x() - DOUBLE_MARGIN_BRICK * BRICK_SIDES_MARGIN
                - (numberOfBricksInRow - 1) * BRICK_PADDING;
        float brickWidth = bricksTotalWidth / numberOfBricksInRow;

        for (int i = 0; i < numberOfBricksInRow; i++) {
            for (int j = 0; j < numberOfBricksInColumn; j++) {

                // Get a random collision strategy from the factory
                CollisionStrategy strategy = randomCollisions.getRandomCollisionStrategy(maxStrategies);

                // Calculate the position of the brick
                float brickXPosition = i * (brickWidth + BRICK_PADDING) + BRICK_SIDES_MARGIN;
                float brickYPosition = j * (BRICK_HEIGHT + BRICK_PADDING) + BRICK_TOP_MARGIN;

                // Create a new Brick object
                GameObject brick = new Brick(new Vector2(brickXPosition, brickYPosition),
                        new Vector2(brickWidth, BRICK_HEIGHT),
                        brickRenderer, strategy);

                brick.setTag(BRICK_TAG);
                // Add the brick to the game objects
                gameObjects().addGameObject(brick, Layer.STATIC_OBJECTS);
            }
        }
    }

    /*
     * Creates the bricker.main green ball for the game and initializes it.
     */
    private void createBall() {
        Renderable ballImage = imageReader.readImage(BALL_IMAGE_PATH, true);
        Sound collisionSound = soundReader.readSound(BLOP_SOUND_PATH);
        ball = new Ball(Vector2.ZERO, new Vector2(BALL_WIDTH, BALL_HEIGHT),
                ballImage, collisionSound);
        ball.setCenter(windowDimensions.mult(MIDDLE_SCREEN_FACTOR));
        ball.initializeRandomVelocity();
        ball.setTag(MAIN_BALL_TAG);
        gameObjects().addGameObject(ball);
    }

    /*
     * Creates the bricker.main paddle for the game and initializes it.
     */
    private void createPaddle() {
        Renderable paddleImage = imageReader.readImage(PADDLE_IMAGE_PATH, true);
        Vector2 dimensions = new Vector2(PADDLE_WIDTH, PADDLE_HEIGHT);
        Paddle paddle = new Paddle(Vector2.ZERO, dimensions, paddleImage,
                inputListener, windowDimensions);
        paddle.setCenter(new Vector2((int) (windowDimensions.x() * PADDLE_CENTER_FACTOR),
                windowDimensions.y() - PADDLE_BOTTOM_MARGIN));
        paddle.setTag(MAIN_PADDLE_TAG);
        gameObjects().addGameObject(paddle, Layer.DEFAULT);
    }

    /*
     * Creates the background for the game display.
     */
    private void createBackground() {
        Renderable backgroundImage = imageReader.readImage(BACKGROUND_IMAGE_PATH, false);
        GameObject background = new GameObject(Vector2.ZERO, windowDimensions, backgroundImage);
        background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        gameObjects().addGameObject(background, Layer.BACKGROUND);
    }

    /*
     * Creates the top, left and right walls for the game.
     */
    private void createWalls() {
        // Create the left, right and top walls
        GameObject leftWall = new GameObject(
                Vector2.ZERO,
                new Vector2(PADDLE_EDGE_DISTANCE, windowDimensions.y()), null);

        GameObject rightWall = new GameObject(
                new Vector2(windowDimensions.x() - PADDLE_EDGE_DISTANCE, 0),
                new Vector2(PADDLE_EDGE_DISTANCE, windowDimensions.y()), null);

        GameObject topWall = new GameObject(
                Vector2.ZERO,
                new Vector2(windowDimensions.x(), PADDLE_EDGE_DISTANCE), null);

        gameObjects().addGameObject(leftWall, Layer.STATIC_OBJECTS);
        gameObjects().addGameObject(rightWall, Layer.STATIC_OBJECTS);
        gameObjects().addGameObject(topWall, Layer.STATIC_OBJECTS);
    }

    /**
     * Updates the game logic, through tracking the game objects and checking for game end.
     * @param deltaTime the time passed since the last update
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        // if pucks are out of bounds remove them
        checkPucksOutOfBounds();

        // check if the paddle has collided enough in order to remove it
        checkPaddleMaxCollisions();

        // check if the camera has been hit enough times to revert it
        checkCameraHits();

        // check if the moving heart is still in the game and remove it if it is not
        checkMovingHeartStillInGame();

        // check if the game has ended (player has lost all lives or won)
        checkForGameEnd();
    }

    /*
     * Checks if the moving heart is still in the game and removes it if it is not.
     */
    private void checkMovingHeartStillInGame() {
        for (GameObject gameObject : gameObjects()) {
            if (gameObject.getTag().equals(MOVING_HEART_TAG)) {
                if (objectOutOfBounds(gameObject) || ((MovingHeart) gameObject).hasCollided()) {
                    gameObjects().removeGameObject(gameObject, Layer.DEFAULT);
                }
            }
        }
    }

    /*
     * Checks if the camera has been hit enough times to remove it.
     */
    private void checkCameraHits() {
        if (camera() != null) {
            int currentCollisions = this.ball.getCollisionCounter();
            if (cameraChangeCollisionCounter.value() + maxCameraChangeCollisions <= currentCollisions) {
                this.setCamera(null);
            }
        }
    }

    /*
     * Checks if any pucks are out of bounds and removes them from the game.
     */
    private void checkPucksOutOfBounds() {
        for (GameObject gameObject : gameObjects()) {
            if (gameObject.getTag().equals(PUCK_TAG) && objectOutOfBounds(gameObject)) {
                gameObjects().removeGameObject(gameObject);
            }
        }
    }

    /*
     * Checks if the paddle has collided with the maximum number of pucks and removes the extra paddle
     * if it has.
     */
    private void checkPaddleMaxCollisions() {
        for (GameObject gameObject : gameObjects()) {
            if (gameObject.getTag().equals(EXTRA_PADDLE_TAG)) {
                ExtraPaddle extraPaddle = (ExtraPaddle) gameObject;
                if (extraPaddle.isPaddleMaxCollision()) {
                    gameObjects().removeGameObject(gameObject);
                    ExtraPaddleStrategy.turnOffStrategy();
                }
            }
        }
    }

    /*
     * Checks if the given object is out of bounds.
     * @param object the object to check
     * @return true if the object is out of bounds, false otherwise
     */
    private boolean objectOutOfBounds(GameObject object) {
        return object.getTopLeftCorner().y() > windowDimensions.y();
    }

    /*
     * Checks if the game has ended and handles the end game scenario.
     */
    private void checkForGameEnd() {
        String prompt = determineGameOutcome();
        if (!prompt.isEmpty()) {
            handleEndGameScenario(prompt);
        }
    }

    /*
     * Handles the end game scenario by displaying a dialog to the user.
     * @param prompt the message to display to the user
     */
    private void handleEndGameScenario(String prompt) {
        boolean playAgain = windowController.openYesNoDialog(prompt);
        if (playAgain) {
            resetLifeCounter();
            windowController.resetGame();
        } else {
            windowController.closeWindow();
        }
    }

    /*
     * Determines the outcome of the game based on if no bricks are left / the user has lost all lives /
     * the user has pressed W.
     * @return the message to display to the user
     */
    private String determineGameOutcome() {
        float ballHeight = ball.getCenter().y();
        if (ballHeight > windowDimensions.y()) {
            if (isGameOverAfterLifeLoss()) {
                return LOSE_MESSAGE;
            }
        }

        if (Brick.getBrickCounter() <= 0 || inputListener.isKeyPressed(KeyEvent.VK_W)) {
            return WIN_MESSAGE;
        }

        return EMPTY_STRING;
    }

    /*
     * Checks if the game is over after the user has lost a life.
     * @return true if the game is over, false otherwise
     */
    private boolean isGameOverAfterLifeLoss() {
        reduceLife();
        if (lifesCounter.value() > 0) {
            ball.setCenter(windowDimensions.mult(MIDDLE_SCREEN_FACTOR));
            ball.initializeRandomVelocity();
            return false;
        }
        return true;
    }

    /*
     * Resets the lives counter to the initial value.
     */
    private void resetLifeCounter() {
        lifesCounter.reset();
        lifesCounter.increaseBy(INITIAL_LIFE_VALUE);
    }

    /*
     * Reduces the lives counter by one.
     */
    private void reduceLife() {
        lifesCounter.decrement();
    }

    /**
     * The bricker.main method of the BrickerGameManager class that creates a new BrickerGameManager object
     * and runs the game.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BrickerGameFactory gameFactory = new BrickerGameFactory();
        BrickerGameManager gameManager = gameFactory.createGame(args);
        gameManager.run();
    }
}