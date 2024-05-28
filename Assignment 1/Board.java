/**
 * The Board class represents a game board for a tic-tac-toe like game.
 * This class provides methods to initialize the board, place marks on it, and
 * retrieve the size and marks from the board. The default board size is 4x4,
 * but a custom size can be specified through the constructor.
 * @author Neriya Ben David
 */
 public class Board {
    private static final int DEFAULT_SIZE = 4;
    private final Mark[][] board;
    private int size;

    /**
     * Constructs a new Board object with the default size of 4x4.
     */
    public Board() {
        this.board = new Mark[DEFAULT_SIZE][DEFAULT_SIZE];
        initializeBoard(DEFAULT_SIZE);
    }

    /**
     * Constructs a new Board object with the specified size.
     * @param size the size of the board
     */
    public Board(int size) {
        this.size = size;
        this.board = new Mark[size][size];
        initializeBoard(size);
    }

    /**
     * Gets the size of the board.
     * @return the size of the board
     */
    public int getSize() {
        return size;
    }

    /**
     * Puts a mark on the board at the specified row and column.
     * @param mark the mark to place on the board
     * @param row the row where the mark should be placed
     * @param col the column where the mark should be placed
     * @return true if the mark was placed successfully, false if the spot has
     * already been taken occupied
     */
    public boolean putMark(Mark mark, int row, int col) {
        if (getMark(row, col) == Mark.BLANK) {
            board[row][col] = mark;
            return true;
        }
        return false;
    }

    /**
     * Gets the mark at the specified row and column.
     * @param row the row of the mark to retrieve
     * @param col the column of the mark to retrieve
     * @return the mark at the specified row and column
     */
    public Mark getMark(int row, int col) {
        return board[row][col];
    }

    /* Initializes the board with BLANK marks. */
    private void initializeBoard(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Mark.BLANK;
            }
        }
    }
}
