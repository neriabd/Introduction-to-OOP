package pepse.world.trees;

import danogl.GameObject;
import danogl.util.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static pepse.util.GameConstants.*;

/**
 * Class for composing a tree object from its components trunk, leaves and fruits.
 * @author Neriya Ben David
 */
public class Tree {

    // Fields
    private GameObject trunk;
    private final List<GameObject> leaves = new ArrayList<>();
    private final List<GameObject> fruits = new ArrayList<>();
    private final Random random;

    /**
     * Creates a new Tree object.
     * @param treeBottomRightCorner the bottom right corner of the tree.
     */
    public Tree(Vector2 treeBottomRightCorner) {
        random = new Random(Objects.hash(treeBottomRightCorner.x(), SEED));
        createTrunk(treeBottomRightCorner);
        createLeavesAndFruits();
    }

    /**
     * a getter for the trunk of the tree.
     * @return the trunk of the tree.
     */
    public GameObject getTrunk() {
        return trunk;
    }

    /**
     * a getter for the leaves of the tree.
     * @return the leaves of the tree.
     */
    public List<GameObject> getLeafs() {
        return leaves;
    }

    /**
     * a getter for the fruits of the tree.
     * @return the fruits of the tree.
     */
    public List<GameObject> getFruits() {
        return fruits;
    }

    /*
     * Creates a trunk for the tree.
     */
    private void createTrunk(Vector2 bottomLeftCorner) {
        int trunkHeight = random.nextInt(MAX_TRUNK_HEIGHT - MIN_TRUNK_HEIGHT + 1)
                + MIN_TRUNK_HEIGHT;

        // create trunk position and dimensions vectors
        Vector2 topLeftCorner = new Vector2(bottomLeftCorner.x(),
                bottomLeftCorner.y() - trunkHeight);
        Vector2 trunkDimensions = new Vector2(BLOCK_SIZE, trunkHeight);
        trunk = new Trunk(topLeftCorner, trunkDimensions);
    }

    /*
     * Creates leaves and fruits for the tree.
     */
    private void createLeavesAndFruits() {
        Vector2 trunkTopLeftCorner = trunk.getTopLeftCorner();

        float objectTopLeftX = trunkTopLeftCorner.x() - LEAF_MAX_DISTANCE_FROM_TRUNK;
        float objectTopLeftY = trunkTopLeftCorner.y() - LEAF_MAX_DISTANCE_FROM_TRUNK;

        for (int i = 0; i < MAX_LEAVES_IN_ROW; i += 1) {
            for (int j = 0; j < MAX_LEAVES_IN_COL; j += 1) {

                float objectX = objectTopLeftX + i * BLOCK_SIZE;
                float objectY = objectTopLeftY + j * BLOCK_SIZE;

                Vector2 topLeftCorner = new Vector2(objectX, objectY);
                if (random.nextFloat() < LEAF_PROBABILITY) {
                    leaves.add(new Leaf(topLeftCorner, LEAF_DIMENSIONS));

                } else if (random.nextFloat() < FRUIT_PROBABILITY) {
                    fruits.add(new Fruit(topLeftCorner, FRUIT_DIMENSIONS));
                }
            }
        }
    }
}
