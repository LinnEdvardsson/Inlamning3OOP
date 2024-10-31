import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameFrameTest extends JFrame {

    /*JPanel gamePanel = new JPanel();*/
    /*JPanel optionPanel = new JPanel();*/
    /*JPanel counterPanel = new JPanel();*/

    public static JButton startButton = new RoundedButton();
    JButton winButton = new RoundedButton();
    JButton exitButton = new RoundedButton();

    JLabel counterLabel = new JLabel("MOVES:");
    JLabel movesLabel = new JLabel("0");
    List<JButton> buttonList = new ArrayList<>();

    public GameFrameTest(){

        GradientPanel mainPanel = getMainPanel();
        JPanel counterPanel = getCounterPanel();
        JPanel optionPanel = getOptionPanel();
        JPanel gamePanel = getGamePanel();

        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(optionPanel, BorderLayout.SOUTH);
        mainPanel.add(counterPanel, BorderLayout.NORTH);

        customizeOptionButtons();

        initializeGameButtons();

        startButton.addActionListener(l -> { CurrentGame game = new CurrentGame(gamePanel, buttonList, movesLabel, false); });
        winButton.addActionListener(l -> { CurrentGame fastGame = new CurrentGame(gamePanel, buttonList, movesLabel, true);});
        exitButton.addActionListener(l -> {System.exit(0);});

        setUpFrame();
    }

    public JPanel getGamePanel(){
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(4,4, 5,5));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gamePanel.setOpaque(false);
        return gamePanel;
    }

    public GradientPanel getMainPanel(){
        GradientPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new BorderLayout());
        this.setContentPane(mainPanel);
        return mainPanel;
    }

    public JPanel getOptionPanel(){
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new FlowLayout());
        optionPanel.add(startButton, BorderLayout.SOUTH);
        optionPanel.add(winButton, BorderLayout.SOUTH);
        optionPanel.add(exitButton, BorderLayout.SOUTH);
        optionPanel.setOpaque(false);
        return optionPanel;
    }
    
    public JPanel getCounterPanel(){
        JPanel counterPanel = new JPanel();
        counterPanel.setLayout(new FlowLayout());
        counterPanel.setOpaque(false);
        counterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        counterPanel.add(counterLabel);
        counterPanel.add(movesLabel);
        counterLabel.setFont(new Font("Courier New", Font.BOLD, 16));
        counterLabel.setForeground(Color.WHITE);
        movesLabel.setFont(new Font("Couri", Font.BOLD, 16));
        movesLabel.setForeground(Color.WHITE);
        return counterPanel;
    }
    
    public void customizeOptionButtons(){
        startButton.setText("Start game");
        exitButton.setText("Exit");
        winButton.setText("Quick win");

        startButton.setSize(10,15);
        startButton.setBackground(buttonColor());
        startButton.setForeground(new Color(255, 255, 255));
        exitButton.setBackground(buttonColor());
        exitButton.setForeground(new Color(255, 255, 255));
        winButton.setBackground(buttonColor());
        winButton.setForeground(new Color(255,255,255));

        assignMouseListener(startButton);
        assignMouseListener(exitButton);
        assignMouseListener(winButton);
    }

    public Color buttonColor() {
        return new Color(250, 95, 94); // Coral with a pink tint
    }

    public Color buttonPressedColor() {
        return new Color(255, 150, 147); // Darker pinkish coral
    }

    public Color buttonHoverColor() {
        Color color1 = buttonColor();
        Color color2 = buttonPressedColor();

        int red = (color1.getRed() + color2.getRed()) / 2;
        int green = (color1.getGreen() + color2.getGreen()) / 2;
        int blue = (color1.getBlue() + color2.getBlue()) / 2;

        return new Color(red, green, blue); // Mid-tone pink coral
    }

    public void initializeGameButtons(){
        for(int i = 0; i <= 16; i++){
            JButton button = new RoundedButton();
            button.setBackground(buttonColor());
            button.setFont(new Font("Courier New", Font.BOLD, 20));
            button.setForeground(new Color(255, 255, 255));
            button.setPreferredSize(new Dimension(80, 80));
            assignMouseListener(button);
            buttonList.add(button);
        }
    }

    /*
    public void assignGameButtonListener(JButton button, boolean isGameButton) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(isGameButton ? new Color(38, 70, 83) : nonGameButtonPressedColor());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(isGameButton ? new Color(0, 190, 228) : new Color(0, 127, 153));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(isGameButton ? gameButtonHoverColor() : nonGameButtonHoverColor());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(isGameButton ? new Color(0, 190, 228) : new Color(0, 127, 153));
            }
        });
    }*/

    public void assignMouseListener(JButton button){
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {button.setBackground(buttonPressedColor());}
            @Override
            public void mouseReleased(MouseEvent e) {button.setBackground(buttonColor());}
            @Override
            public void mouseEntered(MouseEvent e) {button.setBackground(buttonHoverColor()); }
            @Override
            public void mouseExited(MouseEvent e) {button.setBackground(buttonColor()); }
        });
    }

    public void setUpFrame(){
        setSize(500,500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
