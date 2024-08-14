package pepse.world.trees;

import danogl.util.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;

import static pepse.util.GameConstants.*;

/**
 * The Flora class is responsible for creating trees.
 * @author Neriya Ben David
 */
public class Flora {

    // Fields
    private final Function<Float, Float> groundHeightAt;
    private List<Tree> trees;

    /**
     * Creates a new Flora object.
     * @param groundHeightAt a function that returns the height of the ground at a given X coordinate.
     */
    public Flora(Function<Float, Float> groundHeightAt) {
        this.groundHeightAt = groundHeightAt;
    }

    /**
     * Creates trees in a random way in the given range - expected to get a range of the screen]
     * that both minX and maxX are multiples of BLOCK_SIZE.
     * @param minX the minimum X coordinate to generate a tree.
     * @param maxX the maximum X coordinate to generate a tree.
     * @return a list of trees.
     */
    public List<Tree> createInRange(int minX, int maxX) {
        trees = new ArrayList<>();
        for (int x = minX; x < maxX; x += BLOCK_SIZE) {
            if (isTreeShouldBeCreated(minX, maxX)) {
                createTree(x);
            }
        }
        return trees;
    }

    /*
     * Creates a tree at the given X coordinate.
     */
    private void createTree(int x) {
        Vector2 bottomLeftCorner = new Vector2(x - BLOCK_SIZE,
                groundHeightAt.apply((float) x) + BLOCK_SIZE);
        trees.add(new Tree(bottomLeftCorner));
    }

    /*
     * Returns true if a tree should be created.
     */
    private boolean isTreeShouldBeCreated(int i, int j) {
        Random random = new Random(Objects.hash(i + j, SEED));
        return random.nextFloat() < TREE_PROBABILITY;
    }
}
