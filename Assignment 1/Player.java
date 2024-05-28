/**
 * Player interface to represent a player in a tic-tac-toe game.
 * @author Neriya Ben David
 */
public interface Player {

    /**
     * Play a turn in a game by putting a mark on the board.
     * @param board game board to play on
     * @param mark mark to put on board
     */
    void playTurn(Board board, Mark mark);
}
