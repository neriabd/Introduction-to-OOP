/**
 * The PlayerFactory class is responsible for creating instances of
 * various types of players
 * @author Neriya Ben David
 * @see Player
 * @see HumanPlayer
 * @see WhateverPlayer
 * @see CleverPlayer
 * @see GeniusPlayer
 */
public class PlayerFactory {

    // constants for player types
    private static final String HUMAN_PLAYER = "human";
    private static final String WHATEVER_PLAYER = "whatever";
    private static final String CLEVER_PLAYER = "clever";
    private static final String GENIUS_PLAYER = "genius";

    /**
     * Constructs a new PlayerFactory object.
     */
    public PlayerFactory() {
    }


    /**
     * Builds a new player according to the type given
     * @param type the type of player to build
     * @return a new player object based on the type given
     */
    public Player buildPlayer(String type) {
        switch (type) {
            case HUMAN_PLAYER:
                return new HumanPlayer();
            case WHATEVER_PLAYER:
                return new WhateverPlayer();
            case CLEVER_PLAYER:
                return new CleverPlayer();
            case GENIUS_PLAYER:
                return new GeniusPlayer();
            default:
                return null;
        }
    }
}
