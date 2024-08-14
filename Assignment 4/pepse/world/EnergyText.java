package pepse.world;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Vector2;

import static pepse.util.GameConstants.*;

import java.awt.*;
import java.util.function.Supplier;

/**
 * Represents the energy text that is displayed on the screen.
 * @see GameObject
 * @author Neriya Ben David
 */
public class EnergyText extends GameObject {

    // Fields
    private final Supplier<Float> energySupplier;
    private final TextRenderable textRenderable;

    /**
     * Constructor to creates a new EnergyText object.
     * @param topLeftCorner the top left corner position of the energy text.
     * @param dimensions the dimensions of the energy text.
     * @param energySupplier the supplier of the energy.
     */
    public EnergyText(Vector2 topLeftCorner, Vector2 dimensions, Supplier<Float> energySupplier) {
        super(topLeftCorner, dimensions, null);
        this.energySupplier = energySupplier;
        int energy = (int) Math.floor(energySupplier.get());
        textRenderable = new TextRenderable(energy + PERCENT_SIGN);
        renderer().setRenderable(textRenderable);
        setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        setTag(TAG_ENERGY);
    }

    /**
     * Updates the energy text based on the energy level and changes the color of the text accordingly.
     * @param deltaTime the time passed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        int energy = (int) Math.floor(energySupplier.get());
        textRenderable.setString(energy + PERCENT_SIGN);
        textRenderable.setColor(determineColor(energy));
    }

    /*
     * Determines the color of the text based on the energy level.
     */
    private Color determineColor(int energy) {
        if (energy <= energyThresholds[FIRST_COLOR_INDEX]) {
            return TEXT_COLORS[FIRST_COLOR_INDEX]; // Red
        } else if (energy <= energyThresholds[SECOND_COLOR_INDEX]) {
            return TEXT_COLORS[SECOND_COLOR_INDEX]; // Orange
        } else if (energy <= energyThresholds[THIRD_COLOR_INDEX]) {
            return TEXT_COLORS[THIRD_COLOR_INDEX]; // Yellow
        } else {
            return TEXT_COLORS[FOURTH_COLOR_INDEX]; // Green
        }
    }
}
