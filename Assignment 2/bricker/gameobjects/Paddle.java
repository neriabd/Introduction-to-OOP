package bricker.gameobjects;

import danogl.GameObject;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;
import static bricker.utilities.GameConstants.*;

/**
 * This class represents the paddle object in the game.
 * The paddle is controlled by the user input listener object.
 * The paddle can move left and right in the window and is bounded by the window edges.
 * @see GameObject
 * @author Neriya Ben David
 */
public class Paddle extends GameObject {
    private final UserInputListener inputListener;
    private final Vector2 windowDimensions;

    /**
     * The constructor of the Paddle object.
     * @param topLeftCorner the position of the top left corner of the paddle in the window.
     * @param dimensions the dimensions of the paddle.
     * @param renderer the image object of the paddle.
     * @param inputListener the input listener object for the paddle.
     * @param windowDimensions the dimensions of the window displaying the game.
     */
    public Paddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderer,
                  UserInputListener inputListener, Vector2 windowDimensions) {
        super(topLeftCorner, dimensions, renderer);
        this.inputListener = inputListener;
        this.windowDimensions = windowDimensions;
    }

    /**
     * @param deltaTime The time elapsed, in seconds, since the last frame. Can
     * be used to determine a new position/velocity by multiplying
     * this delta with the velocity/acceleration respectively
     * and adding to the position/velocity:
     * velocity += deltaTime*acceleration
     * pos += deltaTime*velocity
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        updateUserMovement();
        resetToOutOfBounds();
    }

    /**
     * This method resets the paddle position if it is going out of the window bounds.
     */
    private void resetToOutOfBounds() {
        Vector2 topLeft = getTopLeftCorner();

        // check if the paddle is going out of the window on the left side
        if (topLeft.x() < (float) PADDLE_EDGE_DISTANCE) {
            setTopLeftCorner(new Vector2(PADDLE_EDGE_DISTANCE, topLeft.y()));
        }

        // check if the paddle is going out of the window on the right side
        float maxXPositionRight = windowDimensions.x() - getDimensions().x() - PADDLE_EDGE_DISTANCE;
        if (topLeft.x() > maxXPositionRight) {
            setTopLeftCorner(new Vector2(maxXPositionRight, topLeft.y()));
        }
    }

    /**
     * This method updates the movement of the paddle based on the user input.
     */
    private void updateUserMovement() {
        // check if the user is pressing the left or right key and update the paddle movement accordingly
        Vector2 movementDir = Vector2.ZERO;
        if (inputListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            movementDir = movementDir.add(Vector2.LEFT);
        }
        if (inputListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            movementDir = movementDir.add(Vector2.RIGHT);
        }
        setVelocity(movementDir.mult(PADDLE_MOVEMENT_SPEED));
    }
}
