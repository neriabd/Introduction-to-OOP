package pepse.world;

import danogl.GameObject;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import pepse.util.ColorSupplier;
import pepse.util.NoiseGenerator;

import java.util.ArrayList;
import java.util.List;

import static pepse.util.GameConstants.*;

/**
 * This class is responsible for the terrain game objects in the game.
 * @author Neriya Ben David
 */
public class Terrain {

    // Fields

    // Stores the desired ground height at x=0
    private final float groundHeightAtX0;
    private final NoiseGenerator noiseGenerator;

    /**
     * Constructs a new Terrain object.
     * @param windowDimensions the dimensions of the game window.
     */
    public Terrain(Vector2 windowDimensions) {
        this.groundHeightAtX0 = windowDimensions.y() * GROUND_HEIGHT_RATIO_INIT;
        this.noiseGenerator = new NoiseGenerator(SEED, (int) this.groundHeightAtX0);
    }

    /**
     * Returns the ground height at a given x coordinate.
     * @param x the x coordinate to return the ground height at.
     * @return the ground height at the given x coordinate.
     */
    public float groundHeightAt(float x) {
        float noise = (float) noiseGenerator.noise(x, BLOCK_SIZE * NOISE_FACTOR);
        return groundHeightAtX0 + noise;
    }

    /**
     * Creates a list of ground blocks in the given range.
     * @param minX the minimum x coordinate of the range.
     * @param maxX the maximum x coordinate of the range.
     * @return a list of ground blocks in the given x range.
     */
    public List<GameObject> createInRange(int minX, int maxX) {
        List<GameObject> blocks = new ArrayList<>();
        int terrainDepthInPixels = TERRAIN_DEPTH * BLOCK_SIZE;

        for (int x = minX; x < maxX; x += BLOCK_SIZE) {
            int groundHeight = alignToBlockSize((int) groundHeightAt(x));

            for (int y = groundHeight; y < groundHeightAt(x) + terrainDepthInPixels; y += BLOCK_SIZE) {
                blocks.add(createBlock(x, y));
            }
        }
        return blocks;
    }

    /*
     * Creates a ground block at the given position.
     */
    private static Block createBlock(int x, int y) {
        Renderable blockRenderer =
                new RectangleRenderable(ColorSupplier.approximateColor(BASE_GROUND_COLOR));
        Block block = new Block(new Vector2(x, y), blockRenderer);
        block.setTag(TAG_GROUND);
        return block;
    }

    /*
     * Aligns a value to the nearest block size in order to create a block at the correct position.
     */
    private int alignToBlockSize(int value) {
        return (int) Math.floor((double) value / BLOCK_SIZE) * BLOCK_SIZE;
    }
}




