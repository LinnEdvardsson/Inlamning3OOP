import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {

    public static JButton startButton = new PuzzleButton("Start game", false);
    private JButton winButton = new PuzzleButton("Quick win", false);
    private JButton exitButton = new PuzzleButton("Exit", false);

    private JLabel movesLabel = new JLabel("MOVES:");
    private JLabel movesDisplay = new JLabel("0");
    private List<JButton> buttonList = new ArrayList<>();

    public GameFrame(){

        GradientPanel mainPanel = getMainPanel();
        JPanel counterPanel = getCounterPanel();
        JPanel optionPanel = getOptionPanel();
        JPanel gamePanel = getGamePanel();

        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(optionPanel, BorderLayout.SOUTH);
        mainPanel.add(counterPanel, BorderLayout.NORTH);

        designMovesDisplay();
        initializeGameButtons();

        startButton.addActionListener(l -> { GameManager game = new GameManager(gamePanel, buttonList, movesDisplay, false); });
        winButton.addActionListener(l -> { GameManager fastGame = new GameManager(gamePanel, buttonList, movesDisplay, true);});
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
        counterPanel.add(movesLabel);
        counterPanel.add(movesDisplay);
        return counterPanel;
    }

    public void designMovesDisplay(){
        movesLabel.setFont(new Font("Courier New", Font.BOLD, 16));
        movesLabel.setForeground(Color.WHITE);
        movesDisplay.setFont(new Font("Courier New", Font.BOLD, 16));
        movesDisplay.setForeground(Color.WHITE);
    }

    public void initializeGameButtons(){
        for(int i = 0; i <= 16; i++){
            JButton button = new PuzzleButton("", true);
            buttonList.add(button);
        }
    }

    public void setUpFrame(){
        setSize(500,500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
