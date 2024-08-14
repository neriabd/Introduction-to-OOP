package pepse.world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;

import static pepse.util.GameConstants.*;

/**
 * Represents the sun in the day night cycle.
 * @author Neriya Ben David
 */
public class Sun {

    /**
     * Create a sun game object in the game
     * @param windowDimensions the dimensions of the window of the game
     * @param cycleLength the length of the day night cycle
     * @return the sun game object
     */
    public static GameObject create(Vector2 windowDimensions, float cycleLength) {
        float groundHeightAtX0 = windowDimensions.y() * GROUND_HEIGHT_RATIO_INIT;
        float middleSkyX = windowDimensions.x() / HALF_FACTOR;
        GameObject sun = new GameObject(calcSunPosition(middleSkyX, groundHeightAtX0),
                new Vector2(SUN_SIZE, SUN_SIZE), new OvalRenderable(SUN_COLOR));
        sun.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sun.setTag(TAG_SUN);

        Vector2 cycleCenter = new Vector2(middleSkyX, groundHeightAtX0);
        Vector2 initialSunCenter = sun.getCenter();
        initializeSunCycle(cycleLength, sun, initialSunCenter, cycleCenter);
        return sun;
    }


    /*
     * Initializes the sun cycle rotation in the game.
     */
    private static void initializeSunCycle(float cycleLength, GameObject sun, Vector2 initialSunCenter,
                                           Vector2 cycleCenter) {
        new Transition<>(sun, angle -> sun.setCenter(initialSunCenter.subtract(cycleCenter)
                .rotated(angle).add(cycleCenter)),
                INITIAL_ANGLE,
                FINAL_ANGLE,
                Transition.LINEAR_INTERPOLATOR_FLOAT,
                cycleLength,
                Transition.TransitionType.TRANSITION_LOOP,
                null);
    }

    /*
     * Calculates the initial position of the sun in the game.
     */
    private static Vector2 calcSunPosition(float middleSkyX, float groundHeightAtX0) {
        float middleSkyY = groundHeightAtX0 / HALF_FACTOR;
        float sunPositionX = middleSkyX - SUN_OFFSET;
        float sunPositionY = middleSkyY - SUN_OFFSET;
        return new Vector2(sunPositionX, sunPositionY);
    }
}
