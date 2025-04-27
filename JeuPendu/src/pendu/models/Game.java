package pendu.models;

import pendu.interfaces.GameInterface;
import pendu.interfaces.PlayerInterface;
import java.util.HashSet;
import java.util.Set;

public class Game {
    private final PlayerInterface player;
    private final GameInterface ui;
    private final Dictionary dictionary;
    private String wordToGuess;
    private Set<Character> guessedLetters;
    private int attemptsLeft;
    private boolean gameWon;

    public Game(PlayerInterface player, GameInterface ui) {
        this.player = player;
        this.ui = ui;
        this.dictionary = new Dictionary();
    }

    public void start() {
        initializeGame();
        gameLoop();
        endGame();
        askToPlayAgain();
    }

    private void initializeGame() {
        wordToGuess = dictionary.getRandomWord();
        guessedLetters = new HashSet<>();
        attemptsLeft = 7;
        gameWon = false;
        ui.displayWelcome();
    }

    private void gameLoop() {
        while (attemptsLeft > 0 && !gameWon) {
            displayGameState();
            processGuess(ui.getPlayerInput());
            checkGameStatus();
        }
    }

    private void displayGameState() {
        int errorsMade = 7 - attemptsLeft;
        ui.displayGameState(
                getHiddenWord(),
                attemptsLeft,
                getGuessedLettersString(),
                errorsMade
        );
    }

    private String getHiddenWord() {
        StringBuilder sb = new StringBuilder();
        for (char c : wordToGuess.toCharArray()) {
            sb.append(guessedLetters.contains(c) ? c : "_");
        }
        return sb.toString();
    }

    private String getGuessedLettersString() {
        return String.join(" ", guessedLetters.stream()
                .sorted()
                .map(String::valueOf)
                .toArray(String[]::new));
    }

    private void processGuess(String input) {
        if (input == null || input.isEmpty()) {
            ui.showMessage("Veuillez entrer une lettre ou un mot");
            return;
        }

        input = input.toUpperCase();

        if (input.length() == 1) {
            processLetter(input.charAt(0));
        } else {
            processWord(input);
        }
    }

    private void processLetter(char letter) {
        if (!Character.isLetter(letter)) {
            ui.showMessage("Entrez une lettre valide (A-Z)");
            return;
        }

        if (guessedLetters.contains(letter)) {
            ui.showMessage("Vous avez déjà essayé cette lettre!");
            return;
        }

        guessedLetters.add(letter);

        if (wordToGuess.indexOf(letter) < 0) {
            attemptsLeft--;
            ui.showMessage("Lettre incorrecte! Tentatives restantes: " + attemptsLeft);
        } else {
            ui.showMessage("Bonne lettre!");
        }
    }

    private void processWord(String word) {
        if (word.equals(wordToGuess)) {
            for (char c : wordToGuess.toCharArray()) {
                guessedLetters.add(c);
            }
            gameWon = true;
        } else {
            attemptsLeft = 0;
            ui.showMessage("Ce n'est pas le bon mot!");
        }
    }

    private void checkGameStatus() {
        gameWon = wordToGuess.chars()
                .allMatch(c -> guessedLetters.contains((char)c));
    }

    private void endGame() {
        if (gameWon) {
            player.incrementScore();
            ui.displayWin(wordToGuess);
        } else {
            ui.displayLoss(wordToGuess);
        }
        player.displayStats();
    }

    private void askToPlayAgain() {
        if (ui.askToPlayAgain()) {
            initializeGame();
            gameLoop();
            endGame();
            askToPlayAgain();
        } else {
            ui.showMessage("Merci d'avoir joué, " + player.getName() + "!");
        }
    }
}