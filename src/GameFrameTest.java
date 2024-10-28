import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameFrameTest extends JFrame {

    JPanel GamePanel = new JPanel();
    JPanel notGamePanel = new JPanel();
    JButton startButton = new JButton("New game");

    public GameFrameTest(){
        this.setLayout(new BorderLayout());

        this.add(GamePanel, BorderLayout.CENTER);
        this.add(notGamePanel, BorderLayout.AFTER_LAST_LINE);
        GamePanel.setLayout(new GridLayout(4,4));
        notGamePanel.setLayout(new FlowLayout());
        notGamePanel.add(startButton, BorderLayout.SOUTH);
        startButton.setSize(10,15);

        startButton.addActionListener(l -> { startGame(); });


        setSize(400,400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void startGame(){
        CurrentGame game = new CurrentGame(GamePanel);
    }


    public java.util.List<JButton> designedButtons(){
        List<JButton> buttons = new ArrayList<>();
        for(int i = 1; i<=16; i++){
            JButton button = new JButton();
            button.setText(String.valueOf(i));
            button.setBounds(10,10,20,20);
            button.setBackground(Color.WHITE);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            if (i==16){
                button.setText("");
            }
            buttons.add(button);
        }
        return buttons;
    }

}
