/**
 * Mark enum to represent the marks on the board.
 * @author Neriya Ben David
 */
public enum Mark {
    X("X"),
    O("O"),
    BLANK(null);

    // symbol of the mark
    private final String symbol;

    /**
     * Constructs a new Mark object with the specified symbol.
     * @param symbol the symbol of the mark
     */
    Mark(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets the symbol of the mark.
     * @return the symbol of the mark
     */
    public String toString() {
        return symbol;
    }
}