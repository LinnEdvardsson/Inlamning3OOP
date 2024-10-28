import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.*;
import java.util.List;
import java.util.concurrent.Flow;

public class GameFrame extends JFrame implements ActionListener {

    JPanel GamePanel = new JPanel();
    JPanel notGamePanel = new JPanel();
    JButton startButton = new JButton("New game");

    public GameFrame(){
        this.setLayout(new BorderLayout());

        this.add(GamePanel, BorderLayout.CENTER);
        this.add(notGamePanel, BorderLayout.AFTER_LAST_LINE);
        GamePanel.setLayout(new GridLayout(4,4));
        notGamePanel.setLayout(new FlowLayout());

        List<JButton> gameButtons = designedButtons();
        addButtonsToPanel(gameButtons);

        notGamePanel.add(startButton, BorderLayout.SOUTH);
        startButton.setSize(10,15);
        startButton.addActionListener(this);;

        startButton.addActionListener(l -> {showAndShuffle(gameButtons);});

        setSize(400,400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addButtonsToPanel(List<JButton> buttonList){
        GamePanel.removeAll();
        Collections.shuffle(buttonList);
        for(JButton button:buttonList){
            button.setVisible(false);
            GamePanel.add(button);
        }
        GamePanel.revalidate();
        GamePanel.repaint();
    }

    public void showAndShuffle(List<JButton> buttonList){
        Collections.shuffle(buttonList);
        for(JButton button:buttonList){
            button.setVisible(true);
        }
    }

    public List<JButton> designedButtons(){
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            showAndShuffle(designedButtons());
        }
    }
}
