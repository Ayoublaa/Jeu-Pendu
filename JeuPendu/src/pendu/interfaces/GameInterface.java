package pendu.interfaces;

public interface GameInterface {
    void displayWelcome();
    void displayGameState(String hiddenWord, int attemptsLeft, String usedLetters, int errorsMade);
    String getPlayerInput();
    void showMessage(String message);
    void displayWin(String word);
    void displayLoss(String word);
    boolean askToPlayAgain();
}