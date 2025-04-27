package pendu.ui.swing;

import javax.swing.*;
import java.awt.*;

public class HangmanPanel extends JPanel {
    private int errors;

    public HangmanPanel() {
        this.errors = 0;
        setPreferredSize(new Dimension(200, 200));
    }

    public void setErrors(int errors) {
        this.errors = Math.min(Math.max(0, errors), 7);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Potence
        g.drawLine(50, 180, 150, 180); // Base
        g.drawLine(100, 180, 100, 30);  // Poteau
        g.drawLine(100, 30, 150, 30);   // Traverse
        g.drawLine(150, 30, 150, 50);   // Corde

        // Pendu
        if (errors >= 1) g.drawOval(140, 50, 20, 20); // Tête
        if (errors >= 2) g.drawLine(150, 70, 150, 110); // Corps
        if (errors >= 3) g.drawLine(150, 80, 130, 70); // Bras gauche
        if (errors >= 4) g.drawLine(150, 80, 170, 70); // Bras droit
        if (errors >= 5) g.drawLine(150, 110, 130, 130); // Jambe gauche
        if (errors >= 6) g.drawLine(150, 110, 170, 130); // Jambe droite
        if (errors >= 7) { // Visage triste
            g.drawArc(145, 55, 10, 10, 200, 140);
            g.fillOval(143, 58, 3, 3);
            g.fillOval(153, 58, 3, 3);
        }
    }
}