import java.util.Random;

/**
 * Random player class for TicTacToe game that chooses random moves in
 * order to play a turn
 * @author Neriya Ben David
 * @see Player
 */
public class WhateverPlayer implements Player {

    /**
     * Constructor for HumanPlayer initializes the player
     */
    public WhateverPlayer() {
    }

    /**
     * play a turn in the game by choosing a
     * random cell on the board
     * @param board game board to play on
     * @param mark mark to put on board
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        boolean isEmptyCell;
        int boardSize = board.getSize();
        Random random = new Random();

        // loop until you pick a coordinate that is not already occupied
        do {
            int row = random.nextInt(boardSize);
            int col = random.nextInt(boardSize);
            isEmptyCell = board.putMark(mark, row, col);
        } while (!isEmptyCell);
    }
}
