import java.util.Random;

/**
 * The CleverPlayer class implements the Player interface and represents a player
 * with a clever strategy for playing a tic-tac-toe game.
 * The clever strategy is to roll a fair dice and choose a strategy to play by
 * in all his moves over the current round.
 * Half the odds, it will play by random moves, otherwise it will play by putting
 * the mark in the first empty cell it finds
 * @author Neriya Ben David
 * @see Player
 */
public class CleverPlayer implements Player {

    private static final int RANDOM_MOVES = 0;
    private int strategy;

    /**
     * Constructs a new CleverPlayer object.
     */
    public CleverPlayer() {
    }

    /**
     * play a turn in the game of tic-tac-toe by the strategy of the clever player
     * @param board game board to play on
     * @param mark mark to put on board
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        int boardSize = board.getSize();
        Random random = new Random();

        // if it's the first move of the player, choose a strategy
        if (isFirstMove(board)) {
            strategy = random.nextInt(2);
        }

        // if the strategy is to make random moves, make a random move
        if (strategy == RANDOM_MOVES) {
            randomMove(board, mark, random, boardSize);
            return;
        }

        // if the strategy is to make clever moves, make a clever move
        seekFirstEmptySpot(board, mark, boardSize);
    }


    /*
     * a function to find the first empty spot on the board and put the mark on it
     */
    private void seekFirstEmptySpot(Board board, Mark mark, int boardSize) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board.getMark(i, j) == Mark.BLANK) {
                    board.putMark(mark, i, j);
                    return;
                }
            }
        }
    }

    /*
     * a function to make a random move on the board
     */
    private void randomMove(Board board, Mark mark, Random random, int boardSize) {
        boolean isValidMove;
        int row;
        int col;
        do {
            row = random.nextInt(boardSize);
            col = random.nextInt(boardSize);
            isValidMove = board.putMark(mark, row, col);
        } while (!isValidMove);
    }

    /*
     * a function to check if the current move is the first move of the player in the game
     */
    private boolean isFirstMove(Board board) {
        int marks = 0;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getMark(i, j) != Mark.BLANK) {
                    if (marks > 1) {
                        return false;
                    }
                    marks++;
                }
            }
        }
        return true;
    }
}
