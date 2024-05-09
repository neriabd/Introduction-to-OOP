import java.util.Scanner;

public class Chat {

    static final int NUMBER_OF_BOTS = 2;
    static final String INITIAL_STATEMENT = "say surprise!";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatterBot[] bots = new ChatterBot[NUMBER_OF_BOTS];

        // Create two bots
        bots[0] = new ChatterBot("Aang",
                new String[]{"say, " + ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST,
                        "stay wild and say " + ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST},
                new String[]{"think outside the " + ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE + " box",
                        "say it's not " + ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE});

        bots[1] = new ChatterBot("Korra",
                new String[]{"imagine " + ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST,
                        "say " + ChatterBot.PLACEHOLDER_FOR_ILLEGAL_REQUEST},
                new String[]{"say life's full of " + ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE,
                        "let's explore deeper, how about " +
                                ChatterBot.PLACEHOLDER_FOR_REQUESTED_PHRASE + "?"});

        String statement = INITIAL_STATEMENT;

        // Start the conversation between the two bots
        for (int i = 0; ; i++) {
            statement = bots[i % NUMBER_OF_BOTS].replyTo(statement);
            System.out.println(bots[i % NUMBER_OF_BOTS].getName() + ": " + statement);
            scanner.nextLine();
        }
    }
}
