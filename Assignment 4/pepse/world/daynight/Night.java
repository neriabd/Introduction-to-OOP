package pepse.world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;

import static pepse.util.GameConstants.*;

/**
 * Represents the night in the day night cycle and set the day night cycle in the game.
 * @author Neriya Ben David
 */
public class Night {

    /**
     * Create a night game object who is responsible the darkness in the day night cycle
     * @param windowDimensions the dimensions of the window
     * @param cycleLength the length of the day night cycle
     * @return the night game object
     */
    public static GameObject create(Vector2 windowDimensions, float cycleLength) {
        GameObject night = new GameObject(Vector2.ZERO, windowDimensions,
                new RectangleRenderable(BASIC_NIGHT_COLOR));
        night.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        night.setTag(TAG_NIGHT);

        initializeDayNightCycle(cycleLength, night);
        return night;
    }


    /*
     * Initializes the day night cycle in the game.
     */
    private static void initializeDayNightCycle(float cycleLength, GameObject night) {
        new Transition<>(night, night.renderer()::setOpaqueness,
                DAY_OPACITY, MIDNIGHT_OPACITY, Transition.CUBIC_INTERPOLATOR_FLOAT,
                cycleLength / HALF_DAY_FACTOR,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH, null);
    }
}
