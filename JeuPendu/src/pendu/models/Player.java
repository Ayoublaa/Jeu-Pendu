package pendu.models;

import pendu.interfaces.PlayerInterface;

public class Player implements PlayerInterface {
    private final String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    @Override public String getName() { return name; }
    @Override public void incrementScore() { score++; }
    @Override public int getScore() { return score; }

    @Override
    public void displayStats() {
        System.out.println("\nStatistiques de " + name + ":");
        System.out.println("Parties gagnées: " + score);
    }
}