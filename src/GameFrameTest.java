import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameFrameTest extends JFrame {

    JPanel GamePanel = new JPanel();
    JPanel notGamePanel = new JPanel();
    JButton startButton = new JButton("New game");
    List<JButton> buttonList = new ArrayList<>();

    public GameFrameTest(){
        this.setLayout(new BorderLayout());

        this.add(GamePanel, BorderLayout.CENTER);
        this.add(notGamePanel, BorderLayout.AFTER_LAST_LINE);
        GamePanel.setLayout(new GridLayout(4,4, 5,5));
        GamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        notGamePanel.setLayout(new FlowLayout());
        notGamePanel.add(startButton, BorderLayout.SOUTH);
        startButton.setSize(10,15);

        initializeButtons();
        startButton.addActionListener(l -> { startGame(); });

        setUpFrame();
    }

    public void startGame(){
        CurrentGame newGame = new CurrentGame(GamePanel, buttonList);
    }

    public Color buttonColor(){
        Color buttonColor = Color.decode("#FC9B6D");
        return buttonColor;
    }
    public Color buttonPressedColor(){
        Color buttonPressedColor = Color.decode("#FFBC9B");
        return buttonPressedColor;
    }

    public void initializeButtons(){
        for(int i = 0; i <= 16; i++){
            JButton button = new RoundedButton();
            button.setBackground(buttonColor());
            button.setFont(new Font("Courier New", Font.BOLD, 20));
            button.setForeground(new Color(255, 255, 255));
            button.setPreferredSize(new Dimension(80, 80));

            button.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            button.setBackground(buttonPressedColor());
                                        }
                                        @Override
                                        public void mouseReleased(MouseEvent e) {
                                            button.setBackground(buttonColor());
                                        }});
            buttonList.add(button);
        }
    }

    public void setUpFrame(){
        setSize(400,400);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
