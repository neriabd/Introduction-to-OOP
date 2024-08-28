package pepse.util;

import danogl.collisions.Layer;
import danogl.util.Vector2;

import java.awt.*;
import java.util.List;

/**
 * Class that contains all the constants used in the game.
 * @author Neriya Ben David
 */
public final class GameConstants {

    /*
     * Constructor for the GameConstants class, should not be used.
     */
    private GameConstants() {
    }

    // BLOCK CONSTANTS

    /**
     * The size of the block in pixels.
     */
    public static final int BLOCK_SIZE = 30;

    /**
     * The tag of the block game object.
     */
    public static final String TAG_BLOCK = "Block";

    /**
     * The layer of the block game object.
     */
    public static final int BLOCK_LAYER = Layer.STATIC_OBJECTS;

    // MAIN CONSTANTS

    /**
     * The seed used for the random generator.
     */
    public static final int SEED = 0;

    /**
     * The time of day and night cycle in seconds.
     */
    public static final int CYCLE_LENGTH = 30;

    /**
     * The padding of the screen from the left side to still keep objects in the game.
     */
    public static final int PADDING_LEFT = -3 * BLOCK_SIZE;

    /**
     * The padding of the screen from the right side to still keep objects in the game.
     */
    public static final int PADDING_RIGHT = 3 * BLOCK_SIZE;


    // AVATAR CONSTANTS

    /**
     * The layer of the sun halo game object.
     */
    public static final int AVATAR_LAYER = Layer.DEFAULT;

    /**
     * The tag of the avatar game object.
     */
    public static final String TAG_AVATAR = "Avatar";

    /**
     * The idle images of the avatar.
     */
    public static final String[] AVATAR_IDLE_IMAGES = {
            "assets/idle_0.png",
            "assets/idle_1.png",
            "assets/idle_2.png",
            "assets/idle_3.png"
    };

    /**
     * The jump images of the avatar.
     */
    public static final String[] AVATAR_JUMP_IMAGES = {
            "assets/jump_0.png",
            "assets/jump_1.png",
            "assets/jump_2.png",
            "assets/jump_3.png"
    };

    /**
     * The run images of the avatar.
     */
    public static final String[] AVATAR_RUN_IMAGES = {
            "assets/run_0.png",
            "assets/run_1.png",
            "assets/run_2.png",
            "assets/run_3.png"
    };

    /**
     * The velocity of the avatar in the X axis - move to left or right.
     */
    public static final float VELOCITY_X = 400;

    /**
     * The velocity of the avatar in the Y axis - jump.
     */
    public static final float VELOCITY_Y = -650;

    /**
     * The gravity of the avatar to pull it down.
     */
    public static final float GRAVITY = 600;

    /**
     * The minimum energy of the avatar.
     */
    public static final int AVATAR_MIN_ENERGY = 0;

    /**
     * The maximum energy of the avatar.
     */
    public static final int AVATAR_MAX_ENERGY = 100;

    /**
     * The energy change when the avatar runs.
     */
    public static final float RUN_ENERGY_REDUCTION = -0.5f;

    /**
     * The energy change when the avatar is idle.
     */
    public static final float IDLE_ENERGY_INCREASE = 1;

    /**
     * The energy change when the avatar jumps.
     */
    public static final float JUMP_ENERGY_REDUCTION = -10;

    /**
     * The frame duration of the avatar animation.
     */
    public static final double FRAME_ANIMATION_DURATION = 0.2;

    /**
     * The dimensions of the avatar when he is idle.
     */
    public static final Vector2 AVATAR_IDLE_DIMENSIONS = new Vector2(50, 78);

    /**
     * The dimensions of the avatar when he jumps.
     */
    public static final Vector2 AVATAR_JUMP_DIMENSIONS = new Vector2(69, 73);

    /**
     * The dimensions of the avatar when he runs.
     */
    public static final Vector2 AVATAR_RUN_DIMENSIONS = new Vector2(58, 73);


    // SKY CONSTANTS

    /**
     * The basic color of the sky.
     */
    public static final Color BASIC_SKY_COLOR = Color.decode("#80C6E5");

    /**
     * The tag of the sky game object.
     */
    public static final String TAG_SKY = "Sky";

    /**
     * The layer of the sky game object.
     */
    public static final int SKY_LAYER = Layer.BACKGROUND;

    // TERRAIN CONSTANTS

    /**
     * The initial ratio of the ground to the window height.
     */
    public static final float GROUND_HEIGHT_RATIO_INIT = (float) 2 / 3;

    /**
     * The ground color variation.
     */
    public static final Color BASE_GROUND_COLOR = new Color(212, 123, 74);

    /**
     * The tag of the terrain game object.
     */
    public static final String TAG_GROUND = "Ground";

    // The depth of every column of the terrain

    /**
     * The depth of the terrain.
     */
    public static final int TERRAIN_DEPTH = 40;

    /**
     * The noise factor of the terrain initialization.
     */
    public static final float NOISE_FACTOR = 7;

    // NIGHT CONSTANTS

    /**
     * The color of the night.
     */
    public static final Color BASIC_NIGHT_COLOR = Color.BLACK;

    /**
     * The tag of the night game object.
     */
    public static final String TAG_NIGHT = "Night";

    /**
     * The opacity of the night background.
     */
    public static final float MIDNIGHT_OPACITY = 0.5f;

    /**
     * The layer of the night game object.
     */
    public static final int NIGHT_LAYER = Layer.FOREGROUND;

    /**
     * The opacity of the day background.
     */
    public static final float DAY_OPACITY = 0;

    /**
     * The factor to divide the cycle length by.
     */
    public static final int HALF_DAY_FACTOR = 2;

    // SUN CONSTANTS

    /**
     * The layer of the sun game object.
     */
    public static final int SUN_LAYER = Layer.BACKGROUND;


    /**
     * The color of the sun.
     */
    public static final Color SUN_COLOR = Color.YELLOW;

    /**
     * A factor of 2 to divide by.
     */
    public static final int HALF_FACTOR = 2;

    /**
     * The size of the sun in pixels (diameter).
     */
    public static final int SUN_SIZE = 120;

    /**
     * The offset of the sun from the center of the sun.
     */
    public static final float SUN_OFFSET = (float) SUN_SIZE / HALF_FACTOR;

    /**
     * The tag of the sun game object.
     */
    public static final String TAG_SUN = "Sun";

    /**
     * The initial angle the sun start rotating from.
     */
    public static final float INITIAL_ANGLE = 0f;

    /**
     * The final angle the sun rotates to.
     */
    public static final float FINAL_ANGLE = 360f;

    // SunHalo CONSTANTS

    /**
     * The layer of the sun halo game object.
     */
    public static final int SUN_HALO_LAYER = Layer.BACKGROUND;

    /**
     * The size of the sun halo in pixels (diameter).
     */
    public static final float SUN_HALO_SIZE = 230;

    /**
     * The color of the sun halo.
     */
    public static final Color SUN_HALO_COLOR = new Color(255, 255, 0, 20);

    /**
     * The tag of the sun halo game object.
     */
    public static final String TAG_SUN_HALO = "SunHalo";

    /**
     * The offset of the sun halo from the center of the sun halo .
     */
    public static final float SUN_HALO_OFFSET = SUN_HALO_SIZE / HALF_FACTOR;

    // ENERGY TEXT CONSTANTS

    /**
     * The tag of the energy text game object.
     */
    public static final String TAG_ENERGY = "EnergyText";

    /**
     * Index of the first color in the text colors array.
     */
    public static final int FIRST_COLOR_INDEX = 0;

    /**
     * Index of the second color in the text colors array.
     */
    public static final int SECOND_COLOR_INDEX = 1;

    /**
     * Index of the third color in the text colors array.
     */
    public static final int THIRD_COLOR_INDEX = 2;

    /**
     * Index of the fourth color in the text colors array.
     */
    public static final int FOURTH_COLOR_INDEX = 3;

    /**
     * Color array for text representing different energy levels.
     */
    public static final Color[] TEXT_COLORS =
            new Color[]{Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN};

    /**
     * The energy thresholds for the different colors.
     */
    public static final int[] energyThresholds = new int[]{0, 33, 66, 100};

    /**
     * The layer of the energy text game object.
     */
    public static final int ENERGY_TEXT_LAYER = Layer.FOREGROUND;

    /**
     * The size of the energy text.
     */
    public static final Vector2 ENERGY_TEXT_DIMENSIONS = new Vector2(100, 50);

    /**
     * The position on screen of the energy text.
     */
    public static final Vector2 ENERGY_TEXT_POSITION = new Vector2(10, 10);

    /**
     * Percent sign for the energy text representation.
     */
    public static final String PERCENT_SIGN = "%";

    // TREE CONSTANTS

    /**
     * The distance of the trunk from the terrain.
     */
    public static final float TREE_PROBABILITY = 0.1f;

    // LEAF CONSTANTS

    /**
     * The layer of the leaf game object.
     */
    public static final int LEAF_LAYER = Layer.STATIC_OBJECTS;

    /**
     * The initial angle of the leaf 90 degrees rotation.
     */
    public static final float INITIAL_ROTATE_90_DEGREE = 0f;

    /**
     * The last angle of the leaf 90 degrees rotation.
     */
    public static final float LAST_ROTATE_90_ANGLE = 90f;

    /**
     * The largest distance of the leaves from the trunk.
     */
    public static final float LEAF_MAX_DISTANCE_FROM_TRUNK = ((float) (BLOCK_SIZE)) * 3.5f;

    /**
     * Probability of a tree appearing at each column.
     */
    public static final float LEAF_PROBABILITY = 0.5f;

    /**
     * The maximum leaves in a row of a tree.
     */
    public static final int MAX_LEAVES_IN_ROW = 8;

    /**
     * The maximum leaves in a column of a tree.
     */
    public static final int MAX_LEAVES_IN_COL = 8;

    /**
     * The dimensions of a leaf.
     */
    public static final Vector2 LEAF_DIMENSIONS = new Vector2(30, 30);

    /**
     * The colors of the leafs.
     */
    public static final Color LEAF_COLOR = new Color(50, 200, 30);

    /**
     * The variation delta of the leaf color.
     */
    public static final int COLOR_DELTA_LEAF = 25;

    /**
     * The wind transition duration.
     */
    public static final float WIND_TRANSITION_DURATION = 0.8f;

    /**
     * The wind effect normalization factor in order to normalize the wind effect.
     */
    public static final float WIND_EFFECT_NORMALIZATION_FACTOR = 0.5f;

    /**
     * The initial stretch factor in the beginning of the leaf stretch transition.
     */
    public static final float START_STRETCH_FACTOR = 1f;

    /**
     * The final stretch factor in the end of the leaf stretch transition.
     */
    public static final float FINAL_STRETCH_FACTOR = 1.2f;

    /**
     * The initial angle of the leaf in the beginning of the leaf angle transition.
     */
    public static final float START_ANGLE_TRANSITION = 0f;

    /**
     * The final angle of the leaf in the end of the leaf angle transition.
     */
    public static final float FINAL_ANGLE_TRANSITION = 20f;

    /**
     * The duration of the leaf rotation.
     */
    public static final float LEAF_ROTATE_DURATION = 0.5f;

    /**
     * The leaf tag.
     */
    public static final String TAG_LEAF = "Leaf";

    // TRUNK CONSTANTS

    /**
     * The layer of the trunk game object.
     */
    public static final int TRUNK_LAYER = Layer.STATIC_OBJECTS;


    /**
     * The color variation of the trunk.
     */
    public static final Color TRUNK_COLOR = new Color(100, 50, 20);

    /**
     * The tag of the trunk game object.
     */
    public static final String TAG_TRUNK = "Trunk";

    /**
     * The delta variation of the trunk color possible for each trunk.
     */
    public static final int COLOR_DELTA_TRUNK = 20;

    /**
     * The minimum height of a trunk.
     */
    public static final int MIN_TRUNK_HEIGHT = 250;

    /**
     * The highest height of a trunk.
     */
    public static final int MAX_TRUNK_HEIGHT = 350;

    // FRUIT CONSTANTS

    /**
     * The layer of the fruit game object.
     */
    public static final int FRUIT_LAYER = Layer.STATIC_OBJECTS;

    /**
     * The colors of the fruits in the game.
     */
    public static final List<Color> FRUIT_COLORS = List.of(Color.RED, Color.YELLOW, Color.BLUE);

    /**
     * The dimensions of a fruit
     */
    public static final Vector2 FRUIT_DIMENSIONS = new Vector2(26, 26);

    /**
     * Probability of a Fruit appearing at coordinate when there isn't a fruit.
     */
    public static final double FRUIT_PROBABILITY = 0.2;

    /**
     * The tag of the fruit game object.
     */
    public static final String TAG_FRUIT = "Fruit";
}
