package bricker.gameobjects;

import danogl.GameObject;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.awt.*;

import static bricker.utilities.GameConstants.*;

/**
 * This class represents the numeric life counter object which is a graphical
 * representation for the life on the game in text form.
 * @author Neriya Ben David
 * @see GameObject
 */
public class NumericLife extends GameObject {

    // Constants
    private static final int MAX_COLOR_VALUE = 2;

    // Fields
    private final Counter livesCounter;
    private final TextRenderable renderer;
    private final Color[] colors;

    /**
     * The constructor of the NumericLifeCounter object.
     * @param topLeftCorner the position of the top left corner of the heart in the window.
     * @param dimensions the dimensions of the heart.
     * @param renderer the image object of the heart.
     * @param currentLives the counter of the current number of life the user has.
     */
    public NumericLife(Vector2 topLeftCorner, Vector2 dimensions, TextRenderable renderer,
                       Counter currentLives) {
        super(topLeftCorner, dimensions, renderer);
        this.livesCounter = currentLives;
        this.renderer = renderer;
        this.colors = new Color[]{Color.RED, Color.YELLOW, Color.GREEN};
        updateNumericTextLife();
    }

    /**
     * This method is called when the ball collides with the moving heart object.
     * @param deltaTime the time passed since the last frame.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        updateNumericTextLife();
    }

    /**
     * This method updates the text of the life counter object.
     */
    private void updateNumericTextLife() {
        renderer.setString(Integer.toString(livesCounter.value()));
        int currentColorIndex = Math.min(livesCounter.value() - 1, MAX_COLOR_VALUE);
        renderer.setColor(colors[currentColorIndex]);
        int currentLive = Math.min(livesCounter.value(), MAX_LIVES);
        renderer.setString(Integer.toString(currentLive));
    }


}
