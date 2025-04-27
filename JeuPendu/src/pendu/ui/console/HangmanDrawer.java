package pendu.ui.console;

public class HangmanDrawer {
    private static final String[] STATES = {

            """
          +---+
              |
              |
              |
              |
              |
        =========
        """,

            """
          +---+
          |   |
              |
              |
              |
              |
        =========
        """,

            """
          +---+
          |   |
          O   |
              |
              |
              |
        =========
        """,

            """
          +---+
          |   |
          O   |
          |   |
              |
              |
        =========
        """,

            """
          +---+
          |   |
          O   |
         /|   |
              |
              |
        =========
        """,

            """
          +---+
          |   |
          O   |
         /|\\  |
              |
              |
        =========
        """,

            """
          +---+
          |   |
          O   |
         /|\\  |
         /    |
              |
        =========
        """,

            """
          +---+
          |   |
          O   |
         /|\\  |
         / \\  |
              |
        =========
        """
    };

    public static void draw(int errorsMade) {
        if (errorsMade < 0 || errorsMade >= STATES.length) return;
        System.out.println(STATES[errorsMade]);
    }
}