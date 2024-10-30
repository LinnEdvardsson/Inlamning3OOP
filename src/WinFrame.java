import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WinFrame {
    private GameFrameTest gameFrameTest;

    JFrame wF = new JFrame();
    JPanel winPanel = new JPanel();
    JPanel soutPanel = new JPanel();
    JLabel label = new JLabel();
    ImageIcon img = new ImageIcon("src/congrats.jpg");
    JButton restartButton = new JButton("Play again");
    JButton exit = new JButton("Exit");
    JButton getRestartButton;

    public WinFrame(){

        wF.add(winPanel);
        winPanel.setLayout(new BorderLayout());
        winPanel.add(soutPanel, BorderLayout.SOUTH);
        winPanel.add(label, BorderLayout.CENTER);
        label.setIcon(img);
        soutPanel.add(restartButton, BorderLayout.CENTER);
        restartButton.addActionListener(e -> {
            getRestartButton.doClick();
            wF.dispose();});
        soutPanel.add(exit, BorderLayout.CENTER);
        exit.addActionListener(a -> {wF.dispose();});


        wF.pack();
        wF.setVisible(true);
        wF.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        wF.setLocationRelativeTo(null);
    }


    /*public WinFrame(GameFrame gameFrame, JButton startButton) {
        //wF.setUndecorated(true);
        this.gameFrame = gameFrame;
        this.getRestartButton = startButton;
        wF.add(winPanel);
        winPanel.setLayout(new BorderLayout());
        winPanel.add(soutPanel, BorderLayout.SOUTH);
        winPanel.add(label, BorderLayout.CENTER);
        label.setIcon(img);
        soutPanel.add(restartButton, BorderLayout.CENTER);
        restartButton.addActionListener(e -> {getRestartButton.doClick();
            wF.dispose();});
        soutPanel.add(exit, BorderLayout.CENTER);
        exit.addActionListener(a -> {wF.dispose();});


        wF.pack();
        wF.setVisible(true);
        wF.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        wF.setLocationRelativeTo(null);
    }*/


}
