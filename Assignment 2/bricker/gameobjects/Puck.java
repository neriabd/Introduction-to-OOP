package bricker.gameobjects;

import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.util.Random;

import static bricker.utilities.GameConstants.*;

/**
 * This class represents the puck object in the game.
 * The puck is a ball that moves around the game screen, and can hit other objects in the game.
 * @see Ball
 * @author Neriya Ben David
 */
public class Puck extends Ball {
    private final Random rand = new Random();

    /**
     * The constructor of the puck object.
     * @param topLeftCorner the position of the top left corner of the puck in the window.
     * @param dimensions the dimensions of the puck.
     * @param renderer the image object of the puck.
     * @param collisionSound the sound that is played when the puck collides with another object.
     */
    public Puck(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderer,
                Sound collisionSound) {
        super(topLeftCorner, dimensions, renderer, collisionSound);
    }

    /**
     * This method initializes the puck object with a random velocity.
     */
    @Override
    public void initializeRandomVelocity() {
        // randomize the velocity to a random direction over the unit circle from 0 to PI.
        double angle = rand.nextDouble() * Math.PI;
        double ballVelocityX = PUCK_SPEED * Math.cos(angle);
        double ballVelocityY = PUCK_SPEED * Math.sin(angle);
        setVelocity(new Vector2((float) ballVelocityX, (float) ballVelocityY));
    }
}