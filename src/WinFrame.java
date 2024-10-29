import javax.swing.*;
import java.awt.*;

public class WinFrame extends JFrame {
    private GameFrame gameFrame;

    JPanel winPanel = new JPanel();
    JLabel label = new JLabel();
    ImageIcon img = new ImageIcon("C:/Users/linnh/OneDrive/Bilder/randomBilder/congrats.jpg");
    JButton restartButton = new JButton("Play again");

    public WinFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;

    }

    public WinFrame() {

        this.add(winPanel);
        winPanel.setLayout(new BorderLayout());
        winPanel.add(label, BorderLayout.CENTER);
        label.setIcon(img);
        winPanel.add(restartButton, BorderLayout.AFTER_LAST_LINE);
        restartButton.addActionListener(e -> {
            if (gameFrame.getStartButton()!= null) {
                gameFrame.getStartButton().doClick();
            }
        });

        pack();
        setForeground(Color.PINK);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


}
