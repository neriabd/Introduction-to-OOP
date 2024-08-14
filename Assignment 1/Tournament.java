/**
 * The Tournament class is responsible for running a tournament between two players
 * @author Neriya Ben David
 */
public class Tournament {

    // Constants Arguments
    private static final int FIRST_ARG = 0;
    private static final int SECOND_ARG = 1;
    private static final int THIRD_ARG = 2;
    private static final int FOURTH_ARG = 3;
    private static final int FIFTH_ARG = 4;
    private static final int SIXTH_ARG = 5;

    // Constants Messages
    private static final String INVALID_ARGUMENTS_MESSAGE =
            "Usage: Please run the game again: java Tournament " +
                    "[round count] [size] [win_streak] [render target: console/none] " +
                    "[first player: human/whatever/clever/genius] " +
                    "[second player: human/whatever/clever/genius]";
    private static final String DECORATED_RESULTS_MESSAGE = "######### Results #########";
    private static final String NUMBER_OF_WINS_OF_PLAYER_1_MESSAGE = "Player 1, %s won: %d rounds\n";
    private static final String NUMBER_OF_WINS_OF_PLAYER2_MESSAGE = "Player 2, %s won: %d rounds\n";
    private static final String NUMBER_OF_TIES_IN_TOURNAMENT_MESSAGE = "Ties: %d\n";

    // Constants Numbers
    private static final int TWO_BASE_NUMBER = 2;

    // private fields
    // rounds to play in the tournament
    private int rounds;

    // renderer to use
    private Renderer renderer;

    // players in the tournament
    private Player playerX;
    private Player playerO;

    // scores of the tournament
    private int player1Wins;
    private int player2Wins;
    private int ties;

    /**
     * Constructor for the Tournament class
     * @param rounds number of rounds to play
     * @param renderer renderer to use
     * @param playerX a player object who uses mark X
     * @param playerO a player object who uses mark O
     */
    public Tournament(int rounds, Renderer renderer, Player playerX, Player playerO) {
        this.rounds = rounds;
        this.renderer = renderer;
        this.playerX = playerX;
        this.playerO = playerO;
        this.player1Wins = 0;
        this.player2Wins = 0;
        this.ties = 0;
    }

    /**
     * Play a tournament of with a couple of rounds between two players
     * @param size the size of the board
     * @param winStreak the win streak to win the game
     * @param playerName1 the name of the first player
     * @param playerName2 the name of the second player
     */
    void playTournament(int size, int winStreak, String playerName1, String playerName2) {
        // loop over the rounds and calculate the scores over the tournament
        for (int i = 0; i < rounds; i++) {
            Player firstPlayer = (i % TWO_BASE_NUMBER == 0) ? playerX : playerO;
            Player secondPlayer = (i % TWO_BASE_NUMBER == 0) ? playerO : playerX;

            // Create a new game and run it
            Game game = new Game(firstPlayer, secondPlayer, size, winStreak, renderer);
            Mark winner = game.run();

            //  Update the scores of the tournament
            updateTournamentScores(winner, firstPlayer, secondPlayer);
        }

        // Print the results of the tournament
        printResults(playerName1, playerName2, player1Wins, player2Wins, ties);
    }

    /*
     * Update the scores of the tournament based on the winner of the game
     */
    private void updateTournamentScores(Mark winner, Player firstPlayer, Player secondPlayer) {
        if (winner == Mark.X) {
            if (firstPlayer == playerX) {
                player1Wins++;
            } else {
                player2Wins++;
            }

        } else if (winner == Mark.O) {
            if (secondPlayer == playerX) {
                player1Wins++;
            } else {
                player2Wins++;
            }

        } else {
            ties++;
        }
    }

    /*
     * Print the results of the tournament in the required format
     */
    private void printResults(String playerName1, String playerName2, int player1Wins, int player2Wins,
                              int ties) {
        // Print the results of the tournament in the required format
        System.out.println(DECORATED_RESULTS_MESSAGE);
        System.out.printf(NUMBER_OF_WINS_OF_PLAYER_1_MESSAGE, playerName1, player1Wins);
        System.out.printf(NUMBER_OF_WINS_OF_PLAYER2_MESSAGE, playerName2, player2Wins);
        System.out.printf(NUMBER_OF_TIES_IN_TOURNAMENT_MESSAGE, ties);
    }

    /**
     * Main method to run the tic-tac-toe game tournament
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // [round count] [size] [win_streak] [render target: console/none]
        // [first player: human/whatever/clever/genius]
        // [second player: human/whatever/clever/genius]
        int rounds = Integer.parseInt(args[FIRST_ARG]);
        int size = Integer.parseInt(args[SECOND_ARG]);
        int winStreak = Integer.parseInt(args[THIRD_ARG]);
        String renderTargetType = args[FOURTH_ARG];
        String firstPlayerType = args[FIFTH_ARG];
        String secondPlayerType = args[SIXTH_ARG];

        // Create the renderer and players based on the arguments
        RendererFactory rendererFactory = new RendererFactory();
        Renderer renderer = rendererFactory.buildRenderer(renderTargetType, size);

        // Create the players based on the arguments
        PlayerFactory playerFactory = new PlayerFactory();
        Player firstPlayer = playerFactory.buildPlayer(firstPlayerType);
        Player secondPlayer = playerFactory.buildPlayer(secondPlayerType);

        // Check if the arguments are valid
        if (renderer == null || firstPlayer == null || secondPlayer == null) {
            System.out.println(INVALID_ARGUMENTS_MESSAGE);
            return;
        }

        // Create a new tournament and play it
        Tournament tournament = new Tournament(rounds, renderer, firstPlayer, secondPlayer);
        tournament.playTournament(size, winStreak, firstPlayerType, secondPlayerType);
    }
}
