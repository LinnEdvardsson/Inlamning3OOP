import javax.swing.*;
import java.awt.*;

public class WinFrame extends JFrame{

    JPanel winPanel = new JPanel();
    JPanel optionPanel = new JPanel();
    JLabel label = new JLabel();
    ImageIcon img = new ImageIcon("src/congrats.jpg");
    JButton restartButton = new PuzzleButton("Play again", false);
    JButton exit = new PuzzleButton("Exit", false);


    public WinFrame() {

        this.add(winPanel);
        winPanel.setLayout(new BorderLayout());
        winPanel.add(optionPanel, BorderLayout.SOUTH);
        winPanel.add(label, BorderLayout.CENTER);
        label.setIcon(img);
        optionPanel.add(restartButton, BorderLayout.CENTER);

        restartButton.addActionListener(e -> { GameFrame.startButton.doClick(); this.dispose(); });

        optionPanel.add(exit, BorderLayout.CENTER);
        exit.addActionListener(a -> {this.dispose();});

        setUpWinFrame();
    }

    public void setUpWinFrame(){
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

}
