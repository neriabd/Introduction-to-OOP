/**
 * The Game class represents a single game of Tic-Tac-Toe between two players.
 * @author Neriya Ben David
 */
public class Game {

    // constants
    private final static int DEFAULT_WIN_STREAK = 3;
    private static final int TWO_BASE_NUMBER = 2;

    // fields
    private final Player playerX;
    private final Player playerO;
    private final Board board;
    private final int winStreak;
    private final Renderer renderer;

    /**
     * Constructs a new Game object with the default win streak of 3.
     * @param playerX the first player
     * @param playerO the second player
     * @param renderer the renderer to render the board
     */
    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
        this.board = new Board();
        this.winStreak = DEFAULT_WIN_STREAK;
    }

    /**
     * Constructs a new Game object with the specified win streak.
     * @param playerX the first player
     * @param playerO the second player
     * @param winStreak the win streak to win the game
     * @param renderer the renderer to render the board
     */
    public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.board = new Board(size);
        if (winStreak > size || winStreak < 2) {
            winStreak = DEFAULT_WIN_STREAK;
        }
        this.winStreak = winStreak;
        this.renderer = renderer;
    }

    /**
     * Gets the size of the board.
     * @return the size of the board
     */
    public int getBoardSize() {
        return board.getSize();
    }

    /**
     * Gets the win streak to win the game.
     * @return the win streak to win the game
     */
    public int getWinStreak() {
        return winStreak;
    }

    /**
     * Runs a single game.
     * @return the mark of the winning player, or BLANK if there is a tie between the players
     */
    public Mark run() {
        int maxTurns = board.getSize() * board.getSize();
        renderer.renderBoard(board);

        // play the game for the maximum number of turns or until a player wins
        for (int turn = 0; turn < maxTurns; turn++) {
            Player currentPlayer = (turn % TWO_BASE_NUMBER == 0) ? playerX : playerO;
            Mark currentMark = (turn % TWO_BASE_NUMBER == 0) ? Mark.X : Mark.O;

            // play the turn and render the board and check if the player has won
            currentPlayer.playTurn(board, currentMark);
            renderer.renderBoard(board);
            if (isPlayerWon(currentMark)) {
                return currentMark;
            }
        }
        return Mark.BLANK;
    }

    /*
     * Check if the player has won the game by using a dynamic programming approach.
     */
    private boolean isPlayerWon(Mark mark) {
        // dynamically check if the player has won the game by checking the
        // rows, columns, diagonals, and anti-diagonals in one pass
        int size = board.getSize();
        int[][] rows = new int[size][size];
        int[][] columns = new int[size][size];
        int[][] diagonals = new int[size][size];
        int[][] antiDiagonals = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getMark(i, j) == mark) {

                    // if first cell in row, column, diagonal, or anti-diagonal then set to 1
                    // else increment by 1 from the previous cell

                    // row check from left to right
                    rows[i][j] = (j == 0) ? 1 : rows[i][j - 1] + 1;

                    // column check from top to bottom
                    columns[i][j] = (i == 0) ? 1 : columns[i - 1][j] + 1;

                    // diagonal check from top-left to bottom-right
                    diagonals[i][j] = (i == 0 || j == 0) ? 1 : diagonals[i - 1][j - 1] + 1;

                    // anti-diagonal check from top-right to bottom-left
                    antiDiagonals[i][j] = (i == 0 || j == size - 1) ? 1 :
                            antiDiagonals[i - 1][j + 1] + 1;

                    if (rows[i][j] == winStreak ||
                            columns[i][j] == winStreak ||
                            diagonals[i][j] == winStreak ||
                            antiDiagonals[i][j] == winStreak) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
