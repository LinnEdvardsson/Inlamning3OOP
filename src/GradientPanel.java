import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Define your gradient color
        /*Color color1 = new Color(241, 84, 108);  // Vibrant coral-pink
        Color color2 = new Color(255, 132, 132, 255); // Slightly lighter coral-pink*/

        /*Color color1 = new Color(255, 235, 235);  // Very light pink
        Color color2 = new Color(255, 220, 220);

        Color color1 = new Color(255, 210, 210);  // Medium light pink
        Color color2 = new Color(255, 190, 190);*/

        /*Color color1 = new Color(255, 180, 200);  // Pinker shade with purple undertone
        Color color2 = new Color(250, 160, 190);  // More purple-pink

        Color color1 = new Color(255, 170, 210);  // Pink-purple
        Color color2 = new Color(240, 150, 200);  // More pronounced purple-pink*/

        Color color1 = new Color(243, 91, 91, 255);  // Soft coral
        Color color2 = new Color(173, 235, 250);  // Soft turquoise

        // Create the gradient
        GradientPaint gradient = new GradientPaint(0, 0, color1, width, height, color2);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
    }

}
