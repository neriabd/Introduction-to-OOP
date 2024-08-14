package bricker.brick_strategies;

import bricker.gameobjects.Puck;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import static bricker.utilities.GameConstants.*;

/**
 * This class is responsible for creating the collision strategy that adds
 * extra pucks to the game.
 * @see CollisionStrategy
 * @author Neriya Ben David
 */
public class ExtraPucksStrategy extends CollisionStrategy {

    // Constants
    private static final int NUMBER_OF_PUCKS = 2;

    // Fields
    private final Renderable renderer;
    private final GameObjectCollection gameObjects;
    private final Sound collisionSound;

    /**
     * The constructor of the ExtraPucksStrategy object.
     * @param gameObjects An object which holds all game objects of the game running.
     * @param renderer the image object for the extra pucks.
     * @param collisionSound the sound object for the collision of the pucks.
     * @param identifier the identifier integer of the strategy.
     */
    public ExtraPucksStrategy(GameObjectCollection gameObjects,Renderable renderer,
                              Sound collisionSound, int identifier) {
        super(gameObjects, identifier);
        this.renderer = renderer;
        this.gameObjects = gameObjects;
        this.collisionSound = collisionSound;
    }

    /**
     * This method is called when the ball collides with a brick object.
     * If the object is the expected object, extra pucks will be added to the game.
     * @param collidedObject the object that was collided with.
     * @param colliderObject the object that collided with the collided object.
     */
    @Override
    public void onCollision(GameObject colliderObject, GameObject collidedObject) {
        super.onCollision(colliderObject, collidedObject);
        // create new pucks in the middle of the collided object
        for (int i = 0; i < NUMBER_OF_PUCKS; i++) {
            Vector2 puckDimensions = new Vector2(PUCK_WIDTH, PUCK_HEIGHT);
            Puck puck = new Puck(collidedObject.getTopLeftCorner(), puckDimensions, renderer,
                    collisionSound);
            puck.setTag(PUCK_TAG);
            puck.initializeRandomVelocity();
            puck.setCenter(collidedObject.getCenter());

            this.gameObjects.addGameObject(puck, Layer.DEFAULT);
        }
    }
}
