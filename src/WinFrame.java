import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WinFrame extends JFrame{

    /*JFrame wF = new JFrame();*/
    JPanel winPanel = new JPanel();
    JPanel soutPanel = new JPanel();
    JLabel label = new JLabel();
    ImageIcon img = new ImageIcon("src/congrats.jpg");
    JButton restartButton = new JButton("Play again");
    JButton exit = new JButton("Exit");


    public WinFrame() {

        this.add(winPanel);
        winPanel.setLayout(new BorderLayout());
        winPanel.add(soutPanel, BorderLayout.SOUTH);
        winPanel.add(label, BorderLayout.CENTER);
        label.setIcon(img);
        soutPanel.add(restartButton, BorderLayout.CENTER);

        restartButton.addActionListener(e -> { GameFrameTest.startButton.doClick(); this.dispose(); });

        soutPanel.add(exit, BorderLayout.CENTER);
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
