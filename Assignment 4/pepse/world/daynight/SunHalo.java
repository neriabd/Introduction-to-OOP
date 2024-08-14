package pepse.world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;

import static pepse.util.GameConstants.*;

/**
 * Represents the sun halo that is hovering around the sun.
 * @author Neriya Ben David
 */
public class SunHalo {

    /**
     * Create a sun halo game object who is responsible for the halo around the sun.
     * @param sun the sun game object
     * @return the sun halo game object
     */
    public static GameObject create(GameObject sun) {
        Vector2 sunHaloDimensions = new Vector2(SUN_HALO_SIZE, SUN_HALO_SIZE);
        Vector2 sunCenter = sun.getCenter();
        Vector2 sunHaloPosition = new Vector2(sunCenter.x() - SUN_HALO_OFFSET,
                sunCenter.y() - SUN_HALO_OFFSET);
        GameObject sunHalo = new GameObject(sunHaloPosition, sunHaloDimensions,
                new OvalRenderable(SUN_HALO_COLOR));
        sunHalo.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sunHalo.setTag(TAG_SUN_HALO);
        sunHalo.addComponent(deltaTime -> sunHalo.setCenter(sun.getCenter()));
        return sunHalo;
    }

}
