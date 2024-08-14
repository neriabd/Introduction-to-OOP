package bricker.main;

import danogl.util.Vector2;

/**
 * This class is responsible for creating the BrickerGameManager object.
 * The BrickerGameManager object is the bricker.main object that manages the game.
 * @author Neriya Ben David
 */
public class BrickerGameFactory {

    // Window constants
    private static final String WINDOW_TITLE = "Bricker Game";
    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_HEIGHT = 500;
    private static final int TWO_ARGUMENTS = 2;

    // Arguments constants
    private static final int FIRST_ARG = 0;
    private static final int SECOND_ARG = 1;

    /**
     * This method creates a new BrickerGameManager object.
     * @param args The arguments provided to the game.
     * @return The BrickerGameManager object.
     */
    public BrickerGameManager createGame(String[] args) {
        int numberOfBricksInRow = 8;
        int numberOfBricksInColumn = 7;

        // Parse arguments if provided and set the number of bricks in the game accordingly
        if (args.length == TWO_ARGUMENTS) {
            numberOfBricksInRow = Integer.parseInt(args[FIRST_ARG]);
            numberOfBricksInColumn = Integer.parseInt(args[SECOND_ARG]);
        }

        return new BrickerGameManager(WINDOW_TITLE, new Vector2(WINDOW_WIDTH, WINDOW_HEIGHT),
                numberOfBricksInRow, numberOfBricksInColumn);
    }
}
