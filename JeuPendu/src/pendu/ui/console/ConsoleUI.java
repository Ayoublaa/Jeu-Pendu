package pendu.ui.console;

import pendu.interfaces.GameInterface;
import java.util.Scanner;

public class ConsoleUI implements GameInterface {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayWelcome() {
        System.out.println("=== JEU DU PENDU ===");
        System.out.println("Devinez le mot en proposant des lettres");
        System.out.println("Vous avez 7 tentatives avant d'être pendu!");
    }

    @Override
    public void displayGameState(String hiddenWord, int attemptsLeft, String usedLetters, int errorsMade) {
        System.out.println("\nMot: " + hiddenWord.replace("", " ").trim());
        System.out.println("Tentatives restantes: " + attemptsLeft);
        System.out.println("Lettres essayées: " + usedLetters);
        System.out.println("Erreurs: " + errorsMade + "/7");
    }

    @Override
    public String getPlayerInput() {
        System.out.print("\nEntrez une lettre ou un mot: ");
        return scanner.nextLine();
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayWin(String word) {
        System.out.println("\nFélicitations! Vous avez trouvé: " + word);
    }

    @Override
    public void displayLoss(String word) {
        System.out.println("\nDommage! Le mot était: " + word);
    }

    @Override
    public boolean askToPlayAgain() {
        System.out.print("\nVoulez-vous rejouer? (o/n) ");
        return scanner.nextLine().equalsIgnoreCase("o");
    }
}