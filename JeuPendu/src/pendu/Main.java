package pendu;

import pendu.interfaces.GameInterface;
import pendu.interfaces.PlayerInterface;
import pendu.models.Game;
import pendu.models.Player;
import pendu.ui.console.ConsoleUI;
import pendu.ui.swing.SwingUI;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        String[] options = {"Console", "Graphique"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choisissez le mode d'interface",
                "Jeu du Pendu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        PlayerInterface player1 = new Player("Ayoub");
        PlayerInterface player2 = new Player("Kaoutar");

        PlayerInterface currentPlayer = player1;

        GameInterface ui = (choice == 0) ? new ConsoleUI() : new SwingUI();

        Game game = new Game(currentPlayer, ui);
        game.start();
    }
}
