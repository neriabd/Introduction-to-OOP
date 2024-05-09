import java.util.*;

/**
 * Base file for the ChatterBot exercise.
 * The bot's replyTo method receives a statement.
 * If it starts with the constant REQUEST_PREFIX, the bot returns
 * whatever is after this prefix. Otherwise, it returns one of
 * a few possible replies as supplied to it via its constructor.
 * In this case, it may also include the statement after
 * the selected reply (coin toss).
 * @author Dan Nirel
 */
class ChatterBot {
    static final String REQUEST_PREFIX = "say ";
    static final String PLACEHOLDER_FOR_REQUESTED_PHRASE = "<phrase>";
    static final String PLACEHOLDER_FOR_ILLEGAL_REQUEST = "<request>";

    Random rand = new Random();
    String botName;
    String[] repliesToIllegalRequest;
    String[] repliesToLegalRequests;

    /**
     * Constructor of the ChatterBot Class
     * @param botName the name of the bot
     * @param repliesToIllegalRequest possible replies to the illegal requests
     * @param repliesToLegalRequests possible replies to the legal requests
     */
    ChatterBot(String botName, String[] repliesToIllegalRequest,
               String[] repliesToLegalRequests) {
        this.botName = botName;

        // Copy the arrays to avoid changing the original arrays
        this.repliesToIllegalRequest = new String[repliesToIllegalRequest.length];
        for (int i = 0; i < repliesToIllegalRequest.length; i = i + 1) {
            this.repliesToIllegalRequest[i] = repliesToIllegalRequest[i];
        }

        // Copy the arrays to avoid changing the original arrays
        this.repliesToLegalRequests = new String[repliesToLegalRequests.length];
        for (int i = 0; i < repliesToLegalRequests.length; i = i + 1) {
            this.repliesToLegalRequests[i] = repliesToLegalRequests[i];
        }
    }

    /**
     * Get the name of the chatbot
     * @return the name of the chatbot
     */
    String getName() {
        return botName;
    }

    /**
     * Bot's reply to the statement
     * @param statement the statement to reply to
     * @return the reply of the chatbot
     */
    String replyTo(String statement) {
        if (statement.startsWith(REQUEST_PREFIX)) {
            return replyToLegalRequest(statement);
        }
        return replyToIllegalRequest(statement);
    }

    /**
     * Respond to illegal request by replacing placeholder in a random pattern
     * @param statement the illegal request
     * @return response to illegal request
     */
    String replyToIllegalRequest(String statement) {
        return replacePlaceholderInARandomPattern(this.repliesToIllegalRequest,
                PLACEHOLDER_FOR_ILLEGAL_REQUEST, statement);
    }

    /**
     * Respond to legal request by replacing placeholder in a random pattern
     * @param statement the legal request
     * @return response to the legal request
     */
    String replyToLegalRequest(String statement) {
        String phrase = statement.replaceFirst(REQUEST_PREFIX, "");
        return replacePlaceholderInARandomPattern(this.repliesToLegalRequests,
                PLACEHOLDER_FOR_REQUESTED_PHRASE, phrase);
    }

    /**
     * Replace placeholder in a random pattern
     * @param patterns array of patterns
     * @param placeholder the placeholder to replace
     * @param replacement the replacement
     * @return the pattern with the placeholder replaced
     */
    String replacePlaceholderInARandomPattern(String[] patterns, String placeholder,
                                              String replacement) {
        int randomIndex = rand.nextInt(patterns.length);
        // Replace the placeholder with the replacement in the selected pattern
        String responsePattern = patterns[randomIndex];
        return responsePattern.replaceAll(placeholder, replacement);
    }
}
