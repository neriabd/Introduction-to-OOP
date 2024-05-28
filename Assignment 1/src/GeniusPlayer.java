/**
 * This class represents a player that go over the board and
 * put a mark in the first empty cell it finds from traversing through rows.
 * @author Neriya Ben David
 * @see Player
 */
public class GeniusPlayer implements Player {

    /**
     * Constructor for CleverPlayer that initializes the player
     */
    public GeniusPlayer() {
    }

    /**
     * Play a turn in a game by putting a mark by the genius player strategy
     * which is to put the mark in the first empty cell it finds from traversing from rows.
     * @param board game board to play on
     * @param mark mark to put on board
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        int boardSize = board.getSize();

        for (int i = boardSize - 1; i >= 0; i--) {
            for (int j = boardSize - 1; j >= 0; j--) {
                if (board.getMark(i, j) == Mark.BLANK) {
                    board.putMark(mark, i, j);
                    return;
                }
            }
        }
    }
}
