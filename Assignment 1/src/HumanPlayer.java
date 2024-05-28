/**
 * Human player for TicTacToe game that gets moves from the user input
 * @author Neriya Ben David
 * @see Player
 */
public class HumanPlayer implements Player {

    // constants messages
    private static final String REQUEST_PLAYER_INPUT_MESSAGE =
            "Player %s, type coordinates: \n";
    private static final String OUT_OF_BOUNDS_MARK_MESSAGE =
            "Invalid mark position, please choose a different position.\n" +
            "Invalid coordinates, type again: ";
    private static final String MARK_POSITION_OCCUPIED_MESSAGE =
            "Mark position is already occupied.\n" +
            "Invalid coordinates, type again: ";
    private static final int DECIMAL_BASE_NUMBER = 10;

    /**
     * Constructor for HumanPlayer initializes the player
     */
    public HumanPlayer() {
    }

    /**
     * Perform a player turn by getting coordinates from the user
     * @param board game board to play on
     * @param mark mark to put on board
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        boolean validInput = false;
        System.out.printf(REQUEST_PLAYER_INPUT_MESSAGE, mark);
        do {
            int coordinates = KeyboardInput.readInt();

            // convert coordinates to row and column
            int row = coordinates / DECIMAL_BASE_NUMBER;
            int column = coordinates % DECIMAL_BASE_NUMBER;

            // put mark on board if the coordinates are valid
            if (!(row < board.getSize() && column < board.getSize())) {
                System.out.println(OUT_OF_BOUNDS_MARK_MESSAGE);
            } else if (!board.putMark(mark, row, column)) {
                System.out.println(MARK_POSITION_OCCUPIED_MESSAGE);
            } else {
                validInput = true;
            }
        } while (!validInput);
    }
}
