package pepse.world;

import danogl.GameObject;
import danogl.components.GameObjectPhysics;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import static pepse.util.GameConstants.*;

/**
 * This class is responsible for the block game objects in the game.
 * @see GameObject
 * @author Neriya Ben David
 */
public class Block extends GameObject {

    /**
     * Creates a new Block object.
     * @param topLeftCorner the top left corner position of the block.
     * @param renderable the renderer of the block.
     */
    public Block(Vector2 topLeftCorner, Renderable renderable) {
        super(topLeftCorner, Vector2.ONES.mult(BLOCK_SIZE), renderable);
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        physics().setMass(GameObjectPhysics.IMMOVABLE_MASS); // immovable when collided with
        setTag(TAG_BLOCK);
    }

}
