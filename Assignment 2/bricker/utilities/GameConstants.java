package bricker.utilities;

/**
 * This class contains all the constants that are shared throughout the game.
 */
public final class GameConstants {

    /**
     * Constructor for the GameConstants class, should not be used.
     */
    private GameConstants() {
    }

    // BALL CONSTANTS //

    /**
     * The width of the ball object.
     */
    public static final int BALL_WIDTH = 20;

    /**
     * The height of the ball object.
     */
    public static final int BALL_HEIGHT = 20;

    /**
     * The speed of the ball object.
     */
    public static final int BALL_SPEED = 250;


    // BRICK CONSTANTS //

    /**
     * The height of the brick object.
     */
    public static final int BRICK_HEIGHT = 15;

    /**
     * The padding between bricks objects.
     */
    public static final int BRICK_PADDING = 2;

    /**
     * margin of the bricks from the top of the window.
     */
    public static final int BRICK_TOP_MARGIN = 10;

    /**
     * margin of the bricks from the sides of the window.
     */
    public static final int BRICK_SIDES_MARGIN = 30;

    /**
     * doubles the margin of the bricks.
     */
    public static final int DOUBLE_MARGIN_BRICK = 2;


    // PUCK CONSTANTS //

    /**
     * the width of the puck ball object.
     */
    public static final float PUCK_WIDTH = (float) (((float) BALL_WIDTH) * 0.75);

    /**
     * the height of the puck ball object.
     */
    public static final float PUCK_HEIGHT = (float) (((float) BALL_HEIGHT) * 0.75);

    /**
     * the speed of the puck ball object.
     */
    public static final int PUCK_SPEED = 300;

    /**
     * the width of the heart object.
     */
    public static final int HEART_WIDTH = 40;

    /**
     * the height of the heart object.
     */
    public static final int HEART_HEIGHT = 30;


    // PADDLE CONSTANTS //

    /**
     * the width of the paddle object.
     */
    public static final int PADDLE_WIDTH = 100;

    /**
     * the height of the paddle object.
     */
    public static final int PADDLE_HEIGHT = 15;

    /**
     * margin from the bottom of the window for the paddle object.
     */
    public static final int PADDLE_BOTTOM_MARGIN = 40;

    /**
     * center factor for the paddle object according to the window size.
     */
    public static final double PADDLE_CENTER_FACTOR = 0.5;

    /**
     * the distance from the edge of the paddle to the edge of the window.
     */
    public static final int PADDLE_EDGE_DISTANCE = 5;

    /**
     * the speed of the paddle object.
     */
    public static final float PADDLE_MOVEMENT_SPEED = 400;


    // NUMERIC LIFE CONSTANTS //

    /**
     * the width of the numeric text life object.
     */
    public static final int LIFE_NUMERIC_TEXT_WIDTH = 20;

    /**
     * the height of the numeric text life object.
     */
    public static final int LIFE_NUMERIC_TEXT_HEIGHT = 20;

    /**
     * the right margin of the numeric text life object.
     */
    public static final int LIFE_NUMERIC_RIGHT_MARGIN = 30;

    /**
     * the bottom margin of the numeric text life object.
     */
    public static final int LIFE_NUMERIC_BOTTOM_MARGIN = 40;


    // Heart constants

    /**
     * The left margin of the heart object.
     */
    public static final int HEART_LEFT_MARGIN = 20;

    /**
     * The bottom margin of the heart object.
     */
    public static final int HEART_BOTTOM_MARGIN = 50;

    /**
     * The padding between heart objects.
     */
    public static final int HEART_PADDING = 10;


    // LIFE VALUES CONSTANTS //

    /**
     * The initial value of the life a player is starting with.
     */
    public static final int INITIAL_LIFE_VALUE = 3;

    /**
     * The maximum number of lives the player can have.
     */
    public static final int MAX_LIVES = 4;


    // CAMERA CONSTANTS //

    /**
     * maximum number of collisions the camera until it resets into the original position.
     */
    public static final int maxCameraChangeCollisions = 4;

    // STRATEGY CONSTANTS //

    /**
     * maximum number of strategies the game can have.
     * 1 - 5 are specific strategies, 6 - 10 is the basic strategy.
     */
    public static final int maxStrategies = 10;


    // WINDOW CONSTANTS //

    /**
     * middle screen factor.
     */
    public static final float MIDDLE_SCREEN_FACTOR = 0.5f;

    // PATHS CONSTANTS //

    /**
     * the path to the brick image.
     */
    public static final String BRICK_IMAGE_PATH = "assets/brick.png";

    /**
     * the path to the ball image.
     */
    public static final String BALL_IMAGE_PATH = "assets/ball.png";

    /**
     * the path to the blop sound.
     */
    public static final String BLOP_SOUND_PATH = "assets/blop.wav";

    /**
     * the path to the paddle image.
     */
    public static final String PADDLE_IMAGE_PATH = "assets/paddle.png";

    /**
     * the path to the background image.
     */
    public static final String BACKGROUND_IMAGE_PATH = "assets/DARK_BG2_small.jpeg";

    /**
     * the path to the game over image.
     */
    public static final String LOSE_MESSAGE = "You lose! Play again?";

    /**
     * the path to the heart image.
     */
    public static final String HEART_IMAGE_PATH = "assets/heart.png";

    /**
     * the path to the puck image.
     */
    public static final String PUCK_IMAGE_PATH = "assets/mockBall.png";


    // MESSAGES PROMPTS CONSTANTS //

    /**
     * the message prompt for the game over.
     */
    public static final String EMPTY_STRING = "";

    /**
     * win message prompt.
     */
    public static final String WIN_MESSAGE = "You win! Play again?";


    // TAGS CONSTANTS //

    /**
     * the tag for the bricker.main ball object.
     */
    public static final String MAIN_BALL_TAG = "mainBall";

    /**
     * the tag for the bricker.main paddle object.
     */
    public static final String MAIN_PADDLE_TAG = "mainPaddle";

    /**
     * the tag for the puck object.
     */
    public static final String PUCK_TAG = "puck";

    /**
     * the tag for the extra paddle object.
     */
    public static final String EXTRA_PADDLE_TAG = "extraPaddle";

    /**
     * the tag for the moving heart object.
     */
    public static final String MOVING_HEART_TAG = "movingHeart";

    /**
     * the tag for the brick object.
     */
    public static final String BRICK_TAG = "brick";
}
