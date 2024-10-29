import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WinFrame {
    private GameFrame gameFrame;

    JFrame wF = new JFrame();
    JPanel winPanel = new JPanel();
    JPanel soutPanel = new JPanel();
    JLabel label = new JLabel();
    ImageIcon img = new ImageIcon("C:/Users/linnh/OneDrive/Bilder/randomBilder/congrats.jpg");
    JButton restartButton = new JButton("Play again");
    JButton exit = new JButton("Exit");

    public WinFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;

    }

    public WinFrame() {

        wF.add(winPanel);
        winPanel.setLayout(new BorderLayout());
        winPanel.add(soutPanel, BorderLayout.SOUTH);
        winPanel.add(label, BorderLayout.CENTER);
        label.setIcon(img);
        soutPanel.add(restartButton, BorderLayout.CENTER);
        restartButton.addActionListener(e -> {
            if (gameFrame.getStartButton()!= null) { //null???
                gameFrame.getStartButton().doClick();
            }
        });
        soutPanel.add(exit, BorderLayout.CENTER);
        exit.addActionListener(a -> {wF.dispose();});

        wF.pack();
        wF.setForeground(Color.PINK);
        wF.setVisible(true);
        wF.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        wF.setLocationRelativeTo(null);
    }


}
