import javax.swing.*;
import java.awt.*;

public class WinFrame extends JFrame{

    /*JFrame wF = new JFrame();*/
    JPanel winPanel = new JPanel();
    JPanel soutPanel = new JPanel();
    JLabel label = new JLabel();
    ImageIcon img = new ImageIcon("src/congrats.jpg");
    JButton restartButton = new RoundedButton();
    JButton exit = new RoundedButton();


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
        custumButton();
    }

    public void custumButton(){
        exit.setText("Exit");
        restartButton.setText("Restart");
        exit.setBackground(new Color(250, 95, 94));
        exit.setForeground(new Color(255, 255, 255));
        restartButton.setForeground(new Color(255, 255, 255));
        restartButton.setBackground(new Color(250, 95, 94));
    }

    public void setUpWinFrame(){
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

}
