import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameFrame extends JFrame {


    //FÖRSTÖRDE DENNA KLASS SÅ DETTA FUNKAR EJ LÄNGRE.........
    JPanel gamePanel = new JPanel();
    JPanel notGamePanel = new JPanel();
    JButton startButton = new JButton("New game");
    List<JButton> gameButtons = designedButtons();

    public GameFrame(){
        this.setLayout(new BorderLayout());

        this.add(gamePanel, BorderLayout.CENTER);
        this.add(notGamePanel, BorderLayout.AFTER_LAST_LINE);
        gamePanel.setLayout(new GridLayout(4,4));

        notGamePanel.setLayout(new FlowLayout());
        notGamePanel.add(startButton, BorderLayout.SOUTH);
        startButton.setSize(10,15);

        addButtonsToPanel(gameButtons);
        startButton.addActionListener(l -> {startGame();});;

        setSize(400,400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void startGame(){
        Game newGame = new Game(gamePanel, gameButtons);
    }

    public void addButtonsToPanel(List<JButton> buttons){
        gamePanel.removeAll();
        Collections.shuffle(buttons);
        for(JButton button:buttons){
            button.setVisible(false);
            gamePanel.add(button);
        }
        gamePanel.revalidate();
        gamePanel.repaint();
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


}
