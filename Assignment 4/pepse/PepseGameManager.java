package pepse;

import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.gui.rendering.Camera;
import danogl.util.Vector2;
import pepse.world.EnergyText;
import pepse.util.Observer;
import pepse.util.Subject;
import pepse.world.*;
import pepse.world.daynight.Night;
import pepse.world.daynight.Sun;
import pepse.world.daynight.SunHalo;
import pepse.world.trees.Flora;
import pepse.world.trees.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pepse.util.GameConstants.*;

/**
 * The main class of the game - responsible for initializing the game objects and running the game.
 * The game is a 2D platformer game where the player can control an avatar to jump on trees and
 * collect fruits.
 * @author Neriya Ben David
 * @see GameManager
 */
public class PepseGameManager extends GameManager {


    // Fields
    private ImageReader imageReader;
    private UserInputListener inputListener;
    private Terrain terrain;
    private Avatar avatar;
    private GameObject sun;
    private final Subject avatarJumpEvent = new Subject();
    private WindowController windowController;
    private Vector2 windowDimensions;
    private final Map<Integer, List<GameObject>> mapGameObjects = new HashMap<>();
    private int previousLeftX;
    private int previousRightX;


    /**
     * Initializes the game objects of the game.
     * @param imageReader Contains a single method: readImage, which reads an image from disk.
     * See its documentation for help.
     * @param soundReader Contains a single method: readSound, which reads a wav file from
     * disk. See its documentation for help.
     * @param inputListener Contains a single method: isKeyPressed, which returns whether
     * a given key is currently pressed by the user or not. See its
     * documentation.
     * @param windowController Contains an array of helpful, self-explanatory methods
     * concerning the window.
     */
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader, UserInputListener
            inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        this.imageReader = imageReader;
        this.inputListener = inputListener;
        this.windowController = windowController;
        this.windowDimensions = windowController.getWindowDimensions();
        previousLeftX = paddingSides(0, PADDING_LEFT);
        previousRightX = paddingSides(alignToBlockSize(windowDimensions.x()), PADDING_RIGHT);

        // create Sky
        createSky();

        // create Ground and Trees
        createGroundAndTrees();

        // create Night
        createDayNightCycle();

        // create Sun
        createSun();

        // create Avatar
        createAvatar();

        // create EnergyText
        createEnergyText();

        // create SunHalo
        createSunHalo();
    }

    /**
     * Updates the game world and handle the infinite world.
     * @param deltaTime the time passed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        updateWorldToCurrentWindow();
    }

    /*
     * Creates the sky in the game.
     */
    private void createSky() {
        // create Sky
        GameObject sky = Sky.create(windowController.getWindowDimensions());
        gameObjects().addGameObject(sky, SKY_LAYER);
    }

    /*
     * Creates the terrain / ground in the game through building blocks.
     */
    private void createTerrain(int minX, int maxX, List<GameObject> list) {
        this.terrain = new Terrain(windowController.getWindowDimensions());
        List<GameObject> blocks = this.terrain.createInRange(minX, maxX);
        list.addAll(blocks);
        createObjects(blocks, BLOCK_LAYER);
    }

    /*
     * Creates the night in the game.
     */
    private void createDayNightCycle() {
        GameObject night = Night.create(windowController.getWindowDimensions(), CYCLE_LENGTH);
        gameObjects().addGameObject(night, NIGHT_LAYER);
    }

    /*
     * Creates the sun in the game.
     */
    private void createSun() {
        this.sun = Sun.create(windowController.getWindowDimensions(), CYCLE_LENGTH);
        gameObjects().addGameObject(sun, SUN_LAYER);
    }

    /*
     * Creates the avatar in the game.
     */
    private void createAvatar() {
        Vector2 windowDimensions = windowController.getWindowDimensions();

        // calculate the initial position of the avatar

        float avatarXPosition = (windowDimensions.x() - AVATAR_IDLE_DIMENSIONS.x()) / HALF_FACTOR;

        float avatarYPosition = terrain.groundHeightAt(windowDimensions.x() / HALF_FACTOR) -
                AVATAR_IDLE_DIMENSIONS.y();


        // create the avatar
        Vector2 initialAvatarLocation = new Vector2(avatarXPosition,
                avatarYPosition);
        this.avatar = new Avatar(initialAvatarLocation, inputListener,
                imageReader, avatarJumpEvent::notifyObservers);
        gameObjects().addGameObject(avatar, AVATAR_LAYER);

        setCamera(new Camera(avatar, Vector2.ZERO, windowDimensions, windowDimensions));
    }

    /*
     * Creates the energy text in the game.
     */
    private void createEnergyText() {
        GameObject energyText = new EnergyText(ENERGY_TEXT_POSITION, ENERGY_TEXT_DIMENSIONS,
                avatar::getEnergy);
        gameObjects().addGameObject(energyText, ENERGY_TEXT_LAYER);
    }

    /*
     * Creates the sun halo in the game.
     */
    private void createSunHalo() {
        GameObject sunHalo = SunHalo.create(sun);
        gameObjects().addGameObject(sunHalo, SUN_HALO_LAYER);
    }

    /*
     * Creates the trees in the game.
     */
    private void createTrees(int minX, int maxX, List<GameObject> list) {
        Flora flora = new Flora(terrain::groundHeightAt);
        List<Tree> trees = flora.createInRange(minX, maxX);
        for (Tree tree : trees) {
            // create trunk
            createTrunk(tree);
            list.add(tree.getTrunk());

            // create leaves
            createObjects(tree.getLeafs(), LEAF_LAYER);
            list.addAll(tree.getLeafs());

            // create fruits
            createObjects(tree.getFruits(), FRUIT_LAYER);
            list.addAll(tree.getFruits());
        }
    }

    /*
     * Creates the trunk of the tree in the game.
     */
    private void createTrunk(Tree tree) {
        gameObjects().addGameObject(tree.getTrunk(), TRUNK_LAYER);
        avatarJumpEvent.addObserver((Observer) tree.getTrunk());
    }

    /*
     * Aligns a given float to the nearest size that is divided by block size.
     */
    private int alignToBlockSize(float x) {
        return (int) (Math.floor((double) x / BLOCK_SIZE) * BLOCK_SIZE);
    }

    /*
     * Creates the objects in the game through an iterable list of GameObjects.
     */
    private void createObjects(List<GameObject> objects, int layer) {
        for (GameObject object : objects) {
            gameObjects().addGameObject(object, layer);
            if (object.getTag().equals(TAG_FRUIT) || object.getTag().equals(TAG_LEAF)) {
                avatarJumpEvent.addObserver((Observer) object);
            }
        }
    }

    /*
     * Removes a GameObject from the subject (to not be observed anymore).
     */
    private void removeFromSubject(GameObject gameObject) {
        if (gameObject.getTag().equals(TAG_FRUIT) || gameObject.getTag().equals(TAG_LEAF)) {
            avatarJumpEvent.removeObserver((Observer) gameObject);
        }
    }

    /*
     * Updates the game world to the current window.
     */
    private void updateWorldToCurrentWindow() {
        // Calculate the current window boundaries aligned to block size
        int currentLeftX = alignToBlockSize(camera().getCenter().x() -
                windowDimensions.x() / HALF_FACTOR);
        int currentRightX = currentLeftX + (int) windowDimensions.x();

        // Apply padding adjustments to window boundaries
        currentLeftX = paddingSides(currentLeftX, PADDING_LEFT);
        currentRightX = paddingSides(currentRightX, PADDING_RIGHT);

        List<Integer> keysToRemove = new ArrayList<>();

        // Add objects that enter the window from the left side
        if (currentLeftX < previousLeftX) {
            addObjectsInRange(currentLeftX, previousLeftX);
        }
        // Add objects that enter the window from the right side
        else if (currentRightX > previousRightX) {
            addObjectsInRange(previousRightX, currentRightX);
        }

        // Remove objects that exit the window on the left side
        removeObjectsOutOfWindow(previousLeftX, currentLeftX, keysToRemove);

        // Remove objects that exit the window on the right side
        removeObjectsOutOfWindow(currentRightX, previousRightX, keysToRemove);

        // Clean up unnecessary keys from the map
        for (Integer key : keysToRemove) {
            mapGameObjects.remove(key);
        }

        // Update the previous boundaries to the current ones
        previousRightX = currentRightX;
        previousLeftX = currentLeftX;
    }

    /*
     * Adds game objects to the game world that are in the window.
     */
    private void addObjectsInRange(int startX, int endX) {
        for (int i = startX; i < endX; i += BLOCK_SIZE) {
            int blockKey = i / BLOCK_SIZE;

            // If the block is not in the map, create it - because it's the visible window
            if (!mapGameObjects.containsKey(blockKey)) {
                createRemovableObjects(i, i + BLOCK_SIZE);
            }
            for (GameObject gameObject : mapGameObjects.get(blockKey)) {

                gameObjects().addGameObject(gameObject, Layer.STATIC_OBJECTS);
            }
        }
    }

    /*
     * Removes game objects from the game world that are out of the window.
     */
    private void removeObjectsOutOfWindow(int startX, int endX, List<Integer> keysToRemove) {
        for (int i = startX; i < endX; i += BLOCK_SIZE) {
            int blockKey = i / BLOCK_SIZE;
            if (mapGameObjects.containsKey(blockKey)) {
                keysToRemove.add(blockKey);

                // Remove all the game objects in the block since they are out of the window
                for (GameObject gameObject : mapGameObjects.get(blockKey)) {
                    gameObjects().removeGameObject(gameObject);

                    // Remove the object from the list of observer.
                    removeFromSubject(gameObject);
                }
            }
        }
    }

    /*
     * Adds padding to the sides of the window.
     */
    private int paddingSides(int x, int padding) {
        return x + padding;
    }

    /*
     * Creates the ground and trees in the game.
     */
    private void createGroundAndTrees() {
        for (int i = previousLeftX; i < previousRightX; i += BLOCK_SIZE) {
            createRemovableObjects(i, i + BLOCK_SIZE);
        }
    }

    /*
     * Creates the removable objects in the game - add them in block size so it will
     * be easier to remove them.
     */
    private void createRemovableObjects(int minX, int maxX) {
        List<GameObject> list = new ArrayList<>();

        // create Terrain
        createTerrain(minX, maxX, list);

        // create Trees
        createTrees(minX, maxX, list);

        // add the list of objects to the map according to its block number (block key)
        mapGameObjects.put(minX / BLOCK_SIZE, list);
    }


    /**
     * The main method of the game - runs the game.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new PepseGameManager().run();
    }
}