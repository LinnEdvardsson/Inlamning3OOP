import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {

    public RoundedButton() {
        setContentAreaFilled(false); // Disable default button appearance
        setFocusPainted(false); // Disable focus painting
        setBorderPainted(false); // Disable border painting
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Create rounded rectangle shape
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Adjust arc width and height for roundness

        // Draw button text
        super.paintComponent(g);
    }
}
