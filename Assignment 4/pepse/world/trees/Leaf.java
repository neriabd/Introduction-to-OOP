package pepse.world.trees;


import danogl.GameObject;
import danogl.components.ScheduledTask;
import danogl.components.Transition;
import danogl.util.Vector2;
import danogl.gui.rendering.RectangleRenderable;
import pepse.util.ColorSupplier;
import pepse.util.Observer;

import java.util.Random;

import static pepse.util.GameConstants.*;

/**
 * This class is responsible for the leaf game objects in the game.
 * @author Neriya Ben David
 * @see GameObject
 * @see Observer
 */
public class Leaf extends GameObject implements Observer {

    // Fields
    private static final Random random = new Random(SEED);

    /**
     * Creates a new Leaf object.
     * @param topLeftCorner the top left corner position of the leaf.
     * @param dimensions the dimensions of the leaf.
     */
    public Leaf(Vector2 topLeftCorner, Vector2 dimensions) {
        super(topLeftCorner, dimensions, null);
        RectangleRenderable renderer =
                new RectangleRenderable(ColorSupplier.approximateColor(LEAF_COLOR, COLOR_DELTA_LEAF));
        renderer().setRenderable(renderer);
        setTag(TAG_LEAF);
        scheduledTaskEffect(this::angleTransition);
        scheduledTaskEffect(this::dimensionTransition);
    }

    /**
     * The observer function that is called when the leaf game object is notified by the subject.
     */
    @Override
    public void performAction() {
        rotate90degrees();
    }

    /*
     * Scheduled a task to perform after a random time.
     */
    private void scheduledTaskEffect(Runnable callback) {
        float waitTimeWindEffect = random.nextFloat() * WIND_EFFECT_NORMALIZATION_FACTOR;
        new ScheduledTask(this,
                waitTimeWindEffect, false, callback);
    }

    /*
     * Create a transition that changes the angle of the leaf.
     */
    private void angleTransition() {
        new Transition<>(this, angle -> renderer().setRenderableAngle(angle),
                START_ANGLE_TRANSITION, FINAL_ANGLE_TRANSITION,
                Transition.LINEAR_INTERPOLATOR_FLOAT, WIND_TRANSITION_DURATION,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH, null);
    }

    /*
     * Create a transition that changes the dimensions of the leaf.
     */
    private void dimensionTransition() {
        float leafSize = getDimensions().x();
        new Transition<>(this,
                factor -> setDimensions(Vector2.ONES.mult(factor * leafSize)),
                START_STRETCH_FACTOR, FINAL_STRETCH_FACTOR,
                Transition.LINEAR_INTERPOLATOR_FLOAT, WIND_TRANSITION_DURATION,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH, null);
    }

    /*
     * rotate the leaf 90 degrees.
     */
    private void rotate90degrees() {
        new Transition<>(this,
                angle -> renderer().setRenderableAngle(angle),
                INITIAL_ROTATE_90_DEGREE, LAST_ROTATE_90_ANGLE,
                Transition.LINEAR_INTERPOLATOR_FLOAT, LEAF_ROTATE_DURATION,
                Transition.TransitionType.TRANSITION_ONCE, null);
    }
}
