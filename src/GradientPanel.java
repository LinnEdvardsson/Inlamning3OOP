import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        Color color1 = new Color(243, 91, 91, 255);  // Soft coral
        Color color3 = new Color(255, 123, 123, 255);  // Soft coral
        Color color2 = new Color(173, 235, 250);  // Soft turquoise

        // Create the gradient
        GradientPaint gradient = new GradientPaint(0, 0, color1, width, height, color2);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
    }

}
