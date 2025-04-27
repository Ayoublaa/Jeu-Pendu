package pendu.ui.swing;

import pendu.interfaces.GameInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SwingUI implements GameInterface {
    private JFrame frame;
    private JLabel wordLabel;
    private JLabel attemptsLabel;
    private JLabel guessedLettersLabel;
    private JTextField inputField;
    private JButton submitButton;
    private JTextArea messageArea;
    private HangmanPanel hangmanPanel;

    private String playerInput;
    private boolean inputReceived;

    public SwingUI() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Jeu du Pendu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());


        hangmanPanel = new HangmanPanel();
        frame.add(hangmanPanel, BorderLayout.NORTH);


        JPanel centerPanel = new JPanel(new GridLayout(4, 1));
        wordLabel = new JLabel("", SwingConstants.CENTER);
        attemptsLabel = new JLabel("", SwingConstants.CENTER);
        guessedLettersLabel = new JLabel("", SwingConstants.CENTER);

        centerPanel.add(wordLabel);
        centerPanel.add(attemptsLabel);
        centerPanel.add(guessedLettersLabel);

        messageArea = new JTextArea(3, 20);
        messageArea.setEditable(false);
        centerPanel.add(new JScrollPane(messageArea));

        frame.add(centerPanel, BorderLayout.CENTER);


        JPanel southPanel = new JPanel();
        inputField = new JTextField(15);
        submitButton = new JButton("Valider");

        submitButton.addActionListener(this::onSubmit);
        inputField.addActionListener(this::onSubmit);

        southPanel.add(inputField);
        southPanel.add(submitButton);
        frame.add(southPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void onSubmit(ActionEvent e) {
        playerInput = inputField.getText();
        inputField.setText("");
        inputReceived = true;
    }

    @Override
    public void displayWelcome() {
        showMessage("Bienvenue au Jeu du Pendu!\nDevinez le mot caché.");
    }

    @Override
    public void displayGameState(String hiddenWord, int attemptsLeft, String usedLetters, int errorsMade) {
        wordLabel.setText("Mot: " + hiddenWord.replace("", " "));
        attemptsLabel.setText("Tentatives restantes: " + attemptsLeft);
        guessedLettersLabel.setText("Lettres essayées: " + usedLetters);
        hangmanPanel.setErrors(errorsMade);
        frame.repaint();
    }

    @Override
    public String getPlayerInput() {
        inputReceived = false;
        playerInput = null;

        while (!inputReceived) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return playerInput;
    }

    @Override
    public void showMessage(String message) {
        messageArea.append(message + "\n");
    }

    @Override
    public void displayWin(String word) {
        showMessage("\nGagné! Le mot était: " + word);
    }

    @Override
    public void displayLoss(String word) {
        showMessage("\nPerdu! Le mot était: " + word);
        hangmanPanel.setErrors(7);
    }

    @Override
    public boolean askToPlayAgain() {
        int choice = JOptionPane.showConfirmDialog(
                frame,
                "Voulez-vous rejouer?",
                "Partie terminée",
                JOptionPane.YES_NO_OPTION
        );
        return choice == JOptionPane.YES_OPTION;
    }
}